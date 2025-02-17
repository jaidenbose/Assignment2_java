import org.example.BlogPost;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BlogPostTest {
    @Test
    void testBlogPostConstructorSuccess() {
        // Create a valid BlogPost object
        BlogPost blogPost = BlogPost.builder()
                .withId("201")
                .withAuthorId("101")
                .withPostContent("This is a blog post by Justin.")
                .build();

        // Verify that the object is created successfully and fields are set correctly
        assertNotNull(blogPost, "The BlogPost object should not be null.");
        assertEquals("201", blogPost.getId(), "The ID should match the provided value.");
        assertEquals("101", blogPost.getAuthorId(), "The author ID should match the provided value.");
        assertEquals("This is a blog post by Justin.", blogPost.getPostContent(), "The post content should match the provided value.");
    }

    @Test
    void testBlogPostConstructorExceptions() {
        // Test for null ID
        Exception exception = assertThrows(IllegalArgumentException.class, () -> BlogPost.builder()
                .withId(null)
                .withAuthorId("101")
                .withPostContent("This is a blog post by Justin.")
                .build());
        assertEquals("BlogPost ID is required and cannot be null.", exception.getMessage());

        // Test for null Author ID
        exception = assertThrows(IllegalArgumentException.class, () -> BlogPost.builder()
                .withId("201")
                .withAuthorId(null)
                .withPostContent("This is a blog post by Justin.")
                .build());
        assertEquals("Author ID is required and cannot be null.", exception.getMessage());
    }
}