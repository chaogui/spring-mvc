package com.spring.mvc.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class Customer {

    @NotNull(message="is required")
    @Size(min = 1, message = "is required")
    private String firstName;

    @NotNull(message="is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    @NotNull(message="is required")
    @Min(value = 0, message = "must be greater than 0")
    @Max(value = 10, message = "must be less than 0")
    private Integer freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 digits/chars")
    private String postCode;
}
