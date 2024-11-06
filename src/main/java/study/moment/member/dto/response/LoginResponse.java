package study.moment.member.dto.response;

public record LoginResponse(
        String accessToken,
        Long memberId
) {
    public static LoginResponse from(String accessToken, Long memberId) {
        return new LoginResponse(accessToken, memberId);
    }
}
