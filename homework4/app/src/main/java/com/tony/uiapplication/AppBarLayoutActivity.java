package com.tony.uiapplication;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppBarLayoutActivity extends AppCompatActivity {

    private RecyclerView mVList;
    private List<String> mList;
    private Adapter mAdapter;
    private Button mVSnack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout);
        mVList = (RecyclerView) findViewById(R.id.recyclerview);
        mVSnack = (Button) findViewById(R.id.snack);
        mVSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mVList, "snack", Snackbar.LENGTH_SHORT).show();
            }
        });
        mVList.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        for (int i = 1; i < 60; i++) {
            mList.add("text" + i);
        }
        mAdapter = new Adapter(this, mList);
        mVList.setAdapter(mAdapter);
    }

    private List<HashMap<String, String>> getData () {
        HashMap<String, String> map = new HashMap<>();
        List<HashMap<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            map.put("text", "text" + i);
            list.add(map);
        }
        return list;
    }

    class Adapter extends RecyclerView.Adapter<ViewHolder> {


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
//            if (position % 2 == 0) {
//                holder.vImage.setVisibility(View.GONE);
//            }
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
