package hr.hyperactive.tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TrackerSettingsActivity extends Activity {	
	PhoneStateBroadcastReciever prefs;
	Handler handler;
	
	EditText timerDelay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.tracker_settings);
		
		timerDelay = (EditText) findViewById(R.id.editText_Set_Timer);
			
		Intent intent = new Intent(this, TrackerService.class);
		startService(intent);
		Toast.makeText(getApplicationContext(), "paljenje servicea", Toast.LENGTH_LONG).show();
		
	}
	
	public void click(View v) {
		String text = timerDelay.getText().toString();
		int TIME_DELAY = 0;
		try {
			TIME_DELAY = Integer.parseInt(text) * 1000;
		} catch(NumberFormatException e) {
			Log.e("TAG", e.toString());
		}
		
		timerDelay.setTextColor(getResources().getColor(R.color.grey));
		
		handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_LONG).show();				
			}
		}, TIME_DELAY);
		
	}
	
}
