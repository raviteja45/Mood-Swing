package in.scheduling;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi on 03-06-2018.
 */

public class ImageAttachement extends AppCompatActivity {

    ImageView moodimage;
    TextView tv1,tv2,tv3;
    private static GridView selectedImageGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageattachment);
        selectedImageGridView = (GridView) findViewById(R.id.selectedImagesGridView);
        Intent intent = getIntent();
        ArrayList<String> fullPath = new ArrayList<>();
        TaskHolder taskHolder = (TaskHolder) intent.getExtras().get("Object");
        tv1 = (TextView)findViewById(R.id.mood);
        tv2 = (TextView)findViewById(R.id.reason);
        tv3 = (TextView)findViewById(R.id.datetime);
        tv1.setText(taskHolder.getMood());
        tv2.setText(taskHolder.getReason().toString());
        tv3.setText(taskHolder.getDate()+" "+taskHolder.getTime());
        moodimage = (ImageView)findViewById(R.id.imageView);
        String[] oiu = TextUtils.join(", ",taskHolder.getAttachment()).replace("[", "").replace("]", "").split(",");
        for(int i=0;i<oiu.length;i++){
            fullPath.add(oiu[i].trim());
        }
        GridView_Adapter adapter = new GridView_Adapter(ImageAttachement.this, fullPath, false);
        selectedImageGridView.setAdapter(adapter);
    }
}
