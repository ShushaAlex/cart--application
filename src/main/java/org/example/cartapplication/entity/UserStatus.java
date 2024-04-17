package org.example.cartapplication.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class UserStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "userStatus", cascade = CascadeType.ALL)
    private Set<User> users;

    private boolean isVip;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    public void addUser(User user) {
        users.add(user);
        user.setUserStatus(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setUserStatus(null);
    }

}
