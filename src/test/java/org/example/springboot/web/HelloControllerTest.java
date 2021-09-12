package org.example.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //Springboot 테스트와 junit 사이에 연결자
@WebMvcTest(controllers = HelloController.class) //web에 집중할수 있는 어노테이션

public class HelloControllerTest {
    @Autowired //스프링이 관리하는 빈을 주입받는다
    private MockMvc mvc; //이 클래스를통해 get post등에 관한 api테스트를 할수있음

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // mvc.perform 결과 검증 200상태인지 확인
                .andExpect(content().string(hello)); //응답 본문의 내용이 hello인지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))//요청파라미터는 string값만 가능 숫자는 문자화해서 보내야됨됨
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
