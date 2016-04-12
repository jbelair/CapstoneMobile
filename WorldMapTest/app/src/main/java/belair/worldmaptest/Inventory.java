package belair.worldmaptest;

import java.util.ArrayList;

/**
 * Created by M_Dez on 4/12/2016.
 */
public class Inventory {

    private ArrayList<Item> inventory = new ArrayList<Item>();
    private int inventorySize = 16;


    public Inventory() {
        inventory = new ArrayList<Item>(inventorySize);
    }

    public void AddItem(Item item){

        //  Tried this. Blew up.
//        for (int i = 0; i < inventorySize; i++){
//            if (inventory.get(i).getId() == item.getId()){
//                inventory.get(i).setQuantity(inventory.get(i).getQuantity() + item.getQuantity());
//                added = true;
//            }
//        }
        inventory.add(item);
    }

    public String GetItemName(int value){
        return inventory.get(value).getItemName();
    }

    public int GetItemValue(int value){
        return inventory.get(value).getValue();
    }

    public int GetItemQuantity(int value){
        return inventory.get(value).getQuantity();
    }

}