package drzazga.daniel.geodezja.Dtos;

public class RoleDto {
    private Long Id;
    private String role;

    public RoleDto() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
