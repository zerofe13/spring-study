package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LogRepository logRepository;

    /**
     * MemberService @Transactional:off
     * MemberRepository @Transactional:on
     * LogRepository @Transactional:on
     */


    @Test
    void outerTxOff_success(){
        String username = "outerTxOff_success";

        memberService.joinV1(username);

        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * MemberService @Transactional:off
     * MemberRepository @Transactional:on
     * LogRepository @Transactional:on Exception
     */
    @Test
    void outerTxOff_fail(){
        String username = "로그예외_outerTxOff_success";

        assertThatThrownBy(()->memberService.joinV1(username)).isInstanceOf(RuntimeException.class);

        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());
    }


    /**
     * MemberService @Transactional:on
     * MemberRepository @Transactional:off
     * LogRepository @Transactional:off
     */

    @Test
    void singleTx(){
        String username = "singleTx";

        memberService.joinV1(username);

        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }
    /**
     * MemberService @Transactional:on
     * MemberRepository @Transactional:on
     * LogRepository @Transactional:on
     */

    @Test
    void outerTxON_success(){
        String username = "outerTxON_success";

        memberService.joinV1(username);

        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * MemberService @Transactional:on
     * MemberRepository @Transactional:on
     * LogRepository @Transactional:on Exception
     */
    @Test
    void outerTxON_fail(){
        String username = "로그예외_outerTxON_fail";

        assertThatThrownBy(()->memberService.joinV1(username)).isInstanceOf(RuntimeException.class);

        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());
    }

    /**
     * MemberService    @Transactional:ON
     * MemberRepository @Transactional:ON
     * LogRepository    @Transactional:ON Exception
     */
    @Test
    void recoverException_fail() {

        String username = "로그예외_recoverException_fail";

        assertThatThrownBy(() -> memberService.joinV2(username))
                .isInstanceOf(UnexpectedRollbackException.class);

        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());
    }
    /**
     * MemberService    @Transactional:ON
     * MemberRepository @Transactional:ON
     * LogRepository    @Transactional(REQUIRES_NEW):ON Exception
     */
    @Test
    void recoverException_success() {

        String username = "로그예외_recoverException_success";

        memberService.joinV2(username);

        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());
    }

}