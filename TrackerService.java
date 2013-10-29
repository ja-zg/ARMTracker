package hr.hyperactive.tracker;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class TrackerService extends Service {
	PhoneStateBroadcastReciever receiver;
	
	@Override
    public void onCreate() {
		Log.d("TAG", "TrackerService onCreate started");
		receiver = new PhoneStateBroadcastReciever();
		
		IntentFilter filter = new IntentFilter();
        filter.addAction(android.telephony.TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        registerReceiver(receiver, filter);
	}
	
	@Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "starting service", Toast.LENGTH_SHORT).show();
	    return Service.START_NOT_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onDestroy() {
		unregisterReceiver(receiver);
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
	}
	
}
