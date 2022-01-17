package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional //JPA는 join이 들어올떄 모든 데이터변경이 Transactional안에서 실행되어야함.
//@Service //컨테이너에 스프링 빈으로 등록하기 위함. 하나만 등록해서 공유함
public class MemberService {
    private final MemberRepository memberRepository;


    //Map 이 static이 아니라면 TEST와 MemberService에서 사용되는 MemoryMemberRepository는 다른객체이므로 다른DB에 접근하게됨
    //따라서 외부에서 값을 받아 객체를 생성하면 같은 DB에 접근가능....?헷갈려 ㅠ
    //이런걸 DI라고 함
 //   @Autowired
    public MemberService(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member)//회원가입
    {//같은 이름이 있는 중복 회원
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member); // 통과하면 저장
            return member.getId(); //왜 리턴하는걸까?
    }

    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId)
    {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

}
