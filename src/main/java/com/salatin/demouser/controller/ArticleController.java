package com.salatin.demouser.controller;

import com.salatin.demouser.model.Article;
import com.salatin.demouser.model.dto.request.ArticleCreationRequestDto;
import com.salatin.demouser.model.dto.response.ArticleResponseDto;
import com.salatin.demouser.service.ArticleService;
import com.salatin.demouser.service.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleMapper articleMapper;
    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleResponseDto> create(
        @RequestBody ArticleCreationRequestDto requestDto,
        @AuthenticationPrincipal UserDetails userDetails) {

        Article articleToSave = articleMapper.toModel(requestDto);
        Article savedArticle = articleService.save(articleToSave, userDetails);

        return new ResponseEntity<>(articleMapper.toDto(savedArticle), HttpStatus.CREATED);
    }
}
