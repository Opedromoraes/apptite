package com.dev.apptite.domain.config;

import lombok.NonNull;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageService implements MessageSource {
    private final MessageSource messageSource;

    public String getMessage(String code) {
        return this.messageSource.getMessage(code, new Object[0], LocaleContextHolder.getLocale());
    }

    public String getMessage(String code, Object... objects) {
        return this.messageSource.getMessage(code, objects, LocaleContextHolder.getLocale());
    }

    public String getMessage(@NonNull String code, Object[] args, String defaultMessage, @NonNull Locale locale) {
        return this.messageSource.getMessage(code, args, defaultMessage, locale);
    }

    public @NonNull String getMessage(@NonNull String code, Object[] args, @NonNull Locale locale) throws NoSuchMessageException {
        return this.messageSource.getMessage(code, args, locale);
    }

    public @NonNull String getMessage(@NonNull MessageSourceResolvable resolvable, @NonNull Locale locale) throws NoSuchMessageException {
        return this.messageSource.getMessage(resolvable, locale);
    }

    public MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
