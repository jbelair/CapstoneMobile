package belair.worldmaptest;


import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Justin on 3/15/2016.
 * Modified by Sean on 4/5/2016.
 */
public abstract class Entity {

    protected float x, y;
    private int speed = 1000;
    public Entity(float x, float y) {

        this.x = x;
        this.y = y;
    }

    public void setSpeed(int speed){

        this.speed = speed;
    }
    protected int getSpeed(){
        return this.speed;
    }

    public abstract void Update();

    public abstract void Render(Canvas canvas);

    /**
     * Created by Sean on 4/5/2016.
     */
    //ALL THE HEALTH STUFF
    private int health = 100;
    private int maxHealth = 100;
    private boolean canRegen = true;
    //health per second
    private int regenAmount = 2;
    private float regenTime = 1000.0f / regenAmount;
    private float time;
    private boolean isAlive = true;
    //takes off that percent of damage;
    private int baseArmour = 5;
    private int prevBaseArmour = baseArmour;
    private float percentOffDamage = baseArmour / 100;

    protected boolean getIsAlive(){return this.isAlive;}
    protected void setIsAlive(boolean state){this.isAlive = state;}

    protected int getHealth() { return this.health; }
    protected void setHealth(int _health) { this.health = _health; }

    protected int getMaxHealth() { return this.maxHealth; }
    protected void setMaxHealth(int _maxHealth) { this.maxHealth = _maxHealth; }

    protected boolean getRegenability() { return this.canRegen; }
    protected void setRegenability(boolean _canRegen) { this.canRegen = _canRegen; }

    protected int getRegenAmount() { return this.regenAmount; }
    protected void setRegenAmount(int _regen) {
        this.regenAmount = _regen;
        regenTime = 1000.0f / regenAmount;
    }

    protected int getBaseArmour() { return this.baseArmour; }
    protected void setBaseArmour(int _newArmourVal) { this.baseArmour = _newArmourVal; }

    protected void SetEntityAttributes(int _health, int _maxHealth, boolean _canRegen, int _regenAmountPerSecond, int _baseArmour ){
        setHealth(_health);
        setMaxHealth(_maxHealth);
        setRegenability(_canRegen);
        setRegenAmount(_regenAmountPerSecond);
        setBaseArmour(_baseArmour);
    }

    boolean DecreaseHealth(int i) {
        if (isAlive) {
            if (this.prevBaseArmour != getBaseArmour()) {
                this.prevBaseArmour = getBaseArmour();
                this.percentOffDamage = getBaseArmour() / 100;
            }

            setHealth(getHealth() - (int) (i - (i * this.percentOffDamage)));
            if (getHealth() <= 0) {
                setHealth(0);
                this.isAlive = false;
            }
        }
        return this.isAlive;
    }

    void IncreaseHealth(int i) {
        if (this.isAlive) {
            if (getHealth() > getMaxHealth()) {
                setHealth(getMaxHealth());
            } else if (getHealth() < getMaxHealth()) {
                setHealth(getHealth() + i);
            }
        }
    }

    void PassiveRegen(float deltaTime) {
        if (getRegenability()) {
            this.time += deltaTime;
            if (this.time >= regenTime) {
                this.time = this.time % regenTime;
                IncreaseHealth(1);
            }
        }
    }
    /**
     * Created by Sean on 4/6/2016.
     */
    //ALL THE EXPERIENCE STUFF

    private int experience;
    private int level = 1;
    private int expToLevelUp = 10;
    private boolean canLevelUp = false;

    protected int getExperience(){
        return this.experience;
    }
    protected void setExperience(int _experience){
        this.experience = _experience;
    }

    protected int getLevel(){
        return this.level;
    }

    protected int getExpToLevelUp(){
        return this.expToLevelUp - this.experience;
    }

    protected void levelUp(){
        this.level += 1;
        this.expToLevelUp *= 10;
    }

    protected boolean getCanLevelUp(){
        return this.canLevelUp;
    }
    protected void setCanLevelUp(boolean _canlevelup){
        this.canLevelUp = _canlevelup;
    }





}
