package models;

public class Bid {
    private Item item;
    private int bidAmount;
    private boolean isLeading;

    public Bid(Item item, int bidAmount, boolean isLeading) {
        this.item = item;
        this.bidAmount = bidAmount;
        this.isLeading = isLeading;
    }

    public Item getItem() {
        return item;
    }

    public int getBidAmount() {
        return bidAmount;
    }

    public boolean isLeading() {
        return isLeading;
    }

    @Override
    public String toString() {
        return "Bid {" +
                "item=" + item +
                ", bidAmount=" + bidAmount +
                ", isLeading=" + isLeading +
                '}';
    }
}
