package hello.aop.proxtvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy(){
        MemberService target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false);//jdk 동적프록시

        //인터페이스로 캐스팅 성공
        MemberService memberServiceProxy= (MemberService) proxyFactory.getProxy();

        //
        Assertions.assertThrows(ClassCastException.class,()->{MemberServiceImpl castingService = (MemberServiceImpl) memberServiceProxy;});

    }

    @Test
    void cglibProxy(){
        MemberService target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);//jdk 동적프록시

        //인터페이스로 캐스팅 성공
        MemberService memberServiceProxy= (MemberService) proxyFactory.getProxy();

        //
        MemberServiceImpl castingService = (MemberServiceImpl) memberServiceProxy;

    }
}
