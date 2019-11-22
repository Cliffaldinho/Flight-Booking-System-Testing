package seng3150.group1.entities;

import javax.persistence.*;

@Entity
@Table(name = "Country")
public class Country {
    @Id
    @Column(name = "countryCode3")
    private String countryCode3;

    @Basic(optional = false)
    @Column(name = "countryCode2")
    private String countryCode2;

    @Basic(optional = false)
    @Column(name = "countryName")
    private String countryName;

    @Basic(optional = false)
    @Column(name = "alternateName1")
    private String alternateName1;

    @Basic(optional = false)
    @Column(name = "alternateName2")
    private String alternateName2;

    @Basic(optional = false)
    @Column(name = "motherCountryCode3")
    private String motherCountryCode3;

    @Basic(optional = false)
    @Column(name = "motherCountryComment")
    private String motherCountryComment;

    public String getCountryCode3() {
        return countryCode3;
    }

    public void setCountryCode3(String countryCode3) {
        this.countryCode3 = countryCode3;
    }

    public String getCountryCode2() {
        return countryCode2;
    }

    public void setCountryCode2(String countryCode2) {
        this.countryCode2 = countryCode2;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAlternateName1() {
        return alternateName1;
    }

    public void setAlternateName1(String alternateName1) {
        this.alternateName1 = alternateName1;
    }

    public String getAlternateName2() {
        return alternateName2;
    }

    public void setAlternateName2(String alternateName2) {
        this.alternateName2 = alternateName2;
    }

    public String getMotherCountryCode3() {
        return motherCountryCode3;
    }

    public void setMotherCountryCode3(String motherCountryCode3) {
        this.motherCountryCode3 = motherCountryCode3;
    }

    public String getMotherCountryComment() {
        return motherCountryComment;
    }

    public void setMotherCountryComment(String motherCountryComment) {
        this.motherCountryComment = motherCountryComment;
    }
}
