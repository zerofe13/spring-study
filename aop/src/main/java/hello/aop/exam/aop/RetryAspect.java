package hello.aop.exam.aop;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspect {
    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable { // 파라미터로 포인트컷 확인
        log.info("[retry] {} retry={}",joinPoint.getSignature(),retry);
        int maxRetry = retry.value();
        Exception exceptionHolder =null;
        int retryCount = 1;
        while (retryCount <= maxRetry) {
            try {
                log.info("[retry] count={}/{}", retryCount, maxRetry);
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
            }
            retryCount++;
        }
        throw exceptionHolder;

    }
}
