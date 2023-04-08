package com.salatin.demouser.service.impl;

import com.salatin.demouser.model.Article;
import com.salatin.demouser.model.User;
import com.salatin.demouser.repository.ArticleRepository;
import com.salatin.demouser.service.ArticleService;
import com.salatin.demouser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Article save(Article article, UserDetails userDetails) {
        Article savedArticle = articleRepository.save(article);

        User user = userService.findByEmail(userDetails.getUsername());
        user.getArticles().add(article);
        userService.save(user);

        return savedArticle;
    }
}
