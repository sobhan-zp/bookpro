package pro.book.ar.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pro.book.ar.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

;

public class ActivityProfile extends Activity {


    @BindView(R.id.btn_insert_profile)
    Button btnInsertProfile;

    Unbinder unbinder;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        //java code


        ///java code


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }




}
