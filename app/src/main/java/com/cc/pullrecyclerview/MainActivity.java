package com.cc.pullrecyclerview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cc.pullrecyclerview.pview.PullToRefreshRecyclerView;
import com.cc.pullrecyclerview.pview.SimpleArrowView;
import com.cc.pullrecyclerview.pview.SimpleItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    PullToRefreshRecyclerView refreshRecyclerView;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i <20; i++) {
                list.add("item-"+(i+1));
        }
        refreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        refreshRecyclerView.setLayoutManager(linearLayoutManager);
        refreshRecyclerView.setRefreshMode(PullToRefreshRecyclerView.MODE_REFRESH_ALL);
        MyAdapter adapter = new MyAdapter();
        refreshRecyclerView.setAdapter(adapter);
        View inflate1 = LayoutInflater.from(this).inflate(R.layout.item, refreshRecyclerView,false);
        TextView inflate = inflate1.findViewById(R.id.text);
        inflate.setText("头布局-1");

        View inflate2 = LayoutInflater.from(this).inflate(R.layout.item, refreshRecyclerView,false);
        TextView inflate22 = inflate2.findViewById(R.id.text);
        inflate22.setText("头布局-2");


        View inflate3 = LayoutInflater.from(this).inflate(R.layout.item, refreshRecyclerView,false);
        TextView inflate33 = inflate3.findViewById(R.id.text);
        inflate33.setText("头布局-3");

        refreshRecyclerView.addHeaderView(inflate1);
        refreshRecyclerView.addHeaderView(inflate2);
        refreshRecyclerView.addHeaderView(inflate3);


        refreshRecyclerView.setArrowView(new SimpleArrowView(this));
        refreshRecyclerView.addItemDecoration(new SimpleItemDecoration());
        refreshRecyclerView.setCanStartLoadAnimation(true);

        refreshRecyclerView.setLoadingListener(new PullToRefreshRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                       refreshRecyclerView.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {

            }
        });

    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent, false);
            ViewHolder viewHolder = new ViewHolder(inflate);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }



    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }




    }

}
