package job.stafffinder.controller;

import job.stafffinder.model.ErrorMessageResponse;
import job.stafffinder.model.ErrorMessageResponse.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AbstractController {

    @Autowired
    private MessageSource messgeSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageResponse processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        Locale locale = LocaleContextHolder.getLocale();
        String errorResponseMessage = messgeSource.getMessage("error.invalid.request", null, locale);

        List<FieldError> fieldErrors = result.getFieldErrors();
        List<Error> errors = fieldErrors.stream().map(fieldError-> toError(fieldError, locale)).collect(Collectors.toList());

        return new ErrorMessageResponse(errorResponseMessage, errors);
    }

    private Error toError(FieldError fieldError, Locale locale) {
        String message = messgeSource.getMessage(fieldError.getDefaultMessage(), null, locale);
        return new Error(fieldError.getField(), message);
    }

}
