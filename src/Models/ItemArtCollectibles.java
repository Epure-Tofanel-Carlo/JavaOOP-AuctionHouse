package Models;

public class ItemArtCollectibles implements Item
{
    private  String itemId;
    private  String name;
    private  String description;
    private  int startPrice;
    private  int currentBid;
    private  RegularUser leadingBidder;
    private  RegularUser userSeller;
    private  long bidEndTime;
    private  String categoryId;
    private boolean isSold;

    // nevoie de fielduri specifice gen materials, tags, author


    public ItemArtCollectibles()
    {
    }

    public ItemArtCollectibles(String itemId, String name, String description, int startPrice, int currentBid, RegularUser leadingBidder, RegularUser userSeller, long bidEndTime, String categoryId, boolean isSold)
    {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.currentBid = currentBid;
        this.leadingBidder = leadingBidder;
        this.userSeller = userSeller;
        this.bidEndTime = bidEndTime;
        this.categoryId = categoryId;
        this.isSold = isSold;
    }

    @Override
    public String getItemId()
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
    public void setItemId(String itemId)
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
}
