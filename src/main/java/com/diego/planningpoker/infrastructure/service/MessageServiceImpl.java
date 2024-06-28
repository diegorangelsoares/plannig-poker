package com.diego.planningpoker.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import javax.validation.Path;

@Service
class MessageServiceImpl implements MessageService {

    @Autowired
    protected MessageSource messageSource;

    @Override
    public String getMessage(String key) {
        String message;
        try {
            message = messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException ex) {
            message = key;
        }
        return message;
    }

    @Override
    public String getMessage(FieldError fieldError) {
        return messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
    }

    @Override
    public String getMessage(Path path) {
        String message;
        try {
            message = messageSource.getMessage(path.toString(), null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            message = path.toString();
        }
        return message;
    }

}
