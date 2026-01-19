package br.healthx.Healthx.session.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        int status,
        String error,
        String message,
        String path,
        Map<String, String> details,
        LocalDateTime timestamp) {
}
