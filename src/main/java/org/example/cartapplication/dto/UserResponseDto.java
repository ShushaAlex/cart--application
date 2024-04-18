package org.example.cartapplication.dto;

import org.example.cartapplication.entity.Grade;

import java.util.Set;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        Long basketId,
        Set<Long> orderIds,
        boolean isVip,
        Grade userGrade,
        Set<Long> expertGroupIds) {


}