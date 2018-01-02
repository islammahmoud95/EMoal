package com.example.islammahoud.emoal.ItemActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.islammahoud.emoal.Catadapter;
import com.example.islammahoud.emoal.Clientsadapter;
import com.example.islammahoud.emoal.L;
import com.example.islammahoud.emoal.R;
import com.example.islammahoud.emoal.Sponseradapter;
import com.example.islammahoud.emoal.data.Clients;
import com.example.islammahoud.emoal.data.Item;
import com.example.islammahoud.emoal.data.Subcat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {
    private static String y="y";
    private int x=1;
    private Sponseradapter sponseradapter;
    private ItemAdapter itemAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ArrayList<Item> items;
    private RequestQueue mRequestQueue;
    private int itemsize=0;
    private ProgressBar progressBar;
    private int pos;
    private int z=0;
    private static String name="E-mool";
    private TextView textView;
    private ImageView imageView;
    public static ItemFragment newInstance(int x,String name) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ItemFragment.y, x);
        args.putString(ItemFragment.name, name);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            x = getArguments().getInt(y);
            name=getArguments().getString(name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_item, container, false);
        Context context=getContext();
        Toast.makeText(context,name,Toast.LENGTH_LONG).show();
        textView=view.findViewById(R.id.detail_title);
        textView.setText(name);
        imageView=view.findViewById(R.id.detail_image);
        progressBar= view.findViewById(R.id.prog);
        recyclerView=view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setHasFixedSize(true);
        // clientsadapter = new Clientsadapter(context,clientses);
        // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // recyclerView.setAdapter(clientsadapter);
        progressBar.isShown();
        getitemtdata(context);
        return view;
    }

    public void getitemtdata(final Context context) {
        progressBar.isActivated();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.isShown();
        RequestQueue requestQueue =getRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.GET, "http://e-mool.com/api/index",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                        try {
                            L.m("s: "+s);

                            JSONObject e = new JSONObject(s);
                            JSONArray a = e.getJSONArray("items");
                            items = new ArrayList<>(a.length());
                            int index = 0;
                            //String id=String.valueOf(x);
                            for (int i = 0; i < a.length(); i++) {
                                JSONObject c = a.getJSONObject(i);
                                // Toast.makeText(getContext(),String.valueOf(c.getInt("id")),Toast.LENGTH_LONG).show();
                                //  Toast.makeText(getContext(),String.valueOf(x),Toast.LENGTH_LONG).show();
                                if(c.getInt("user_id")==x)
                                {

                                    items.add(new Item());
                                    //Toast.makeText(context,c.getString("name"),Toast.LENGTH_LONG).show();
                                    items.get(itemsize).setId(c.getInt("id"));
                                    items.get(itemsize).setName(c.getString("name"));
                                    items.get(itemsize).setPhoto(c.getString("photo"));
                                    items.get(itemsize).setSalary(c.getString("salary"));
                                    items.get(itemsize).setDiscount(c.getString("discount"));
                                    items.get(itemsize).setDescription(c.getString("description"));
                                    itemsize=itemsize+1;
                                    //Toast.makeText(context,c.getString("name"),Toast.LENGTH_SHORT).show();
                                    // Log.d("my trace", "loop num " + i + " sesname : " + c.getString("ses_name"));


                                }
                                else {items.add(new Item());}
                            }

                            itemAdapter = new ItemAdapter(context,items,itemsize,name);
                            // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(itemAdapter);
                            progressBar.setVisibility(View.GONE);
                            itemsize=0;


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressBar.setVisibility(View.GONE);

                        L.T(context,"there is a trouble with connectivity \n please check your network connection");
                        L.m("error message sessions: "+volleyError.getMessage());
                    }
                });

        requestQueue.add(request);


    }
    public RequestQueue getRequestQueue(Context con) {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(con);
        }
        return mRequestQueue;
    }
}