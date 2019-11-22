package seng3150.group1.entities;

import javax.persistence.*;

@Entity
@Table(name = "PlaneType")
public class PlaneType {
    @Id
    @Column(name = "PlaneCode")
    private String planeCode;

    @Basic(optional = false)
    @Column(name = "Details")
    private String details;

    @Basic(optional = false)
    @Column(name = "NumFirstClass")
    private int numFirstClass;

    @Basic(optional = false)
    @Column(name = "NumBusiness")
    private int numBusiness;

    @Basic(optional = false)
    @Column(name = "NumPremiumEconomy")
    private int numPremiumEconomy;

    @Basic(optional = false)
    @Column(name = "Economy")
    private int economy;

    public String getPlaneCode() {
        return planeCode;
    }

    public void setPlaneCode(String planeCode) {
        this.planeCode = planeCode;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getNumFirstClass() {
        return numFirstClass;
    }

    public void setNumFirstClass(int numFirstClass) {
        this.numFirstClass = numFirstClass;
    }

    public int getNumBusiness() {
        return numBusiness;
    }

    public void setNumBusiness(int numBusiness) {
        this.numBusiness = numBusiness;
    }

    public int getNumPremiumEconomy() {
        return numPremiumEconomy;
    }

    public void setNumPremiumEconomy(int numPremiumEconomy) {
        this.numPremiumEconomy = numPremiumEconomy;
    }

    public int getEconomy() {
        return economy;
    }

    public void setEconomy(int economy) {
        this.economy = economy;
    }
}
