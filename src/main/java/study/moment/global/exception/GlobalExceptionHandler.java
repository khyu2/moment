package study.moment.global.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.moment.global.exception.dto.ExceptionResponse;
import study.moment.member.exception.MemberException;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ExceptionResponse handleException(Exception exception) {
        return ExceptionResponse.from("INTERNAL_SERVER_ERROR", 500, exception.getMessage());
    }

    @ExceptionHandler(MemberException.class)
    public ExceptionResponse handleMemberException(MemberException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        return getExceptionResponse(errorCode);
    }

//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ExceptionHandler(TokenExpiredException.class)
//    public ErrorResponse handleTokenExpiredException(TokenExpiredException exception) {
//        return ErrorResponse.builder()
//                .name(ErrorCode.EXPIRED_ACCESS_TOKEN.name())
//                .errorCode(ErrorCode.EXPIRED_ACCESS_TOKEN.getErrorCode())
//                .message(ErrorCode.EXPIRED_ACCESS_TOKEN.getMessage())
//                .build();
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse handleValidationException(MethodArgumentNotValidException exception) {
        return ExceptionResponse.from("VALIDATION_ERROR",
                exception.getStatusCode().value(),
                Objects.requireNonNull(exception.getFieldError()).getDefaultMessage());
    }

    private ExceptionResponse getExceptionResponse(ErrorCode errorCode) {
        return ExceptionResponse.from(errorCode.name(), errorCode.getErrorCode(), errorCode.getMessage());
    }
}
