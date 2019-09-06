package ua.edu.chmnu.fks.oop.patterns.adapter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExternalUser {
    private int age;
    private String firstName;
    private String lastName;

    private String address;
    private String phone;

    public void hiddenMethod() {

    }
}
