package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
//@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence); //ID세팅
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    } //null이 반환될수있기때문에 Optional 사용

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //찾을때가지 무한루프
                .filter(member -> member.equals(name))
                .findAny();//하나라도 찾으면

    }

    @Override
    public List<Member> findAll()
    {
       return new ArrayList<>(store.values());
    }

    public void clearStore()
    {
        store.clear();
    }
}
