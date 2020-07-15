package com.yiting.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tina Huang on 2017/3/25.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyTextViewHolder> implements  RecyclerViewMoveHelper.Callback{

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<String> mData;
    private OnItemClickListener mOnItemClickListener = null;

    public  MyRecyclerViewAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mData = new ArrayList<>();
    }

    public void updateData(List<String> items){
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public MyTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyTextViewHolder(mLayoutInflater.inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyTextViewHolder holder, int position) {
        holder.mTextView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /*實作拖曳效果*/
    @Override
    public void onMove(int fromPosition, int toPosition) {
        Collections.swap(mData, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onSwipe(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
    /*實作拖曳效果*/

    public void setmOnIteClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public class MyTextViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textView)
        TextView mTextView;

        public MyTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v,getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
}
