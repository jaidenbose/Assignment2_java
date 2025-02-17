package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@ToString
@Jacksonized
@Builder(builderClassName = "Builder", setterPrefix = "with")
public class BlogPost {
    private final String id;
    private final String authorId;
    private final String postContent;

    // Private constructor to enforce validation rules during object creation
    private BlogPost(String id, String authorId, String postContent) {
        // Ensure the ID is provided and not null
        if (id == null) {
            throw new IllegalArgumentException("BlogPost ID is required and cannot be null.");
        }

        // Ensure the author ID is provided and not null
        if (authorId == null) {
            throw new IllegalArgumentException("Author ID is required and cannot be null.");
        }

        // Assign validated values to the object's fields
        this.id = id;
        this.authorId = authorId;
        this.postContent = postContent;
    }

    // Compare two BlogPost objects based on their ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Same object
        if (o == null || getClass() != o.getClass()) return false; // Different class or null
        BlogPost blogPost = (BlogPost) o; // Cast to BlogPost
        return id.equals(blogPost.id); // Compare IDs
    }
}