package com.example.test7weeks;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.enssat.caronnantel.utilities.DataImporter;


public class EEGSignalsActivity extends AppCompatActivity {

    private  Map<Integer, Channels> eegsignals;



    public EEGSignalsActivity()  {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eegsignals);
        GraphView graph = (GraphView) findViewById(R.id.graph); // Get graph from layout
        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(); // form series (curve for graph)
        List<Channels> tempseries2 = null;
        try {
            tempseries2 = getSeries(0, 267619);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Channels c : tempseries2) {
            series1.appendData(new DataPoint(c.getTime(), c.getChannel1()), true, 267619);
        }


        // Enabling zoom!
        // set manual X bounds
        graphPlot(graph, series1);

    }

    private void graphPlot(GraphView graph, LineGraphSeries<DataPoint> series) {
        graph = graph;
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-60); //Change this for show
        graph.getViewport().setMaxY(60);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(5);

        // Enable or disable
        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true); // enables vertical scrolling
        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling


        graph.addSeries(series);

        // Graphical content
        // set color, title of curve, DataPoints radius, thickness
        //series.setColor(Color.rgb(16,172,132)); // graph color to red
        //series.setTitle("TP9"); // for legend
        series.setDrawDataPoints(false); // show datam points
        //series.setDataPointsRadius(16); // size data points
        series.setThickness(3); // thickness of graph

        // custom paint to make a dotted line
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.rgb(16,172,132));
        paint.setPathEffect(new DashPathEffect(new float[]{8, 5}, 0));
        series.setCustomPaint(paint);

        // Set title of graph
        graph.setTitle("TP9"); // set title of graph
        graph.setTitleTextSize(70); // Size the text
        graph.setTitleColor(Color.rgb(193,183,198)); // set color of title

        // axis titles
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Time (s)");
        gridLabel.setHorizontalAxisTitleTextSize(50);
        gridLabel.setVerticalAxisTitle("Amplitude"); //what units?
        gridLabel.setVerticalAxisTitleTextSize(50);
    }


    public List<Channels> getSeries(int start, int end) throws IOException {
        DataImporter importereeg = new DataImporter();
        eegsignals = importereeg.getEEG(this.getResources());
        List<Channels> series = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            Channels channel = eegsignals.get(i);
            series.add(channel);
        }
        return series;
    }

}

