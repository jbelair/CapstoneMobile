package belair.worldmaptest.Tile;

/**
 * Created by Justin on 3/17/2016.
 */

public class RiverTile extends Tile {
    public RiverTile(int id) {
        super(river, id);
    }

    @Override
    public boolean IsSolid(){

        return true;
    }

    @Override
    public boolean IsDamaging(){

        return true;
    }
}
