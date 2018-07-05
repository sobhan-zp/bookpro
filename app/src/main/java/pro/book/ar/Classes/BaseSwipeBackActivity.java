package pro.book.ar.Classes;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gw.swipeback.SwipeBackLayout;

import pro.book.ar.R;

/**
 * Created by GongWen on 17/8/25.
 */

public abstract class BaseSwipeBackActivity extends BaseToolBarActivity {
    protected SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSwipeBackLayout = (SwipeBackLayout) findViewById(R.id.swipeBackLayout);
        mSwipeBackLayout.setDirectionMode(SwipeBackLayout.FROM_LEFT);
    }


}

