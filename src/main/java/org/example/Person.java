package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@ToString
@Jacksonized
@Builder(builderClassName = "Builder", setterPrefix = "with")
public class Person {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final Integer age;
    private final String gender;

    // Private constructor to enforce validation rules during object creation
    private Person(String id, String firstName, String lastName, Integer age, String gender) {
        // Ensure the ID is provided and not null
        if (id == null) {
            throw new IllegalArgumentException("Person ID is required and cannot be null.");
        }

        // Ensure the first name is provided and not empty
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name is required and cannot be null or blank.");
        }

        // Ensure the last name is provided and not empty
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name is required and cannot be null or blank.");
        }

        // Ensure the age is not negative (if provided)
        if (age != null && age < 0) {
            throw new IllegalArgumentException("Age cannot be negative. Please provide a valid age.");
        }

        // Assign validated values to the object's fields
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    // Compare two Person objects based on their ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Same object
        if (o == null || getClass() != o.getClass()) return false; // Different class or null
        Person person = (Person) o; // Cast to Person
        return id.equals(person.id); // Compare IDs
    }
}