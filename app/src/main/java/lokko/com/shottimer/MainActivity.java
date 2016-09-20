package lokko.com.shottimer;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.AsyncTask;
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

    private boolean started = false;

    long timeInMilliseconds = 0L;
    long updatedTime = 0L;
    long timeOnStart = 0L;
    long timeOnStop = 0L;

    Handler timerHandler = new Handler();

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            if(!started){
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                mp.start();
                timeOnStart = SystemClock.uptimeMillis();
                started = true;
            }
            timeInMilliseconds = SystemClock.uptimeMillis() - timeOnStart + timeOnStop;
            updatedTime = timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timer.setText("" + mins + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));
            timerHandler.postDelayed(this, 0);
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
                    if(!started){
                        Random r = new Random();
                        int l = 1000;
                        int h = 3000;
                        int soundDelay = r.nextInt(h-l) + l;
                        timerHandler.postDelayed(updateTimerThread, soundDelay);
                    }
                    else {
                        timeOnStart = SystemClock.uptimeMillis();
                        timerHandler.postDelayed(updateTimerThread, 0);
                    }
                }
                else{
                    timeOnStop = updatedTime;
                    b.setText(getResources().getString(R.string.start));
                    timerHandler.removeCallbacks(updateTimerThread);
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                started = false;
                timeOnStop = 0L;
                timeOnStart = 0L;
                timeInMilliseconds = 0L;
                updatedTime = 0L;
                timer.setText(getResources().getString(R.string.timeVal));
                startStopButton.setText(getResources().getString(R.string.start));
                timerHandler.removeCallbacks(updateTimerThread);
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

    public class StopTimer extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

//            lagay mo lang dito yung code for stopping the timer

            /*

            gawa ka ng instance ng StopTimer, tapos call mo yung execute(); Matic gagawin nya na yung AsyncTask
            [blank lang since vinoid ko yung parameters (since napansin ko lahat nasa MainActivity naman]
            parts kasi ng AsyncTask <Parameters, Progress, Result> pwede mo ivoid lahat

            * Wag ka na gumamit ng handler since background thread naman to
            * Wag mo din pala i-initialize yung AsyncTask statically (rekta .execute na agad. rerequire nya Runnable. Di nya makukuha yung nasa doInBackground (ex. StopTimer.execute(...))

            Usage:
            StopTimer s = new StopTimer();
            s.execute();

             */

            return null;
        }
    }
}
