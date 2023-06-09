package com.example.firstproject.controller;

import com.example.firstproject.api.CommentApiController;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j // 로깅을 위한 에너테이션
public class ArticleController {
    @Autowired //스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }
    @PostMapping("articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());
//        System.out.println(form.toString()); --> Logging 기능으로 대체

        //1. Dto를 Entity로 변환
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString()); --> Logging 기능으로 대체
        //2. Repository에게 Entity를 DB안에 저장하게 함
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
//        System.out.println(saved.toString()); --> Logging 기능으로 대체
        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        //1. id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);
        //2. 가져온 데이터를 모델에 등록
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos",commentDtos);
        //3. 보여줄 page 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 Article를 가져온다.
        List<Article> articleEntityList = articleRepository.findAll();
        //2. 가져온 Article 묶음을 뷰로 전달한다.
        model.addAttribute("articleList",articleEntityList);

        //3. 뷰 페이지를 설정한다.
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(Model model, @PathVariable Long id){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //뷰 페이지 설정
        model.addAttribute("article",articleEntity);
        return "articles/edit";

    }
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());

        //1. DTO -> Entity로 변환
        Article articleEntity = form.toEntity();

        //2. Entity -> DB로 변환
        //2-1. DB에서 기존 데이터를 가져온다.
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        //2-2. 기존 데이터가 있다면 값을 갱신한다.
        if(target != null){
            articleRepository.save(articleEntity); // Entity가 DB로 갱신
        }
        //3. 수정 결과 페이지로 리다이렉트

        return "redirect:/articles/"+articleEntity.getId();
    }
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){

        //1. 삭제 대상을 가져온다.
            Article target = articleRepository.findById(id).orElse(null);
            log.info(target.toString());
            //2. 대상을 삭제한다.
            if(target != null){
                articleRepository.delete(target);
                rttr.addFlashAttribute("msg","삭제가 완료 되었습니다.");
            }
        //3. 결과 페이지로 리다이렉트한다.
        return "redirect:/articles";
    }
}
