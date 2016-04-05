package belair.worldmaptest;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Justin on 4/4/2016.
 */
public class Log extends Entity{

    public Bitmap logImage;

    public Log(float x, float y){
        super(x, y);


    }
    @Override
    public void Update() {

    }

    @Override
    public void Render(Canvas canvas) {
        canvas.drawBitmap(logImage, x, y, null);
    }
}
