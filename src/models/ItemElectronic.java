package models;

import java.util.UUID;

public class ItemElectronic implements Item
{
    private UUID itemId;
    private  String name;
    private  String description;
    private  int startPrice;
    private  int currentBid;
    private  RegularUser leadingBidder;
    private  RegularUser userSeller;
    private  long bidEndTime;
    private  String categoryId;
    private boolean isSold;
    private String brand;
    private String model;
    private String specifications;
    private String condition;
    private int manufactureYear;

    public ItemElectronic()
    {
        this.itemId = UUID.randomUUID();
    }

    public ItemElectronic(String name, String description, int startPrice, int currentBid, RegularUser leadingBidder, RegularUser userSeller, long bidEndTime, String categoryId, boolean isSold, String brand, String model, String specifications, String condition, int manufactureYear)
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
        this.brand = brand;
        this.model = model;
        this.specifications = specifications;
        this.condition = condition;
        this.manufactureYear = manufactureYear;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    @Override
    public String toString() {
        return "ItemElectronic {" +
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
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", specifications='" + specifications + '\'' +
                ", condition='" + condition + '\'' +
                ", manufactureYear=" + manufactureYear +
                '}';
    }
}
