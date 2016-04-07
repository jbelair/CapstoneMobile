package belair.worldmaptest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import belair.worldmaptest.Maps.Map;
import belair.worldmaptest.Tile.Tile;

/**
 * Created by Justin on 3/15/2016.
 */

public class Player extends Entity {

    //Animations
    private Animation walkDown, walkUp, walkLeft, walkRight, idle;
    private long lastTick, deltaTime;

    public Bitmap bmp;
    public Bitmap[] animWalkDown = new Bitmap[2];
    public Bitmap[] animWalkUp = new Bitmap[2];
    public Bitmap[] animWalkLeft = new Bitmap[2];
    public Bitmap[] animWalkRight = new Bitmap[2];
    public Bitmap[] animIdle = new Bitmap[2];

    Map map;
    float time = 0.01f;
    float temp = 0.0f;

    public Player(float x, float y, Map map) {
        super(x, y);
        ///FOR COLLISION
        this.map = map;
        this.setMaxHealth(200);
        this.setHealth(200);

        walkDown = new Animation(500, animWalkDown);
        walkUp = new Animation(500, animWalkUp);
        walkLeft = new Animation(500, animWalkLeft);
        walkRight = new Animation(500, animWalkRight);
        idle = new Animation(500, animIdle);
        lastTick = System.currentTimeMillis();
    }

    @Override
    protected void Update() {
        ////////////////
        // NEEDS WORK //
        ////////////////

        walkDown.Update();
        walkUp.Update();
        walkLeft.Update();
        walkRight.Update();
        idle.Update();

        //An example of how the health works with incrementing and decrementing
        //values such as how much is regened each second can be changed with a set

        //deltaTime and lastTick used for updating the players health
        deltaTime = System.currentTimeMillis() - lastTick;
        lastTick = System.currentTimeMillis();
        temp += deltaTime;
        this.PassiveRegen(deltaTime);
        paint.setColor(Color.GREEN);

        if (temp >=10000.0f){
            this.DecreaseHealth(10);
            temp = temp % 10000.0f;
            paint.setColor(Color.RED);
        }


        if(getIsMoving()){
            setStartX(getX());
            setStartY(getY());

            FindDistance();

            PlayerX();
            PlayerY();


            if(Math.sqrt(Math.pow(getX() - getStartX(),2) + Math.pow(getY() - getStartY(),2)) >= getDistance())
            {
                setX(getEndX());
                setY(getEndY());
                setStartX(getX());
                setStartY(getY());
                setIsMoving(false);
            }
        }
    }

    @Override
    protected void Render(Canvas canvas) {
        paint.setTextSize(32);
        canvas.drawBitmap(GetCurrentAnimationFrame(), getX(), getY(), null);
        int temp = getHealth();
        canvas.drawText(Integer.toString(temp), getX(), getY() - 50, paint);
    }

    public boolean CollisionWithTile(int x, int y){
        return ( map.GetTile(x, y).IsSolid());
    }

    private Bitmap GetCurrentAnimationFrame(){

        if(getDirectionX() == 0 && getDirectionY() == 0){
            return idle.GetCurrentFrame();
        }
        else if (getDirectionX() > 0 && getDirectionX() > getDirectionY()){
            return walkRight.GetCurrentFrame();
        }
        else if (getDirectionY() < 0 && getDirectionX() < getDirectionY()){
            return walkLeft.GetCurrentFrame();
        }
        else if (getDirectionY() > 0 && getDirectionY() > getDirectionX()){
            return walkDown.GetCurrentFrame();
        }
        /////////
        // BUG //
        /////////
        else if (getDirectionY() < 0 && getDirectionY() < getDirectionX()){
            return walkUp.GetCurrentFrame();
        }
        else{
            return idle.GetCurrentFrame();
        }
    }

    public void FindDistance(){
        setDistance((float)Math.sqrt(Math.pow(getEndX() - getStartX(),2) + Math.pow(getEndY() - getStartY(),2)));

        setDirectionX((getEndX() - getStartX()) / getDistance());
        setDirectionY((getEndY() - getStartY()) / getDistance());


        if(getDistance() <= 20){
            setDirectionX(0);
            setDirectionY(0);
        }

    }

    public void FingerLift(){

        FindDistance();
        setX(getStartX());
        setY(getStartY());
        setIsMoving(true);
    }

    public void PlayerX(){

        if(getDirectionX() > 0){//going right
            int tempX = (int) ((getX() + getDirectionX() + bmp.getWidth()) / Tile.tileWidth);

            if(!CollisionWithTile(tempX, (int)getY() / Tile.tileHeight) &&
                    !CollisionWithTile(tempX, (int)(getY() + bmp.getHeight()) / Tile.tileHeight)) {
                setX(getX() + getDirectionX() * getSpeed() * time);
            }
        }
        else if(getDirectionX() < 0){//going left
            int tempX = (int)((getX() + getDirectionX()) / Tile.tileWidth);
            if(!CollisionWithTile(tempX, (int)getY() / Tile.tileHeight) &&
                    !CollisionWithTile(tempX, (int)(getY() + bmp.getHeight()) / Tile.tileHeight)) {
                setX(getX() + getDirectionX() * getSpeed() * time);
            }
        }
    }

    public void PlayerY(){

        if(getDirectionY() > 0){//going down
            int tempY = (int) ((getY() + getDirectionY() + bmp.getHeight()) / Tile.tileHeight);
            if(!CollisionWithTile((int)getX() / Tile.tileWidth, tempY) &&
                    !CollisionWithTile((int)(getX() + bmp.getWidth()) / Tile.tileWidth, tempY)) {
                setY(getY() + getDirectionY() * getSpeed() * time);
            }
        }
        else if(getDirectionY() < 0){//going down
            int tempY = (int) ((getY() + getDirectionY()) / Tile.tileHeight);
            if(!CollisionWithTile((int)getX() / Tile.tileWidth, tempY) &&
                    !CollisionWithTile((int)(getX() + bmp.getWidth()) / Tile.tileWidth, tempY)) {
                setY(getY() + getDirectionY() * getSpeed() * time);
            }
        }
    }


}
