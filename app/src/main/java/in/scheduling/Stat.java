package in.scheduling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi on 29-05-2018.
 */

public class Stat extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    RecyclerView rc1;
    ArrayList<TaskHolder> taskHolder=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapterview);
        rc1 = (RecyclerView) findViewById(R.id.rc1);
        rc1.setHasFixedSize(true);
        rc1.setNestedScrollingEnabled(false);
        layoutManager = new LinearLayoutManager(this);
        Intent intent = getIntent();
        taskHolder = (ArrayList<TaskHolder>) intent.getExtras().get("Object");
        rc1.setLayoutManager(layoutManager);
        adapter = new CustomAdapter(taskHolder, "");
        adapter.notifyDataSetChanged();
        rc1.setAdapter(adapter);
        rc1.setHasFixedSize(true);
    }

}
