package belair.worldmaptest;

import android.graphics.Bitmap;

import java.sql.SQLSyntaxErrorException;

/**
 * Created by Justin on 4/4/2016.
 */
public class Animation {
    private int index, speed;
    private long lastTick, timer;
    private Bitmap[] frames;

    public Animation(int speed, Bitmap[] frames){

        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTick = System.currentTimeMillis();
    }

    public void Update(){
        timer += System.currentTimeMillis() - lastTick;
        lastTick = System.currentTimeMillis();
        if(timer > speed){

            index++;
            timer = 0;

            if(index >= frames.length){

                index = 0;
            }
        }
    }

    public Bitmap GetCurrentFrame(){
        return frames[index];
    }
}
