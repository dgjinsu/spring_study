package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {//테이블m과 같은 엔티티m 중 이름이 name인것
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name) //파라미터 값 설정(파라미터 바인딩)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() { //테이블m과 같은 엔티티m
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
    }
}
