package com.joonsang.example.SpringbootBasic.listener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// == 애플리케이션 시작 될 때 실행
/**
 * Bean 으로 등록 할 필요 없는 이유
 * = Application Context 가 만들어 지기 전, 동작하는 구현체라 Bean 으로 등록해도 동작하지 않음
 *   따로 Application 클래스에서 동작하도록 설정 해줘야 한다.
 * = ApplicationContext 를 만들기 전에 사용하는 리스너는 @Bean 으로 등록할 수 없다
 */
public class SampleListener implements ApplicationListener<ApplicationStartingEvent>  {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.out.println("=================================================");
        System.out.println("Application is Starting");
        System.out.println("=================================================");
    }
}

// == 애플리케이션 실행되었을 때 실행
@Component
class SampleListener2 implements ApplicationListener<ApplicationStartedEvent>  {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("=================================================");
        System.out.println("Application is Started");
        System.out.println("=================================================");

    }
}

// == 애플리케이션 실행한 뒤 실행

@Component
@Order(0)
class SampleListener3 implements ApplicationRunner  {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("=================================================");
        System.out.println("Application is Runner1");
        System.out.println("=================================================");
    }
}

@Component
@Order(1)
class SampleListener4 implements ApplicationRunner  {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("=================================================");
        System.out.println("Application is Runner2");
        System.out.println("=================================================");
    }
}