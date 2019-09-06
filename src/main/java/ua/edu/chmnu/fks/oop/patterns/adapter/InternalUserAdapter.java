package ua.edu.chmnu.fks.oop.patterns.adapter;

import java.time.LocalDateTime;

public class InternalUserAdapter {

    private ExternalUser externalUser;

    public InternalUserAdapter(ExternalUser externalUser) {
        this.externalUser = externalUser;
    }

    InnerUser getUser() {
        InnerUser innerUser = new InnerUser();
        innerUser.setAddress(this.externalUser.getAddress());
        innerUser.setPhone(this.externalUser.getPhone());
        String fullName = this.externalUser.getFirstName() + " " + this.externalUser.getLastName();
        innerUser.setFullName(fullName);
        innerUser.setBirthDate(LocalDateTime.now().minusYears(this.externalUser.getAge()));
        return innerUser;
    }

    void goodMethod() {
        this.externalUser.hiddenMethod();
    }
}
