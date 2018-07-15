package pro.book.ar.Activity;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gw.swipeback.SwipeBackLayout;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pro.book.ar.Classes.BaseSwipeBackActivity;
import pro.book.ar.Classes.MyCountDownTimer;
import pro.book.ar.Network.AppController;
import pro.book.ar.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class LockscreenActivity extends BaseSwipeBackActivity {

    ShimmerTextView tv;
    Shimmer shimmer;
    @BindView(R.id.swipeBackLayout)
    SwipeBackLayout swipeBackLayout;
    @BindView(R.id.et_phone_lock)
    EditText etPhoneLock;

    final int maxLength = 10;
    public static MyCountDownTimer myCountDownTimer;
    @BindView(R.id.btn_sendNum_lock)
    Button btnSendNumLock;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lockscreen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        //loadTarget();


        tv = (ShimmerTextView) findViewById(R.id.shimmer_tv);
        shimmer = new Shimmer();


        shimmer.setRepeatCount(-1)
                .setDuration(1200)
                .setStartDelay(300)
                .setDirection(Shimmer.ANIMATION_DIRECTION_RTL)
                .setAnimatorListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });


        shimmer.start(tv);

        swipeBackLayout.setSwipeFromEdge(true);
        swipeBackLayout.setSwipeBackFactor(1f);

        btnSendNumLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    myCountDownTimer = new MyCountDownTimer(60 * 1000, 1000);
                    myCountDownTimer.setSourceActivity((LoginActivity) AppController.context);
                    Toast.makeText(LockscreenActivity.this, "شماره ارسال شد", Toast.LENGTH_SHORT).show();
                    myCountDownTimer.start();

                //Server Code

            }
        });






        etPhoneLock.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (etPhoneLock.getText().toString().length() == maxLength) {
                    tv.setVisibility(View.VISIBLE);
                    swipeBackLayout.setSwipeFromEdge(false);
                    btnSendNumLock.setEnabled(true);

                } else {
                    tv.setVisibility(View.GONE);
                    btnSendNumLock.setEnabled(false);
                }

            }
        });


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}