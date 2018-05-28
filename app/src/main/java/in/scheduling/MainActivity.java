package in.scheduling;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;

import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DbHelper db = null;
    ImageButton happy,sad,meh,excited;
    Button date,time,stat;
    TaskHolder taskHolder =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        happy = (ImageButton)findViewById(R.id.happy);
        happy.setOnClickListener(this);
        sad = (ImageButton)findViewById(R.id.sad);
        sad.setOnClickListener(this);
        meh = (ImageButton)findViewById(R.id.meh);
        meh.setOnClickListener(this);
        excited = (ImageButton)findViewById(R.id.excited);
        excited.setOnClickListener(this);
        date = (Button)findViewById(R.id.date);
        time=(Button)findViewById(R.id.time);
        stat = (Button)findViewById(R.id.stat);
        db = new DbHelper(this);
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<TaskHolder> moodList = null;
                try {
                    moodList = db.getHistory();
                    Intent intent = new Intent(view.getContext(),Stat.class);
                    intent.putExtra("Object", moodList);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(TaskHolder m1:moodList) {
                    System.out.println("Mood is " + m1.getMood());
                    System.out.println("Date is " + m1.getDate());
                    System.out.println("Time is " + m1.getTime());
                    System.out.println("Reason is " + m1.getReason());
                    System.out.println("Comments are "+m1.getComments());
                    System.out.println("URI is "+m1.getAttachment());
                    //Toast.makeText(view.getContext(),"Here is ",Toast.LENGTH_LONG).show();
                }

            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showDialog(1);
           }
       });
        db = new DbHelper(this);

        if(date.getText()!=null&&date.getText().toString().equalsIgnoreCase("Date")){
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String currentDate = df.format(Calendar.getInstance().getTime());
            date.setText(currentDate);
        }
        if(time.getText()!=null&&time.getText().toString().equalsIgnoreCase("Time")){
            DateFormat df = new SimpleDateFormat("h:mm a");
            String currentDate = df.format(Calendar.getInstance().getTime());
            time.setText(currentDate);
        }
        /*happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<TaskHolder> moodList =  db.getHistory();
                for(TaskHolder m1:moodList) {
                    System.out.println("Mood is " + m1.getMood());
                    System.out.println("Date is " + m1.getDate());
                    System.out.println("Time is " + m1.getTime());
                    System.out.println("Reason is " + m1.getReason());
                    Toast.makeText(view.getContext(),"Here is ",Toast.LENGTH_LONG).show();
                }
            }
        });*/

       /* bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                String dateValue = date.format(new Date());
                SimpleDateFormat time = new SimpleDateFormat("h:mm a");
                String timeValue = time.format(new Date());
                TaskHolder taskHolder = new TaskHolder();
                taskHolder.setDate(dateValue);
                taskHolder.setTime(timeValue);
                taskHolder.setMood("Sad");
                taskHolder.setReason("Chatting");
                db.insertRecords(taskHolder);

            }
        });*/

       /* bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<TaskHolder> moodList =  db.getHistory();
                for(TaskHolder m1:moodList){
                    System.out.println("Mood is "+m1.getMood());
                    System.out.println("Date is "+m1.getDate());
                    System.out.println("Time is "+m1.getTime());
                    System.out.println("Reason is "+m1.getReason());
                }
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        taskHolder = new TaskHolder();
        taskHolder.setDate(date.getText().toString());
        taskHolder.setTime(time.getText().toString());
        Intent intent = new Intent(this,Reason.class);

        switch(view.getId()){
            case R.id.happy:
                taskHolder.setMood("happy");
                Toast.makeText(view.getContext(),"Happy ",Toast.LENGTH_SHORT).show();
                intent.putExtra("Object", taskHolder);
                startActivity(intent);
                break;
            case R.id.sad:
                taskHolder.setMood("sad");
                Toast.makeText(view.getContext(),"Sad ",Toast.LENGTH_SHORT).show();
                intent.putExtra("Object", taskHolder);
                startActivity(intent);
                break;
            case R.id.excited:
                taskHolder.setMood("excited");
                Toast.makeText(view.getContext(),"Excited ",Toast.LENGTH_SHORT).show();
                intent.putExtra("Object", taskHolder);
                startActivity(intent);
                break;
            case R.id.meh:
                taskHolder.setMood("meh");
                Toast.makeText(view.getContext(),"Meh ",Toast.LENGTH_SHORT).show();
                intent.putExtra("Object", taskHolder);
                startActivity(intent);
                break;
        }


    }

    public Dialog onCreateDialog(int id) {

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        switch (id) {
            case 0:

                return new DatePickerDialog(MainActivity.this, date_listener, year,
                        month, day);
            case 1:

                return new TimePickerDialog(MainActivity.this, time_listener, hour,
                        minute, false);

        }
        return null;
    }


    DatePickerDialog.OnDateSetListener date_listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            String date1 = String.valueOf(month) + "/" + String.valueOf(day)
                    + "/" + String.valueOf(year);
            date.setText(date1);
            Toast.makeText(view.getContext(),"Date is "+date1,Toast.LENGTH_SHORT).show();
        }
    };
    TimePickerDialog.OnTimeSetListener time_listener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            Time current = new Time(hour,minute,0);
            Format formatter;
            formatter = new SimpleDateFormat("h:mm a");
            String totalTime = formatter.format(current);
            time.setText(totalTime);
            Toast.makeText(view.getContext(),"Time is "+totalTime,Toast.LENGTH_SHORT).show();
        }
    };
}
