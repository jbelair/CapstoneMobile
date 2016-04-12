package belair.worldmaptest;

/**
 * Created by M_Dez on 4/12/2016.
 */
public class Weapon extends Item{

    private String weaponName;
    private int damage;
    private int critChance;
    private int critDamage;
    private int range;
    private int value;
    private int id;
    private int quantity;

    public String getWeaponName(){
        return weaponName;
    }

    public int getDamage(){
        return damage;
    }

    public int getCritChance(){
        return critChance;
    }

    public int getCritDamage(){
        return critDamage;
    }

    public int getRange(){
        return range;
    }

    public int getValue(){
        return value;
    }

    public Weapon(String _weaponName, int _id, int _damage, int _critChance, int _critDamage, int _range, int _quantity, int _value){
        super(_weaponName, _id, _quantity, _value);
        this.weaponName = _weaponName;
        this.id = _id;
        this.damage = _damage;
        this.critChance = _critChance;
        this.critDamage = _critDamage;
        this.range = _range;
        this.quantity = _quantity;
        this.value = _value;
    }
}