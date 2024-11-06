package study.moment.global.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PasswordEncoderTest {

    @Test
    void 비밀번호_암호화() {
        // given
        String plainPassword = "password";

        // when
        String hashedPassword = PasswordEncoder.encode(plainPassword);

        // then
        assertThat(hashedPassword).isNotEqualTo(plainPassword);
    }

    @Test
    void 비밀번호_복호화() {
        // given
        String plainPassword = "password";

        // when
        String hashedPassword = PasswordEncoder.encode(plainPassword);

        // then
        assertThat(PasswordEncoder.matches(plainPassword, hashedPassword)).isTrue();
    }
}