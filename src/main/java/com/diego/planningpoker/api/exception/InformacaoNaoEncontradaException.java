package com.diego.planningpoker.api.exception;

import com.diego.planningpoker.infrastructure.annotation.BusinessException;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@BusinessException(key = "recurso.informacao-nao-encontrada", status = HttpStatus.NOT_FOUND, returnMessageException = true)
public class InformacaoNaoEncontradaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String PROPRIEDADE_PADRAO = "id";
    private static final String SERVICO_NOME_REGEX = "Service.*";
    private static final String STRING_VAZIA = "";
    private static final String MENSAGEM = "Em %s, o(a) %s = %s não existe. ";

    public InformacaoNaoEncontradaException() {
    }

    public InformacaoNaoEncontradaException(String servico, String propriedade, String valor) {
        super(construirMensagem(servico, propriedade, valor));
    }

    public InformacaoNaoEncontradaException(String servico, String valor) {
        super(construirMensagem(servico, PROPRIEDADE_PADRAO, valor));
    }

    private static String construirMensagem(String servico, String propriedade, String valor) {
        String mensagem = STRING_VAZIA;

        if (Objects.nonNull(servico)) {
            String entidade = servico.replaceAll(SERVICO_NOME_REGEX, STRING_VAZIA);
            mensagem = String.format(MENSAGEM, entidade, propriedade, valor);
        }

        return mensagem;
    }

}

