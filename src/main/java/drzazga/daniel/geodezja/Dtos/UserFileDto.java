package drzazga.daniel.geodezja.Dtos;

public class UserFileDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer active;
    private Long nrRoli;

    public UserFileDto() {
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

    public Long getNrRoli() {
        return nrRoli;
    }

    public void setNrRoli(Long nrRoli) {
        this.nrRoli = nrRoli;
    }
}
