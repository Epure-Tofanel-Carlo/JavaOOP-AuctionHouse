package dao;

import models.Item;
import models.ItemArtCollectibles;
import models.ItemClothes;
import models.ItemElectronic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemDao
{

     private static List<ItemElectronic> itemElectronicList = new ArrayList<>();
     private static List<ItemClothes> itemClothesList = new ArrayList<>();
     private static List<ItemArtCollectibles> itemArtCollectiblesList = new ArrayList<>();


     public void createItem(Item item) {

          if (item instanceof ItemElectronic) {
               itemElectronicList.add((ItemElectronic) item);
          } else if (item instanceof ItemClothes) {
               itemClothesList.add((ItemClothes) item);
          } else if (item instanceof ItemArtCollectibles) {
               itemArtCollectiblesList.add((ItemArtCollectibles) item);
          } else {
               throw new IllegalArgumentException("Unsupported item type");
          }
     }

     public void removeItem(Item item)
     {
         if (item == null)
         {
               throw new IllegalArgumentException("Item cannot be null");
          }
          if (item instanceof ItemElectronic)
          {
               if (!itemElectronicList.contains(item))
               {
                    throw new IllegalArgumentException("ItemElectronic not found");
               }
               itemElectronicList.remove(item);
          } else if (item instanceof ItemArtCollectibles)
          {
               if (!itemArtCollectiblesList.contains(item))
               {
                    throw new IllegalArgumentException("ItemArtCollectibles not found");
               }
               itemArtCollectiblesList.remove(item);
          } else if (item instanceof ItemClothes)
          {
               if (!itemClothesList.contains(item))
               {
                    throw new IllegalArgumentException("ItemClothes not found");
               }
               itemClothesList.remove(item);
          } else
          {
               throw new IllegalArgumentException("Unsupported item type");
          }
     }


     public Item readItemById(UUID itemId) {
          for (ItemElectronic item : itemElectronicList) {
               if (item.getItemId().equals(itemId)) {
                    return item;
               }
          }

          for (ItemClothes item : itemClothesList) {
               if (item.getItemId().equals(itemId)) {
                    return item;
               }
          }

          for (ItemArtCollectibles item : itemArtCollectiblesList) {
               if (item.getItemId().equals(itemId)) {
                    return item;
               }
          }

          return null;
     }



     public ItemElectronic readItemElectronicById(UUID itemId) {
          for (ItemElectronic item : itemElectronicList) {
               if (item.getItemId().equals(itemId)) {
                    return item;
               }
          }
          return null;
     }

     public ItemClothes readItemClothesById(UUID itemId) {
          for (ItemClothes item : itemClothesList) {
               if (item.getItemId().equals(itemId)) {
                    return item;
               }
          }
          return null;
     }

     public ItemArtCollectibles readItemArtCollectiblesById(UUID itemId) {
          for (ItemArtCollectibles item : itemArtCollectiblesList) {
               if (item.getItemId().equals(itemId)) {
                    return item;
               }
          }
          return null;
     }

     public List<ItemElectronic> getAllElectronicItems() {
          return new ArrayList<>(itemElectronicList);
     }

     public List<ItemArtCollectibles> getAllArtCollectiblesItems() {
          return new ArrayList<>(itemArtCollectiblesList);
     }

     public List<ItemClothes> getAllClothesItems() {
          return new ArrayList<>(itemClothesList);
     }

     public List<Item> getAllItems() {
     List<Item> allItems = new ArrayList<>();
     allItems.addAll(itemElectronicList);
     allItems.addAll(itemClothesList);
     allItems.addAll(itemArtCollectiblesList);
     return allItems;
}



}
