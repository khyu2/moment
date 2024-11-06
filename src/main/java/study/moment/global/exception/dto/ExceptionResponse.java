package study.moment.global.exception.dto;

public record ExceptionResponse(
        String name,
        int errorCode,
        String message
) {
    public static ExceptionResponse from(String name, int errorCode, String message) {
        return new ExceptionResponse(name, errorCode, message);
    }
}
