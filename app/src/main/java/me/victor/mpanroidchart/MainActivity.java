package me.victor.mpanroidchart;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LineChart lcLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lcLine = (LineChart) findViewById(R.id.lc_line);
        lcLine.getDescription().setEnabled(false);
//        lcLine.setDrawBorders(true);
//        lcLine.setBorderColor(Color.WHITE);
//        lcLine.setBorderWidth(2);
        Legend legend = lcLine.getLegend();
        legend.setEnabled(false);
        XAxis xAxis = lcLine.getXAxis();
        xAxis.setTextSize(14);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(getResources().getColor(android.R.color.white));
        xAxis.setDrawGridLines(false);
        lcLine.getAxisRight().setEnabled(false);
        lcLine.getAxisLeft().setTextColor(getResources().getColor(android.R.color.white));
        lcLine.getAxisLeft().setTextSize(14);
        lcLine.getAxisLeft().setDrawZeroLine(false);
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(5, 50));
        values.add(new Entry(10, 66));
        values.add(new Entry(15, 120));
        values.add(new Entry(20, 30));
        values.add(new Entry(35, 10));
        values.add(new Entry(40, 110));
        values.add(new Entry(45, 30));
        values.add(new Entry(50, 160));
        values.add(new Entry(100, 30));
        LineDataSet set1;
        if (lcLine.getData() != null && lcLine.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) lcLine.getData().getDataSetByIndex(0);
            set1.setValues(values);
            lcLine.getData().notifyDataChanged();
            lcLine.notifyDataSetChanged();
        } else {
            // 创建一个数据集,并给它一个类型
            set1 = new LineDataSet(values, "AA");

            // 在这里设置线
            set1.disableDashedLine();
            set1.setColor(Color.WHITE);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawValues(false);
            set1.setDrawCircles(false);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);
            set1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

            if (Utils.getSDKInt() >= 18) {
                // 填充背景只支持18以上
                //Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_launcher);
                //set1.setFillDrawable(drawable);
                set1.setFillColor(Color.BLACK);
            } else {
                set1.setFillColor(Color.BLACK);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            //添加数据集
            dataSets.add(set1);

            //创建一个数据集的数据对象
            LineData data = new LineData(dataSets);

            //谁知数据
            lcLine.setData(data);
        }
    }
}
