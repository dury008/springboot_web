package org.example.springboot.web.domain.posts;

import org.example.springboot.domain.posts.Posts;
import org.example.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class) //Springboot 테스트와 junit 사이에 연결자
@SpringBootTest //H2데이터베이스 자동실행
public class PostsRepositoryTest {
    @Autowired//스프링이 관리하는 빈을 주입받는다
    PostsRepository postsRepository;

    @After //Junit에서 단위테스트가 끝날 떄마다 수행되는 메서드 지정
    public void cleanup() {
        postsRepository.deleteAll(); //테이블 posts에있는 모든 데이터 삭제
    }

    @Test
    public void 게시글저장_불러오기() {
        String title = "테스트 게시글";
        String content = "테스트 본분";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("gogodo@gmail.com")
                .build()
        ); // 테이블posts에 insert/update 쿼리 실행, id값있으면 update 없으면 insert

        List<Posts> postsList = postsRepository.findAll(); // 테이블 posts에있는 모든 데이터 조회

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }
}
