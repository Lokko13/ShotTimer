package lokko.com.shottimer;

import android.os.AsyncTask;

/**
 * Created by Shaun on 10/2/2016.
 */

public class StopTimer extends AsyncTask<Void, Void, Void> {

    private MainActivity m;

    public StopTimer(MainActivity m) {
//        For the purpose of makuha mo lang yung variables and methods na sinet mo sa MainActivity
        this.m = m;
    }

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

    @Override
    protected void onPostExecute(Void aVoid) {
//        This is optional. Kung may balak ka pa after mo i-stop yung timer, dito mo lang ilagay (say a Toast (gamitin mo lang yung MainActivity as a context requirement)
    }
}
