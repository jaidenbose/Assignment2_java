import org.example.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    void testPersonConstructorSuccess() {
        // Create a valid Person object
        Person person = Person.builder()
                .withId("101")
                .withFirstName("Justin")
                .withLastName("Trudeau")
                .withAge(45)
                .withGender("Male")
                .build();

        // Verify that the object is created successfully and fields are set correctly
        assertNotNull(person, "The Person object should not be null.");
        assertEquals("101", person.getId(), "The ID should match the provided value.");
        assertEquals("Justin", person.getFirstName(), "The first name should match the provided value.");
        assertEquals("Trudeau", person.getLastName(), "The last name should match the provided value.");
        assertEquals(45, person.getAge(), "The age should match the provided value.");
        assertEquals("Male", person.getGender(), "The gender should match the provided value.");
    }

    @Test
    void testPersonConstructorExceptions() {
        // Test for null ID
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Person.builder()
                .withId(null)
                .withFirstName("Justin")
                .withLastName("Trudeau")
                .withAge(45)
                .withGender("Male")
                .build());
        assertEquals("Person ID is required and cannot be null.", exception.getMessage());

        // Test for blank first name
        exception = assertThrows(IllegalArgumentException.class, () -> Person.builder()
                .withId("101")
                .withFirstName("")
                .withLastName("Trudeau")
                .withAge(45)
                .withGender("Male")
                .build());
        assertEquals("First name is required and cannot be null or blank.", exception.getMessage());

        // Test for blank last name
        exception = assertThrows(IllegalArgumentException.class, () -> Person.builder()
                .withId("101")
                .withFirstName("Justin")
                .withLastName("")
                .withAge(45)
                .withGender("Male")
                .build());
        assertEquals("Last name is required and cannot be null or blank.", exception.getMessage());

        // Test for negative age
        exception = assertThrows(IllegalArgumentException.class, () -> Person.builder()
                .withId("101")
                .withFirstName("Justin")
                .withLastName("Trudeau")
                .withAge(-5)
                .withGender("Male")
                .build());
        assertEquals("Age cannot be negative. Please provide a valid age.", exception.getMessage());
    }
}