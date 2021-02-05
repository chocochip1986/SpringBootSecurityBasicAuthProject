package security.endpoints;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import security.exceptions.InvalidUserCreationException;
import security.exceptions.UserNotFoundException;

@ControllerAdvice
public class ExceptionsController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ApiErrorResponse handleNoUserFound() {
        return ApiErrorResponse.builder().errorCode("WHATEVER").errorMessage("LOL").build();
    }

    @ExceptionHandler(InvalidUserCreationException.class)
    public ApiErrorResponse handleInvalidUserCreation() {
        return ApiErrorResponse.builder().errorCode("BUAY SAI SIAL").errorMessage("roFTL").build();
    }
}
