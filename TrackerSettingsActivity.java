package hr.hyperactive.tracker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TrackerSettingsActivity extends Activity {	
	PhoneStateBroadcastReciever prefs;
	int TIME_DELAY = 5000;
	Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.tracker_settings);
		
		EditText timerDelay = (EditText) findViewById(R.id.editText_Set_Timer);
		String text = timerDelay.getText().toString();
		int time = Integer.parseInt(text);
	
		Intent intent = new Intent(this, TrackerService.class);
		startService(intent);
		Toast.makeText(getApplicationContext(), "Service se upalio", Toast.LENGTH_LONG).show();
		
	}
	
	public void click(View v) {
		handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), "Toast poruka", Toast.LENGTH_LONG).show();		
			}
		}, TIME_DELAY);
	}
	
	private void updateDataLocally() {
		SharedPreferences m_sharedPrefs = getSharedPreferences("MyPrefs", 0);
		String number = m_sharedPrefs.getString(prefs.PREFS_NUMBER, "1");
		String startTime = m_sharedPrefs.getString(prefs.PREFS_START_TIME, "2");
		String endTime = m_sharedPrefs.getString(prefs.PREFS_END_TIME, "3");
		Toast.makeText(getApplicationContext(), number + " " + startTime + " " + endTime, Toast.LENGTH_LONG).show();
	}
	
}
