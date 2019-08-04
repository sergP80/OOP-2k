package ua.edu.chmnu.fks.oop.design.users;

import ua.edu.chmnu.fks.oop.design.users.config.UserAppConfigurator;
import ua.edu.chmnu.fks.oop.design.users.exceptions.UserNotFoundException;
import ua.edu.chmnu.fks.oop.design.users.model.AuthUser;
import ua.edu.chmnu.fks.oop.design.users.model.UserPersonalInformation;
import ua.edu.chmnu.fks.oop.design.users.services.AuthService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        AuthService authService = UserAppConfigurator.configureAuthUserService();
        AuthUser authUser = AuthUser.builder()
                .login("login1")
                .hashPassword("ewrgebdfbfklsfsfdsd3243452t43")
                .loginTime(LocalDateTime.now())
                .personalInformation(
                        UserPersonalInformation.builder()
                                .firstName("John")
                                .lastName("Doe")
                                .birthDate(LocalDate.now().minusYears(19)
                                                          .minusMonths(2)
                                                          .minusDays(4)
                                )
                                .phone("+380913423411")
                                .address("Niko 2, Central avenu, 172")
                                .build()
                )
                .build();

        authService.create(authUser);

        authUser = AuthUser.builder()
                .login("login2")
                .hashPassword("dsfgdgfssgfrgrtegrw24t3efsd")
                .loginTime(LocalDateTime.now())
                .personalInformation(
                        UserPersonalInformation.builder()
                                .firstName("Lee")
                                .lastName("Dong")
                                .birthDate(LocalDate.now().minusYears(22)
                                        .minusMonths(3)
                                        .minusDays(10)
                                )
                                .phone("+38042235234421")
                                .address("Niko 3, Central avenu, 22")
                                .build()
                )
                .build();

        authService.create(authUser);

        authUser = AuthUser.builder()
                .login("login3")
                .hashPassword("fERtyrtuyktfgf4sdyh")
                .logoutTime(LocalDateTime.now())
                .personalInformation(
                        UserPersonalInformation.builder()
                                .firstName("Jeel")
                                .lastName("Robinson")
                                .birthDate(LocalDate.now().minusYears(25)
                                        .minusMonths(4)
                                        .minusDays(12)
                                )
                                .phone("+3803425234132")
                                .address("Niko 3, Central avenu, 42")
                                .build()
                )
                .build();

        authService.create(authUser);

        System.out.println("**********Logged users " + authService.countLoginUsers());
        List<AuthUser> userOldThe19List =  authService.findOldThen(19);
        userOldThe19List.forEach(au -> System.out.println("------------>" + au));

        try {
            authUser = authService.findById(10L);
        }
        catch (UserNotFoundException e) {
            System.out.println("???? " + e.getMessage());
        }

        try {
            final Long userId = 2L;
            authUser = authService.findById(userId);
            authUser.getPersonalInformation().setFirstName("Leen");
            authUser.getPersonalInformation().setAddress("Odessa, Fontanka 5, 22");

            authService.update(authUser);
            authUser = authService.findById(userId);
            System.out.println("Updated user [ID " + userId + "]: " + authUser);
        }
        catch (UserNotFoundException e) {
            System.out.println("???? " + e.getMessage());
        }

        //TODO don't use in real app
        authService.clearAll();
    }
}
