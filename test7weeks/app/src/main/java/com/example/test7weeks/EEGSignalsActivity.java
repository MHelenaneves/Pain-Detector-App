package com.example.test7weeks;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Map;


public class EEGSignalsActivity extends AppCompatActivity {

    private Map<Time, Channels> eegsignals;

    public void setEEGSignals(Map<Time, Channels> eegsignals) {
        this.eegsignals = eegsignals;
    }

    /*public EEGSignalsActivity(Resources resources) throws IOException {
        DataImporter importereeg = new DataImporter();
        Map<Time, Channels> eegsignals = importereeg.getEEG(resources);

    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eegsignals);
        //getX();
        // Get graph from layout
        GraphView graph =(GraphView) findViewById(R.id.graph);

        // form series (curve for graph)
        LineGraphSeries<DataPoint> series =
                new LineGraphSeries<>();
        double y;
        for (int x=0;x<150; x++){
            y= Math.sin(2*x*0.2)-Math.sin(x*0.2);
            series.appendData(new DataPoint(x,y),true, 150);
        }

        /*BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, -2),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });*/


        // Enabling zoom!
        // set manual X bounds
        graph = graph;
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-15); //Change this for show
        graph.getViewport().setMaxY(10);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(4);
        graph.getViewport().setMaxX(80);

        // Enable or disable
        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true); // enables vertical scrolling
        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling

        //graph.addSeries(series2); //HUSK AT UDKOMMENTER DETTE
        graph.addSeries(series);

        // Graphical content
        // set color, title of curve, DataPoints radius, thickness
        series.setColor(Color.RED); // graph color to red
        series.setTitle("Test curve"); // for legend
        series.setDrawDataPoints(true); // show datam points
        series.setDataPointsRadius(16); // size data points
        series.setThickness(8); // thickness of graph

        // Set title of graph
        graph.setTitle("Test curve"); // set title of graph
        graph.setTitleTextSize(90); // Size the text
        graph.setTitleColor(Color.BLUE); // set color of title

        // legend
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);

        // axis titles
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("X Axis title");
        gridLabel.setHorizontalAxisTitleTextSize(50);
        gridLabel.setVerticalAxisTitle("Y Axis title");
        gridLabel.setVerticalAxisTitleTextSize(50);

    }


/*    public  getX(){

        Time timestamp = new Time();
        timestamp.setTime();
        List<Time> x = new ArrayList<>();

        for (Map.Entry<Time, Channels> entry : eegsignals.entrySet()) {
            Time key = entry.getKey();
            Channels channels = entry.getValue();
        }
        return channels;

        Collection<Channels> signalValues = eegsignals.values();
        Double channelSeries = eegsignals.getValue();


    }*/



}
