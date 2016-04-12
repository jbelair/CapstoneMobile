package belair.worldmaptest;

/**
 * Created by M_Dez on 4/12/2016.
 */
public class Consumable extends Item{

    private String consumableName;
    private int id;
    private int quantity;
    private int value;


    public Consumable(String _consumableName, int _id, int _quantity, int _value){
        super(_consumableName, _id, _quantity, _value);
        this.consumableName = _consumableName;
        this.id = _id;
        this.quantity = _quantity;
        this.value = _value;
    }
}