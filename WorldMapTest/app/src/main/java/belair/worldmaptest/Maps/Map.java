package belair.worldmaptest.Maps;
import android.content.Context;
import android.graphics.Canvas;
import java.util.Random;
import belair.worldmaptest.LoadFile;
import belair.worldmaptest.Tile.Tile;

/**
 * Created by Justin on 3/16/2016.
 */
public class Map {

    private int width, height;
    public int spawnX, spawnY;
    private int[][] mapXY;
    private Random rand = new Random();

    public Map(Context context, String path){

        LoadWorld(context, path);

    }

    public void Update(){

    }

    public Tile GetTile(int x, int y){


        Tile tileObject = Tile.tileArray[mapXY[x][y]];
        if(tileObject == null){

            return Tile.forestTile;
        }
        return tileObject;
    }

    public void Draw(Canvas canvas){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++) {

                GetTile(x, y).Draw(canvas,(x * Tile.tileWidth), (y * Tile.tileHeight));

            }
        }
    }

    private void LoadWorld(Context context, String path){

        String file = LoadFile.LoadFileAsString(context, path);
        String[] numbersInFile = file.split("\\s+");

        width = LoadFile.ParseInt(numbersInFile[0]);
        height = LoadFile.ParseInt(numbersInFile[1]);

        spawnX = LoadFile.ParseInt(numbersInFile[2]);
        spawnY = LoadFile.ParseInt(numbersInFile[3]);

        mapXY = new int[width][height];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                mapXY[x][y] = LoadFile.ParseInt(numbersInFile[(x + y * width) + 4]);

            }

        }
        /*//////////////////// Random spawn for map ////////////////////
        width = 10;
        height = 20;
        mapXY = new int[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                mapXY[x][y] = rand.nextInt(3);
            }
        }*/
    }
}
