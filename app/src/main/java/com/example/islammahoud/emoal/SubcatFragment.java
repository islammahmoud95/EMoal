package com.example.islammahoud.emoal;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.islammahoud.emoal.data.Clients;
import com.example.islammahoud.emoal.parser.Requester;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubcatFragment extends Fragment {

    private static String y="y";
    private int x=1;
    private Clientsadapter clientsadapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ArrayList<Clients> clientses;
    private RequestQueue mRequestQueue;
    private int itemsize=0;
    private ProgressBar progressBar;
    // TODO: Rename and change types and number of parameters
    public static SubcatFragment newInstance(int x) {
        SubcatFragment fragment = new SubcatFragment();
        Bundle args = new Bundle();
        args.putInt(SubcatFragment.y, x);
        fragment.setArguments(args);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            x = getArguments().getInt(y);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_subcat, container, false);
        Context context= view.getContext();
        progressBar= view.findViewById(R.id.prog);
        recyclerView=view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
       // clientsadapter = new Clientsadapter(context,clientses);
       // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       // recyclerView.setAdapter(clientsadapter);
        progressBar.isShown();
        getclientdata(context);
       // progressBar.setVisibility(View.GONE);
        return view;
    }

    public void getclientdata(final Context context) {
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
                            JSONArray a = e.getJSONArray("clients");
                            String z= String.valueOf(a.length());
                            clientses = new ArrayList<>(a.length());
                            int index = 0;
                            String id=String.valueOf(x);
                            for (int i = 0; i < a.length(); i++) {
                                JSONObject c = a.getJSONObject(i);
                               // Toast.makeText(getContext(),String.valueOf(c.getInt("id")),Toast.LENGTH_LONG).show();
                              //  Toast.makeText(getContext(),String.valueOf(x),Toast.LENGTH_LONG).show();
                                if(c.getInt("cat_id")==x)
                                {

                                    clientses.add(new Clients());
                                    //Toast.makeText(context,c.getString("name"),Toast.LENGTH_LONG).show();
                                    clientses.get(itemsize).setId(c.getInt("id"));
                                clientses.get(itemsize).setName(c.getString("name"));
                                clientses.get(itemsize).setLogo(c.getString("logo"));
                                clientses.get(itemsize).setAddress(c.getString("address"));
                                clientses.get(itemsize).setPhone(c.getString("phone"));
                                clientses.get(itemsize).setOpen(c.getString("open"));
                                clientses.get(itemsize).setDescription(c.getString("description"));
                                clientses.get(itemsize).setAddress2(c.getString("address2"));
                                    itemsize=itemsize+1;
                                //Toast.makeText(context,c.getString("name"),Toast.LENGTH_SHORT).show();
                                // Log.d("my trace", "loop num " + i + " sesname : " + c.getString("ses_name"));


                            }
                            else {clientses.add(new Clients());}
                            }

                            clientsadapter = new Clientsadapter(context,clientses,itemsize);
                            // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(clientsadapter);
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
