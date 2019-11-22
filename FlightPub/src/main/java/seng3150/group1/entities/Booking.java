package seng3150.group1.entities;

import javax.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /** Each Booking is linked to a single UserBooking*/
    @ManyToOne
    @JoinColumn(name = "userBookingId")
    private UserBooking userBooking;

    /** Each Booking also links to a single FlightInformation, containing the exact flight that was booked */
    @ManyToOne
    @JoinColumn(name = "flightInformationId")
    private FlightInformation flightInformation;

    @Column(name="legNo")
    private int legNo;

    @Column(name="reviewed")
    private boolean reviewed;

    public Booking (){}

    public Booking(UserBooking userBooking, FlightInformation flightInformation, int legNo) {
        this.userBooking = userBooking;
        this.flightInformation = flightInformation;
        this.legNo = legNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserBooking getUserBooking() {
        return userBooking;
    }

    public void setUserBooking(UserBooking userBooking) {
        this.userBooking = userBooking;
    }

    public FlightInformation getFlightInformation() {
        return flightInformation;
    }

    public void setFlightInformation(FlightInformation flightInformation) {
        this.flightInformation = flightInformation;
    }

    public int getLegNo() {
        return legNo;
    }

    public void setLegNo(int legNo) {
        this.legNo = legNo;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }


}
