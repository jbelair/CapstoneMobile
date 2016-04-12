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
    private float startX = 0, startY = 0;
    private float endX = 0, endY = 0;
    private float directionX = 0, directionY = 0;
    private float distance = 0;
    private boolean isMoving = false;

    private boolean isColliding = false;
    private float radius;

    public Entity(float _x, float _y) {
        this.x = _x;
        this.y = _y;
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
    protected void setEnd(float _x, float _y){
        setEndX(_x);
        setEndY(_y);
    }

    protected Paint getPaint(){return this.paint;}

    protected float getDirectionX(){return this.directionX;}
    protected void setDirectionX(float _newDirectionX){this.directionX = _newDirectionX;}
    protected float getDirectionY(){return this.directionY;}
    protected void setDirectionY(float _newDirectionY){this.directionY = _newDirectionY;}

    protected float getDistance(){return this.distance;}
    protected void setDistance(float _newDistance){this.distance = _newDistance;}

    protected boolean getIsMoving(){return this.isMoving;}
    protected void setIsMoving(boolean _state){this.isMoving = _state;}

    public void setSpeed(int speed){this.speed = speed;}
    protected int getSpeed(){return this.speed;}

    protected boolean getIsColliding(){return this.isColliding;}
    protected void setIsColliding(boolean state){this.isColliding = state;}

    protected float getRadius(){return this.radius;}
    protected void setRadius(float _newRadius){this.radius = _newRadius;}

    ////////////////////
    ////////////////////
    ///// ABSTRACT /////
    ////////////////////
    ////////////////////
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
    private float time = 0.01f;
    private boolean isAlive = true;
    //takes off that percent of damage;
    private int baseArmour = 5;
    private int prevBaseArmour = baseArmour;
    private float percentOffDamage = baseArmour / 100;

    protected boolean getIsAlive(){return this.isAlive;}
    protected void setIsAlive(boolean state){this.isAlive = state;}

    protected int getHealth() {
        if (this.health <= getMaxHealth() * 0.2f) {
            this.paint.setColor(Color.RED);
        }
        else if (this.health <= getMaxHealth() * 0.5f){
            this.paint.setColor(Color.YELLOW);
        }
        return this.health;
    }
    protected void setHealth(int _health) { this.health = _health; }

    protected int getMaxHealth() { return this.maxHealth; }
    protected void setMaxHealth(int _maxHealth) { this.maxHealth = _maxHealth; }

    protected boolean getRegenability() { return this.canRegen; }
    protected void setRegenability(boolean _canRegen) { this.canRegen = _canRegen; }

    protected int getRegenAmount() { return this.regenAmount; }
    protected void setRegenAmount(int _regen) {
        this.regenAmount = _regen;
        this.regenTime = 1000.0f / this.regenAmount;
    }

    protected int getBaseArmour() { return this.baseArmour; }
    protected void setBaseArmour(int _newArmourVal) { this.baseArmour = _newArmourVal; }

    protected void SetEntityHealthAttributes(int _health, int _maxHealth, boolean _canRegen, int _regenAmountPerSecond, int _baseArmour ){
        setHealth(_health);
        setMaxHealth(_maxHealth);
        setRegenability(_canRegen);
        setRegenAmount(_regenAmountPerSecond);
        setBaseArmour(_baseArmour);
        setIsAlive(true);
    }

    boolean DecreaseHealth(int i) {
        if (getIsAlive()) {
            if (this.prevBaseArmour != getBaseArmour()) {
                this.prevBaseArmour = getBaseArmour();
                this.percentOffDamage = getBaseArmour() / 100;
            }

            setHealth(getHealth() - (int)(i - (i * this.percentOffDamage)));
            this.paint.setColor(Color.RED);
            if (getHealth() <= 0) {
                setHealth(0);
                setIsAlive(false);
            }
        }
        return getIsAlive();
    }

    void IncreaseHealth(int i) {
        if (getIsAlive()) {
            if (getHealth() > getMaxHealth()) {
                setHealth(getMaxHealth());
            } else if (getHealth() < getMaxHealth()) {
                setHealth(getHealth() + i);
            }
            this.paint.setColor(Color.GREEN);
        }
    }

    void PassiveRegen(float deltaTime) {
        if (getRegenability()) {
            this.time += deltaTime;
            if (this.time >= this.regenTime) {
                this.time = this.time % this.regenTime;
                IncreaseHealth(1);
            }
        }
    }
    /**
     * Created by Sean on 4/6/2016.
     */
    //ALL THE EXPERIENCE STUFF

    private int experience = 0;
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


    /*
    * Justin
    */
    protected void CircleCircleCollision(float otherX, float otherY, float otherRadius){

        float distance = (float)Math.sqrt(((getX() - otherX) * (getX() - otherX)) + ((getY() - otherY) * (getY() - otherY)));
        if(distance < getRadius() + otherRadius){
            setIsColliding(true);
        }
        else{
            setIsColliding(false);
        }
    }

    protected void FindDistance(){
        setDistance((float)Math.sqrt(Math.pow(getEndX() - getStartX(),2) + Math.pow(getEndY() - getStartY(),2)));

        setDirectionX((getEndX() - getStartX()) / getDistance());
        setDirectionY((getEndY() - getStartY()) / getDistance());


        if(getDistance() <= 20){
            setDirectionX(0);
            setDirectionY(0);
        }

    }

    protected void UpdateEntityXY(){
        EntityX();
        EntityY();
    }

    protected void EntityX(){
        if (getDirectionX() > 0 || getDirectionX() < 0){
            setX(getX() + getDirectionX() * getSpeed() * time);
        }
    }

    protected void EntityY() {
        if (getDirectionY() > 0 || getDirectionY() < 0) {
            setY(getY() + getDirectionY() * getSpeed() * time);
        }
    }


}
