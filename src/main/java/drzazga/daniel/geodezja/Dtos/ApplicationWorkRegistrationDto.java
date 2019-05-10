package drzazga.daniel.geodezja.Dtos;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;

public class ApplicationWorkRegistrationDto {

    private Long Id;

    //todo
    //zrobić walicadję null user
//    @NotNull(message = "{error.customer.empty}")
    private UserDto customer;
    private String customrrId;

    @NotBlank(message = "{error.licensePlate.empty}")
    private String licensePlate;

    @NotBlank(message = "{error.description.empty}")
    private String description;

    private String status;
    private List<PartDto> parts;
    private List<WorkDto> works;
    private UserDto employee;
    private Date startDate;
    private Date endDate;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public UserDto getCustomer() {
        return customer;
    }

    public void setCustomer(UserDto customer) {
        this.customer = customer;
    }

    public String getCustomrrId() {
        return customrrId;
    }

    public void setCustomrrId(String customrrId) {
        this.customrrId = customrrId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PartDto> getParts() {
        return parts;
    }

    public void setParts(List<PartDto> parts) {
        this.parts = parts;
    }

    public List<WorkDto> getWorks() {
        return works;
    }

    public void setWorks(List<WorkDto> works) {
        this.works = works;
    }

    public UserDto getEmployee() {
        return employee;
    }

    public void setEmployee(UserDto employee) {
        this.employee = employee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
