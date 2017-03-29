package com.yiting.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Tina Huang on 2017/3/28.
 */

public class RecyclerViewMoveHelper extends ItemTouchHelper.Callback {

    private Callback mCallback;
    private boolean mLongPressDragEnable = true;
    private boolean mItemViewSwipeEnable = true;

    /**
     *  必須放入實作(Implements){@link #Callback} 的RecyclerView.Adapter
     * @param recyclerViewaAapterImplementsCallback
     */
    public RecyclerViewMoveHelper(Callback  recyclerViewaAapterImplementsCallback){
        mCallback = recyclerViewaAapterImplementsCallback;
    }

    public void setLongPressDragEnable(boolean mLongPressDragEnable) {
        this.mLongPressDragEnable = mLongPressDragEnable;
    }

    public void setItemViewSwipeEnable(boolean mItemViewSwipeEnable) {
        this.mItemViewSwipeEnable = mItemViewSwipeEnable;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return mItemViewSwipeEnable;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return mLongPressDragEnable;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.RIGHT;
        int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlag, swipeFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

        mCallback.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mCallback.onSwipe(viewHolder.getAdapterPosition());
    }

    public interface Callback {
        void onMove(int fromPosition, int toPosition);
        void onSwipe(int position);
    }
}
