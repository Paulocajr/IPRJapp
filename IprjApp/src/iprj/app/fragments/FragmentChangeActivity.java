package iprj.app.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuInflater;

import com.actionbarsherlock.view.Menu;
import com.facebook.Session;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;

import iprj.app.fragments.Home_Fragment;
import iprj.app.main.Left_Menu;
import iprj.app.main.R;

import com.iprjappteste.data.MySQLiteHelper;

import iprj.app.main.BaseActivity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.sinch.messagingtutorial.app.ListUsersActivity;
import com.sinch.messagingtutorial.app.LoginActivity;
import com.sinch.messagingtutorial.app.MessageService;


public class FragmentChangeActivity extends BaseActivity {
	
	 private static final String TAG_MY_CLASS = null;
	@SuppressWarnings("deprecation")
	private AsyncFacebookRunner mAsyncRunner;
	private Fragment mContent;
	private String[] id;
	private static String APP_ID ="1458439531078582";
    public String Userid;
    @SuppressWarnings("deprecation")
	private Facebook facebook = new Facebook(APP_ID);
    public String User_name;
    public String usuario;
    public MySQLiteHelper db = new MySQLiteHelper(FragmentChangeActivity.this);
    public String text = "Adicione um Lembrete!";
	 private Intent intent;
	    private Intent serviceIntent;
	
	
	public FragmentChangeActivity() {
		super(R.string.changing_fragments);
	      }
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.app_name);
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		        
		Parse.initialize(this, "8FttRlqaAUPabxXzuLeTMbmgAA389L7oVbFaSqjj", "6sFwktakiPYSDg1NKzgP18yanSjlB9CQKFGUOShs");
			
		ParseInstallation.getCurrentInstallation().saveInBackground();
		
		// set the Above View
		getSlidingMenu().setMode(SlidingMenu.LEFT);
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
		if (mContent == null)
			mContent = new Home_Fragment();	
		
	// set the Above View
		setContentView(R.layout.content_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, mContent)
		
		.commit();
		
		
		
		
	/*getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
    getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
    getSupportFragmentManager()
	.beginTransaction()
    .replace(R.id.menu_frame_two, new ListUsersActivity())
   .commit();*/
		

 
		
		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame, new Left_Menu())
		.commit();
		
		// customize the SlidingMenu
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
    
    
		 @Override
	    public void onActivityResult(int requestCode, int resultCode, Intent data){
	    super.onActivityResult(requestCode, resultCode, data);
	    Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	    }
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}

	 public void sair(){
	    	
    	 AlertDialog.Builder alert = new AlertDialog.Builder(this);

		 alert.setTitle("Encerrar IPRJapp");
		 alert.setMessage("Deseja desconectar do IPRJapp?");

		
		  new MySQLiteHelper(this);
	    	 
		 
   			
		  alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
   		  @Override
		public void onClick(DialogInterface dialog, int whichButton) {
   			
   		
   		
   			FragmentChangeActivity.this.finish();
        		     
   			     }
   			     
   			     
   		 });
	 }
	 

	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, fragment)
		.commit();
		getSlidingMenu().showContent();
	}
	
	  
	  

}

