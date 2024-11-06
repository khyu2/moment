package study.moment.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.moment.member.dto.request.LoginRequest;
import study.moment.member.dto.request.MemberSignUpRequest;
import study.moment.member.dto.response.LoginResponse;
import study.moment.member.dto.response.MemberResponse;
import study.moment.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/register")
    public ResponseEntity<MemberResponse> register(@Valid @RequestBody MemberSignUpRequest request) {
        MemberResponse memberResponse = memberService.signUp(request);

        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse loginResponse = memberService.login(request);

        return ResponseEntity.ok(loginResponse);
    }
}
