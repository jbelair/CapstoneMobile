package belair.worldmaptest.Tile;

import android.graphics.Bitmap;

/**
 * Created by Justin on 3/15/2016.
 */
public class ForestTile extends Tile {
    public ForestTile(int id) {
        super(forest, id);
    }

    @Override
    public boolean IsSolid(){

        return true;
    }
}
