package ua.edu.chmnu.fks.oop.design.users.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AuthUser implements Serializable {
    @EqualsAndHashCode.Exclude
    private Long id;
    private String login;
    private String hashPassword;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;
    private UserPersonalInformation personalInformation;

    public Boolean isLogged() {
        return this.loginTime != null;
    }
}
