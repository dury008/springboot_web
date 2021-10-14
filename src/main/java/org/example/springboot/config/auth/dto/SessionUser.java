package org.example.springboot.config.auth.dto;


import lombok.Getter;
import org.example.springboot.domain.user.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable { //인증된 사용자 정보만 필요, 세션에 저장하기위해 직렬화를 구현한 DTO따로 생성
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
