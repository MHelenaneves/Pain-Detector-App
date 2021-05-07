package fr.enssat.caronnantel.utilities;

import android.content.res.Resources;
import android.util.Log;

import com.example.test7weeks.Channels;
import com.example.test7weeks.MainActivity;
import com.example.test7weeks.R;
import com.example.test7weeks.Time;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import fr.enssat.caronnantel.model.FrequencyBands;
import fr.enssat.caronnantel.model.PainNopain;

//import fr.enssat.caronnantel.model.Flower;
//import fr.enssat.caronnantel.model.IrisClass;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;
//import java.io.File;
//import java.lang.reflect.Field;
//import java.net.URI;
//import java.net.URL;
//import java.nio.charset.Charset;
//import java.util.Objects;



public class DataImporter extends MainActivity {
    private static final String TAG7 = "test1";
    private static final String TAG8 = "test2";
    private static final String TAG9 = "test3";
    private static final String TAG10 = "test4";
    private static final String TAG11 = "test5";


    private Map<FrequencyBands,PainNopain> importData(int fileName, Resources resources) throws IOException {
        //first we have to save the mat file into data/csv, put the file in the resources folder
        //fileName depends on which file we want to read


        InputStream is = resources.openRawResource(fileName);//now working
        //the files have to be in the raw folder, test is the name of the file
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        Map<FrequencyBands,PainNopain> data = new HashMap<>();
        try {
            // While the BufferedReader readLine is not null
            String readLine = null;
            while ((readLine = br.readLine()) != null) {//this will read for every line of the file
                String[] row =  readLine.split(",");   // create a list of strings, separated by characters(commas) - Split
                //Flower flower = new Flower();
                FrequencyBands frequencyBands= new FrequencyBands();

                // Warning: the order matter ! See the CSV file description
                /*parseDouble converts the strings into doubles
                we have 5 features (i think), we will have 5 columns + pain/no pain ML class
                flower.setSepalLength(Double.parseDouble(row[0])); //first columns
                flower.setSepalWidth(Double.parseDouble(row[1]));
                flower.setPetalLength(Double.parseDouble(row[2]));
                flower.setPetalWidth(Double.parseDouble(row[3]));
                data.put(flower, IrisClass.forValue(row[4]));//ML class*/

                frequencyBands.setAveragedelta(Double.parseDouble(row[0]));
                frequencyBands.setAveragetheta(Double.parseDouble(row[1]));
                frequencyBands.setAveragealpha(Double.parseDouble(row[2]));
                frequencyBands.setAveragebeta(Double.parseDouble(row[3]));
                frequencyBands.setAveragegamma(Double.parseDouble(row[4]));
                data.put(frequencyBands, PainNopain.forValue(row[5]));

            }
            // Close the InputStream and BufferedReader
            is.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public Map<FrequencyBands, PainNopain> getTrainingSet(Resources resources) throws IOException {
        return importData(R.raw.training, resources);
    }

   public Map<FrequencyBands, PainNopain> getTestSet(Resources resources) throws IOException {
        return importData(R.raw.testing, resources);
    }


    public Map<FrequencyBands, PainNopain> getRealTime(int count, Resources resources) throws IOException {
        //method choose file, call functions after choosing files
        //there are functions to choose the files from the same directory, maybe from an sd card
        //after choosing the files we get the predictions, figure out how we have to read the files to give the notifications every 5 min (files have different number of lines)

        int fileName = 0;

        switch(count){
            case 1:
                fileName=R.raw.sub1class0part1;//no pain
                Log.d(TAG7, " File changed" );
                System.out.println("File changed");
                break;

            case 2:
                fileName=R.raw.sub1class0part2;//no pain
                Log.d(TAG8, " File changed" );
                System.out.println("File changed");
                break;
            case 3:
                fileName=R.raw.sub2class0part1;//no pain
                Log.d(TAG9, " File changed" );
                System.out.println("File changed");
                break;
            case 4:
                fileName=R.raw.sub2class0part2; //no pain
                Log.d(TAG10, " File changed" );
                System.out.println("File changed");
                break;
            case 5:
                fileName=R.raw.sub1class1part1; //pain
                Log.d(TAG11, " File changed" );
                System.out.println("File changed");
                break;
            case 6:
                fileName=R.raw.sub1class1part2; //pain
                System.out.println("File changed");
                break;
            case 7:
                fileName=R.raw.sub2class1part1; //pain
                System.out.println("File changed");
                break;
            case 8:
                fileName=R.raw.sub2class1part2; //pain
                System.out.println("File changed");
                break;
            case 9:
                fileName=R.raw.sub3class1part1; //pain
                System.out.println("File changed");
                break;
            case 10:
                fileName=R.raw.sub3class1part2; //pain
                System.out.println("File changed");
                break;
            case 11:
                fileName=R.raw.sub4class1part1; //pain
                System.out.println("File changed");
                break;
            case 12:
                fileName=R.raw.sub4class1part2; //pain
                System.out.println("File changed");
                break;
            case 13:
                fileName=R.raw.sub3class0part1; //no pain
                System.out.println("File changed");
                break;
            case 14:
                fileName=R.raw.sub3class0part2; //no pain
                System.out.println("File changed");
                break;
            case 15:
                fileName=R.raw.sub4class0part1; //no pain
                System.out.println("File changed");
                break;
            case 16:
                fileName=R.raw.sub4class0part2; //no pain
                System.out.println("File changed");
                break;
        }

        return importData(fileName, resources);
    }


    public Map<Integer, Channels> importEEG(int fileName, Resources resources) throws IOException{
        InputStream is = resources.openRawResource(fileName);//now working
        //the files have to be in the raw folder
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        Map<Integer, Channels> signals = new HashMap<>();
        int i = 0;
        try {
            // While the BufferedReader readLine is not null
            String readLine = null;
            while ((readLine = br.readLine()) != null) {//this will read for every line of the file
                String[] row =  readLine.split(",");   // create a list of strings, separated by characters(commas) - Split
                Channels channels= new Channels();
                //Time timestamp = new Time();
                //timestamp.setTime(Double.parseDouble(row[0]));
                channels.setTime(Double.parseDouble(row[0]));
                channels.setChannel1(Double.parseDouble(row[1]));
                channels.setChannel2(Double.parseDouble(row[2]));
                channels.setChannel3(Double.parseDouble(row[3]));
                channels.setChannel4(Double.parseDouble(row[4]));
                signals.put(i , channels);
                i++;
            }
            // Close the InputStream and BufferedReader
            is.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return signals;

    }

    public Map<Integer, Channels> getEEG(Resources resources) throws IOException {
        return importEEG(R.raw.patient51, resources);
    }


}
