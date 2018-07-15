package pro.book.ar.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pro.book.ar.Classes.BaseToolBarActivity;
import pro.book.ar.Classes.MyCountDownTimer;
import pro.book.ar.Network.AppController;
import pro.book.ar.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import za.co.riggaroo.materialhelptutorial.TutorialItem;
import za.co.riggaroo.materialhelptutorial.tutorial.MaterialTutorialActivity;

public class LoginActivity extends BaseToolBarActivity {

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
    @BindView(R.id.tv_resms_login)
    TextView tvResmsLogin;
    @BindView(R.id.tv_changeNum_login)
    TextView tvChangeNumLogin;


    private GoogleApiClient client;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        AppController.context = this;



        //swipe
        startActivity(LockscreenActivity.class);
        //Send Verify Code
        tvResmsLogin.setEnabled(false);


    }

    public void onCountDownTimerFinishEvent()
    {
        this.tvResmsLogin.setEnabled(true);
    }

    public void onCountDownTimerTickEvent(long millisUntilFinished)
    {
        long leftSeconds = millisUntilFinished / 1000;

        String sendButtonText = "ثانیه " + (leftSeconds -1) ;

        if((leftSeconds -1 )==0)
        {
            sendButtonText = "ارسال مجدد کد";
            tvResmsLogin.setEnabled(true);
            LockscreenActivity.myCountDownTimer.onFinish();

        }

        this.tvResmsLogin.setText(sendButtonText);
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
           case R.id.tv_resms_login:{

                LockscreenActivity.myCountDownTimer = new MyCountDownTimer(60 * 1000, 1000);
                LockscreenActivity.myCountDownTimer.setSourceActivity((LoginActivity) AppController.context);
                LockscreenActivity.myCountDownTimer.start();

            }
            break;
            case R.id.tv_changeNum_login:{
                Intent i = new Intent(LoginActivity.this , LockscreenActivity.class);
                startActivity(i);
            }
            break;


        }

        if (passwordField.getText().toString().equals("00000000")) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    // Swipe
    public void startActivity(Class<?> clazz) {
        startActivity(new Intent(LoginActivity.this, clazz));
    }

    protected <T extends View> T $(@IdRes int id) {
        return (T) super.findViewById(id);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



}
