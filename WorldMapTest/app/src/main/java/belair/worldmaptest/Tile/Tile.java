package belair.worldmaptest.Tile;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Justin on 3/15/2016.
 */
public class Tile {

    public static Tile[] tileArray = new Tile[64];
    public static Tile grassTile = new GrassTile(0);
    public static Tile forestTile = new ForestTile(1);
    public static Tile riverTile = new RiverTile(2);

    public static final int tileWidth = 192;
    public static final int tileHeight = 192;

    public static Bitmap bmp;
    public static Bitmap grass;
    public static Bitmap forest;
    public static Bitmap river;
    protected Paint paint;
    protected final int id;


    public Tile(Bitmap bmp, int id){
        this.bmp = bmp;
        this.id = id;
        tileArray[id] = this;

    }

    public void Update(){


    }

    public void Draw(Canvas canvas, int x, int y){
        if(this.id == 0) {
            canvas.drawBitmap(grass, x, y, paint);
        }
        else if(this.id == 1){

            canvas.drawBitmap(forest, x, y, paint);
        }
        else if(this.id == 2){
            canvas.drawBitmap(river, x, y, paint);

        }
    }

    public int GetID(){

        return id;
    }

    public boolean IsSolid(){

        return false;
    }

    public boolean IsDamaging(){

        return false;
    }
}
