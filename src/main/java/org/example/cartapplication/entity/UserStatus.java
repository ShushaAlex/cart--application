package org.example.cartapplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class UserStatus {

    @Column(name = "status_is_vip")
    private boolean isVip;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_grade")
    private Grade grade = Grade.NONE;

}
