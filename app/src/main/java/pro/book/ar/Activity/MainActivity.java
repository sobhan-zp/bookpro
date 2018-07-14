package pro.book.ar.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import pro.book.ar.Adapter.RecyclerViewAdapter_main;
import pro.book.ar.Classes.BlureImage;
import pro.book.ar.Classes.ImageUtil;
import pro.book.ar.Classes.RuntimePermissionHelper;
import pro.book.ar.Classes.SavePref;
import pro.book.ar.Classes.SwipeableRecyclerViewTouchListener;
import pro.book.ar.Model.Main;
import pro.book.ar.Model.Target;
import pro.book.ar.Network.AppController;
import pro.book.ar.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.btn_imagepicker_main)
    ImageView btnImagepickerMain;
    @BindView(R.id.ll_img_back_main)
    LinearLayout llImgBackMain;
    private ImageView gyroscopeObserver;

    SavePref save;

    int pos = -1;
    ArrayList<Main> stringArrayList = new ArrayList<>();
    //choose bg
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;

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

        RunTimePermission();

        save = new SavePref(this);
        ButterKnife.bind(this);
        setPermission_Camera();
        setUpRecyclerView();
        setData();
        showPictureDialog();
        loadimgGg();




    }


    private void RunTimePermission(){

        if (Build.VERSION.SDK_INT >= 23) {
            RuntimePermissionHelper runtimePermissionHelper = RuntimePermissionHelper.getInstance(this);
            if (runtimePermissionHelper.isAllPermissionAvailable()) {
            // All permissions available. Go with the flow
            } else {
            // Few permissions not granted. Ask for ungranted permissions
                runtimePermissionHelper.setActivity(this);
                runtimePermissionHelper.requestPermissionsIfDenied();
            }
        }else{
            // SDK below API 23. Do nothing just go with the flow.
        }

    }

    private void loadimgGg() {

        try {

            if(!save.load("imgBg" , "").equals("")){

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                BitmapDrawable background = new BitmapDrawable(BitmapFactory.decodeFile(save.load("imgBg" , ""), options));
                llImgBackMain.setBackgroundDrawable(background);
            }

        }catch (Exception e){

        }


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
                switch (pos) {


                    case 0:
                        startActivity(new Intent(MainActivity.this, ArModulActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, ActivityGallery.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, ActivityMusic.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, EnterProfileActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, ActivityProfile.class));
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




    private void setData() {

        for (int i = 0; i < tlt.length; i++) {
            Main model = new Main();
            model.setTitle(tlt[i]);
            model.setImage(img[i]);
            stringArrayList.add(model);
        }

    }


    private void showPictureDialog() {

        btnImagepickerMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                AlertDialog.Builder pictureDialog = new AlertDialog.Builder(MainActivity.this);
                String[] pictureDialogItems = {
                        "انتخاب از گالری",
                        "انتخاب از دوربین"};
                pictureDialog.setItems(pictureDialogItems,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        choosePhotoFromGallary();
                                        break;
                                    case 1:
                                        takePhotoFromCamera();
                                        break;
                                }
                            }
                        });

                pictureDialog.show();

            }
        });
    }


    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(MainActivity.this, "عکس ذخیره شد", Toast.LENGTH_SHORT).show();

                    //blure Gallery
                    bitmap = new BlureImage(bitmap , 1 , 6).getBitmap();
                    BitmapDrawable background = new BitmapDrawable(bitmap);
                    llImgBackMain.setBackgroundDrawable(background);




                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "لغو شد", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

            //blure Camera
            thumbnail = new BlureImage(thumbnail , 1 , 6).getBitmap();
            BitmapDrawable background = new BitmapDrawable(thumbnail);
            llImgBackMain.setBackgroundDrawable(background);
            saveImage(thumbnail);
            Toast.makeText(MainActivity.this, "عکس ذخیره شد", Toast.LENGTH_SHORT).show();
        }
    }


    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.e("TAG", "File Saved::---->" + f.getAbsolutePath());

            save.save("imgBg" , f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            Log.e("TAG", "ERROR::---->" + e1.getMessage());
            e1.printStackTrace();
        }

        return "";
    }


    //for Permission
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 12234: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //dastrasi dade shode

                }
            }
            return;
        }
    }

    //checkPermission
    public void setPermission_Camera()          {
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


