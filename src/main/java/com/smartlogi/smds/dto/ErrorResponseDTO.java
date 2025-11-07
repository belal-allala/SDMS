package com.smartlogi.smds.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {

    private int statusCode;
    private String message;
    private String details;
    private LocalDateTime timestamp;
    private String path;
}

