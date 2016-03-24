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


    public Bitmap bmp;
    Paint paint = new Paint();
    Map map;
    public int speed = 1000;
    public boolean isMoving;
    float time = 0.01f;

    float startX;
    float startY;
    float endX;
    float endY;
    float directionX;
    float directionY;
    float distance;

    int health = 100;

    public Player(float x, float y, Map map) {
        super(x, y);
        this.map = map;
    }

    @Override
    public void Update() {

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
        paint.setColor(Color.RED);
        paint.setTextSize(32);
        canvas.drawBitmap(bmp, x, y, null);
        canvas.drawText(Integer.toString(health), x, y - 50, paint);
    }

    public boolean CollisionWithTile(int x, int y){

        return ( map.GetTile(x, y).IsSolid());

    }

    public void SetSpeed(int speed){

        this.speed = speed;
    }

    public int GetHealth(){

        return this.health;
    }
    public void SetHealth(int health){

        this.health = health;
    }

    public void FindDistance(){

        distance = (float)Math.sqrt(Math.pow(endX - startX,2) + Math.pow(endY - startY,2));
        directionX = (endX - startX) / distance;
        directionY = (endY - startY) / distance;

    }

    public void FingerLift(){

        FindDistance();
        x = startX;
        y = startY;
        isMoving = true;
    }

    ////////////////////////////////////////////
    // Both PlayerX and PlayerY need editing. //
    // They only search for a corner per side //
    // going right - top right                //
    // going left - top left                  //
    // going up - top left                    //
    // going down - bottom left               //
    ////////////////////////////////////////////
    public void PlayerX(){

        if(directionX > 0){//going right
            int tempX = (int) ((x + directionX + bmp.getWidth()) / Tile.tileWidth);
            if(!CollisionWithTile(tempX, (int)y / Tile.tileHeight)) {
                x += directionX * speed * time;
            }
        }
        else if(directionX < 0){//going left
            int tempX = (int)((x + directionX) / Tile.tileWidth);
            if(!CollisionWithTile(tempX, (int)y / Tile.tileHeight)) {
                x += directionX * speed * time;
            }
        }
    }
    public void PlayerY(){

        if(directionY > 0){//going down
            int tempY = (int) ((y + directionY + bmp.getHeight()) / Tile.tileHeight);
            if(!CollisionWithTile((int)x / Tile.tileWidth, tempY)) {
                y += directionY * speed * time;
            }
        }
        else if(directionY < 0){//going down
            int tempY = (int) ((y + directionY) / Tile.tileHeight);
            if(!CollisionWithTile((int)x / Tile.tileWidth, tempY)) {
                y += directionY * speed * time;
            }
        }
    }


}
