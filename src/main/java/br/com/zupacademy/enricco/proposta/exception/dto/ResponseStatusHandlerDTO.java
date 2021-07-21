package br.com.zupacademy.enricco.proposta.exception.dto;

import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

public class ResponseStatusHandlerDTO {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private Integer status;

    public ResponseStatusHandlerDTO(ResponseStatusException e) {
        this.message = e.getReason();
        this.status = e.getStatus().value();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }
}
