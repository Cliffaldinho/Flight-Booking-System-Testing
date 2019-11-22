package seng3150.group1.entities;

import javax.persistence.*;

@Entity
@Table(name = "TicketClass")
public class TicketClass {
    @Id
    @Column(name = "ClassCode")
    private String classCode;

    @Basic(optional = false)
    @Column(name = "Details")
    private String details;

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
