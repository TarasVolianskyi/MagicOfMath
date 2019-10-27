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

    double a = 0.0;
    double b = 0.0;
    double c = 0.0;
    double d = 0.0;
    double e = 0.0;
    double f = 0.0;
    double p = 0.0;


    // int mainSize = 200;
    private SeekBar seekBar;
    private SeekBar seekBarPaporot;


    private ArrayList<Integer> arrayListPointsX = new ArrayList<>();
    private ArrayList<Integer> arrayListPointsY = new ArrayList<>();
    private ArrayList<Integer> arrayListPointsXsorted = new ArrayList<>();
    private MyPoint myPoint = new MyPoint();
    private MyPointPaporot myPointPaporot = new MyPointPaporot();
    private ArrayList<MyPoint> myPointArrayList = new ArrayList<>();
    private ArrayList<MyPointPaporot> myPointArrayListPaporot = new ArrayList<>();
    private GraphView graph;
    private GraphView graphPaporot;
    private PointsGraphSeries<DataPoint> series;
    private PointsGraphSeries<DataPoint> series2;
    private PointsGraphSeries<DataPoint> seriesPaporot;
    private PointsGraphSeries<DataPoint> series2Paporot;
    private Constants myConstants = new Constants();

    private ArrayList<Double> arrayListPointsXpaporot = new ArrayList<>();
    private ArrayList<Double> arrayListPointsYpaporot = new ArrayList<>();


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
        createGrapg2appendDataPaporot();
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
                graph.removeAllSeries();
                graph.removeSeries(series);

                pointBx = progress;
                createPointsForFractal();
                createGrapg2resetData();
                graph.addSeries(series2);

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
    private void sortForPaporot() {
        Comparator<MyPointPaporot> byPointxPaporot = Comparator.comparing(MyPointPaporot::getPointXcoordinatePaporot);
        Collections.sort(myPointArrayListPaporot, byPointxPaporot);
        //  Collections.sort(arrayListPointsX);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createGrapg2appendDataPaporot() {
        double x = 0, y = 0;
        graphPaporot = (GraphView) findViewById(R.id.graph_paporot);
        sortForPaporot();
        seriesPaporot = new PointsGraphSeries<DataPoint>();
        for (int i = 0; i < arrayListPointsXpaporot.size(); i++) {
            //  x = arrayListPointsX.get(i);      y = arrayListPointsY.get(i);
            //  x=  myPoint.getPointXcoordinate();  y=myPoint.getPointYcoordinate();
            x = myPointArrayListPaporot.get(i).getPointXcoordinatePaporot() - 1;
            y = myPointArrayListPaporot.get(i).getPointYcoordinatePaporot() - 1;
            seriesPaporot.appendData(new DataPoint(x, y), true, 999999999);
        }
        graphPaporot.addSeries(seriesPaporot);
        graphPaporot.getLegendRenderer().setWidth(40000);
        //
        graphPaporot.getViewport().setXAxisBoundsManual(true);
        graphPaporot.getViewport().setMinX(0);
        graphPaporot.getViewport().setMaxX(9999999);
        graphPaporot.getViewport().setYAxisBoundsManual(true);
        graphPaporot.getViewport().setMinY(0);
        graphPaporot.getViewport().setMaxY(getMaxPoints(pointAy, pointBy, pointCy));

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
        createPointsForPaporot();
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
        for (int i = 0; i < 20000; i++) {
            int localRandom = getRandomNumberInRange(1, 3);
            int localResX = (arrayListPointsX.get(i) + getFinalPointX(localRandom)) / 2;
            int localResY = (arrayListPointsY.get(i) + getFinalPointY(localRandom)) / 2;
            arrayListPointsX.add(localResX);
            arrayListPointsY.add(localResY);

            myPointArrayList.add(new MyPoint(i + 1, localResX, localResY));
        }
    }

    private void createPointsForPaporot() {
        arrayListPointsXpaporot.add(0.0);
        arrayListPointsYpaporot.add(0.0);
        myPointArrayListPaporot.add(new MyPointPaporot(0.0, 0.0, 0.0));
        for (int i = 0; i < 20000; i++) {
            int localRandom = getRandomNumberInRange(1, 4);
            chooseRightFormulaForRandomPoints(localRandom);
            //formula here
            double localResX = (arrayListPointsXpaporot.get(i) * a) + (b * arrayListPointsYpaporot.get(i)) + e;
            //X=ax+by+e    Y=cx+dy+f,
            double localResY = (arrayListPointsXpaporot.get(i) * c + d * arrayListPointsYpaporot.get(i) + f);


            arrayListPointsXpaporot.add(localResX);
            arrayListPointsYpaporot.add(localResY);

            myPointArrayListPaporot.add(new MyPointPaporot(i + 1, localResX, localResY));
        }
    }

    private void chooseRightFormulaForRandomPoints(int intRandom) {
        if (intRandom == 1) {
            a = Constants.A_1_PAPOROT;
            b = Constants.B_1_PAPOROT;
            c = Constants.C_1_PAPOROT;
            d = Constants.D_1_PAPOROT;
            e = Constants.E_1_PAPOROT;
            f = Constants.F_1_PAPOROT;
            p = Constants.P_1_PAPOROT;
        } else if (intRandom == 2) {
            a = Constants.A_2_PAPOROT;
            b = Constants.B_2_PAPOROT;
            c = Constants.C_2_PAPOROT;
            d = Constants.D_2_PAPOROT;
            e = Constants.E_2_PAPOROT;
            f = Constants.F_2_PAPOROT;
            p = Constants.P_2_PAPOROT;
        } else if (intRandom == 3) {
            a = Constants.A_3_PAPOROT;
            b = Constants.B_3_PAPOROT;
            c = Constants.C_3_PAPOROT;
            d = Constants.D_3_PAPOROT;
            e = Constants.E_3_PAPOROT;
            f = Constants.F_3_PAPOROT;
            p = Constants.P_3_PAPOROT;
        } else if (intRandom == 4) {
            a = Constants.A_4_PAPOROT;
            b = Constants.B_4_PAPOROT;
            c = Constants.C_4_PAPOROT;
            d = Constants.D_4_PAPOROT;
            e = Constants.E_4_PAPOROT;
            f = Constants.F_4_PAPOROT;
            p = Constants.P_4_PAPOROT;
        }

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
