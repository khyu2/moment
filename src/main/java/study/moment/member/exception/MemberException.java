package study.moment.member.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import study.moment.global.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public class MemberException extends RuntimeException {

    private final ErrorCode errorCode;
}