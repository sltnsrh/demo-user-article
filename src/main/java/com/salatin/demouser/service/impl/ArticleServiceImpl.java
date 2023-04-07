package com.salatin.demouser.service.impl;

import com.salatin.demouser.model.Article;
import com.salatin.demouser.repository.ArticleRepository;
import com.salatin.demouser.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }
}
