package drzazga.daniel.geodezja.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "application")
public class ApplicationWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long Id;

    @OneToOne
    private User customer;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "application_parts"
            , joinColumns = @JoinColumn(name = "application_id")
            , inverseJoinColumns = @JoinColumn(name = "part_id"))
    private List<Part> parts;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "application_works"
            , joinColumns = @JoinColumn(name = "application_id")
            , inverseJoinColumns = @JoinColumn(name = "work_id"))
    private List<Work> works;

    @OneToOne
    private User employee;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    public ApplicationWork() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
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

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
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
