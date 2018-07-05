package pro.book.ar.Data;


import java.util.ArrayList;

import pro.book.ar.Model.GalleryModel;
import pro.book.ar.R;


public class Gallery_app {

    ArrayList<GalleryModel> data = new ArrayList<>();

    String[] title =new String[]{


            "قائمیه کرف آباد",
            "مسجد الفتح چمارسرا",
            "مسجد مستوفی استادسرا",
            "مسجد درویش مخلص",
            "مسجد حاج صفی",
            "مسجد حمزه"


    };


    int[] image =new int[]{
            R.drawable.amozesh1,
            R.drawable.amozesh2,
            R.drawable.amozesh3,
            R.drawable.amozesh4,
            R.drawable.amozesh5,
            R.drawable.amozesh6


    };



    public Gallery_app() {
        setData();
    }

    private void setData() {

        for (int i = 0; i < title.length; i++) {
            GalleryModel model = new GalleryModel();
            model.setTitle(title[i]);
            //model.setDescription(description[i]);
            model.setImage(image[i]);
            //model.setText1(text[i]);
            data.add(model);
        }
    }

    public ArrayList<GalleryModel> getData(){
        return data;
    }

}
