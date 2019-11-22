package seng3150.group1.entities;

import javax.persistence.*;

@Entity
@Table(name = "AirlineReviews")
public class AirlineReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AirlineReviewId")
    private int AirlineReviewId;

    @ManyToOne
    @JoinColumn(name = "AirlineCode")
    private Airlines airline;

    @Column(name = "AirlineOverallRating")
    private double airlineOverallRating;

    @Column(name = "AirlineServiceRating")
    private double airlineServiceRating;

    @Column(name = "AirlineFoodAndBeverageRating")
    private double airlineFoodAndBeverageRating;

    @Column(name = "AirlineSeatAndComfortRating")
    private double airlineSeatAndComfortRating;

    @Column(name = "AirlinePunctualityRating")
    private double airlinePunctualityRating;

    @Column(name = "AirlineCleanlinessRating")
    private double airlineCleanlinessRating;

    @Column(name = "AirlineEntertainementRating")
    private double airlineEntertainmentRating;

    @Column(name = "AirlineRecommendedRating")
    private double airlineRecommendedRating;

    public int getAirlineReviewId() {
        return AirlineReviewId;
    }

    public void setAirlineReviewId(int airlineReviewId) {
        AirlineReviewId = airlineReviewId;
    }

    public Airlines getAirline() {
        return airline;
    }

    public void setAirline(Airlines airline) {
        this.airline = airline;
    }

    public double getAirlineOverallRating() {
        return airlineOverallRating;
    }

    public void setAirlineOverallRating(double airlineOverallRating) {
        this.airlineOverallRating = airlineOverallRating;
    }

    public double getAirlineServiceRating() {
        return airlineServiceRating;
    }

    public void setAirlineServiceRating(double airlineServiceRating) {
        this.airlineServiceRating = airlineServiceRating;
    }

    public double getAirlineFoodAndBeverageRating() {
        return airlineFoodAndBeverageRating;
    }

    public void setAirlineFoodAndBeverageRating(double airlineFoodAndBeverageRating) {
        this.airlineFoodAndBeverageRating = airlineFoodAndBeverageRating;
    }

    public double getAirlineRecommendedRating() {
        return airlineRecommendedRating;
    }

    public void setAirlineRecommendedRating(double airlineRecommendedRating) {
        this.airlineRecommendedRating = airlineRecommendedRating;
    }

    public double getAirlineSeatAndComfortRating() {
        return airlineSeatAndComfortRating;
    }

    public void setAirlineSeatAndComfortRating(double airlineSeatAndComfortRating) {
        this.airlineSeatAndComfortRating = airlineSeatAndComfortRating;
    }

    public double getAirlinePunctualityRating() {
        return airlinePunctualityRating;
    }

    public void setAirlinePunctualityRating(double airlinePunctualityRating) {
        this.airlinePunctualityRating = airlinePunctualityRating;
    }

    public double getAirlineCleanlinessRating() {
        return airlineCleanlinessRating;
    }

    public void setAirlineCleanlinessRating(double airlineCleanlinessRating) {
        this.airlineCleanlinessRating = airlineCleanlinessRating;
    }

    public double getAirlineEntertainmentRating() {
        return airlineEntertainmentRating;
    }

    public void setAirlineEntertainmentRating(double airlineEntertainmentRating) {
        this.airlineEntertainmentRating = airlineEntertainmentRating;
    }

    public int getAirlineRecommendRating() {
        return airlineRecommendRating;
    }

    public void setAirlineRecommendRating(int airlineRecommendRating) {
        this.airlineRecommendRating = airlineRecommendRating;
    }

    @Column(name = "AirlineRecommendRating")
    private int airlineRecommendRating;
}
