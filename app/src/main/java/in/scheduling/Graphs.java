package in.scheduling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi on 24-06-2018.
 */

public class Graphs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphs);
        BarChart barChart = (BarChart) findViewById(R.id.barchart);
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Jan");
        labels.add("Feb");
        labels.add("Mar");
        labels.add("Apr");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("Aug");
        labels.add("Sep");
        labels.add("Oct");
        labels.add("Nov");
        labels.add("Dec");
        ArrayList<BarEntry> bargroup = new ArrayList<>();
        bargroup.add(new BarEntry(0,8));
        bargroup.add(new BarEntry(1,2));
        bargroup.add(new BarEntry(2,5));
        bargroup.add(new BarEntry(3,20));
        bargroup.add(new BarEntry(4,15));
        bargroup.add(new BarEntry(5,19));
        BarDataSet bardataset = new BarDataSet(bargroup, "Cells");
        BarData data = new BarData(bardataset);
        barChart.setData(data);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        ArrayList<PieEntry> bargroup1 = new ArrayList<>();
        bargroup1.add(new PieEntry(8f, "June"));
        bargroup1.add(new PieEntry(2f, 1));
        bargroup1.add(new PieEntry(5f, 2));
        bargroup1.add(new PieEntry(20f, 3));
        bargroup1.add(new PieEntry(15f, 4));
        bargroup1.add(new PieEntry(19f, 5));
        PieDataSet pieDataset = new PieDataSet(bargroup1,"Cell");
        pieDataset.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData pieData = new PieData(pieDataset);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
       // barChart.setDrawSliceText(false);
        pieChart.getLegend().setEnabled(false);

    }
}
