package br.com.zupacademy.enricco.proposta.exception;

import br.com.zupacademy.enricco.proposta.exception.dto.ResponseStatusHandlerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ResponseStatusHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResponseStatusHandlerDTO> handle(ResponseStatusException e){
        ResponseStatusHandlerDTO dto = new ResponseStatusHandlerDTO(e);
        return ResponseEntity.status(e.getStatus()).body(dto);
    }
}
