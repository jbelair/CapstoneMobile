package belair.worldmaptest;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Justin on 3/15/2016.
 * Modified by Sean on 4/5/2016.
 */
public abstract class Entity {

    Paint paint = new Paint();
    private float x, y;
    private int speed = 1000;
    private float startX, startY;
    private float endX, endY;
    private float directionX,directionY;
    private float distance;
    private boolean isMoving;

    public boolean isColliding = false;
    float radius;
    public Entity(float x, float y) {

        this.x = x;
        this.y = y;
    }

    protected float getX(){return this.x;}
    protected void setX(float _newX){this.x = _newX;}
    protected float getY(){return this.y;}
    protected void setY(float _newY){this.y = _newY;}

    protected float getStartX(){return this.startX;}
    protected void setStartX(float _newStartX){this.startX = _newStartX;}
    protected float getStartY(){return this.startY;}
    protected void setStartY(float _newStartY){this.startY = _newStartY;}

    protected float getEndX(){return this.endX;}
    protected void setEndX(float _newEndX){this.endX = _newEndX;}
    protected float getEndY(){return this.endY;}
    protected void setEndY(float _newEndY){this.endY = _newEndY;}

    protected float getDirectionX(){return this.directionX;}
    protected void setDirectionX(float _newDirectionX){this.directionX = _newDirectionX;}
    protected float getDirectionY(){return this.directionY;}
    protected void setDirectionY(float _newDirectionY){this.directionY = _newDirectionY;}

    protected float getDistance(){return this.distance;}
    protected void setDistance(float _newDistance){this.distance = _newDistance;}

    protected boolean getIsMoving(){return this.isMoving;}
    protected void setIsMoving(boolean _state){this.isMoving = _state;}

    public void setSpeed(int speed){

        this.speed = speed;
    }
    protected int getSpeed(){
        return this.speed;
    }

    protected abstract void Update();

    protected abstract void Render(Canvas canvas);

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

    protected void SetEntityHealthAttributes(int _health, int _maxHealth, boolean _canRegen, int _regenAmountPerSecond, int _baseArmour ){
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

    public void CircleCircleCollision(float otherX, float otherY, float otherRadius){

        float distance = (float)Math.sqrt(((getX() - otherX) * (getX() - otherX)) + ((getY() - otherY) * (getY() - otherY)));
        if(distance < radius + otherRadius){
            isColliding = true;
        }
        else{
            isColliding = false;
        }
    }
}
