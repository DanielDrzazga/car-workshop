package drzazga.daniel.geodezja.Dtos;

import java.util.Set;

public class UserUpdateDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Integer active;
    private Set<RoleDto> rolesDto;
    private Long nrRoli;

    public UserUpdateDto() {
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

    public Long getNrRoli() {
        return nrRoli;
    }

    public void setNrRoli(Long nrRoli) {
        this.nrRoli = nrRoli;
    }
}
