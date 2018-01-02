package com.example.islammahoud.emoal.ItemActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.islammahoud.emoal.R;
import com.example.islammahoud.emoal.data.Clients;
import com.example.islammahoud.emoal.data.Item;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by islam mahoud on 12/25/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> implements AdapterView.OnItemClickListener, View.OnClickListener {
    private Context context;
    private ArrayList<Item> items;
    private LayoutInflater inflator;
    private ProgressBar progressBar;
    private int size;
    private String name;
   // private int id;


    public ItemAdapter(Context context, ArrayList<Item> items, int size,String name)
    {
        this.size=size;
        this.items=items;
        this.name=name;
       // Toast.makeText(context,String.valueOf(clientses.size()),Toast.LENGTH_LONG).show();
        this.context = context;
        if (items == null) {
            items = new ArrayList<>();
            Item s = new Item();
            s.setName("Waiting for network connection");
           // s.setIcon("Sorry");
            items.add(s);

        }


    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String imagurl="http://e-mool.com/public/main/images/clients/" +name+"/"+"items"+"/"+"/"+ items.get(position).getPhoto();
        URL url= null;
        try {
            url = new URL(imagurl.replaceAll(" ","%20"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Picasso.with(context).load(url.toString()).error(R.drawable.logo)
                .into(holder.imageView);
        holder.name.setText(items.get(position).getName());
       holder.desc.setText(items.get(position).getDescription());
        holder.salary.setText(items.get(position).getSalary());
        holder.discount.setText(items.get(position).getDiscount());
      // id= clientses.get(position).getId();
       // holder.open.setText(clientses.get(position).getOpen());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=items.get(position).getId();
                Intent intent=new Intent(context, ItemActiv.class);
                intent.putExtra("position",id);
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
      //  ViewHolder holder = (ViewHolder) v.getTag();
        //int id =v.getId();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,salary,discount,desc;
        LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=  itemView.findViewById(R.id.itemimage);
            name=itemView.findViewById(R.id.name);
            salary=itemView.findViewById(R.id.salary);
            discount=itemView.findViewById(R.id.discount);
            desc=itemView.findViewById(R.id.desc);
            linearLayout=itemView.findViewById(R.id.linearlayout);
        }
    }
}
