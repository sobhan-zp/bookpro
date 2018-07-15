package pro.book.ar.Activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import butterknife.ButterKnife;
import pro.book.ar.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class ActivityMusic extends Activity implements QRCodeReaderView.OnQRCodeReadListener {

    private static final int MY_PERMISSION_REQUEST_CAMERA = 0;

    private QRCodeReaderView qrCodeReaderView;
    private CheckBox flashlightCheckbox;
    private CheckBox enableDecodingCheckBox;
    private boolean isRunning= false;
    private TextView resultTextView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        ButterKnife.bind(this);

        //java code




        flashlightCheckbox = (CheckBox)findViewById(R.id.flashlight_checkbox);
        enableDecodingCheckBox = (CheckBox) findViewById(R.id.enable_decoding_checkbox);
        qrCodeReaderView = (QRCodeReaderView)findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnQRCodeReadListener(ActivityMusic.this);
        resultTextView = (TextView)findViewById(R.id.result_text_view);

        qrCodeReaderView.setQRDecodingEnabled(true);

        qrCodeReaderView.setAutofocusInterval(2000L);
        qrCodeReaderView.setTorchEnabled(true);
        qrCodeReaderView.setFrontCamera();
        qrCodeReaderView.setBackCamera();

        flashlightCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                qrCodeReaderView.setTorchEnabled(b);

            }
        });


        enableDecodingCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                qrCodeReaderView.setQRDecodingEnabled(isChecked);
            }
        });






        ///java code

    }


    @Override
    public void onResume() {
        super.onResume();
        qrCodeReaderView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
    }



    @Override
    public void onQRCodeRead(String text, PointF[] points  )
    {
        if(isRunning)
            return;

        isRunning = true;

        //Your code

        resultTextView.setText(text+"");

        //your code


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
