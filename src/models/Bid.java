package models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Bid {
    private UUID bidId;
    private double bidAmount;
    private LocalDateTime bidTime;
    private RegularUser bidder;
    private Item item;

    public Bid(double bidAmount, RegularUser bidder, Item item) {
        this.bidId = UUID.randomUUID();
        this.bidAmount = bidAmount;
        this.bidTime = LocalDateTime.now();
        this.bidder = bidder;
        this.item = item;
    }

    public UUID getBidId()
    {
        return bidId;
    }

    public double getBidAmount()
    {
        return bidAmount;
    }

    public LocalDateTime getBidTime()
    {
        return bidTime;
    }

    public RegularUser getBidder()
    {
        return bidder;
    }

    public Item getItem()
    {
        return item;
    }

    public void setBidId(UUID bidId)
    {
        this.bidId = bidId;
    }

    public void setBidAmount(double bidAmount)
    {
        this.bidAmount = bidAmount;
    }

    public void setBidTime(LocalDateTime bidTime)
    {
        this.bidTime = bidTime;
    }

    public void setBidder(RegularUser bidder)
    {
        this.bidder = bidder;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }
}
