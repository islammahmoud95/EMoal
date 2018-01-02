package com.example.islammahoud.emoal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.islammahoud.emoal.data.Category;
import com.example.islammahoud.emoal.parser.Requester;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by islam mahoud on 12/17/2017.
 */

public class Catadapter extends RecyclerView.Adapter<Catadapter.ViewHolder> implements AdapterView.OnItemClickListener, View.OnClickListener {
    private Context context;
    ArrayList<Category> categories;
    private LayoutInflater inflator;

    public Catadapter(Context context)
    {
        categories = new Requester().getsavedcatdata(context);
        this.context = context;
        if (categories == null) {
            categories = new ArrayList<>();
            Category s = new Category();
            s.setName("Waiting for network connection");
            s.setIcon("Sorry");
            categories.add(s);

        }


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String imagurl=" "+categories.get(position).getIcon();
        Picasso.with(context).load(imagurl).error(R.drawable.logo)
                .into(holder.imageView);
        holder.text.setText(categories.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.spimage);
            text=itemView.findViewById(R.id.cat);
        }
    }
}
