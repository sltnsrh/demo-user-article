package com.salatin.demouser.service;

import com.salatin.demouser.model.User;
import java.util.List;

public interface UserService {
    User save(User user);

    List<User> getUsersWithAgeGreaterThan(short minAge);

    List<User> getAllWithArticleColor(String color);

    List<String> getAllNamesWithArticleCountGte(int count);
}
