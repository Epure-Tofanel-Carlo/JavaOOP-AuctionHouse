package models;

public class ItemFactory
{
    public static Item createItem(String type, String name, String description, int startPrice, int currentBid,
                                  RegularUser leadingBidder, RegularUser userSeller, long bidEndTime, String categoryId,
                                  boolean isSold, String... additionalInfo)
    {
        switch (type.toLowerCase())
        {
            case "artcollectibles":
                if (additionalInfo.length < 4)
                {
                    throw new IllegalArgumentException("Insufficient information for creating ItemArtCollectibles");
                }
                return new ItemArtCollectibles(name, description, startPrice, currentBid, leadingBidder, userSeller,
                        bidEndTime, categoryId, isSold, additionalInfo[0], additionalInfo[1], additionalInfo[2],
                        Integer.parseInt(additionalInfo[3]));
            case "clothes":
                if (additionalInfo.length < 6)
                {
                    throw new IllegalArgumentException("Insufficient information for creating ItemClothes");
                }
                return new ItemClothes(name, description, startPrice, currentBid, leadingBidder, userSeller,
                        bidEndTime, categoryId, isSold, additionalInfo[0], additionalInfo[1], additionalInfo[2],
                        additionalInfo[3], additionalInfo[4], additionalInfo[5]);
            case "electronic":
                if (additionalInfo.length < 5)
                {
                    throw new IllegalArgumentException("Insufficient information for creating ItemElectronic");
                }
                return new ItemElectronic(name, description, startPrice, currentBid, leadingBidder, userSeller,
                        bidEndTime, categoryId, isSold, additionalInfo[0], additionalInfo[1], additionalInfo[2],
                        additionalInfo[3], Integer.parseInt(additionalInfo[4]));
            default:
                throw new IllegalArgumentException("Unknown item type: " + type);
        }
    }
}