package drzazga.daniel.geodezja.Dtos;

import drzazga.daniel.geodezja.annotations.CorrectUserEmail;
import drzazga.daniel.geodezja.annotations.CorrectUserPassword;
import drzazga.daniel.geodezja.annotations.UniqueUserEmail;

import javax.validation.constraints.NotBlank;

public class UserPasswordChangeDto {
    @UniqueUserEmail
    @CorrectUserEmail
    @NotBlank(message = "{error.userEmail.empty}")
    private String email;

    @CorrectUserPassword
    @NotBlank(message = "{error.userPassword.empty}")
    private String password;

    public UserPasswordChangeDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
