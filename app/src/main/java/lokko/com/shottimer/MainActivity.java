package lokko.com.shottimer;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView timer;
    private Button startStopButton;
    private Button resetButton;

    private long startTime = 0L;
    private boolean started = false;

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    Handler handler = new Handler();

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            if(started == false){
                startTime = SystemClock.uptimeMillis();
                started = true;
            }
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timer.setText("" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));
            handler.postDelayed(this, 0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        timer = (TextView) findViewById(R.id.txt_timer_display);
        startStopButton = (Button) findViewById(R.id.btn_startstop);
        resetButton = (Button) findViewById(R.id.btn_reset);

        startStopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if(b.getText().toString() == getResources().getString(R.string.start)){
                    b.setText(getResources().getString(R.string.stop));
                    Random r = new Random();
                    int l = 1000;
                    int h = 5000;
                    int soundDelay = r.nextInt(h-l) + l;
                    handler.postDelayed(updateTimerThread, soundDelay);
                }
                else{
                    b.setText(getResources().getString(R.string.start));
                    timeSwapBuff += timeInMilliseconds;
                    handler.removeCallbacks(updateTimerThread);
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = 0L;
                started = false;
                timeInMilliseconds = 0L;
                timeSwapBuff = 0L;
                updatedTime = 0L;
                timer.setText(getResources().getString(R.string.timeVal));
                startStopButton.setText(getResources().getString(R.string.start));
                handler.removeCallbacks(updateTimerThread);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
