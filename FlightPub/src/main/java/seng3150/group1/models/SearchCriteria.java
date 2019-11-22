// Class used to store an instance of a set of a user's preferences/importance weightings and place the user in a category

package seng3150.group1.models;

import java.io.Serializable;

public class SearchCriteria implements Serializable {

    private double costWeight;
    private double durationWeight;
    private double comfortWeight;
    private double noStopOversWeight;
    //calculated based on the above
    private String userCategory;

    //placeholders are used to remember the values in the sliders when the user returns to
    //the sliders page to refine their search
    private String costPlaceholder;
    private String durationPlaceholder;
    private String comfortPlaceholder;
    private String noStopOversPlaceholder;

    /*May be used soon
    private double closeDateWeight;
    private boolean refundable;
    private boolean directFlightsOnly;
    */

    public SearchCriteria(){

    }

    public SearchCriteria(String costWeight, String durationWeight, String comfortWeight, String noStopoversWeight) {
        //convert a 1 - 5 slider into a 0 - 1 multiplier (== 0, 0.25, 0.5, 0.75 or 1)
        this.costWeight = (Double.parseDouble(costWeight) - 1)*0.25;
        this.durationWeight = (Double.parseDouble(durationWeight) - 1)*0.25;
        this.comfortWeight = (Double.parseDouble(comfortWeight) - 1)*0.25;
        this.noStopOversWeight = (Double.parseDouble(noStopoversWeight) - 1)*0.25;

        System.out.println("Cost weight: "+getCostWeight());
        System.out.println("Duration weight: "+getDurationWeight());
        System.out.println("Comfort weight: "+getComfortWeight());

        if ((this.costWeight >= 0.75) && (this.durationWeight <= 0.5))
        {
            userCategory = "Backpacker";
        }
        else if ((this.costWeight <= 0.5) && (this.durationWeight >= 0.75))
        {
            userCategory = "Business Traveller";
        }
        else if ((this.costWeight <= 0.5) && (this.durationWeight <= 0.5) && (this.comfortWeight >= 0.75))
        {
            userCategory = "Frequent Weekender";
        }
        else if ((this.costWeight >= 0.5) && (this.durationWeight >= 0.75))
        {
            userCategory = "Holiday Maker";
        }
        else
        {
            userCategory = "FlightPub user";
        }
    }

    public double getCostWeight() {
        return costWeight;
    }

    public void setCostWeight(double costWeight) {
        this.costWeight = costWeight;
    }

    public double getDurationWeight() {
        return durationWeight;
    }

    public void setDurationWeight(double durationWeight) {
        this.durationWeight = durationWeight;
    }

    public double getComfortWeight() {
        return comfortWeight;
    }

    public void setComfortWeight(double comfortWeight) {
        this.comfortWeight = comfortWeight;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }


    public double getNoStopOversWeight() {
        return noStopOversWeight;
    }

    public void setNoStopOversWeight(double noStopOversWeight) {
        this.noStopOversWeight = noStopOversWeight;
    }


    public String getCostPlaceholder() {
        return Double.toString(costWeight*4 +1);
    }

    public void setCostPlaceholder(String costPlaceholder) {
        this.costPlaceholder = costPlaceholder;
    }

    public String getDurationPlaceholder() {
        return Double.toString(durationWeight*4 +1);
    }

    public void setDurationPlaceholder(String durationPlaceholder) {
        this.durationPlaceholder = durationPlaceholder;
    }

    public String getComfortPlaceholder() {
        return Double.toString(comfortWeight*4 +1);
    }

    public void setComfortPlaceholder(String comfortPlaceholder) {
        this.comfortPlaceholder = comfortPlaceholder;
    }

    public String getNoStopOversPlaceholder() {
        return Double.toString(noStopOversWeight*4 +1);
    }

    public void setNoStopOversPlaceholder(String noStopOversPlaceholder) {
        this.noStopOversPlaceholder = noStopOversPlaceholder;
    }

    /*
    public boolean isRefundable() {
        return refundable;
    }

    public void setRefundable(boolean refundable) {
        this.refundable = refundable;
    }

    public boolean isDirectFlightsOnly() {
        return directFlightsOnly;
    }

    public void setDirectFlightsOnly(boolean directFlightsOnly) {
        this.directFlightsOnly = directFlightsOnly;
    }
    */



}
