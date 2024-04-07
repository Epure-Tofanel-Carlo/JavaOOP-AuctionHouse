package models;

public class ItemFactory
{

    public static Item createItem(String type, String name, String description, int startPrice, int currentBid, RegularUser leadingBidder, RegularUser userSeller, long bidEndTime, String categoryId, boolean isSold)
    {
        switch (type.toLowerCase())
        {
            case "artcollectibles":
                return new ItemArtCollectibles(  name,  description,  startPrice,  currentBid,  leadingBidder, userSeller,  bidEndTime,  categoryId , isSold = false);
            case "clothes":
                return new ItemClothes(  name,  description,  startPrice,  currentBid,  leadingBidder, userSeller,  bidEndTime,  categoryId , isSold = false);
            case "electronic":
                return new ItemElectronic(  name,  description,  startPrice,  currentBid,  leadingBidder, userSeller,  bidEndTime,  categoryId , isSold = false);
            default:
                throw new IllegalArgumentException("Unknown item type: " + type);
        }
    }
}
