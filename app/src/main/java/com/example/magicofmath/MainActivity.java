package com.example.magicofmath;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int pointAx = 0;
    int pointAy = 0;
    int pointBx = 500;
    int pointBy = 20000;
    int pointCx = 10000;
    int pointCy = 10000;
    int pointStartX = 50;
    int pointStartY = 50;
    int finalPointX;
    int finalPointY;
    // int mainSize = 200;
    private SeekBar seekBar;

    ArrayList<Integer> arrayListPointsX = new ArrayList<>();
    ArrayList<Integer> arrayListPointsY = new ArrayList<>();
    ArrayList<Integer> arrayListPointsXsorted = new ArrayList<>();
    MyPoint myPoint = new MyPoint();
    ArrayList<MyPoint> myPointArrayList = new ArrayList<>();
private     GraphView graph;
    private  PointsGraphSeries<DataPoint> series;
    private  PointsGraphSeries<DataPoint> series2;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSeekBarView();
        initBL();
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initView() {
        createGrapg2appendData();

    }

    private void initSeekBarView() {
        seekBar = findViewById(R.id.seekBar);
        seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                arrayListPointsX.clear();
                arrayListPointsY.clear();
                myPointArrayList.clear();
                pointBx= progress;
                createPointsForFractal();
                createGrapg2resetData();
                graph.getSecondScale().addSeries(series2);

               // initBL();
              //  initView();
              //  Toast.makeText(MainActivity.this, "" + progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sort() {
        Comparator<MyPoint> byPointx = Comparator.comparing(MyPoint::getPointXcoordinate);
        Collections.sort(myPointArrayList, byPointx);
        //  Collections.sort(arrayListPointsX);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createGrapg2appendData() {
        int x = 0, y = 0;
        graph = (GraphView) findViewById(R.id.graph);
        sort();
        series = new PointsGraphSeries<DataPoint>();
        for (int i = 0; i < arrayListPointsX.size(); i++) {
            //  x = arrayListPointsX.get(i);      y = arrayListPointsY.get(i);
            //  x=  myPoint.getPointXcoordinate();  y=myPoint.getPointYcoordinate();
            x = myPointArrayList.get(i).getPointXcoordinate() - 1;
            y = myPointArrayList.get(i).getPointYcoordinate() - 1;
            series.appendData(new DataPoint(x, y), true, 999999999);
        }
        graph.addSeries(series);
        graph.getLegendRenderer().setWidth(40000);
        //
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(getMaxPoints(pointAx, pointBx, pointCx));
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(getMaxPoints(pointAy, pointBy, pointCy));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createGrapg2resetData() {
        int x = 0, y = 0;
        sort();
        series2 = new PointsGraphSeries<DataPoint>();
        for (int i = 0; i < arrayListPointsX.size(); i++) {
            //  x = arrayListPointsX.get(i);      y = arrayListPointsY.get(i);
            //  x=  myPoint.getPointXcoordinate();  y=myPoint.getPointYcoordinate();
            x = myPointArrayList.get(i).getPointXcoordinate() - 1;
            y = myPointArrayList.get(i).getPointYcoordinate() - 1;
           // series2.resetData(data());
            series2.appendData(new DataPoint(x, y), true, 999999999);

        }
      //  graph.addSeries(series2);
        graph.getLegendRenderer().setWidth(40000);
        //
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(getMaxPoints(pointAx, pointBx, pointCx));
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(getMaxPoints(pointAy, pointBy, pointCy));

    }

    private int getMaxPoints(int pointA, int pointB, int pointC) {
        ArrayList<Integer> myLocalArrayList = new ArrayList<>();
        myLocalArrayList.add(pointA);
        myLocalArrayList.add(pointB);
        myLocalArrayList.add(pointC);

        return Collections.max(myLocalArrayList);
    }

    private void initBL() {
        createPointsForFractal();

    }


    private void createGrapg() {
        GraphView graph = (GraphView) findViewById(R.id.graph);
        DataPoint[] dp = new DataPoint[arrayListPointsX.size()];
        List<DataPoint> values = new ArrayList<DataPoint>();
        for (int i = 0; i < arrayListPointsX.size(); i++) {
            values.add(new DataPoint(arrayListPointsX.get(i), arrayListPointsY.get(i)));

        }
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>();

        graph.addSeries(series);
        //    new DataPoint(3, 2),


    }

    public DataPoint[] data() {
        int x = 0, y = 0;

        DataPoint[] values = new DataPoint[arrayListPointsX.size()];

            for (int i = 0; i < arrayListPointsX.size(); i++) {
                //  x = arrayListPointsX.get(i);      y = arrayListPointsY.get(i);
                //  x=  myPoint.getPointXcoordinate();  y=myPoint.getPointYcoordinate();
                x = myPointArrayList.get(i).getPointXcoordinate() - 1;
                y = myPointArrayList.get(i).getPointYcoordinate() - 1;
                DataPoint v = new DataPoint(x, y);
                values[i] = v;
        }
        return values;
    }

    private void createPointsForFractal() {


        arrayListPointsX.add(pointStartX);
        arrayListPointsY.add(pointStartY);
        myPointArrayList.add(new MyPoint(0, pointStartX, pointStartY));
        for (int i = 0; i < 5000; i++) {
            int localRandom = getRandomNumberInRange(1, 3);
            int localResX = (arrayListPointsX.get(i) + getFinalPointX(localRandom)) / 2;
            int localResY = (arrayListPointsY.get(i) + getFinalPointY(localRandom)) / 2;
            arrayListPointsX.add(localResX);
            arrayListPointsY.add(localResY);

            myPointArrayList.add(new MyPoint(i + 1, localResX, localResY));
        }
     /*   arrayListPointsX.add(pointAx);
        arrayListPointsX.add(pointBx);
        arrayListPointsX.add(pointCx);
        arrayListPointsY.add(pointAy);
        arrayListPointsY.add(pointBy);
        arrayListPointsY.add(pointCy);

      */
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private int getFinalPointX(int randomWayToFinalPoint) {
        if (randomWayToFinalPoint == 1) {
            finalPointX = pointAx;
        } else if (randomWayToFinalPoint == 2) {
            finalPointX = pointBx;
        } else if (randomWayToFinalPoint == 3) {
            finalPointX = pointCx;
        }
        return finalPointX;
    }

    private int getFinalPointY(int randomWayToFinalPoint) {
        if (randomWayToFinalPoint == 1) {
            finalPointY = pointAy;
        } else if (randomWayToFinalPoint == 2) {
            finalPointY = pointBy;
        } else if (randomWayToFinalPoint == 3) {
            finalPointY = pointCy;
        }
        return finalPointY;
    }


}
