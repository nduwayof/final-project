package edu.mum.cs.restaurants.utilities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * The type Error details.
 *
 * @author nduwayofabrice
 * @version 1.0
 */
@Getter
@AllArgsConstructor
class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;

}
