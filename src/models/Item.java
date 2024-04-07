package models;

import java.util.UUID;

public interface Item
{

    UUID getItemId();
    String getName();
    String getDescription();
    int getStartPrice();
    int getCurrentBid();
    RegularUser getLeadingBidder();
    RegularUser getUserSeller();
    long getBidEndTime();
    String getCategoryId();
    boolean getIsSold();



    void setItemId(UUID itemId);
    void setName(String name);
    void setDescription(String description);
    void setStartPrice(int startPrice);
    void setCurrentBid(int currentBid);
    void setLeadingBidder(RegularUser leadingBidder);
    void setUserSeller(RegularUser userSeller);
    void setBidEndTime(long bidEndTime);
    void setCategoryId(String categoryId);
    void setIsSold(boolean isSold);


    
}
