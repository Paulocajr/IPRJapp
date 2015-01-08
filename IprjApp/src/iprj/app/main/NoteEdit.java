package iprj.app.main;

import java.util.List;
import com.iprjappteste.data.Atividade;
import com.iprjappteste.data.Curso;
import com.iprjappteste.data.MySQLiteHelper;
import iprj.app.fragments.FragmentChangeActivity;
import iprj.app.fragments.Home_Fragment;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class NoteEdit extends Activity implements OnItemSelectedListener{

          private EditText TitleText;
          private EditText BodyText;
          public Curso curso;
          public PendingIntent pintent;
	  public String nome1;
	  public String User_name;
	  public AlarmManager manager;
	  public String Userid;
	  public long time_alarm;
	  public static String text1;
	  public static String text2;
	  public static Activity NoteEdit;
	  public TimePicker myTimePicker;
	  public Button buttonstartSetDialog;
	  public TextView textAlarmPrompt;
	  private static Context context;
	  final static int RQS_1 = 1;
          public EditText lembrete; 
	  public String date;
	  public Spinner periodo;
	  public CheckBox repetir;
	  public String time;
	  public String notificacao;
	  public String position;
	  public Button confirmButton;
	  public EditText data_1;
          public MySQLiteHelper db = new MySQLiteHelper(NoteEdit.this);
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.note_edit);
      
        db.getWritableDatabase();        
        NoteEdit = this;       

        confirmButton = (Button) findViewById(R.id.confirm);
        Button cancelmButton = (Button) findViewById(R.id.cancel);
        
                       
        confirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
			public void onClick(View view) {
                Bundle bundle = new Bundle();
                                
             		          
	            NoteEdit.this.addAtividade();                 
          	    Intent intent = new Intent();  		   
          	          	 
                    intent.setClass(NoteEdit.this,FragmentChangeActivity.class);
                    startActivity(intent);                        
                    finish();
	                            
            }
                                   

        });
        
        cancelmButton.setOnClickListener(new View.OnClickListener() {

            @Override
			public void onClick(View view) {
                            	

          		   
          	      Intent intent = new Intent();         		
           	      intent.setClass(NoteEdit.this,FragmentChangeActivity.class);
                      startActivity(intent);
                      finish();
                     
            }
        });
        
    	
    }    
    
    
protected void addAtividade_2() {
		
          final MySQLiteHelper db = new MySQLiteHelper(this);
      	
    	  TitleText = (EditText) findViewById(R.id.titulo);
          BodyText = (EditText) findViewById(R.id.body);
          int maxLength = 50; 
    	  int maxLength_1 = 300;
    	  TitleText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
  	  BodyText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength_1)})
    	  text1 = TitleText.getText().toString();
	  text2 = BodyText.getText().toString();
	    			    
	    			     
	     if(text1.equals("")){
	    			     
	    			  }
	    			     
	     else{
	    			     	 
	         db.addAtividade(new Atividade(text1,text2));	   		
	         int listcounter=0;
	    	 final List<Atividade> atividades = db.getAllAtividades();
	         Atividade atv =atividades.get(listcounter);	   		             	
	         text1 = atv.getTitle();	   		        	     
	         Home_Fragment.aviso_caip.setText(text1);
	   		      	       	   		      	        
	   		      	          		        
              		    	Toast.makeText(getApplicationContext(), "Atividade salva!", Toast.LENGTH_SHORT).show();
	   
	    			    }
	    			    
			}

	
 public void addAtividade() {
		
             final MySQLiteHelper db = new MySQLiteHelper(this);
  	
              TitleText = (EditText) findViewById(R.id.titulo);
              BodyText = (EditText) findViewById(R.id.body);
              int maxLength = 50; 
              int maxLength_1 = 300;
              TitleText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
              BodyText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength_1)});
              text1 = TitleText.getText().toString();
      	      text2 = BodyText.getText().toString();
      	    			    
      	    			     
      	    	 if(text1.equals("")){
      	    	    }
      	    			     
      	    	 else{
      	    			     	       	    			    	 
      	    			    	// SetAlarm();
      	          db.addAtividade(new Atividade(text1,text2));      	   		
      	          int listcounter=0;
      	    	  final List<Atividade> atividades = db.getAllAtividades();
      	          Atividade atv =atividades.get(listcounter);      	   		             	
      	          text1 = atv.getTitle();      	   		        	     
      	          Home_Fragment.aviso_caip.setText(text1);
      	   		      	         
      	   		                        		    	
      	   		                 Toast.makeText(getApplicationContext(), "Lembrete salvo!", Toast.LENGTH_SHORT).show();
      	         	    		     }
      	    		        }
    
    public static Context getAppContext(){
        return NoteEdit.getBaseContext();
    }
    
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
      
    
}
