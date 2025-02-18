package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType personListType = mapper.getTypeFactory().constructCollectionType(List.class, Person.class);
        CollectionType blogPostListType = mapper.getTypeFactory().constructCollectionType(List.class, BlogPost.class);

        try {
            // Read the file person.json and throw exception if couldn't
            File personFile = new File("person.json");
            if (!personFile.exists()) {
                throw new IOException("person.json file cannot be found.");
            }
            List<Person> persons = mapper.readValue(personFile, personListType);

            // Read the file blogPosts.json and throw exception if couldn't
            File blogPostFile = new File("blogPosts.json");
            if (!blogPostFile.exists()) {
                throw new IOException("blogPosts.json file cannot be found.");
            }
            List<BlogPost> blogPosts = mapper.readValue(blogPostFile, blogPostListType);

            // Validate author IDs in blogPosts and throw exception if couldn't
            for (BlogPost post : blogPosts) {
                boolean authorExists = persons.stream()
                        .anyMatch(person -> person.getId().equals(post.getAuthorId()));
                if (!authorExists) {
                    System.err.println("Warning: Author ID " + post.getAuthorId() + " in blogPosts.json does not exist in person.json.");
                }
            }

            // Creating a Blog instance
            Blog blog = new Blog(blogPosts, persons);

            // Call getPostsByAuthorAge and print the results
            Integer targetAge = 30; // Giving an example age
            List<String> postIds = blog.getPostsByAuthorAge(targetAge);
            System.out.println("BlogPost IDs by authors aged " + targetAge + ": " + postIds);

            // Print the total number of blog posts and contributors
            System.out.println("Total blog posts: " + blogPosts.size());
            System.out.println("Total contributors: " + persons.size());

        } catch (IOException e) {
            System.err.println("Error reading JSON files: " + e.getMessage());
        }
    }
}