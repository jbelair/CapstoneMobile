package belair.worldmaptest;

/**
 * Created by Justin on 4/4/2016.
 */
public class Log extends Item{

    public Log(String _itemName, int _id, int _quantity, int _value){
        super(_itemName, _id, _quantity, _value);
        this.itemName = "Log";
        this.id = 1;
        this.quantity = 1;
        this.value = 100;
    }
}
