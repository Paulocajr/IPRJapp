package iprj.app.main;


import com.sinch.messagingtutorial.app.LoginActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class Tutorial_3 extends Activity{
	
	private int TutoRes = -1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);		

			if (savedInstanceState != null)
			
				TutoRes = savedInstanceState.getInt("TutoRes");
			
	overridePendingTransition(R.anim.fadein, R.anim.fadeout);	
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.tutorial_3);
	
	
	final Button  Button = (Button) findViewById(R.id.button1);
	
	Button.setOnClickListener(new View.OnClickListener() {
	    
        @Override
       public void onClick(View v) {
        	
         
        	 Intent intent = new Intent();
        	 intent.setClass(Tutorial_3.this,LoginActivity.class);
         	 startActivity(intent);
         	 finish();
        	 
        }
        
	     });
    }
    
    @Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("TutoRes", TutoRes);
	}

}
