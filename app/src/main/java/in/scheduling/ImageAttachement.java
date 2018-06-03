package in.scheduling;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Ravi on 03-06-2018.
 */

public class ImageAttachement extends AppCompatActivity {

    ImageView moodimage;
    TextView tv1,tv2,tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageattachment);
        Intent intent = getIntent();
        TaskHolder taskHolder = (TaskHolder) intent.getExtras().get("Object");
       // images = (ImageView)findViewById(R.id.images);
        LinearLayout imageGallery = (LinearLayout) findViewById(R.id.images);
        tv1 = (TextView)findViewById(R.id.mood);
        tv2 = (TextView)findViewById(R.id.reason);
        tv3 = (TextView)findViewById(R.id.datetime);
        moodimage = (ImageView)findViewById(R.id.imageView);
        Uri imagesUri = Uri.parse(taskHolder.getAttachment());
        String[] path = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(imagesUri, path, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(path[0]);
        String imgDecodableString = cursor.getString(columnIndex);
        cursor.close();
        ImageView imageView = new ImageView(getApplicationContext());
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layout);
        imageView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
        imageGallery.addView(imageView);
        tv1.setText(taskHolder.getMood());
        tv2.setText(TextUtils.join(", ",taskHolder.getReason()).replace("[", "").replace("]", ""));
        tv3.setText(taskHolder.getDate()+" "+taskHolder.getTime());

        switch (taskHolder.getMood()){
            case "happy":
                moodimage.setImageResource(R.drawable.happy48);
                break;
            case "sad":
                moodimage.setImageResource(R.drawable.sad48);
                break;
            case "excited":
                moodimage.setImageResource(R.drawable.excited48);
                break;
            case "meh":
                moodimage.setImageResource(R.drawable.meh48);
                break;
        }

    }
}
