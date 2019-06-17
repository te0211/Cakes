package com.teo.cakes.view.ui;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;


import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.teo.cakes.R;
import com.teo.cakes.lifecycle.CakeObserver;
import com.teo.cakes.service.model.Cake;
import com.teo.cakes.view.adaptor.CakeAdapter;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class MainActivity extends AppCompatActivity {

    public final static String LIST_STATE_KEY = "recycler_list_state";
    Parcelable listState;

    OrientationEventListener mOrientationListener;


    final String TAG = this.getClass().getSimpleName().toString();
    RequestQueue queue;
    String URL = "https://gist.githubusercontent.com/t-reed/739df99e9d96700f17604a3971e701fa/raw/1d4dd9c5a0ec758ff5ae92b7b13fe4d57d34e1dc/waracle_cake-android-client";

    private RecyclerView cList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Cake> cakeList;
    private RecyclerView.Adapter adapter;

    int someIntValue;
    String someStringValue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cList = findViewById(R.id.main_list);
        cakeList = new ArrayList<>();
        adapter = new CakeAdapter(getApplicationContext(), cakeList);



        linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(cList.getContext(), linearLayoutManager.getOrientation());
        dividerItemDecoration = new DividerItemDecoration(cList.getContext(), linearLayoutManager.getOrientation());

        cList.setHasFixedSize(true);
        cList.setLayoutManager(linearLayoutManager);
        cList.addItemDecoration(dividerItemDecoration);
        cList.setAdapter(adapter);

        getData();
        // finishAfterTransition();
//        mOrientationListener = new OrientationEventListener(this,
//                SensorManager.SENSOR_DELAY_NORMAL) {
//
//            @Override
//            public void onOrientationChanged(int orientation) {
//                Log.v("view",
//                        "Orientation changed to " + orientation);
//
//            }
//        };

//        if (mOrientationListener.canDetectOrientation() == true) {
//            Log.v("view", "Can detect orientation");
//            mOrientationListener.enable();
//        } else {
//            Log.v("view", "Cannot detect orientation");
//            mOrientationListener.disable();
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refresh:
                recreate();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void getData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Cake> list = new ArrayList<Cake>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Cake cake = new Cake();
                        cake.setTitle(jsonObject.getString("title"));
                        cake.setDesc(jsonObject.getString("desc"));
                        cake.setImage(jsonObject.getString("image"));

                        list.add(cake);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                cakeList.addAll(removeDuplicates(list));
//
                Log.d("count", String.valueOf(cakeList.size()));

                Collections.sort(cakeList, new Comparator<Cake>() {
                    @Override
                    public int compare(Cake o1, Cake o2) {
                        return o1.title.compareTo(o2.title);
                    }
                });

                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "network error", Toast.LENGTH_LONG).show();


            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    private List<Cake> removeDuplicates(List<Cake> list) {
        Set<Cake> set = new HashSet<>();

        // Add the elements to set
        set.addAll(list);
        Log.d("listsize before", String.valueOf(list.size()));
        list.clear();
//        list.addAll(set);
        List<Cake> ls = new ArrayList<Cake>();
        ls.addAll(set);
        Log.d("listsize", String.valueOf(ls.size()));
        return ls;
    }



//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        // Checks the orientation of the screen
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
//        }
//    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mOrientationListener.disable();
//    }


    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        // Save list state
        listState = linearLayoutManager.onSaveInstanceState();
        state.putParcelable(LIST_STATE_KEY, listState);
    }

    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        // Retrieve list state and list/item positions
        if(state != null)
            listState = state.getParcelable(LIST_STATE_KEY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listState != null) {
            linearLayoutManager.onRestoreInstanceState(listState);
        }
}

}