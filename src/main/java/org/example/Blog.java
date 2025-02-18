package org.example;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Blog {
    private final List<BlogPost> posts;
    private final List<Person> contributors;

    // Constructor to initialize the Blog object with posts and contributors
    public Blog(List<BlogPost> posts, List<Person> contributors) {
        this.posts = posts;
        this.contributors = contributors;
    }

    //Returns a list of BlogPost IDs where the author's age matches the input age.

    public List<String> getPostsByAuthorAge(Integer age) {
        return posts.stream()
                .filter(post -> contributors.stream()
                        .anyMatch(person -> person.getId().equals(post.getAuthorId()) &&
                                age.equals(person.getAge())))
                .map(BlogPost::getId)
                .collect(Collectors.toList());
    }

    // Compare two Blog objects based on their posts and contributors
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Same object
        if (o == null || getClass() != o.getClass()) return false; // Different class or null
        Blog blog = (Blog) o; // Cast to Blog
        return posts.equals(blog.posts) && contributors.equals(blog.contributors); // Compare posts and contributors
    }
}