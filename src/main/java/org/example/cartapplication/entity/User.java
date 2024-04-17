package org.example.cartapplication.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString(exclude = {"basket", "orders", "userVerificationData", "userStatus", "expertGroup"}) //TODO повторить в остальных энтити(исключить все поля с связями)
@EqualsAndHashCode(exclude = {"basket", "orders", "userVerificationData", })
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    private Basket basket;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    private UserVerificationData userVerificationData;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserStatus userStatus;

    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_expertGroup",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "expertGroup_id")
    )
    private Set<ExpertGroup> expertGroups = new HashSet<>();

    public void setBasket(Basket basket) {
        this.basket = basket;
        basket.setUser(this);
    }

    public void setUserVerificationData(UserVerificationData userVerificationData) {
        this.userVerificationData = userVerificationData;
        userVerificationData.setUser(this);
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setUser(null);
    }

    public void addExpertGroup(ExpertGroup expertGroup) {
        expertGroups.add(expertGroup);
        expertGroup.getUsers().add(this);
    }

    public void removeExpertGroup(ExpertGroup expertGroup) {
        expertGroups.remove(expertGroup);
        expertGroup.getUsers().remove(this);
    }

}
