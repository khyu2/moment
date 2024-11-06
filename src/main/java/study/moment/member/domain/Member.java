package study.moment.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import study.moment.member.enums.Gender;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 회원 아이디
    private String username;

    private String password;

    private Long age;

    private Gender gender;

    private String address;

    // 신뢰도 점수
    @Builder.Default
    private Long reliability = 0L;

    public void encodePassword(String password) {
        this.password = password;
    }
}
