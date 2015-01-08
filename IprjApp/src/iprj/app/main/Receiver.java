package iprj.app.main;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.iprjappteste.data.Avisos_Caip;
import com.iprjappteste.data.Avisos_Prof;
import com.iprjappteste.data.MySQLiteHelper;

import iprj.app.fragments.FragmentChangeActivity;

import com.parse.ParsePushBroadcastReceiver;
import com.parse.ParseUser;

public class Receiver extends ParsePushBroadcastReceiver {

    private static final String TAG = null;
    public String notification;
    public String current_user;
    public String user;
 
    
  
	@Override
    public void onPushOpen(Context context, Intent intent) {
        Log.e("Push", "Clicked");
        
        
        final MySQLiteHelper db = new MySQLiteHelper(context);

        Intent i = new Intent(context, FragmentChangeActivity.class);
        //i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        

 
    		     	
		 try {
		      JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
		      
		      notification = json.getString("alert");
		      current_user = json.getString("user");
		      //user = current_user.toString();
		      
		      if(current_user.equals("caip")){
		 		 
				     db.addAviso(new Avisos_Caip(notification,current_user));		 
		             context.startActivity(i);
		           }
				 
				 else {
				  
					 db.addAviso_Prof(new Avisos_Prof(notification,current_user)); 
					 context.startActivity(i);
				 }
		  

		    } catch (JSONException e) {
		      Log.d(TAG, "JSONException: " + e.getMessage());
		    }
		 
		
		 
			 
    }
	

	/*public void onPushReceive(Context context, Intent intent) {
        Log.e("Push", "Clicked");
        
        
        final MySQLiteHelper db = new MySQLiteHelper(context);

        Intent i = new Intent(context, MainActivity.class);
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        

       
		 Bundle extras = intent.getExtras(); 
		 try {
		      JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
		      
		      notification = json.getString("alert");

		    } catch (JSONException e) {
		      Log.d(TAG, "JSONException: " + e.getMessage());
		    }
		 
		db.addAviso(new Avisos_Caip(notification,""));
      // context.startActivity(i);
    }*/
	
	@Override
	public void onPushDismiss(Context context, Intent intent) {
        Log.e("Push", "Clicked");
                
        final MySQLiteHelper db = new MySQLiteHelper(context);

        Intent i = new Intent(context, FragmentChangeActivity.class);
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               
		 try {
		      JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
		      
		      notification = json.getString("alert");

		    } catch (JSONException e) {
		      Log.d(TAG, "JSONException: " + e.getMessage());
		    }
		 
		db.addAviso(new Avisos_Caip(notification,""));
       //context.startActivity(i);
    }
}