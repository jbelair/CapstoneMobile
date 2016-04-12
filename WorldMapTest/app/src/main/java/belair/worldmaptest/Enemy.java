package belair.worldmaptest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

/**
 * Created by Sean on 4/7/2016.
 */
public class Enemy extends Entity {
    private long lastTick, deltaTime;
    private Bitmap bmp;
    protected void setBmp(Bitmap _bmp){bmp = _bmp;}
    float temp = 0.0f, time = 0.01f;

    public Enemy(float x, float y){
        super(x, y);
        setSpeed(500);
        SetEntityHealthAttributes(100, 100, true, 1, 5);
        setExperience(48);
        if (getCanLevelUp())
        setCanLevelUp(false);
        setIsMoving(true);
    }

    @Override
    protected void Update() {
        if(getIsMoving()){
            setStartX(getX());
            setStartY(getY());

            FindDistance();
            UpdateEntityXY();

            if(Math.sqrt(Math.pow(getX() - getStartX(),2) + Math.pow(getY() - getStartY(),2)) >= getDistance()) {
                setX(getEndX());
                setY(getEndY());
                setStartX(getX());
                setStartY(getY());
                setIsMoving(false);
            }
        }

    }

    @Override
    protected void Render(Canvas canvas){
        getPaint().setTextSize(32);
        getPaint().setColor(Color.GREEN);
        canvas.drawBitmap(bmp, getX(), getY(), null);
        int temp = getHealth();
        canvas.drawText(Integer.toString(temp), getX(), getY() - 50, getPaint());
    }

    protected void Attack(){//might have to pass in the other entity that it is hitting

    }

    protected void Defend(){//Dont think anything has to be passed through :/ maybe an int for the damage that is in question

    }



}
