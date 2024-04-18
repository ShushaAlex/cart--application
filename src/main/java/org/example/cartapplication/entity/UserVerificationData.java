package org.example.cartapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class UserVerificationData {

    @Id
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    private String photo;
    private String voice;
    private String biometry;

    public void setUser(User user) {
        this.user = user;
        user.setUserVerificationData(this);
    }

}
