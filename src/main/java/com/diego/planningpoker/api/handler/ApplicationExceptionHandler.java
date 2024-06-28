package com.diego.planningpoker.api.handler;

import com.diego.planningpoker.infrastructure.annotation.BusinessException;
import com.diego.planningpoker.infrastructure.dto.ErrorResponse;
import com.diego.planningpoker.infrastructure.dto.FieldError;
import com.diego.planningpoker.infrastructure.exception.HttpRuntimeException;
import com.diego.planningpoker.infrastructure.service.MessageService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    protected final MessageService messageService;

    private final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    private final String SEPARADOR = "|";

    public ApplicationExceptionHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        BusinessException businessExAnnotation = AnnotationUtils.findAnnotation(ex.getClass(), BusinessException.class);
        if (Objects.nonNull(businessExAnnotation))
            return handleException(ex, request, businessExAnnotation);

        return handleException(ex, HttpStatus.BAD_REQUEST, request, Constants.RECURSO_OPERACAO_INVALIDA);
    }

    @ExceptionHandler({HttpRuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(HttpRuntimeException ex, WebRequest request) {
        return handleException(ex, ex.getHttpStatus(), request, ex.getMessage());
    }

//    @ExceptionHandler({UsuarioNaoLogadoException.class})
//    public ResponseEntity<Object> handleUserNotLoggedException(UsuarioNaoLogadoException ex, WebRequest request) {
//        return handleException(null, HttpStatus.UNAUTHORIZED, request, Constants.SEGURANCA_PERMISSAO_NEGADA);
//    }

    @ExceptionHandler({TimeoutException.class})
    public ResponseEntity<Object> handleTimeoutException(TimeoutException ex, WebRequest request) {
        return handleException(null, HttpStatus.REQUEST_TIMEOUT, request, Constants.RECURSO_OPERACAO_EXCEDIDA);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return handleException(ex, HttpStatus.UNAUTHORIZED, request, Constants.SEGURANCA_PERMISSAO_NEGADA);
    }

    @ExceptionHandler({CannotCreateTransactionException.class})
    public ResponseEntity<Object> handleCannotCreateTransactionException(CannotCreateTransactionException ex, WebRequest request) {
        return handleException(ex, HttpStatus.BAD_REQUEST, request, Constants.MULTITENANCY_NAO_ENCONTRADO);
    }

//    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = criarRespostaDeErro(status, request, Constants.RECURSO_REQUISICAO_CAMPO_OBRIGATORIO)
                .fieldErrors(Stream.of(new FieldError(null, ex.getVariableName(), "Variável de URI obrigatória.")).toList());
        return this.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

//    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = messageService.getMessage(Constants.RECURSO_REQUISICAO_TIPO_INVALIDO);
        ErrorResponse errorResponse = criarRespostaDeErro(status, request, message)
                .fieldErrors(Stream.of(obterErro(ex)).toList());
        return this.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

//    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = criarRespostaDeErro(status, request, messageService.getMessage(Constants.RECURSO_REQUISICAO_ILEGIVEL))
                .fieldErrors(obterErroNotReadableException(ex));
        return this.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

//    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = criarRespostaDeErro(status, request, messageService.getMessage(Constants.RECURSO_REQUISICAO_CAMPO_OBRIGATORIO))
                .fieldErrors(Stream.of(new FieldError(null, ex.getParameterName(), "Parametro de requisição obrigatório")).toList());
        return this.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    protected ResponseEntity<Object> handleException(Exception ex, HttpStatus status, WebRequest request, String messageCode) {
        ErrorResponse errorResponse = criarRespostaDeErro(status, request, messageCode);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }

    protected ResponseEntity<Object> handleException(Exception ex, WebRequest req, BusinessException businessException) {
        String message = messageService.getMessage(businessException.key());
        String exceptionMessage = Objects.nonNull(ex.getMessage()) ? " " + ex.getMessage() : "";

        if (businessException.returnMessageException())
            message += exceptionMessage;

        ErrorResponse errorResponse = criarRespostaDeErro(businessException.status(), req, message);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), businessException.status(), req);
    }

//    protected ResponseEntity<Object> handleExceptionIntegration(IntegrationException ex, HttpStatus status, WebRequest request) {
//        ErrorResponse errorResponse = criarRespostaDeErro(status, request, ex.getErrorIntegration().getMessage());
//        Map<String, Object> detail = ex.getErrorIntegration().getDetail();
//        if (detail != null && !detail.isEmpty())
//            errorResponse.setDetail(detail);
//        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
//    }

    private List<FieldError> obterListaErros(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(f -> new FieldError(f.getObjectName(), f.getField(), messageService.getMessage(f)))
                .toList();
    }

    private String obterPath(WebRequest request) {
        return request instanceof ServletWebRequest servletWebRequest ? servletWebRequest.getRequest().getRequestURI()
                : request.getContextPath();
    }

    private FieldError obterErro(TypeMismatchException ex) {
        String message = "O tipo do campo deve ser: ";
        if (ex instanceof MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
            if (methodArgumentTypeMismatchException.getCause() instanceof ConversionFailedException cvFailedException
                    && cvFailedException.getLocalizedMessage() != null
                    && cvFailedException.getLocalizedMessage().contains("enum")) {
                return new FieldError(
                        null,
                        methodArgumentTypeMismatchException.getName(),
                        getMensagemEnumQuery(cvFailedException)
                );
            }
            return new FieldError(
                    null,
                    methodArgumentTypeMismatchException.getName(),
                    message + Objects.requireNonNull(ex.getRequiredType()).getSimpleName());

        } else if (ex instanceof MethodArgumentConversionNotSupportedException methodArgumentConversionNotSupportedException) {
            return new FieldError(
                    null,
                    methodArgumentConversionNotSupportedException.getName(),
                    message + Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
        }
        return null;
    }

    private List<FieldError> obterErroNotReadableException(HttpMessageNotReadableException ex) {
        List<FieldError> erros = new ArrayList<>();

        if (ex.getCause() instanceof InvalidFormatException invalidFormatEx && !invalidFormatEx.getPath().isEmpty()) {
            int lastIndexPath = invalidFormatEx.getPath().size() - 1;
            if (invalidFormatEx.getValue().toString().isBlank()) {
                erros.add(new FieldError(
                        null,
                        invalidFormatEx.getPath().get(lastIndexPath).getFieldName(),
                        "campo não pode ser vazio."
                ));
            } else {
                String mensagemErro = getMensagemErroInvalidFormat(invalidFormatEx);
                erros.add(new FieldError(getInvalidFormatExceptionObjectName(invalidFormatEx),
                        invalidFormatEx.getPath().get(lastIndexPath).getFieldName(),
                        mensagemErro
                ));
            }
        }
        return erros;
    }

    private String getInvalidFormatExceptionObjectName(InvalidFormatException ex) {
        if (ex.getPath() != null && ex.getPath().size() > 2) {
            int objectIndex = ex.getPath().size() - 2;
            return ex.getPath().get(objectIndex).getFieldName();
        }
        return null;
    }

    private String getMensagemErroInvalidFormat(InvalidFormatException ex) {
        StringBuilder enumsValidos = new StringBuilder();
        Object[] enums = ex.getTargetType().getEnumConstants(); // Valida Enums
        for (int count = 0; count < (enums != null ? enums.length : 0); count++) {
            enumsValidos.append(SEPARADOR).append(enums[count].toString());
        }
        enumsValidos.append(SEPARADOR);

        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Valor inválido: ").append(ex.getValue());
        if (enums != null) {
            mensagem.append(". Valores aceitos: ").append(enumsValidos);
        }

        return mensagem.toString();
    }

    private String getMensagemEnumQuery(ConversionFailedException ex) {
        StringBuilder enumsValidos = new StringBuilder();
        Object[] enums = ex.getTargetType().getType().getEnumConstants(); // Valida Enums
        if (enums == null)
            return null;

        for (Object anEnum : enums) {
            enumsValidos.append(SEPARADOR).append(anEnum.toString());
        }
        enumsValidos.append(SEPARADOR);

        return "Valor inválido: " + ex.getValue() +
                ". Valores aceitos: " + enumsValidos;
    }

    private ErrorResponse criarRespostaDeErro(HttpStatus status, WebRequest request, String messageCode) {
        return ErrorResponse.of()
                .status(status.value())
                .message(messageService.getMessage(messageCode))
                .path(obterPath(request));
    }

}
