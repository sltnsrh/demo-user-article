package com.salatin.demouser.service.impl;

import com.salatin.demouser.model.User;
import com.salatin.demouser.repository.UserRepository;
import com.salatin.demouser.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsersWithAgeGte(short minAge) {
        return userRepository.findAllUsersWithAgeGte(minAge);
    }

    @Override
    public List<User> getAllWithArticleColor(String color) {
        return userRepository.findAllUsersWithArticleColor(color.toUpperCase());
    }

    @Override
    public List<String> getAllUserNamesWithArticleCountGte(int count) {
        return userRepository.findAllUsersWithArticleCountGte(count).stream()
            .map(User::getName)
            .collect(Collectors.toList());
    }
}
