package study.moment.member.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import study.moment.global.exception.ErrorCode;
import study.moment.member.domain.Member;
import study.moment.member.exception.MemberException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member getByIdOrThrow(Long memberId) {
        return findById(memberId).orElseThrow(() -> new MemberException(ErrorCode.NOT_FOUND_MEMBER));
    }

    boolean existsByUsername(String username);

    Optional<Member> findByUsername(String username);
}
