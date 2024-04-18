package org.example.cartapplication.mapper;

import org.example.cartapplication.dto.UserCreateRequestDto;
import org.example.cartapplication.dto.UserResponseDto;
import org.example.cartapplication.entity.Basket;
import org.example.cartapplication.entity.ExpertGroup;
import org.example.cartapplication.entity.Order;
import org.example.cartapplication.entity.User;
import org.example.cartapplication.entity.UserVerificationData;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponseDto toUserResponseDto(User user) {

        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getBasket().getUserId(),
                user.getOrders().stream().map(Order::getId).collect(Collectors.toSet()),
                user.getUserStatus().isVip(),
                user.getUserStatus().getGrade(),
                user.getExpertGroups().stream().map(ExpertGroup::getId).collect(Collectors.toSet())
        );
    }

    public User toUser(UserCreateRequestDto userCreateRequestDto) {
        return User.builder()
                .username(userCreateRequestDto.username())
                .email(userCreateRequestDto.email())
                .password(userCreateRequestDto.password())
                .basket(new Basket())
                .userVerificationData(new UserVerificationData())
                .build();
    }

}
