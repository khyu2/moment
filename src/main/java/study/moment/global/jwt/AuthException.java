package study.moment.global.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import study.moment.global.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public class AuthException extends RuntimeException {

    private final ErrorCode errorCode;
}
