package belair.worldmaptest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    Paint paint = new Paint();
    Map map;
    public boolean isMoving;
    float time = 0.01f;

    float startX;
    float startY;
    float endX;
    float endY;
    float directionX;
    float directionY;
    float distance;

    float temp = 0.0f;

    public Player(float x, float y, Map map) {
        super(x, y);
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
    public void Update() {
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


        if(isMoving){
            startX = x;
            startY = y;

            FindDistance();

            PlayerX();
            PlayerY();


            if(Math.sqrt(Math.pow(x - startX,2) + Math.pow(y - startY,2)) >= distance)
            {
                x = endX;
                y = endY;
                startX = x;
                startY = y;
                isMoving = false;
            }
        }
    }

    @Override
    public void Render(Canvas canvas) {
        paint.setTextSize(32);
        canvas.drawBitmap(GetCurrentAnimationFrame(), x, y, null);
        int temp = getHealth();
        canvas.drawText(Integer.toString(temp), x, y - 50, paint);
    }

    public boolean CollisionWithTile(int x, int y){

        return ( map.GetTile(x, y).IsSolid());

    }

    private Bitmap GetCurrentAnimationFrame(){

        if(directionX > 0){//going right
            return walkRight.GetCurrentFrame();
        }
        else if(directionX < 0){//going left
            return walkLeft.GetCurrentFrame();
         }
        if(directionY > 0) {//going down
            return walkDown.GetCurrentFrame();
        }
        else if(directionY < 0) {//going up
            return walkDown.GetCurrentFrame();
        }
        if(directionX == 0 && directionY == 0){
            return idle.GetCurrentFrame();
        }
        else{

            return idle.GetCurrentFrame();
        }
    }



    public void FindDistance(){

        distance = (float)Math.sqrt(Math.pow(endX - startX,2) + Math.pow(endY - startY,2));
        directionX = (endX - startX) / distance;
        directionY = (endY - startY) / distance;

        if(distance <= 20){

            directionX = 0;
            directionY = 0;
        }

    }

    public void FingerLift(){

        FindDistance();
        x = startX;
        y = startY;
        isMoving = true;
    }

    public void PlayerX(){

        if(directionX > 0){//going right
            int tempX = (int) ((x + directionX + bmp.getWidth()) / Tile.tileWidth);

            if(!CollisionWithTile(tempX, (int)y / Tile.tileHeight) &&
                    !CollisionWithTile(tempX, (int)(y + bmp.getHeight()) / Tile.tileHeight)) {
                x += directionX * getSpeed() * time;
            }
        }
        else if(directionX < 0){//going left
            int tempX = (int)((x + directionX) / Tile.tileWidth);
            if(!CollisionWithTile(tempX, (int)y / Tile.tileHeight) &&
                    !CollisionWithTile(tempX, (int)(y + bmp.getHeight()) / Tile.tileHeight)) {
                x += directionX * getSpeed() * time;
            }
        }
    }
    public void PlayerY(){

        if(directionY > 0){//going down
            int tempY = (int) ((y + directionY + bmp.getHeight()) / Tile.tileHeight);
            if(!CollisionWithTile((int)x / Tile.tileWidth, tempY) &&
                    !CollisionWithTile((int)(x + bmp.getWidth()) / Tile.tileWidth, tempY)) {

                y += directionY * getSpeed() * time;
            }
        }
        else if(directionY < 0){//going down
            int tempY = (int) ((y + directionY) / Tile.tileHeight);
            if(!CollisionWithTile((int)x / Tile.tileWidth, tempY) &&
                    !CollisionWithTile((int)(x + bmp.getWidth()) / Tile.tileWidth, tempY)) {

                y += directionY * getSpeed() * time;
            }
        }
    }


}
