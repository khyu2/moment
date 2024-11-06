package study.moment.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.moment.global.exception.ErrorCode;
import study.moment.global.jwt.AuthException;
import study.moment.global.jwt.JwtProvider;
import study.moment.global.utils.PasswordEncoder;
import study.moment.member.domain.Member;
import study.moment.member.dto.request.LoginRequest;
import study.moment.member.dto.request.MemberSignUpRequest;
import study.moment.member.dto.response.LoginResponse;
import study.moment.member.dto.response.MemberResponse;
import study.moment.member.repo.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final JwtProvider jwtProvider;

    public MemberResponse signUp(MemberSignUpRequest request) {
        if (memberRepository.existsByUsername(request.username())) {
            throw new AuthException(ErrorCode.ALREADY_EXIST_MEMBER);
        }

        Member member = request.toEntity();

        String hashedPassword = PasswordEncoder.encode(member.getPassword());
        member.encodePassword(hashedPassword);

        return MemberResponse.from(member);
    }

    public LoginResponse login(LoginRequest request) {
        Member member = memberRepository.findByUsername(request.username())
                .orElseThrow(() -> new AuthException(ErrorCode.NOT_FOUND_MEMBER));

        if (!PasswordEncoder.matches(request.password(), member.getPassword())) {
            throw new AuthException(ErrorCode.MISMATCH_PASSWORD);
        }

        String token = jwtProvider.createToken(member.getUsername());
        return LoginResponse.from(token, member.getId());
    }
}
