package pro.book.ar.Classes;

import android.os.CountDownTimer;

import pro.book.ar.Activity.LoginActivity;

public class MyCountDownTimer extends CountDownTimer {

    // This variable refer to the source activity which use this CountDownTimer object.
    private LoginActivity sourceActivity;

    public void setSourceActivity(LoginActivity sourceActivity) {
        this.sourceActivity = sourceActivity;
    }

    public MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if(this.sourceActivity!=null) {
            // Invoke source activity's tick event method.
            this.sourceActivity.onCountDownTimerTickEvent(millisUntilFinished);
        }
    }

    @Override
    public void onFinish() {
        if(this.sourceActivity!=null)
        {
            // Invoke source activity's tick event method.
            this.sourceActivity.onCountDownTimerFinishEvent();
        }
    }
}