package org.example.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //spring security 설정들을 활성화시켜줌
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService; //소셜 로그인이후 가져온 사용자의 정보를 기반으로 가입 및 정보수정, 세션 저장 등의 기능 지원

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()  //h2-console화면을 사용하기 위해 해당 옵션들을 disable함
                .and()
                    .authorizeRequests() //URL별 권한 관리를 설정하는 옵션의 시작점 antMatchers옵션을 사용하기위해 필요
                    .antMatchers("/", "/css/**", "/images/**", "/js/**","/h2-console/**").permitAll() //지정된 URL들은 전체열람 권한을 줌
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 지정된 URL들은 USER권한을 가진 사람만 가능하도록 허용
                    .anyRequest().authenticated() //설정값 이외 나머지 URL은 로그인한 사용자들만 접근허용'
                .and()
                    .logout()
                        .logoutSuccessUrl("/") //로그아웃성공시 /주소로 이동
                .and()
                    .oauth2Login() // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint() //로그인 성공이후 사용자 정보를 가져올 때의 설정을 담당
                            .userService(customOAuth2UserService);// 소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
    }
}

