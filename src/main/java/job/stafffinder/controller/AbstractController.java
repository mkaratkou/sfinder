package job.stafffinder.controller;

import job.stafffinder.model.ErrorMessageResponse;
import job.stafffinder.model.ErrorMessageResponse.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public abstract class AbstractController {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageResponse processValidationError(MethodArgumentNotValidException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String errorResponseMessage = messageSource.getMessage("error.invalid.request", null, locale);

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<Error> errors = fieldErrors.stream().map(fieldError-> toError(fieldError, locale)).collect(Collectors.toList());

        return new ErrorMessageResponse(errorResponseMessage, errors);
    }

    private Error toError(FieldError fieldError, Locale locale) {
        String message = messageSource.getMessage(fieldError.getDefaultMessage(), null, locale);
        return new Error(fieldError.getField(), message);
    }

}
