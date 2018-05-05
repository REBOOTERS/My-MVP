package home.smart.fly.http.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import home.smart.fly.http.R;

/**
 * Created by engineer on 2017/12/4.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private List<ItemInfo> demos = new ArrayList<>();
    private Context mContext;

    public MyAdapter(List<ItemInfo> demos) {
        this.demos = demos;
    }

    public MyAdapter() {

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.demo_info_item, null);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.title.setText(demos.get(position).activitys.getSimpleName());
        holder.desc.setText(demos.get(position).desc);
        holder.itemshell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, demos.get(position).activitys);
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public int getItemCount() {
        return demos.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView title, desc;
        LinearLayout itemshell;

        public MyHolder(View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            itemshell = itemView.findViewById(R.id.itemshell);
        }
    }
}



