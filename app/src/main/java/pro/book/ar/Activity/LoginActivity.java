package pro.book.ar.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pro.book.ar.Classes.BaseToolBarActivity;
import pro.book.ar.R;
import za.co.riggaroo.materialhelptutorial.TutorialItem;
import za.co.riggaroo.materialhelptutorial.tutorial.MaterialTutorialActivity;

public class LoginActivity extends BaseToolBarActivity  {

    private static final int REQUEST_CODE = 1234;
    SharedPreferences prefs;
    @BindView(R.id.password_field)
    EditText passwordField;
    @BindView(R.id.t9_key_1)
    TextView t9Key1;
    @BindView(R.id.t9_key_2)
    TextView t9Key2;
    @BindView(R.id.t9_key_3)
    TextView t9Key3;
    @BindView(R.id.t9_key_4)
    TextView t9Key4;
    @BindView(R.id.t9_key_5)
    TextView t9Key5;
    @BindView(R.id.t9_key_6)
    TextView t9Key6;
    @BindView(R.id.t9_key_7)
    TextView t9Key7;
    @BindView(R.id.t9_key_8)
    TextView t9Key8;
    @BindView(R.id.t9_key_9)
    TextView t9Key9;
    @BindView(R.id.t9_key_clear)
    TextView t9KeyClear;
    @BindView(R.id.t9_key_0)
    TextView t9Key0;
    @BindView(R.id.t9_key_backspace)
    TextView t9KeyBackspace;

    


    private GoogleApiClient client;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        //swipe
        startActivity(LockscreenActivity.class);

        // Start code for Intro App
        prefs = getSharedPreferences("pro.rasht.ar", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            loadTutorial();
            prefs.edit().putBoolean("firstrun", false).commit();
        }
        // End code for Intro App


        //keyboard




    }




    public void tvOnClickLogin(View v) {
       
        switch (v.getId()) {
            case R.id.t9_key_0:
                passwordField.append("0");
                break;
            case R.id.t9_key_1:
                passwordField.append("1");
                break;
            case R.id.t9_key_2:
                passwordField.append("2");
                break;
            case R.id.t9_key_3:
                passwordField.append("3");
                break;
            case R.id.t9_key_4:
                passwordField.append("4");
                break;
            case R.id.t9_key_5:
                passwordField.append("5");
                break;
            case R.id.t9_key_6:
                passwordField.append("6");
                break;
            case R.id.t9_key_7:
                passwordField.append("7");
                break;
            case R.id.t9_key_8:
                passwordField.append("8");
                break;
            case R.id.t9_key_9:
                passwordField.append("9");
                break;
            case R.id.t9_key_clear: { // handle clear button
                passwordField.setText(null);
            }
            break;
            case R.id.t9_key_backspace: { // handle backspace button
                // delete one character
                Editable editable = passwordField.getText();
                int charCount = editable.length();
                if (charCount > 0) {
                    editable.delete(charCount - 1, charCount);
                }


            }
            break;

        }
    }


    // Start code for Intro App
    public void loadTutorial() {
        Intent mainAct = new Intent(this, MaterialTutorialActivity.class);
        mainAct.putParcelableArrayListExtra(MaterialTutorialActivity.MATERIAL_TUTORIAL_ARG_TUTORIAL_ITEMS, getTutorialItems(this));
        startActivityForResult(mainAct, REQUEST_CODE);

    }

    private ArrayList<TutorialItem> getTutorialItems(Context context) {
        TutorialItem tutorialItem1 = new TutorialItem(R.string.slide1_title, R.string.slide1_subtitle,
                R.color.slide1, R.drawable.tut_page_3_foreground, R.drawable.tut_page_3_foreground);
        TutorialItem tutorialItem2 = new TutorialItem(R.string.slide2_title, R.string.slide2_subtitle,
                R.color.slide2, R.drawable.tut_page_3_foreground, R.drawable.tut_page_3_foreground);
        TutorialItem tutorialItem3 = new TutorialItem(R.string.slide3_title, R.string.slide3_subtitle,
                R.color.slide3, R.drawable.tut_page_3_foreground, R.drawable.tut_page_3_foreground);
        TutorialItem tutorialItem4 = new TutorialItem(R.string.slide4_title, R.string.slide4_subtitle,
                R.color.slide4, R.drawable.tut_page_3_foreground, R.drawable.tut_page_3_foreground);


        ArrayList<TutorialItem> tutorialItems = new ArrayList<>();
        tutorialItems.add(tutorialItem1);
        tutorialItems.add(tutorialItem2);
        tutorialItems.add(tutorialItem3);
        tutorialItems.add(tutorialItem4);

        return tutorialItems;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //    super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            Toast.makeText(this, "Tutorial finished", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        /*client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://pro.rasht.ar/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);*/
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        /*Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://pro.rasht.ar/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();*/
    }
    // End code for Intro App

    // Swipe
    public void startActivity(Class<?> clazz) {
        startActivity(new Intent(LoginActivity.this, clazz));
    }

    protected <T extends View> T $(@IdRes int id) {
        return (T) super.findViewById(id);
    }


}
