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
		 
		 static final String[] LEMBRETES = new String[]{"Não lembrar","10 minutos","30 minutos",
		    	"1 hora","5 horas","12 horas","1 dia","1 semana",
		    	};
		
		 public EditText data_1; 
		 MySQLiteHelper db = new MySQLiteHelper(NoteEdit.this);
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_edit);
       // setTitle(R.string.edit_note);
        db.getWritableDatabase();        
        NoteEdit = this;       

        confirmButton = (Button) findViewById(R.id.confirm);
        Button cancelmButton = (Button) findViewById(R.id.cancel);
        
                       

       /* Spinner periodo = (Spinner) findViewById(R.id.periodos);
		periodo.setOnItemSelectedListener(this);
        ArrayAdapter adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, LEMBRETES);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_item);
        periodo.setAdapter(adp);
        
        periodo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			 public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
	            //pega nome pela posição
				
				    Calendar c = Calendar.getInstance();
			        
				
				notificacao = parent.getItemAtPosition(posicao).toString();
	            
	            position = Integer.toString(posicao) ;
	            
	            if(notificacao.equals("10 minutos")){
	            	
	            	
	            	
	            	time_alarm = (1000*60)*(1);
	            	
	            }
	            
	            else if(notificacao.equals("30 minutos")){
	            	
	            	time_alarm = (1000*60)*(3)*(10);
	            }

                 

                   else if(notificacao.equals("1 hora")){
   	            	
   	            	time_alarm = (1000)*(3600);
   	            }
	            
	            

                   else if(notificacao.equals("5 horas")){
   	            	
   	            	time_alarm = (1000*60)*(60)*(5);
   	            }
	            
	            

                   else if(notificacao.equals("12 horas")){
   	            	
   	            	time_alarm = 1000*60*60*12;
   	            }
	            
                   else if(notificacao.equals("1 dia")){
      	            	
      	            	time_alarm = 24*60*60*1000;
      	            }
	            
	            
                   else if(notificacao.equals("1 semana")){
     	            	
     	            	time_alarm =(24*60*60*1000)*7;
     	            }
	           
	           
	           
	        }
	 

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				 //String   lembrete = parent.getItemAtPosition(posicao).toString();
		            
				//notificacao = "Não lembrar";
			}
         
         } );*/
        

        confirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
			public void onClick(View view) {
                Bundle bundle = new Bundle();
                                
             		          
	            	NoteEdit.this.addAtividade();                 
          		    Intent intent = new Intent();         		   
          		           	          //SetAlarm();
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
  	      BodyText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength_1)});
    		    		 	    			
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
	   		                // Home_Fragment.visualiza_lembrete.setText("Toque para visualizar");
	   		        	 Home_Fragment.aviso_caip.setText(text1);
	   		      	        	   		      	        
	   		      	          		        	   //output.setText( text1);
              		    	Toast.makeText(getApplicationContext(), "Atividade salva!", Toast.LENGTH_SHORT).show();
	   
	    			    }
	    			    
			}


	/*public void SetAlarm()
    {
        //final Button button = buttons[2]; // replace with a button from your own UI
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override public void onReceive( Context context, Intent _ )
            {
            	//
            	createNotification(textAlarmPrompt);
                finish();
            	//confirmButton.setBackgroundColor( Color.RED );
                context.unregisterReceiver( this ); // this == BroadcastReceiver, not Activity
            }
        };

        this.registerReceiver( receiver, new IntentFilter("com.blah.blah.somemessage") );

        PendingIntent   pintent = PendingIntent.getBroadcast( this, 0, new Intent("com.blah.blah.somemessage"), 0 );
         AlarmManager   manager = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));
        

        // set alarm to fire 5 sec (1000*5) from now (SystemClock.elapsedRealtime())
       
        manager.set( AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + time_alarm, pintent );
        
        finish();
    }*/
    

	

	
    /*public void createNotification(View view) {
        // Prepare intent which is triggered if the
        // notification is selected
    	Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    	

        Intent intent = new Intent(this, NotificationReceiverActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        
        NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(this);	

        mBuilder.setContentTitle("Lembrete");
        mBuilder.setContentText(text1).setSmallIcon(R.drawable.ic_home);
       // mBuilder.setTicker("Implicit: New Message Received!");
        //mBuilder.setSmallIcon(R.drawable.ic_launcher);
        
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

         String[] events = new String[3];
         events[0] = new String(text1);
         events[1] = new String(text2);
         

         // Sets a title for the Inbox style big view
         inboxStyle.setBigContentTitle("Lembrete");
         // Moves events into the big view
         for (int i=0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
         }
         mBuilder.setStyle(inboxStyle);
         mBuilder.setAutoCancel(true);
         mBuilder.setSound(soundUri);
         mBuilder.setAutoCancel(true);
         
         Notification noti = mBuilder.build();
         noti.flags |= Notification.FLAG_AUTO_CANCEL;

        

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        
        notificationManager.notify(0, mBuilder.build());  


      

      }*/
   
  
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
      	   		                 //Home_Fragment.atividade_toque.setText("Toque para visualizar");
      	   		        	Home_Fragment.aviso_caip.setText(text1);
      	   		      	         
      	   		        	   //output.setText( text1);
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
