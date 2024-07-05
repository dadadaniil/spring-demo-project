package com.example.springdemoproject.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Book}
 */

public record BookDto(Long id,
                      @NotNull(message = "Book name can not be null") @Size(max = 255) @Length(message = "Book name should be between 2 and 200 characters", min = 2, max = 200) String name,
                      @NotNull(message = "Author name can not be empty") @Size(max = 255) @Length(message = "Author name should be between 5 and 200 characters", min = 5, max = 200) String author,
                      @NotNull @Range(message = "Amount of pages should be between 1 and 30000 pages", min = 1, max = 30000) Long sizePages,
                      @NotNull @PastOrPresent(message = "Publishing date should be in past or present") LocalDate publishingDate) implements Serializable {
}
