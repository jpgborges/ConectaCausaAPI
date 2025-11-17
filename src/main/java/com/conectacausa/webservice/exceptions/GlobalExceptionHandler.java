package com.splitjao.webservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Classe responsável por tratar exceções globais da aplicação.
 * Define respostas padronizadas para erros específicos e genéricos.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata a exceção RecursoNaoEncontradoException.
     * Retorna status 404 com informações detalhadas do erro.
     *
     * @param ex exceção lançada
     * @return resposta com detalhes do erro e status 404
     */
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Object> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Recurso não encontrado");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Trata exceções genéricas não previstas.
     * Retorna status 500 com informações detalhadas do erro.
     *
     * @param ex exceção lançada
     * @return resposta com detalhes do erro e status 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Erro interno do servidor");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
