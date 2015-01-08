package iprj.app.main;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.model.GraphUser;
import com.iprjappteste.data.MySQLiteHelper;
import com.iprjappteste.data.Usuario;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.Permission.Type;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.listeners.OnLoginListener;


public class Tutorial_1 extends Activity{
	
	private int TutoRes = -1;
	@SuppressWarnings("deprecation")
	//private AsyncFacebookRunner mAsyncRunner;
	private Fragment mContent;
	private String[] id;
	//private static String APP_ID ="1458439531078582";
    public String Userid;
    private Button mButtonLogin;
	private Button mButtonLogout;
	private TextView mTextStatus;
	
    private static final String APP_ID = "1458439531078582";
	protected static final String TAG = null;
     @SuppressWarnings("deprecation")
    private Facebook facebook;
	//private Facebook facebook = new Facebook(APP_ID);
    public String User_name;
    private 	SimpleFacebook mSimpleFacebook ;;
    public String usuario;
    public MySQLiteHelper db = new MySQLiteHelper(Tutorial_1.this);
	
    @SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);		

			if (savedInstanceState != null)
			
				TutoRes = savedInstanceState.getInt("TutoRes");
			facebook = new Facebook(APP_ID);
			AsyncFacebookRunner   mAsyncRunner = new AsyncFacebookRunner(facebook);
			//loginFacebook();
	
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.tutorial_1);
	//setLogin();
	
	

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
   

	
	final Button  Button = (Button) findViewById(R.id.button1);
	
	Button.setOnClickListener(new View.OnClickListener() {
	    
        @Override
       public void onClick(View v) {
        
        	if(Userid != null && User_name != null){
        		
           	 Intent intent = new Intent();
           	 intent.setClass(Tutorial_1.this,Tutorial_2.class);
           	 db.addUsuario(new Usuario(User_name,Userid));
             startActivity(intent);
              finish();

    	    
        	}
        	
        	else {
    	      
        		Toast.makeText(getApplicationContext(), "Aguarde o carregamento dos dados do perfil do Facebook", Toast.LENGTH_SHORT).show();

        	}
        	 
        }
        
	});
    }
    

    
    public void getProfileInformation() {


        try {

            JSONObject profile = Util.parseJson(facebook.request("me"));
            Log.e("Profile", "" + profile);

            Userid = profile.getString("id");
            //mUserToken = facebook.getAccessToken();
            User_name = profile.getString("name");
            //mUserEmail = profile.getString("email");

            runOnUiThread(new Runnable() {

                public void run() {

                   Log.e("FaceBook_Profile",""+User_name+"\n"+Userid+"\n");

                   Toast.makeText(getApplicationContext(),
                         "Name: " + User_name+ "\nEmail: " + Userid,
                           Toast.LENGTH_LONG).show();



                }

            });

        } catch (FacebookError e) {

            e.printStackTrace();
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }
     
    @Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("TutoRes", TutoRes);
	}

}
