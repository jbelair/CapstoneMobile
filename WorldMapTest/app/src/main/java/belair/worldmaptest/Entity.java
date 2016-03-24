package belair.worldmaptest;


import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Justin on 3/15/2016.
 */
public abstract class Entity {

    protected float x, y;

    public Entity(float x, float y){

        this.x = x;
        this.y = y;
    }

    public abstract void Update();
    public abstract void Render(Canvas canvas);
}
