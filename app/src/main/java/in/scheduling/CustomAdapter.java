package in.scheduling;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ravi on 26-04-2017.
 */


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.DataObjectHolder> {
    private ArrayList<TaskHolder> mDataset;
    private String flagValue;

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2,tv3;
        ImageView img1;

        public DataObjectHolder(View v) {
            super(v);
            tv1 = (TextView) v.findViewById(R.id.reason);
            tv2 = (TextView) v.findViewById(R.id.mood);
            tv3 = (TextView) v.findViewById(R.id.datetime);
            img1 = (ImageView)v.findViewById(R.id.imageView);
            //images = (ImageView)v.findViewById(R.id.images);
            /*v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"Clicked on it "+view,Toast.LENGTH_SHORT).show();
                }
            });*/
        }

    }


    public CustomAdapter(ArrayList<TaskHolder> myDataset,String flag) {
        mDataset = myDataset;
        flagValue = flag;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.results, parent, false);


        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, final int position) {
        if(mDataset.get(position).getReason()!=null){
            holder.tv1.setText(TextUtils.join(", ",mDataset.get(position).getReason()).replace("[", "").replace("]", ""));
        }
        switch (mDataset.get(position).getMood()){
            case "happy":
                holder.img1.setImageResource(R.drawable.happy48);
                break;
            case "sad":
                holder.img1.setImageResource(R.drawable.sad48);
                break;
            case "excited":
                holder.img1.setImageResource(R.drawable.excited48);
                break;
            case "meh":
                holder.img1.setImageResource(R.drawable.meh48);
                break;
        }
        holder.tv2.setText(mDataset.get(position).getMood());
        holder.tv3.setText(mDataset.get(position).getDate()+" "+mDataset.get(position).getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDataset.get(position).getAttachment()!=null&&!mDataset.get(position).getAttachment().isEmpty()&&!mDataset.get(position).getAttachment().contains("default")){
                    Intent intent = new Intent(view.getContext(),ImageAttachement.class);
                    intent.putExtra("Object", mDataset.get(position));
                    view.getContext().startActivity(intent);
                    Toast.makeText(view.getContext(),"Clicked on it "+mDataset.get(position).getMood(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
