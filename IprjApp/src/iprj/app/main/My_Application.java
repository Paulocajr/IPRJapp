package iprj.app.main;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.SignUpCallback;

public class My_Application extends Application {
	
	@Override
	@SuppressWarnings("deprecation")
	public void onCreate() {
		  Parse.initialize(this, "8FttRlqaAUPabxXzuLeTMbmgAA389L7oVbFaSqjj", "6sFwktakiPYSDg1NKzgP18yanSjlB9CQKFGUOShs");
		  // Also in this method, specify a default Activity to handle push notifications
		  PushService.setDefaultPushCallback(this, Notification.class);
		// PushService.subscribe(this, "Everyone", Notification.class);
		
		  ParseInstallation.getCurrentInstallation().saveInBackground();
		  ParseObject.registerSubclass(Message.class);
				  
	    // ParsePush.subscribeInBackground("Giants");

		  ParseUser user = new ParseUser();			 
		  user.setUsername("caip");
		  user.setPassword("piacpiac");
		  user.setEmail("Caip.iprj@gmail.com");
		  
		  ParseUser user_2 = new ParseUser();		  
		  user_2.setUsername("professor");
		  user_2.setPassword("prof_iprj");
		  user_2.setEmail("Caip.iprj@gmail.com");
		  
			 		    
		 user.signUpInBackground(new SignUpCallback() {
		    @Override
			public void done(ParseException e) {
		      if (e == null) {
		        // Hooray! Let them use the app now.
		      } else {
		        // Sign up didn't succeed. Look at the ParseException
		        // to figure out what went wrong
		      }
		    }
		  });
		  
		}

}
