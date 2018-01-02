package com.example.islammahoud.emoal;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.

 */
public class Sponserfragment extends Fragment {
    private GridLayoutManager manager;
    private int columnWidth = -1;
    private static String y="y";
    private int x=1;
    private Sponseradapter sponseradapter;
    private Catadapter catadapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    public static Sponserfragment newInstance(int x) {
        Sponserfragment fragment = new Sponserfragment();
        Bundle args = new Bundle();
        args.putInt(Sponserfragment.y, x);
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
        View view= inflater.inflate(R.layout.fragment_csponser, container, false);
        Context context=view.getContext();
        swipeRefreshLayout= view.findViewById(R.id.swiperefresh);
        int mNoOfColumns = calculateNoOfColumns(context);
       // Requester requester=new Requester();
       // ArrayList<Sponser> sponsers= (ArrayList<Sponser>) requester.getsponserdata();
         recyclerView=view.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        sponseradapter = new Sponseradapter(context);
        catadapter=new Catadapter(context);
        recyclerView.setLayoutManager(new GridLayoutManager(context,mNoOfColumns));
        if(x==1)
        {
            recyclerView.setAdapter(sponseradapter);
        }
        else if(x==2)
        {
            recyclerView.setAdapter(catadapter);
        }

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if(x==1)
                        {
                            recyclerView.setAdapter(sponseradapter);
                        }
                        else if(x==2)
                        {
                            recyclerView.setAdapter(catadapter);
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
        return view;
    }

        public int calculateNoOfColumns(Context context) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

            int noOfColumns = (int) (dpWidth / 120);
            return noOfColumns;
        }
}