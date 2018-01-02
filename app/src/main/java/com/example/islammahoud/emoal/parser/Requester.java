package com.example.islammahoud.emoal.parser;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.islammahoud.emoal.Clientsadapter;
import com.example.islammahoud.emoal.L;
import com.example.islammahoud.emoal.SubcatFragment;
import com.example.islammahoud.emoal.data.Category;
import com.example.islammahoud.emoal.data.Clients;
import com.example.islammahoud.emoal.data.Sponser;
import com.example.islammahoud.emoal.data.Subcat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static android.widget.Toast.makeText;

/**
 * Created by islam mahoud on 12/18/2017.
 */

public class Requester {
    private static Context VV;
    private final String LOG_TAG = Requester.class.getSimpleName();
    private RequestQueue requestQueue;
    private ArrayList<Sponser> sponsers;
    private ArrayList<Category> categories;
    private ArrayList<Clients> clientses;
    private ArrayList<Subcat> subcats;
    private  ArrayList<Clients> cc;
    private RequestQueue mRequestQueue;

    public RequestQueue getRequestQueue(Context con) {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(con);
        }
        return mRequestQueue;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Sponser> getSpondata(final Context context) {
        RequestQueue requestQueue =getRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, "http://e-mool.com/api/index",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                try {
                    L.m("s: "+s);

                    JSONObject e = new JSONObject(s);
                    JSONArray a = e.getJSONArray("sponsers");
                    String x= String.valueOf(a.length());
                    sponsers = new ArrayList<>(a.length());
                    int index = 0;
                    for (int i = 0; i < a.length(); i++) {
                        sponsers.add(new Sponser());
                        JSONObject c = a.getJSONObject(i);
                        sponsers.get(i).setId(c.getInt("id"));
                        sponsers.get(i).setName(c.getString("name"));
                        sponsers.get(i).setLogo(c.getString("logo"));
                       // Log.d("my trace", "loop num " + i + " sesname : " + c.getString("ses_name"));

                    }
                    saveSpondata(sponsers,context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                L.T(context,"there is a trouble with connectivity \n please check your network connection");
                L.m("error message sessions: "+volleyError.getMessage());
            }
        });
        requestQueue.add(request);
        return sponsers;
    }

    public void saveSpondata(ArrayList<Sponser> news, Context con) {

        File folder = con.getExternalFilesDir("Spondata");
        File newsfolder= new File(folder, "Spon_data.json");

        FileOutputStream fs= null;
        try {
            fs = new FileOutputStream(newsfolder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(news);
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Sponser> getsavedSpondata(Context cont) {
        ArrayList<Sponser> data = new ArrayList<>();
        if (sponsers == null) {
            File folder = cont.getExternalFilesDir("Spondata");
            File newsfolder= new File(folder, "Spon_data.json");
            ObjectInputStream ois =
                    null;
            try {
                ois = new ObjectInputStream(new FileInputStream(newsfolder));
                try {
                    data = ((ArrayList<Sponser>) ois.readObject());
                    ois.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                L.m("file not created");
            }


            if (data != null) {
                L.m("data retrieved from file " + data.size());
                sponsers = data;
            }
        }
return sponsers;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Category> getcatdata(final Context context) {
        RequestQueue requestQueue =getRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, "http://e-mool.com/api/index",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                        try {
                            L.m("s: "+s);

                            JSONObject e = new JSONObject(s);
                            JSONArray a = e.getJSONArray("categories");
                            String x= String.valueOf(a.length());
                            categories = new ArrayList<>(a.length());
                            int index = 0;
                            for (int i = 0; i < a.length(); i++) {
                                categories.add(new Category());
                                JSONObject c = a.getJSONObject(i);
                                categories.get(i).setId(c.getInt("id"));
                                categories.get(i).setName(c.getString("name"));
                                categories.get(i).setIcon(c.getString("icon"));
                               // Toast.makeText(context,c.getString("icon"),Toast.LENGTH_SHORT).show();
                                // Log.d("my trace", "loop num " + i + " sesname : " + c.getString("ses_name"));

                            }
                            savecatdata(categories,context);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        L.T(context,"there is a trouble with connectivity \n please check your network connection");
                        L.m("error message sessions: "+volleyError.getMessage());
                    }
                });
        requestQueue.add(request);
        return categories;
    }

    public void savecatdata(ArrayList<Category> news, Context con) {

        File folder = con.getExternalFilesDir("Catdata");
        File newsfolder= new File(folder, "Cat_data.json");

        FileOutputStream fs= null;
        try {
            fs = new FileOutputStream(newsfolder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(news);
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Category> getsavedcatdata(Context cont) {
        ArrayList<Category> data = new ArrayList<>();
        if (categories == null) {
            File folder = cont.getExternalFilesDir("Catdata");
            File newsfolder= new File(folder, "Cat_data.json");
            ObjectInputStream ois =
                    null;
            try {
                ois = new ObjectInputStream(new FileInputStream(newsfolder));
                try {
                    data = ((ArrayList<Category>) ois.readObject());
                    ois.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                L.m("file not created");
            }


            if (data != null) {
                L.m("data retrieved from file " + data.size());
                categories = data;
            }
        }
        return categories;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void getsubcatstdata(final Context context) {
        RequestQueue requestQueue =getRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, "http://e-mool.com/api/index",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                        try {
                            L.m("s: "+s);

                            JSONObject e = new JSONObject(s);
                            JSONArray a = e.getJSONArray("subCats");
                            String x= String.valueOf(a.length());
                            subcats = new ArrayList<>(a.length());
                            int index = 0;
                            for (int i = 0; i < a.length(); i++) {
                                subcats.add(new Subcat());
                                JSONObject c = a.getJSONObject(i);
                                subcats.get(i).setId(c.getInt("id"));
                                subcats.get(i).setName(c.getString("name"));

                                //Toast.makeText(context,c.getString("name"),Toast.LENGTH_SHORT).show();
                                // Log.d("my trace", "loop num " + i + " sesname : " + c.getString("ses_name"));

                            }
                            savesubcatsdata(subcats,context);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        L.T(context,"there is a trouble with connectivity \n please check your network connection");
                        L.m("error message sessions: "+volleyError.getMessage());
                    }
                });
        requestQueue.add(request);
    }

    public void savesubcatsdata(ArrayList<Subcat> news, Context con) {

        File folder = con.getExternalFilesDir("subcats");
        File newsfolder= new File(folder, "subcats_data.json");

        FileOutputStream fs= null;
        try {
            fs = new FileOutputStream(newsfolder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(news);
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Clients> testcl(ArrayList<Clients> cc)
    {
        clientses=cc;
        return clientses;
    }
    public ArrayList<Subcat> getsavedsubcatstdata(Context cont) {
        ArrayList<Subcat> data = new ArrayList<>();
        if (subcats == null) {
            File folder = cont.getExternalFilesDir("subcats");
            File newsfolder= new File(folder, "subcats_data.json");
            ObjectInputStream ois =
                    null;
            try {
                ois = new ObjectInputStream(new FileInputStream(newsfolder));
                try {
                    data = ((ArrayList<Subcat>) ois.readObject());
                    ois.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                L.m("file not created");
            }


            if (data != null) {
                L.m("data retrieved from file " + data.size());
                subcats = data;
            }
        }
        return subcats;
    }


    public void Requestexcute(Context context)
    {
         getSpondata(context);
        getcatdata(context);
        getsubcatstdata(context);
    }
}