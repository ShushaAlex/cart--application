package org.example.cartapplication.service;

import org.example.cartapplication.dto.UserCreateRequestDto;
import org.example.cartapplication.dto.UserResponseDto;
import org.example.cartapplication.entity.User;
import org.example.cartapplication.exception.UserException;
import org.example.cartapplication.mapper.UserMapper;
import org.example.cartapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found"));
        return userMapper.toUserResponseDto(user);
    }

    @Transactional  // !!! from spring framework
    public UserResponseDto saveUser(UserCreateRequestDto dto) {
        User user = userMapper.toUser(dto);
        user = userRepository.save(user);
        return userMapper.toUserResponseDto(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserResponseDto updateUser(UserCreateRequestDto dto, Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserException("There is no user with such id"));
        User user = userMapper.toUser(dto);
        user.setId(id);

        return userMapper.toUserResponseDto(userRepository.save(user));
    }

}
