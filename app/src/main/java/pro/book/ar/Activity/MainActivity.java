package pro.book.ar.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pro.book.ar.Adapter.RecyclerViewAdapter_main;
import pro.book.ar.Classes.ImageUtil;
import pro.book.ar.Classes.SwipeableRecyclerViewTouchListener;
import pro.book.ar.Model.Main;
import pro.book.ar.Model.Target;
import pro.book.ar.Network.AppController;
import pro.book.ar.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    private ImageView gyroscopeObserver;


    int pos = -1;
     ArrayList<Main> stringArrayList = new ArrayList<>();

    public static String[] tlt =

            {
                    "واقعیت افزوده",
                    "تصاویر",
                    "پادکست",
                    "تکمیل اطلاعات",
                    "درباره سازنده",

            };




    public static int[] img =

            {
                    R.mipmap.ic_home,
                    R.mipmap.ic_gallery,
                    R.mipmap.ic_radio,
                    R.mipmap.ic_profile,
                    R.mipmap.ic_about,

            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setPermission_Camera();
        loadTarget();
        setUpRecyclerView();
        setData();


    }


    /*  set up recycler view */
    private void setUpRecyclerView() {

        rvMain.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvMain.setLayoutManager(linearLayoutManager);




        //set recycler view adapter
        final RecyclerViewAdapter_main recyclerViewAdapter = new RecyclerViewAdapter_main(this, stringArrayList);
        rvMain.setAdapter(recyclerViewAdapter);


        /*  set swipe touch listener */
        SwipeableRecyclerViewTouchListener swipeTouchListener = new SwipeableRecyclerViewTouchListener(rvMain, new SwipeableRecyclerViewTouchListener.SwipeListener() {
            @Override
            public boolean canSwipeLeft(int position) {
                //enable/disable left swipe on checkbox base else use true/false
                //Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                pos = position;




                return true;
            }

            @Override
            public boolean canSwipeRight(int position) {
                //enable/disable right swipe on checkbox base else use true/false
                return false;
            }

            @Override
            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                //on cardview swipe left dismiss update adapter
                //onCardViewDismiss(reverseSortedPositions, stringArrayList, recyclerViewAdapter);
                switch (pos){


                    case 0:
                        startActivity(new Intent(MainActivity.this , ArModulActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this , ActivityGallery.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this , ActivityMusic.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this , EnterProfileActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this , ActivityProfile.class));
                        break;


                }
            }

            @Override
            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                //on cardview swipe right dismiss update adapter
                //onCardViewDismiss(reverseSortedPositions, stringArrayList, recyclerViewAdapter);
            }
        });

        //add item touch listener to recycler view
        rvMain.addOnItemTouchListener(swipeTouchListener);


    }


    private void onCardViewDismiss(int[] reverseSortedPositions, ArrayList<Main> stringArrayList, RecyclerViewAdapter_main recyclerViewAdapter) {
        for (int position : reverseSortedPositions) {
            stringArrayList.remove(position);
            recyclerViewAdapter.notifyItemRemoved(position);
        }
        recyclerViewAdapter.notifyDataSetChanged();
    }

    /**
     * method to return dummy string array list data
     **/




    private void loadTarget() {
        JsonArrayRequest req = new JsonArrayRequest(AppController.URL_TARGET,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.e("TAG---------OK", response.toString());
                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject object = response.getJSONObject(i);
                                String url = object.getString("url");

                                Target target = new Target(
                                        String.valueOf(i),
                                        url.substring(url.lastIndexOf('/') + 1, url.length()),
                                        object.getString("url"),
                                        object.getString("value")
                                );

                                new ImageUtil(MainActivity.this, target.getUrl(), target.getName());

                                AppController.TARGET.add(target);
                            }
                            AppController.TARGET_NUMBERS = response.length();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG------------Error", "Error: " + error.getMessage());
            }
        });
        req.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(req, "loadTarget");
    }

    private void setData() {

        for (int i = 0; i < tlt.length; i++) {
            Main model = new Main();
            model.setTitle(tlt[i]);
            model.setImage(img[i]);
            stringArrayList.add(model);
        }

    }


    //for Permission
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 12234: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //dastrasi dade shode

                } else {
                    new AlertDialog.Builder(this)
                            .setMessage("برای اجرای برنامه باید حتما دسترسی رو به برنامه بدهید")
                            .setCancelable(false)
                            .setNegativeButton("دادن دسترسی", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setPermission_Camera();

                                }
                            })
                            .show();
                }
            }
            return;
        }
    }

    //checkPermission
    public void setPermission_Camera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 12234);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 12234);
            }
        }
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}


