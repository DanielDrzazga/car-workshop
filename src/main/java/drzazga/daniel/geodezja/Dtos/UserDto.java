package drzazga.daniel.geodezja.Dtos;

import drzazga.daniel.geodezja.annotations.CorrectUserEmail;
import drzazga.daniel.geodezja.annotations.UniqueUserEmail;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class UserDto {

    private Long id;

    @UniqueUserEmail
    @CorrectUserEmail
    @NotBlank(message = "{error.userEmail.empty}")
    private String email;

    private String password;

    @NotBlank(message = "{error.userName.empty}")
    private String firstName;

    @NotBlank(message = "{error.userLastName.empty}")
    private String lastName;
    private Integer active;
    private Set<RoleDto> rolesDto;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Set<RoleDto> getRolesDto() {
        return rolesDto;
    }

    public void setRolesDto(Set<RoleDto> rolesDto) {
        this.rolesDto = rolesDto;
    }
}
