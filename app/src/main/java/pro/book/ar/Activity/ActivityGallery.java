package pro.book.ar.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import pro.book.ar.Adapter.GalleryRecyclerAdapter;
import pro.book.ar.Data.Gallery_app;
import pro.book.ar.Model.GalleryModel;
import pro.book.ar.R;


public class ActivityGallery extends Activity {




    private List<GalleryModel> itemList = new ArrayList<>();
    private RecyclerView rv_gallery;
    private GalleryRecyclerAdapter adapter;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        //java code




        rv_gallery = (RecyclerView)findViewById(R.id.rv_gallery);
        adapter = new GalleryRecyclerAdapter(ActivityGallery.this, itemList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(ActivityGallery.this);
        rv_gallery.setLayoutManager(mLayoutManager);
        rv_gallery.setItemAnimator(new DefaultItemAnimator());
        rv_gallery.setAdapter(adapter);




        itemList.addAll(new Gallery_app().getData());
        adapter.notifyDataSetChanged();







        ///java code

    }





}
