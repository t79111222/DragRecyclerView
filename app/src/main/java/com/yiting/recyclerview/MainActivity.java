package com.yiting.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    MyRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /*三個排法*/
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, OrientationHelper.HORIZONTAL));
        /*三個排法*/

        mAdapter = new MyRecyclerViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        /*添加拖曳效果*/ /***RecyclerViewAdapter必須放入實作{@link RecyclerViewMoveHelper.Callback} ***/
        RecyclerViewMoveHelper recyclerViewMoveHelper = new RecyclerViewMoveHelper(mAdapter);
        recyclerViewMoveHelper.setItemViewSwipeEnable(false);
        ItemTouchHelper helper = new ItemTouchHelper(recyclerViewMoveHelper);
        helper.attachToRecyclerView(mRecyclerView);
        /*添加拖曳效果*/

        recyclerViewMoveHelper.setItemViewSwipeEnable(false);//是否開啟滑動刪除
        recyclerViewMoveHelper.setLongPressDragEnable(true);//是否開啟長按排序

        mAdapter.setmOnIteClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Log.d("MyTextViewHolder", "onClick--> AdapterPosition = " + position);
            }
        });
        List data = new ArrayList<>();
        data.add("one");
        data.add("two");
        data.add("three");
        data.add("four");
        data.add("five");
        data.add("six");
        data.add("seven");
        mAdapter.updateData(data);
    }
}
