package seng3150.group1.entities;

import javax.persistence.*;

@Entity
@Table(name = "Airlines")
public class Airlines {
    @Id
    @Column(name = "AirlineCode")
    private String airlineCode;

    @Basic(optional = false)
    @Column(name = "AirlineName")
    private String airlineName;

    @Basic(optional = false)
    @Column(name = "AirlineRating")
    private Double airlineRating;

    @OneToOne
    @JoinColumn(name = "AirlineCode")
    private Airlines airline;


    @ManyToOne
    @JoinColumn(name = "countryCode3")
    private Country country;

    public Double getAirlineRating() {
        return airlineRating;
    }

    public void setAirlineRating(Double airlineRating) {
        this.airlineRating = airlineRating;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
