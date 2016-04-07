package belair.worldmaptest;

import android.graphics.Canvas;
import android.graphics.Color;

/**
 * Created by Sean on 4/7/2016.
 */
public class Enemy extends Entity {
    private long lastTick, deltaTime;
    float temp = 0.0f;

    public Enemy(float x, float y){
        super(x, y);

    }

    @Override
    protected void Update() {

        //An example of how the health works with incrementing and decrementing
        //values such as how much is regened each second can be changed with a set

        //deltaTime and lastTick used for updating the players health
        deltaTime = System.currentTimeMillis() - lastTick;
        lastTick = System.currentTimeMillis();
        temp += deltaTime;
        this.PassiveRegen(deltaTime);
        paint.setColor(Color.GREEN);

        if (temp >= 10000.0f) {
            this.DecreaseHealth(10);
            temp = temp % 10000.0f;
            paint.setColor(Color.RED);
        }


        /*startX = x;
        startY = y;

        FindDistance();

        PlayerX();
        PlayerY();


        if (Math.sqrt(Math.pow(x - startX, 2) + Math.pow(y - startY, 2)) >= distance) {
            x = endX;
            y = endY;
            startX = x;
            startY = y;
            isMoving = false;
        }*/

    }

    @Override
    protected void Render(Canvas canvas){

    }

}
