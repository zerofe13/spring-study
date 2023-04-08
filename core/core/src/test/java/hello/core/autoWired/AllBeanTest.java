package hello.core.autoWired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {
    @Test
    void findAllBean(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L,"userA", Grade.VIP);
        int discountPrice = discountService.discount(member,20000,"fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member,30000,"rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(3000);
    }
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;
        private final Map<String, MemberRepository> memberRepositoryMap;


        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies, Map<String, MemberRepository> memberRepositoryMap) {
            this.policyMap = policyMap;
            this.policies = policies;
            this.memberRepositoryMap = memberRepositoryMap;
            System.out.println("policyMap = " + policyMap);
            System.out.println("memberRepositoryMap = " + memberRepositoryMap);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member,price);
        }
    }
}
