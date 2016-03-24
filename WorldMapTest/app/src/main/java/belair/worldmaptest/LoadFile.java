package belair.worldmaptest;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Justin on 3/16/2016.
 */
public class LoadFile {
    public static String LoadFileAsString(Context context, String path){

        StringBuilder stringBuilder = new StringBuilder();
        InputStream stream = context.getResources().openRawResource(context.getResources().getIdentifier("map", "raw", context.getPackageName() ));
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while((line = bufferedReader.readLine()) != null){

                stringBuilder.append(line +" \n");
            }
            bufferedReader.close();
        }
        catch(IOException e){

            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public static int ParseInt(String number){

        try{
            return Integer.parseInt(number);
        }
        catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }
}
