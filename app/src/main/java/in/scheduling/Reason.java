package in.scheduling;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi on 28-05-2018.
 */

public class Reason extends AppCompatActivity implements View.OnClickListener {
    ImageView coding,friends,songs,yoga,sleep,movie;
    ImageButton attachment;
    Button done;
    List<String> allList = new ArrayList<>();
    DbHelper db = null;
    EditText comments;
    TaskHolder taskHolder=null;
    Uri imagesUri=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reason);
        coding = (ImageView)findViewById(R.id.coding);
        friends=(ImageView)findViewById(R.id.friends);
        songs = (ImageView)findViewById(R.id.headphones);
        yoga = (ImageView)findViewById(R.id.yoga);
        sleep = (ImageView)findViewById(R.id.sleep);
        movie = (ImageView)findViewById(R.id.movie);
        comments = (EditText)findViewById(R.id.comments);
        attachment = (ImageButton)findViewById(R.id.attachment);
        coding.setOnClickListener(this);
        friends.setOnClickListener(this);
        songs.setOnClickListener(this);
        yoga.setOnClickListener(this);
        sleep.setOnClickListener(this);
        movie.setOnClickListener(this);
        done = (Button)findViewById(R.id.done);
        db = new DbHelper(this);
        Intent intent = getIntent();
        taskHolder = (TaskHolder) intent.getExtras().get("Object");
        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 2);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    taskHolder.setReason(allList);
                    taskHolder.setComments(comments.getText().toString());
                    if(imagesUri!=null){
                        taskHolder.setAttachment(imagesUri.toString());
                    }
                    db.insertRecords(taskHolder);
                } catch (JSONException e) {
                }
                ArrayList<TaskHolder> moodList = null;
                try {
                    moodList = db.getHistory();
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
                System.out.println("Total records are "+allList);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2){
            imagesUri = data.getData();
           /* String[] path = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(imagesUri,
                    path, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(path[0]);
            String imgDecodableString = cursor.getString(columnIndex);
            cursor.close();
            ImageView imgView = (ImageView) findViewById(R.id.imgView);
            imgView.setImageBitmap(BitmapFactory
                    .decodeFile(imgDecodableString));*/

        }
    }
    @Override
    public void onClick(View view) {

        ImageView img= (ImageView)findViewById(view.getId());
        if (!allList.isEmpty() && allList.contains(img.getTag().toString())) {
            allList.remove(img.getTag().toString());
            img.setBackgroundColor(Color.parseColor("#55FF0000"));
        }
        else{
            allList.add(img.getTag().toString());
            img.setBackgroundColor(Color.parseColor("#FFFF00"));

        }

    }
}
