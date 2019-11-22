package seng3150.group1.entities;

import javax.persistence.*;

@Entity
@Table(name = "userBookings")
public class UserBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userBookingId")
    private int userBookingId;

    /**A UserBooking is owned by a single User, who booked it. */
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public UserBooking(){}

    public UserBooking(User user) {
        this.user = user;
    }

    public int getUserBookingId() {

        return userBookingId;
    }

    public void setUserBookingId(int userBookingId) {
        this.userBookingId = userBookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
