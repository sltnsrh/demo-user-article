package com.salatin.demouser.service.impl;

import com.salatin.demouser.model.User;
import com.salatin.demouser.repository.UserRepository;
import com.salatin.demouser.service.UserService;
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
}
