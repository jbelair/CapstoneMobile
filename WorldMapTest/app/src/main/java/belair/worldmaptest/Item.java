package belair.worldmaptest;

/**
 * Created by M_Dez on 4/12/2016.
 */
public class Item{

    String itemName;
    int id;
    int quantity;
    int value;

    public Item(String _itemName, int _id, int _quantity, int _value){
        this.itemName = _itemName;
        this.id = _id;
        this.quantity = _quantity;
        this.value = _value;
    }

    public int getId(){
        return id;
    }

    public String getItemName(){
        return itemName;
    }

    public int getValue(){
        return value;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int value){
        quantity = value;
    }
}
