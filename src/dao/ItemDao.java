package dao;

import models.Item;
import models.ItemArtCollectibles;
import models.ItemClothes;
import models.ItemElectronic;

import java.util.ArrayList;
import java.util.List;

public class ItemDao
{

     private static List<ItemElectronic> itemElectronicList = new ArrayList<>();
     private static List<ItemClothes> itemClothesList = new ArrayList<>();
     private static List<ItemArtCollectibles> itemArtCollectiblesList = new ArrayList<>();


     public void createItemElectronic (ItemElectronic item)
     {
          itemElectronicList.add(item);
     }
     public void createItemClothes (ItemClothes item)
     {
          itemClothesList.add(item);
     }
     public void createItemArt (ItemArtCollectibles item)
     {
          itemArtCollectiblesList.add(item);
     }

     public void removeItem (ItemElectronic item)
     {
        itemElectronicList.remove(item);
     }

     public void removeItem (ItemArtCollectibles item)
     {
          itemArtCollectiblesList.remove(item);
     }

     public void removeItem (ItemClothes item)
     {
          itemArtCollectiblesList.remove(item);
     }

     public void readItem (ItemArtCollectibles item)
     {
          if (!(itemArtCollectiblesList.isEmpty()))
          {

          }
     }

     public void readItem (ItemElectronic item)
     {
         if (!(itemElectronicList).isEmpty())
         {

         }
     }

     public ItemElectronic readItemElectronicById(String itemId) {
          for (ItemElectronic item : itemElectronicList) {
               if (item.getItemId().equals(itemId)) {
                    return item;
               }
          }
          return null;
     }

     public ItemClothes readItemClothesById(String itemId) {
          for (ItemClothes item : itemClothesList) {
               if (item.getItemId().equals(itemId)) {
                    return item;
               }
          }
          return null;
     }

     public ItemArtCollectibles readItemArtCollectiblesById(String itemId) {
          for (ItemArtCollectibles item : itemArtCollectiblesList) {
               if (item.getItemId().equals(itemId)) {
                    return item;
               }
          }
          return null;
     }






}
