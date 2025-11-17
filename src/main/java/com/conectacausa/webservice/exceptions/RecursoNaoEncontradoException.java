package com.splitjao.webservice.exceptions;

/**
 * Exceção lançada quando um recurso solicitado não é encontrado.
 */
public class RecursoNaoEncontradoException extends RuntimeException {

    /**
     * Cria uma nova exceção com a mensagem informada.
     *
     * @param mensagem descrição do erro
     */
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}

