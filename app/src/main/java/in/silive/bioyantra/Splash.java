package in.silive.bioyantra;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.florent37.viewanimator.ViewAnimator;

/**
 * Created by akriti on 1/7/16.
 */
public class Splash extends AppCompatActivity {
    RelativeLayout splash;
    Context context;
    ImageView image;
    TextView text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        splash = (RelativeLayout) findViewById(R.id.splash);
        context = getApplicationContext();
        image = (ImageView) findViewById(R.id.image);
        text = (TextView) findViewById(R.id.text);
        ViewAnimator
                .animate(image)
                .translationY(-1000, 0)
                .alpha(0,1)
                .andAnimate(text)
                .dp().translationX(-30, 0)
                .descelerate()
                .duration(2000)
                .thenAnimate(image)
                .scale(1f, 0.5f, 1f)
                .accelerate()
                .duration(1000)
                .start();
        checkConnection();
    }
    public void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null) {
            Snackbar snackbar = Snackbar
                    .make(splash, "No internet connection!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkConnection();
                        }
                    });

// Changing message text color
            snackbar.setActionTextColor(Color.RED);

// Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        } else {


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 4000);


        }

    }

}
