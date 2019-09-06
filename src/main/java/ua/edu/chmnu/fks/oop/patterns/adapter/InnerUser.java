package ua.edu.chmnu.fks.oop.patterns.adapter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InnerUser {
    private LocalDateTime birthDate;
    private String fullName;
    private String address;
    private String phone;

    public void goodMethod() {

    }
}
