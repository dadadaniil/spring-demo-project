package com.example.springdemoproject.model;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record UserDto(Long id, String username,
                      @Length(message = "Password should be at least 4 symbols long") String password) implements Serializable {
}