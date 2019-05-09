package drzazga.daniel.geodezja.Dtos;

import drzazga.daniel.geodezja.annotations.CorrectUserEmail;
import drzazga.daniel.geodezja.annotations.CorrectUserPassword;
import drzazga.daniel.geodezja.annotations.UniqueUserEmail;

import javax.validation.constraints.NotBlank;

public class UserRegistrationDto {

    @UniqueUserEmail
    @CorrectUserEmail
    @NotBlank(message = "{error.userEmail.empty}")
    private String email;

    @CorrectUserPassword
    @NotBlank(message = "{error.userPassword.empty}")
    private String password;

    @NotBlank(message = "{error.userName.empty}")
    private String firstName;

    @NotBlank(message = "{error.userLastName.empty}")
    private String lastName;

    private String activationCode;

    public UserRegistrationDto() {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
