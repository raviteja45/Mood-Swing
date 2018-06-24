package in.scheduling;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Ravi on 09-06-2018.
 */

public class GridView_Adapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> imageUrls;
    private SparseBooleanArray mSparseBooleanArray;
    private DisplayImageOptions options;
    private boolean isCustomGalleryActivity;
    private ImageView imageView;

    public GridView_Adapter(Context context, ArrayList<String> imageUrls, boolean isCustomGalleryActivity) {
        this.context = context;
        this.imageUrls = imageUrls;
        this.isCustomGalleryActivity = isCustomGalleryActivity;
        mSparseBooleanArray = new SparseBooleanArray();


        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .resetViewBeforeLoading(true).cacheOnDisk(true)
                .considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    public ArrayList<String> getCheckedItems() {
        ArrayList<String> mTempArry = new ArrayList<String>();

        for (int i = 0; i < imageUrls.size(); i++) {
            if (mSparseBooleanArray.get(i)) {
                mTempArry.add(imageUrls.get(i));
            }
        }

        return mTempArry;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public Object getItem(int i) {
        return imageUrls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.customgridview, viewGroup, false);

        CheckBox mCheckBox = (CheckBox) view.findViewById(R.id.selectCheckBox);
         imageView = (ImageView) view.findViewById(R.id.galleryImageView);
        //if (!isCustomGalleryActivity)
            mCheckBox.setVisibility(View.GONE);

        ImageLoader.getInstance().displayImage("file://" + imageUrls.get(position), imageView, options);

        mCheckBox.setTag(position);
        mCheckBox.setChecked(mSparseBooleanArray.get(position));
      //  mCheckBox.setOnCheckedChangeListener(mCheckedChangeListener);
        imageView.setBackgroundResource(R.drawable.imageborder);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(view.getContext(),"Clicked on image "+imageUrls.get(position),Toast.LENGTH_LONG).show();
                System.out.println("Clciked on "+position);
                //mSparseBooleanArray.put(position, true);
                imageView.setBackgroundResource(R.drawable.imageborder);
                if(mSparseBooleanArray.get(position)){
                    mSparseBooleanArray.put(position, false);
                }
                else{
                    mSparseBooleanArray.put(position, true);
                }
                ((Gallery) context).showSelectButton();

            }
        });
        return view;
    }


   /* CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);
            System.out.println("Button view "+buttonView.getTag());
            ((Gallery) context).showSelectButton();
        }
    };*/
   /* @Override
    public void onClick(View view) {
        imageView.setBackgroundResource(R.drawable.imageborder);

    }*/
}

