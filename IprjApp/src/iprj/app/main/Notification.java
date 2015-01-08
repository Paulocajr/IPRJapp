package iprj.app.main;

import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Notification extends Activity{
	
	private static final String TAG = null;
	public String notificationText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);	
		
		 setContentView(R.layout.notification);
		 
		 Intent intent = getIntent();
		 try {
		      JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));

		      notificationText = json.getString("alert");
		    } catch (JSONException e) {
		      Log.d(TAG, "JSONException: " + e.getMessage());
		    }
		// String jsonData = extras.getString( "com.parse.Data" );
			 
		
		 TextView noti = (TextView) findViewById(R.id.textView1);
		 noti.setText(notificationText);
		 
		 		}

}


