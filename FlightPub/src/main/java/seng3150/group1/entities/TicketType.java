package seng3150.group1.entities;

import javax.persistence.*;

@Entity
@Table(name = "TicketType")
public class TicketType {
    @Id
    @Column(name = "TicketCode")
    private char ticketCode;

    @Basic(optional = false)
    @Column(name = "Name")
    private String name;

    @Basic(optional = false)
    @Column(name = "Transferrable")
    private boolean transferrable;

    @Basic(optional = false)
    @Column(name = "Refundable")
    private boolean refundable;

    @Basic(optional = false)
    @Column(name = "Exchangeable")
    private boolean exchangeable;

    @Basic(optional = false)
    @Column(name = "FrequentFlyerPoints")
    private boolean frequentFlyerPoints;

    public char getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(char ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTransferrable() {
        return transferrable;
    }

    public void setTransferrable(boolean transferrable) {
        this.transferrable = transferrable;
    }

    public boolean isRefundable() {
        return refundable;
    }

    public void setRefundable(boolean refundable) {
        this.refundable = refundable;
    }

    public boolean isExchangeable() {
        return exchangeable;
    }

    public void setExchangeable(boolean exchangeable) {
        this.exchangeable = exchangeable;
    }

    public boolean isFrequentFlyerPoints() {
        return frequentFlyerPoints;
    }

    public void setFrequentFlyerPoints(boolean frequentFlyerPoints) {
        this.frequentFlyerPoints = frequentFlyerPoints;
    }
}
