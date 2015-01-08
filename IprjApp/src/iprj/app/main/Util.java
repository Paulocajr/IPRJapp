package iprj.app.main;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Util {

	public static void goToGitHub(Context context) {
		Uri uriUrl = Uri.parse("http://github.com/jfeinstein10/slidingmenu");
		Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl); 
		context.startActivity(launchBrowser);
	}

	public static JSONObject parseJson(String request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
