package com.cc.pullrecyclerview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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




        View itemView = LayoutInflater.from(this).inflate(R.layout.item_view, null,false);
        TextView textView = itemView.findViewById(R.id.text);
        Button button = itemView.findViewById(R.id.btn);
        textView.setText("头布局");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHeader();
            }
        });


        refreshRecyclerView.addHeaderView(itemView);



        View inflate4 = LayoutInflater.from(this).inflate(R.layout.item, null,false);
        TextView inflate44 = inflate4.findViewById(R.id.text);
        inflate44.setText("footView-1");

        refreshRecyclerView.addFooterView(inflate4);

        View inflate5 = LayoutInflater.from(this).inflate(R.layout.item, null,false);
        TextView inflate55 = inflate5.findViewById(R.id.text);
        inflate55.setText("footView-2");

        refreshRecyclerView.addFooterView(inflate5);


        refreshRecyclerView.setArrowView(new SimpleArrowView(this));
        refreshRecyclerView.addItemDecoration(new SimpleItemDecoration());
        refreshRecyclerView.setCanStartLoadAnimation(true);
        refreshRecyclerView.setEmptyView(LayoutInflater.from(this).inflate(R.layout.item_empty,refreshRecyclerView,false));
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
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        refreshRecyclerView.loadMoreComplete();
                        refreshRecyclerView.setNoMore(true);
                    }
                }, 2000);
            }
        });
    }

    private void addHeader() {
        View itemView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_view, null,false);
        TextView textView = itemView.findViewById(R.id.text);
        Button button = itemView.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHeader();
            }
        });
        textView.setText("头布局");
        refreshRecyclerView.addHeaderView(itemView);
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
