package belair.worldmaptest;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by M_Dez on 4/12/2016.
 */
public class Inventory {

    private ArrayList<Item> inventory = new ArrayList<Item>();
    private int inventorySize = 16;
    Item blankItem = new Item("null", 0,  0, 0);


    public Inventory() {
        inventory = new ArrayList<Item>(inventorySize);
        for (int i = 0; i < inventorySize; i++){
            inventory.add(i, blankItem);
        }
    }

    public void AddItem(Item item) {
        boolean isAdded = false;
        for (int i = 0; i < inventorySize; i++) {
            if (isAdded == false) {
                if (inventory.get(i).getId() != 0) {
                    if (inventory.get(i).getId() == item.getId()) {
                        inventory.get(i).setQuantity(inventory.get(i).getQuantity() + item.getQuantity());
                        isAdded = true;
                    }
                } else {
                    inventory.set(i, item);
                    isAdded = true;
                }
            }
        }
        isAdded = false;
    }

    public void RemoveItem(int index){
        inventory.remove(index);
        inventory.add(blankItem);
    }

    public String GetItemName(int index){
        return inventory.get(index).getItemName();
    }

    public int GetItemValue(int index){
        return inventory.get(index).getValue();
    }

    public int GetItemQuantity(int index){
        return inventory.get(index).getQuantity();
    }
    public void SetItemQuantity(int index, int quantity){
        inventory.get(index).setQuantity(inventory.get(index).getQuantity() + quantity);
    }

    public int SellItem(int index){
        int tempValue = inventory.get(index).getValue();
        SetItemQuantity(index, -1);
        if (inventory.get(index).getQuantity() == 0)
            RemoveItem(index);
        return tempValue;
    }

    public int SellItemStack(int index){
        int tempValue = inventory.get(index).getValue() * inventory.get(index).getQuantity();
        RemoveItem(index);
        return tempValue;
    }
}