package models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_user_roles")
@IdClass(UserRolePK.class)// звязує ключі з класом
public class UserRole { //таблиця, яка об'єднує користувачів і ролі
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
