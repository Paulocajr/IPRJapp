package iprj.app.main;


/*
  * NoteEdit.java
  * Versão: <v2.0>
  * Data de Criação : 10/09/2014
  * Copyright (C) 2014 Paulo cabral
  * Instituto Politécnico do Estado do Rio de Janeiro
  * IPRJ - http://www.iprj.uerj.br
  * Classe responsável pela tela de edição do lembrete
  * Todos os direitos reservados.
 */


// Imports
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
	
	
//*********Declaração de Variáveis****************//	

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
        
        //inicializando objeto do banco de dados
        db.getWritableDatabase();        
        NoteEdit = this;       
        
        //inicializnando componentes do layout
        confirmButton = (Button) findViewById(R.id.confirm);
        Button cancelmButton = (Button) findViewById(R.id.cancel);
        
        //ação do botão confirmar               
        confirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
			public void onClick(View view) {
               
                    //chama o método que salva o lembrete
	            NoteEdit.this.addAtividade();                 
          	    Intent intent = new Intent();
          	    //volta para a tela principal do app
                    intent.setClass(NoteEdit.this,FragmentChangeActivity.class);
                    startActivity(intent);                        
                    finish();
	                            
            }
                                   

        });
        
        cancelmButton.setOnClickListener(new View.OnClickListener() {

            @Override
			public void onClick(View view) {
                      
                      //não faz nenhuama ação a não ser voltar a tela principal do app      	
          	      Intent intent = new Intent();         		
           	      intent.setClass(NoteEdit.this,FragmentChangeActivity.class);
                      startActivity(intent);
                      finish();
                     
            }
        });
        
    	
    }    
    
//Método resonsável por adicionar o lemnrete
//pega o dados que o usuário digita nos campos e salva no banco de dados

protected void addAtividade() {
		
          final MySQLiteHelper db = new MySQLiteHelper(this);
          
         //Cria as EditText para o usuário digitar os textos
    	  TitleText = (EditText) findViewById(R.id.titulo);
          BodyText = (EditText) findViewById(R.id.body);
          
          //define tamanho máximo de caracteres que podem ser digitados
          int maxLength = 50; 
    	  int maxLength_1 = 300;
    	  
    	  TitleText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
  	  BodyText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength_1)})
  	  
  	  //pega o texto digitgado converte para string e atribui a variavel text1 e text2
    	  text1 = TitleText.getText().toString();
	  text2 = BodyText.getText().toString();
	    			    
	    			     
	     if(text1.equals("")){
	    			     
	    			  }
	    			     
	     else {
	    			     	 
	         db.addAtividade(new Atividade(text1,text2));	   		
	         int listcounter=0;
	    	 final List<Atividade> atividades = db.getAllAtividades();
	         Atividade atv =atividades.get(listcounter);	   		             	
	         text1 = atv.getTitle();	   		        	     
	         Home_Fragment.aviso_caip.setText(text1);
	   		      	       	   		      	        
	   		      	          		        
              		    	Toast.makeText(getApplicationContext(), "Atividade salva!", Toast.LENGTH_SHORT).show();
	   
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
