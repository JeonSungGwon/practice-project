package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest //JPA와 연동한 테스트
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* case 1 : 4번 게시글의 모든 댓글 조회 */
        {
            //입력 데이터 준비
            Long articleId = 4L;
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            Article article = new Article(4L,"wjs4518@sdfsfdsdf","q1231er");
            Comment a = new Comment(1L,article,"개구리", "배블");
            Comment b = new Comment(2L,article,"고양이", "개굴");
            Comment c = new Comment(3L,article,"말랑이", "마블");
            List<Comment> expected = Arrays.asList(a,b,c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* case 1 : 개구리의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            String nickname = "개구리";
            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상하기
            Article article = new Article(4L,"wjs4518@sdfsfdsdf","q1231er");
            Comment a = new Comment(1L,new Article(4L,"wjs4518@sdfsfdsdf","q1231er"),"개구리", "배블");
            Comment b = new Comment(4L,new Article(5L,"wjs45@sadfsadf.com","asd5wqef"),"개구리", "배블");
            Comment c = new Comment(7L,new Article(6L,"wjs18@dfgdf.com","zxcretrev"),"개구리", "배블");
            List<Comment> expected = Arrays.asList(a,b,c);
            // 검증
            assertEquals(expected.toString(),comments.toString(),"개구리의 모든 댓글 출력");
        }
    }
}