package com.salatin.demouser.service;

import com.salatin.demouser.model.User;
import java.util.List;

public interface UserService {
    User save(User user);

    User findByEmail(String email);

    List<User> getUsersWithAgeGte(short minAge);

    List<User> getAllWithArticleColor(String color);

    List<String> getAllUserNamesWithArticleCountGte(int count);
}
