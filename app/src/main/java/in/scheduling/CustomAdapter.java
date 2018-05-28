package in.scheduling;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ravi on 26-04-2017.
 */


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.DataObjectHolder> {
    private ArrayList<TaskHolder> mDataset;
    private String flagValue;

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2;

        public DataObjectHolder(View v) {
            super(v);
            tv1 = (TextView) v.findViewById(R.id.reason);
            tv2 = (TextView) v.findViewById(R.id.datetime);
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
        holder.tv1.setText(mDataset.get(position).getReason().get(0));
        holder.tv2.setText(mDataset.get(position).getMood());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
