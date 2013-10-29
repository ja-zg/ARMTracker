package hr.hyperactive.tracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.util.Log;

public class PhoneStateBroadcastReciever extends BroadcastReceiver {
	Context m_context;
	
	String m_number = null;
	String m_startTime = null;
	String m_endTime = null;
	
	SharedPreferences m_sharedPrefs;
	Editor editor;
	
	public String PREFS_NUMBER;
	public String PREFS_START_TIME;
	public String PREFS_END_TIME;
	public String PREFS__NAME;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		m_sharedPrefs = m_context.getSharedPreferences("MyPrefs", 0);
    	editor = m_sharedPrefs.edit();
    	
    	Bundle bundle = intent.getExtras();
        if (bundle == null)
            return;
        
        String state = bundle.getString(TelephonyManager.EXTRA_STATE);
        
        if ((state != null) && (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING))) {
        	Log.d("TAG", "incoming call");
        	
        	Uri contactUri = intent.getData();
        	String[] projection = { Phone.DISPLAY_NAME };

        	Cursor cursor = context.getContentResolver().query(contactUri, projection, null, null, null);
        	
        	int columnName = cursor.getColumnIndex(Phone.DISPLAY_NAME);
            String contactName = cursor.getString(columnName);
            
            if(contactName != null) {
            	editor.putString(PREFS__NAME, contactName);
            }
        	
        	m_number = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        	
        	editor.putString(PREFS_NUMBER, m_number);
        	editor.commit();
        
        } else if(state == null) {
        	Log.d("TAG", "outgoing call");
        	
        	Uri contactUri = intent.getData();
        	String[] projection = { Phone.DISPLAY_NAME };
        	
        	Cursor cursor = context.getContentResolver().query(contactUri, projection, null, null, null);
        	
        	int columnName = cursor.getColumnIndex(Phone.DISPLAY_NAME);
            String contactName = cursor.getString(columnName);
            
            if(contactName != null) {
            	editor.putString(PREFS__NAME, contactName);
            }
        	
        	m_number = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
        	
        	editor.putString(PREFS_NUMBER, m_number);
        	editor.commit();
        	
        } else if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
        	Log.d("TAG", "off hook");
        	
        	Time dtstart = new Time(Time.getCurrentTimezone());
            dtstart.setToNow();
            
            m_startTime = dtstart.format("%k:%M:%S");
            
            editor.putString(PREFS_START_TIME, m_startTime);
            editor.commit();
            
        } else if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_IDLE)) {
        	Log.d("TAG", "on idle");
        	
        	Time dtend = new Time(Time.getCurrentTimezone());
        	dtend.setToNow();
            
            m_endTime = dtend.format("%k:%M:%S");
            
            editor.putString(PREFS_END_TIME, m_endTime);
            editor.commit();
        }
    	
        		
	}

}
