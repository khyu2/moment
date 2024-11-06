package study.moment.member.dto.response;

import study.moment.member.domain.Member;

public record MemberResponse(
        Long id,
        String username
) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getId(), member.getUsername());
    }
}
