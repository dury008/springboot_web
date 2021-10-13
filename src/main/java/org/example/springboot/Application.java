package org.example.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //Jpa Auditing 활성화
@SpringBootApplication //현재위치부터 스프링 부트의 자동설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
