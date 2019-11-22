// Simple class to be used as a node when building the graph using JGraphT when performing a search
// Each node in the graph consist of a location as well as a time that all proceeding flights will depart after

package seng3150.group1.models;

public class DestinationNode {

    private String location; // The airport/city of this node

    // The time the flight that leads to this destination/node arrives.
    // Needed to ensure flights from this node in the graph do not depart before the previous flight would have arrived
    private String time;

    public DestinationNode() {
        this.location = "";
        this.time = "";
    }

    public DestinationNode(String location, String time) {
        this.location = location;
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}