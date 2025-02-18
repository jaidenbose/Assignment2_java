import org.example.Blog;
import org.example.BlogPost;
import org.example.Person;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BlogPostTest {
    @Test
    void testGetPostsByAuthorAge() {
        // Creating sample data
        Person person1 = Person.builder().withId("1").withFirstName("Justin").withLastName("Trudeau").withAge(30).withGender("Male").build();
        Person person2 = Person.builder().withId("2").withFirstName("Jaiden").withLastName("Smith").withAge(25).withGender("Female").build();
        BlogPost post1 = BlogPost.builder().withId("101").withAuthorId("1").withPostContent("Post by Justin").build();
        BlogPost post2 = BlogPost.builder().withId("102").withAuthorId("2").withPostContent("Post by Jaiden").build();
        BlogPost post3 = BlogPost.builder().withId("103").withAuthorId("1").withPostContent("Another post by Justin").build();

        // Creating a Blog instance
        Blog blog = new Blog(Arrays.asList(post1, post2, post3), Arrays.asList(person1, person2));

        // Test getPostsByAuthorAge
        List<String> result = blog.getPostsByAuthorAge(30);
        assertEquals(Arrays.asList("101", "103"), result, "Should return posts by authors aged 30.");
    }

    @Test
    void testGetPostsByAuthorAge_NoMatches() {
        // Create sample data
        Person person1 = Person.builder().withId("1").withFirstName("Justin").withLastName("Trudeau").withAge(30).withGender("Male").build();
        BlogPost post1 = BlogPost.builder().withId("101").withAuthorId("1").withPostContent("Post by Justin").build();

        // Create a Blog instance
        Blog blog = new Blog(Collections.singletonList(post1), Collections.singletonList(person1));

        // Test getPostsByAuthorAge with no matches
        List<String> result = blog.getPostsByAuthorAge(40);
        assertTrue(result.isEmpty(), "Should return an empty list for no matching authors.");
    }

    @Test
    void testGetPostsByAuthorAge_MissingAuthor() {
        // Create sample data with a missing author
        BlogPost post1 = BlogPost.builder().withId("101").withAuthorId("999").withPostContent("Post by unknown author").build();

        // Create a Blog instance
        Blog blog = new Blog(Collections.singletonList(post1), Collections.emptyList());

        // Test getPostsByAuthorAge with a missing author
        List<String> result = blog.getPostsByAuthorAge(30);
        assertTrue(result.isEmpty(), "Should return an empty list for missing authors.");
    }
}