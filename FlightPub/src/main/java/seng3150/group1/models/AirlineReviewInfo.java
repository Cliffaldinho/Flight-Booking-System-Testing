package seng3150.group1.models;

import seng3150.group1.entities.Airlines;

import javax.lang.model.type.NullType;
import javax.persistence.*;
import java.util.List;


public class AirlineReviewInfo {

    private String airlineCode;
    private String airlineName;
    private double overallRating;
    private double serviceRating;
    private double foodAndBeverageRating;
    private double seatAndComfortRating;
    private double punctualityRating;
    private double cleanlinessRating;
    private double entertainementRating;
    private double recommendedRating;


    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    public void setServiceRating(double serviceRating) {
        this.serviceRating = serviceRating;
    }

    public void setFoodAndBeverageRating(double foodAndBeverageRating) {
        this.foodAndBeverageRating = foodAndBeverageRating;
    }

    public void setSeatAndComfortRating(double seatAndComfortRating) {
        this.seatAndComfortRating = seatAndComfortRating;
    }

    public void setPunctualityRating(double punctualityRating) {
        this.punctualityRating = punctualityRating;
    }

    public void setCleanlinessRating(double cleanlinessRating) {
        this.cleanlinessRating = cleanlinessRating;
    }

    public void setEntertainementRating(double entertainementRating) {
        this.entertainementRating = entertainementRating;
    }

    public void setRecommendedRating(double recommendedRating) {
        this.recommendedRating = recommendedRating;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public double getOverallRating() {
        return overallRating;
    }

    public double getServiceRating() {
        return serviceRating;
    }

    public double getFoodAndBeverageRating() {
        return foodAndBeverageRating;
    }

    public double getSeatAndComfortRating() {
        return seatAndComfortRating;
    }

    public double getPunctualityRating() {
        return punctualityRating;
    }

    public double getCleanlinessRating() {
        return cleanlinessRating;
    }

    public double getEntertainementRating() {
        return entertainementRating;
    }

    public double getRecommendedRating() {
        return recommendedRating;
    }




    public void convertAirlineToAirlineReviewInformation(Airlines airlineToConvert) {
        this.airlineCode = airlineToConvert.getAirlineCode();
        this.airlineName = airlineToConvert.getAirlineName();
        this.overallRating = airlineToConvert.getAirlineRating();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("flightpub");
        EntityManager em = emf.createEntityManager();


        Query query = em.createQuery("Select AVG(a.airlineServiceRating) from AirlineReviews a Where a.airline = :sairlinePassed");
        query.setParameter("sairlinePassed", airlineToConvert);

        try {
            this.serviceRating = (double) query.getSingleResult();
        }
        catch (Exception e1) {
            this.serviceRating = 0;
        }


        query = em.createQuery("Select AVG(a.airlineFoodAndBeverageRating) from AirlineReviews a Where a.airline = :sairlinePassed");
        query.setParameter("sairlinePassed", airlineToConvert);
        try {
            this.foodAndBeverageRating = (double) query.getSingleResult();
        }
        catch (Exception e1) {
            this.foodAndBeverageRating = 0;
        }


        query = em.createQuery("Select AVG(a.airlineSeatAndComfortRating) from AirlineReviews a Where a.airline = :sairlinePassed");
        query.setParameter("sairlinePassed", airlineToConvert);
        try {
            this.seatAndComfortRating = (double) query.getSingleResult();
        }
        catch (Exception e1) {
            this.seatAndComfortRating = 0;
        }


        query = em.createQuery("Select AVG(a.airlinePunctualityRating) from AirlineReviews a Where a.airline = :sairlinePassed");
        query.setParameter("sairlinePassed", airlineToConvert);
        try {
            this.punctualityRating = (double) query.getSingleResult();
        }
        catch (Exception e1) {
            this.punctualityRating = 0;
        }

        query = em.createQuery("Select AVG(a.airlineCleanlinessRating) from AirlineReviews a Where a.airline = :sairlinePassed");
        query.setParameter("sairlinePassed", airlineToConvert);
        try {
            this.cleanlinessRating = (double) query.getSingleResult();
        }
        catch (Exception e1) {
            this.cleanlinessRating = 0;
        }


        query = em.createQuery("Select AVG(a.airlineEntertainmentRating) from AirlineReviews a Where a.airline = :sairlinePassed");
        query.setParameter("sairlinePassed", airlineToConvert);
        try {
            this.entertainementRating = (double) query.getSingleResult();
        }
        catch (Exception e1) {
            this.entertainementRating = 0;
        }


        query = em.createQuery("Select AVG(a.airlineRecommendedRating) from AirlineReviews a Where a.airline = :sairlinePassed");
        query.setParameter("sairlinePassed", airlineToConvert);
        try {
            this.recommendedRating = (double) query.getSingleResult();
        }
        catch (Exception e1) {
            this.recommendedRating = 0;
        }

    }
}
