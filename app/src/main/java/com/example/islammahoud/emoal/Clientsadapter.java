package com.example.islammahoud.emoal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.islammahoud.emoal.ItemActivity.ItemActiv;
import com.example.islammahoud.emoal.data.Category;
import com.example.islammahoud.emoal.data.Clients;
import com.example.islammahoud.emoal.parser.Requester;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by islam mahoud on 12/25/2017.
 */

public class Clientsadapter extends RecyclerView.Adapter<Clientsadapter.ViewHolder> implements AdapterView.OnItemClickListener, View.OnClickListener {
    private Context context;
    private ArrayList<Clients> clientses;
    private LayoutInflater inflator;
    private ProgressBar progressBar;
    private int size;
   // private int id;


    public Clientsadapter(Context context,ArrayList<Clients> clientses,int size)
    {
        this.size=size;
        this.clientses=clientses;
       // Toast.makeText(context,String.valueOf(clientses.size()),Toast.LENGTH_LONG).show();
        this.context = context;
        if (clientses == null) {
            clientses = new ArrayList<>();
            Clients s = new Clients();
            s.setName("Waiting for network connection");
           // s.setIcon("Sorry");
            clientses.add(s);

        }


    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.subcat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String imagurl="http://e-mool.com/public/main/images/clients/" + clientses.get(position).getName()+ "/"+ clientses.get(position).getLogo();
        URL url= null;
        try {
            url = new URL(imagurl.replaceAll(" ","%20"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Picasso.with(context).load(url.toString()).error(R.drawable.logo)
                .into(holder.imageView);
        holder.name.setText(clientses.get(position).getName());
        holder.address.setText(clientses.get(position).getAddress());
        holder.phone.setText(clientses.get(position).getPhone());
      // id= clientses.get(position).getId();
       // holder.open.setText(clientses.get(position).getOpen());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=clientses.get(position).getId();
                String name=clientses.get(position).getName();
                Intent intent=new Intent(context, ItemActiv.class);
                intent.putExtra("position",id);
                intent.putExtra("name",name);
                Toast.makeText(context,name,Toast.LENGTH_LONG).show();
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return size;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }


    @Override
    public void onClick(View v) {
        ViewHolder holder = (ViewHolder) v.getTag();
        //int id =v.getId();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,address,phone,open;
        LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=  itemView.findViewById(R.id.spimage);
            name=itemView.findViewById(R.id.name);
            address=itemView.findViewById(R.id.add);
            phone=itemView.findViewById(R.id.phone);
            open=itemView.findViewById(R.id.open);
            linearLayout=itemView.findViewById(R.id.linearlayout);
        }
    }
}
