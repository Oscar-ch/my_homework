package com.tony.uiapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mVRecycler;
    private Adapter mAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVRecycler = (RecyclerView) findViewById(R.id.recyclerview);
//        mVRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mVRecycler.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
//        mVRecycler.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mList = new ArrayList<>();
        for (int i = 1; i < 60; i++) {
            mList.add("text" + i);
        }
        mAdapter = new Adapter(this, mList);
        mVRecycler.setAdapter(mAdapter);
    }

    class Adapter extends RecyclerView.Adapter<MainActivity.ViewHolder> {

        private Context context;
        private List<String> list;

        public Adapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (position % 2 == 0) {
                holder.vImage.setVisibility(View.GONE);
            }
            holder.vText.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView vImage;
        TextView vText;
        public ViewHolder(View itemView) {
            super(itemView);
            vImage = (ImageView) itemView.findViewById(R.id.img);
            vText = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
