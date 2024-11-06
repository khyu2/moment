package study.moment.member.dto.request;

import jakarta.validation.constraints.NotNull;
import study.moment.member.domain.Member;
import study.moment.member.enums.Gender;

public record MemberSignUpRequest(
        @NotNull(message = "아이디가 입력되지 않았습니다.") String username,
        @NotNull(message = "비밀번호가 입력되지 않았습니다.") String password,
        @NotNull(message = "나이가 입력되지 않았습니다.") Long age,
        @NotNull(message = "성별이 입력되지 않았습니다.") Gender gender,
        @NotNull(message = "주소가 입력되지 않았습니다.") String address
) {
    public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(password)
                .age(age)
                .gender(gender)
                .address(address)
                .build();
    }
}
