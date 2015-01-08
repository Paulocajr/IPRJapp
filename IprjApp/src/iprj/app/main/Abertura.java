package iprj.app.main;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.android.Facebook;
import com.facebook.model.GraphUser;

import iprj.app.fragments.FragmentChangeActivity;
import iprj.app.main.Login;
import iprj.app.main.Tutorial_1;



import com.iprjappteste.data.Curso;
import com.iprjappteste.data.MySQLiteHelper;
import com.iprjappteste.data.Usuario;



public class Abertura extends Activity{

    private int AberturaRes = -1;
    protected static final String TAG = "Abertura";    
    public ProgressBar progress; 
    public String Userid;
    private static final String APP_ID = "1458439531078582";
    public String User_name;
    private Facebook facebook;
    private int progressStatus = 0;
	//private ExamplesAdapter mExamplesAdapter;

    private Handler handler = new Handler();
    public MySQLiteHelper db = new MySQLiteHelper(Abertura.this);
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);		
	
	     if (savedInstanceState != null)
	 		
	    	 AberturaRes = savedInstanceState.getInt("AberturaRes");
	
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.abertura);
	facebook = new Facebook(APP_ID);
	progress = (ProgressBar) findViewById(R.id.progressBar1); 
	final Button  Button = (Button) findViewById(R.id.button1);
	
	
	   context = this;
     sharedPreferences = PreferenceManager
             .getDefaultSharedPreferences(context);
     
	
     switch (checkAppStart()) {
     
     
     case NORMAL:
         // We don't want to get on the user's nerves
     	
      //Button.setVisibility(View.GONE);
         Button.setVisibility(View.GONE);
         final List<Curso> cursos = db.getAllCursos();         
         	        	         
         new Thread(new Runnable() {
             public void run() {
                while (progressStatus < 100) {
                   progressStatus += 1;
            // Update the progress bar and display the 
                                 //current value in the text view
            handler.post(new Runnable() {
            public void run() {
               progress.setProgress(progressStatus);
              
            }
            	            
                });
            
  	        	            
                try {
                   // Sleep for 200 milliseconds. 
                                 //Just to display the progress slowly
                   Thread.sleep(50);
                } catch (InterruptedException e) {
                   e.printStackTrace();
                }
             }
                if(cursos.isEmpty()){
  		    		       
                	       //Button.setVisibility(View.VISIBLE);
                	       Intent intent = new Intent();
		        		   intent.setClass(Abertura.this,Login.class);
			               startActivity(intent);
			               finish(); 			                
		    	   }
                
                else {
                
		               Intent intent = new Intent();
	                   intent.setClass(Abertura.this,FragmentChangeActivity.class);
	        	       startActivity(intent);
	        	       finish();
                           }
                }}).start();
      	        	     	        	     	       	
 	 
         break;
     case FIRST_TIME_VERSION:
         // TODO show what's new
         break;
     case FIRST_TIME:	        	
     	
     Button.setOnClickListener(new View.OnClickListener() {	    		    
             @Override
            public void onClick(View v) {
             	
             	 Intent intent = new Intent();
             	 intent.setClass(Abertura.this,Tutorial_1.class);
              	 startActivity(intent);
              	 finish();
             }
             
 		});	
     
    	 
         // TODO show a tutorial
         break;
     default:
         break;
     }
					
	
    }
    
    
    public void login_facebook(){
    	
    	Session.openActiveSession(this, true, new Session.StatusCallback() {

    	    
    	    @Override
    	    public void call(final Session session, SessionState state, Exception exception) {
    	    	if (session.isOpened()) {
    	    		  	
    	    		Request.newMeRequest(session, new Request.GraphUserCallback() {

    	    			  @Override
    	    			  public void onCompleted(GraphUser user, Response response) {
    	    				  
    	    				  if (user != null) {
    	    					  
    	    					//welcome = (TextView) findViewById(R.id.welcome);    					
    	    					Userid = user.getId();
    	    					User_name= user.getFirstName();
    	    					   					 		    					
    	    				
    	    					}
    	    			  }
    	    			}).executeAsync();
    	    		}
    	    }
    	  }); 
    	
    }
    
	 @Override
	 public void onActivityResult(int requestCode, int resultCode, Intent data) {		 
	     super.onActivityResult(requestCode, resultCode, data);
	     
	     Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	 }
	 /**
	  * Distinguishes different kinds of app starts: <li>
	  * <ul>
	  * First start ever ({@link #FIRST_TIME})
	  * </ul>
	  * <ul>
	  * First start in this version ({@link #FIRST_TIME_VERSION})
	  * </ul>
	  * <ul>
	  * Normal app start ({@link #NORMAL})
	  * </ul>
	  * 
	  * @author schnatterer
	  * 
	  */
	 public enum AppStart {
	     FIRST_TIME, FIRST_TIME_VERSION, NORMAL;
	 }


	 // Note: These variables need to be initialized see below
	 private Context context;
	 private SharedPreferences sharedPreferences;

	 /**
	  * The app version code (not the version name!) that was used on the last
	  * start of the app.
	  */
	 private static final String LAST_APP_VERSION = "1.0";

	 /**
	  * Caches the result of {@link #checkAppStart()}. To allow idempotent method
	  * calls.
	  */
	 private static AppStart appStart = null;

	 /**
	  * Finds out started for the first time (ever or in the current version).
	  * 
	  * @return the type of app start
	  */
	 public AppStart checkAppStart() {
	     if (appStart == null) {
	         PackageInfo pInfo;
	         try {
	             pInfo = context.getPackageManager().getPackageInfo(
	                     context.getPackageName(), 0);
	             int lastVersionCode = sharedPreferences.getInt(
	                     LAST_APP_VERSION, -1);
	             // String versionName = pInfo.versionName;
	             int currentVersionCode = pInfo.versionCode;
	             appStart = checkAppStart(currentVersionCode, lastVersionCode);
	             // Update version in preferences
	             sharedPreferences.edit()
	                     .putInt(LAST_APP_VERSION, currentVersionCode).commit();
	         } catch (NameNotFoundException e) {
	            

	         }
	     }
	     return appStart;
	 }

	 public AppStart checkAppStart(int currentVersionCode, int lastVersionCode) {
	     if (lastVersionCode == -1) {
	         return AppStart.FIRST_TIME;
	     } else if (lastVersionCode < currentVersionCode) {
	         return AppStart.FIRST_TIME_VERSION;
	     } else if (lastVersionCode > currentVersionCode) {
	       
	         return AppStart.NORMAL;
	     } else {
	         return AppStart.NORMAL;
	     }
	 }
	 
	 
	

	 @Override
		public void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
			outState.putInt("AberturaRes", AberturaRes);
		}
	 }
	



	