package es.mindata.superheroes.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HandlingTimeAspect {

    private static final Logger logger = LoggerFactory.getLogger(HandlingTimeAspect.class);


    @Pointcut("@annotation(es.mindata.superheroes.annotation.HandlingTime)")
    public void handlingTimePointcut() {}

    @Around("handlingTimePointcut()")
    public Object handlingTimeAround(ProceedingJoinPoint joinPoint){
        try {
            long startTime = System.currentTimeMillis();
            Object proceed = joinPoint.proceed();
            logger.info(String.valueOf(proceed));
            logger.info("method execution time: " + (System.currentTimeMillis() - startTime) + "ms");
            return proceed;
        } catch (Throwable throwable) {
            logger.error(String.valueOf(throwable.getStackTrace()));
        }
        return null;
    }

}
