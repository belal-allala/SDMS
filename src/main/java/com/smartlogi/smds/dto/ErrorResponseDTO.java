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
// Commit 120 on 2025-10-28 10:01:06
