package com.salatin.demouser.service;

import com.salatin.demouser.model.Article;
import org.springframework.security.core.userdetails.UserDetails;

public interface ArticleService {
    Article save(Article article, UserDetails UserDetails);
}
