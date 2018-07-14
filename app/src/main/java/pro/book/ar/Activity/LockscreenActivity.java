package pro.book.ar.Activity;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.gw.swipeback.SwipeBackLayout;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pro.book.ar.Classes.BaseSwipeBackActivity;
import pro.book.ar.Classes.ImageUtil;
import pro.book.ar.Model.Target;
import pro.book.ar.Network.AppController;
import pro.book.ar.R;


public class LockscreenActivity extends BaseSwipeBackActivity {

    ShimmerTextView tv;
    Shimmer shimmer;
    @BindView(R.id.swipeBackLayout)
    SwipeBackLayout swipeBackLayout;
    @BindView(R.id.et_phone_lock)
    EditText etPhoneLock;

    final int maxLength = 10;

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

        swipeBackLayout.setSwipeBackListener(new SwipeBackLayout.OnSwipeBackListener() {
            @Override
            public void onViewPositionChanged(View mView, float swipeBackFraction, float SWIPE_BACK_FACTOR) {



            }

            @Override
            public void onViewSwipeFinished(View mView, boolean isEnd) {

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

                if(etPhoneLock.getText().toString().length() == maxLength){
                    tv.setVisibility(View.VISIBLE);

                    //swipeBackLayout.setEnabled(true);

                }else {
                    tv.setVisibility(View.GONE);
                    //swipeBackLayout.setEnabled(false);
                }

            }
        });



    }



}