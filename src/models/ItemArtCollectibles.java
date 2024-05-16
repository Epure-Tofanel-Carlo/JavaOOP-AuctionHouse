package models;

import java.util.UUID;

public class ItemArtCollectibles implements Item {
    private UUID itemId;
    private String name;
    private String description;
    private int startPrice;
    private int currentBid;
    private RegularUser leadingBidder;
    private RegularUser userSeller;
    private long bidEndTime;
    private String categoryId;
    private boolean isSold;
    private String artist;
    private String medium;
    private String dimensions;
    private int year;


    public ItemArtCollectibles()
    {
        this.itemId = UUID.randomUUID();
    }

    public ItemArtCollectibles(String name, String description, int startPrice, int currentBid, RegularUser leadingBidder, RegularUser userSeller, long bidEndTime, String categoryId, boolean isSold, String artist, String medium, String dimensions, int year)
    {
        this.itemId = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.currentBid = currentBid;
        this.leadingBidder = leadingBidder;
        this.userSeller = userSeller;
        this.bidEndTime = bidEndTime;
        this.categoryId = categoryId;
        this.isSold = isSold;
        this.artist = artist;
        this.medium = medium;
        this.dimensions = dimensions;
        this.year = year;
    }

    @Override
    public UUID getItemId()
    {
        return itemId;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public int getStartPrice()
    {
        return startPrice;
    }

    @Override
    public int getCurrentBid()
    {
        return currentBid;
    }

    @Override
    public RegularUser getLeadingBidder()
    {
        return leadingBidder;
    }

    @Override
    public RegularUser getUserSeller()
    {
        return userSeller;
    }

    @Override
    public long getBidEndTime()
    {
        return bidEndTime;
    }

    @Override
    public String getCategoryId()
    {
        return categoryId;
    }

    @Override
    public boolean getIsSold()
    {
        return isSold;
    }

    @Override
    public void setItemId(UUID itemId)
    {
        this.itemId = itemId;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public void setStartPrice(int startPrice)
    {
        this.startPrice = startPrice;
    }

    @Override
    public void setCurrentBid(int currentBid)
    {
        this.currentBid = currentBid;
    }

    @Override
    public void setLeadingBidder(RegularUser leadingBidder)
    {
        this.leadingBidder = leadingBidder;
    }

    @Override
    public void setUserSeller(RegularUser userSeller)
    {
        this.userSeller = userSeller;
    }

    @Override
    public void setBidEndTime(long bidEndTime)
    {
        this.bidEndTime = bidEndTime;
    }

    @Override
    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }

    @Override
    public void setIsSold(boolean sold)
    {
        isSold = sold;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "ItemArtCollectibles {" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startPrice=" + startPrice +
                ", currentBid=" + currentBid +
                ", leadingBidder=" + (leadingBidder != null ? leadingBidder.getUserName() : "None") +
                ", userSeller=" + (userSeller != null ? userSeller.getUserName() : "None") +
                ", bidEndTime=" + bidEndTime +
                ", categoryId='" + categoryId + '\'' +
                ", isSold=" + isSold +
                ", artist='" + artist + '\'' +
                ", medium='" + medium + '\'' +
                ", dimensions='" + dimensions + '\'' +
                ", year=" + year +
                '}';
    }
}
