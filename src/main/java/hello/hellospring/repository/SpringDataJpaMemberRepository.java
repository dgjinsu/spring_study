package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //Jpql select m from Member m where m.name = ? 으로 자동으로 코드를 짜줌     findBy"name"
    Optional<Member> findByname(String name);


}
