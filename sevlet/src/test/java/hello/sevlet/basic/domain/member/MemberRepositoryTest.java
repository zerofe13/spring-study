package hello.sevlet.basic.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() throws Exception {
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Member member = new Member("hello",20);

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(saveMember.getId());
        Assertions.assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void findALl(){
        //given
        Member member = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}