package com.example.islammahoud.emoal;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.islammahoud.emoal.data.Sponser;
import com.example.islammahoud.emoal.parser.Requester;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by islam mahoud on 12/17/2017.
 */

public class Sponseradapter extends RecyclerView.Adapter<Sponseradapter.ViewHolder> {
    private Context context;
    ArrayList<Sponser> sponsers;
    private LayoutInflater inflator;

    public Sponseradapter(Context context)
    {
        sponsers = new Requester().getsavedSpondata(context);
        this.context = context;
        if (sponsers == null) {
            sponsers = new ArrayList<>();
            Sponser s = new Sponser();
            s.setName("Waiting for network connection");
            s.setLogo("Sorry");
            sponsers.add(s);

        }


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String imagurl="http://e-mool.com/public/main/images/clients/" +sponsers.get(position).getName()+"/"+ sponsers.get(position).getLogo();
        Uri uri=Uri.parse(imagurl).buildUpon().build();
        URL url= null;
        try {
            url = new URL(imagurl.replaceAll(" ","%20"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Picasso.with(context).load(url.toString()).error(R.drawable.logo)
                .into(holder.imageView);
        holder.text.setText(sponsers.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return sponsers.size();
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
