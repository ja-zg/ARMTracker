package hr.hyperactive.armtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

public class CallDurationReceiver extends BroadcastReceiver {
	SharedPreferences mSharedPrefernce;
    Editor editor;
    
    String number, start, end;
    int startTime, endTime, duration;

	@Override
	public void onReceive(Context context, Intent intent) {
		mSharedPrefernce = context.getSharedPreferences("MyPref", 0);
        editor = mSharedPrefernce.edit();
        
        Bundle bundle = intent.getExtras();
        if (bundle == null)
            return;
        
        number = null;
        start = null;
        end = null;
        startTime = 0;
        endTime = 0;
        duration = 0;
		
        String state = bundle.getString(TelephonyManager.EXTRA_STATE);
        if ((state != null)
                && (state
                        .equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING))) {

            Log.i("info", "Phone Ringing..");

            number = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Log.i("info", "Incomng Number: " + number);
            
            Time today = new Time(Time.getCurrentTimezone());
            today.setToNow();
            
            editor.putString("number", number);
            
            editor.commit();
            
            Toast.makeText(
                    context,
                    "Detecting call numbers: "
                            + number, Toast.LENGTH_SHORT).show();
            
        } else if (state == null) {
            number = bundle.getString(Intent.EXTRA_PHONE_NUMBER);
            Log.i("info", "Outgoing Number: " + number);
            Time today = new Time(Time.getCurrentTimezone());
            today.setToNow();

            time = today.format("%k:%M:%S");

            // putting the values into the SharedPreferences
            e.putString("number", number);
            e.putString("Type", calltype);
            e.putString("date", date);
            e.putString("time", time);
            e.commit();

            Toast.makeText(
                    context,
                    "Detect Calls sample application\nOutgoing number: "
                            + number, Toast.LENGTH_SHORT).show();

        }
        
	}

}
