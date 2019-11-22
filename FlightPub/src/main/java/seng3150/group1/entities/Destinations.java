package seng3150.group1.entities;

import javax.persistence.*;

@Entity
@Table(name = "Destinations")
public class Destinations {
    @Id
    @Column(name = "DestinationCode")
    private String destinationCode;

    @Basic(optional = false)
    @Column(name = "Airport")
    private String airport;

    @ManyToOne
    @JoinColumn(name = "countryCode3")
    private Country country;

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
