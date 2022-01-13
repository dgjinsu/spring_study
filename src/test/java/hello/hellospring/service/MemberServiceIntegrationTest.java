package hello.hellospring.service;

import
        hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //스프링 컨테이너와 테스트를 함께 실행함
@Transactional  //테스트 끝나면 롤백해줌. 테스트한 데이터는 DB에 남지않음->다음테스트에 영향 X
class MemberServiceIntegration {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository; //테스트할땐 필드인젝션으로 하는것도 괜찮음

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("ab");

        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    @Test
    public void 중복회원_예외()
    {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        memberService.join(member2);
        //IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join)
        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}