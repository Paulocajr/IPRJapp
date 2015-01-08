package iprj.app.main;

/*
  * Abertura.java
  * Versão: <v2.0>
  * Data de Criação : 01/09/2014
  * Copyright (C) 2014 Paulo cabral
  * Instituto Politécnico do Estado do Rio de Janeiro
  * IPRJ - http://www.iprj.uerj.br
  * Todos os direitos reservados.
 */


// Imports
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
	
//*************Variáveis publicas e privadas************//
	
    private int AberturaRes = -1;
    protected static final String TAG = "Abertura";    
    public ProgressBar progress;   
    private int progressStatus = 0;
    private Handler handler = new Handler();
    public MySQLiteHelper db = new MySQLiteHelper(Abertura.this);
    
    
//Início do método onCreate
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);		
	
	     if (savedInstanceState != null)
	 		
	AberturaRes = savedInstanceState.getInt("AberturaRes");
	
	// request para mostrar tela sem a barra superior
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	
	//setando conteudo do xml que contem o layout da tela
	setContentView(R.layout.abertura);	
	
	//declarando elementos contidos no layout
	progress = (ProgressBar) findViewById(R.id.progressBar1); 
	final Button  Button = (Button) findViewById(R.id.button1);
	
	
	 context = this;
     sharedPreferences = PreferenceManager
             .getDefaultSharedPreferences(context);
     
	
     switch (checkAppStart()) {
     
     
     // inicialização normal do app
     case NORMAL:
         
         Button.setVisibility(View.GONE);
         final List<Curso> cursos = db.getAllCursos();        
         	        	         
         new Thread(new Runnable() {
             public void run() {
                while (progressStatus < 100) {
                   progressStatus += 1;
            
            handler.post(new Runnable() {
            public void run() {
               progress.setProgress(progressStatus);
              
             }
            	            
                });
            
  	        	            
                try {
                 
                   Thread.sleep(50);
                } catch (InterruptedException e) {
                   e.printStackTrace();
                }
             }
                //verifica se o há algum curso cadastrado no banco
                if(cursos.isEmpty()){
  		    		       
                	    //se não há curso inicia a classse de Login
                	    Intent intent = new Intent();
		        		intent.setClass(Abertura.this,Login.class);
			            startActivity(intent);
			            finish(); 			                
		    	   }
                
                else {
                       
                	//se há curso cadastrado vai para tela inicial do app
		               Intent intent = new Intent();
	                   intent.setClass(Abertura.this,FragmentChangeActivity.class);
	        	       startActivity(intent);
	        	       finish();
                           }
                }}).start();
      	        	     	        	     	       	
 	 
         break;
         
     //primeira vez rodando em nova versão    
     case FIRST_TIME_VERSION:
    	 
    	 // case reservado para mosrar mudanças em novas versões do aplivativo
    	 
         break;
         
     // primeira instalção do aplicativo no dispositivo   
     // inicia um breve tutorial mostrando funcionalidades    
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
      
	 /**
	  * Diferencia diferentes modo de inicialização do aplicativo: <li>
	  * <ul>
	  * Primira instalaçao ({@link #FIRST_TIME})
	  * </ul>
	  * <ul>
	  * Primeira inicialização de nova versão ({@link #FIRST_TIME_VERSION})
	  * </ul>
	  * <ul>
	  * Inicialização Normal ({@link #NORMAL})
	  * </ul>
	  * 
	  * @author schnatterer
	  * 
	  */
	 public enum AppStart {
	     FIRST_TIME, FIRST_TIME_VERSION, NORMAL;
	 }


	 private Context context;
	 private SharedPreferences sharedPreferences;
     private static final String LAST_APP_VERSION = "1.0";	
	 private static AppStart appStart = null;


	 public AppStart checkAppStart() {
	     if (appStart == null) {
	         PackageInfo pInfo;
	         try {
	             pInfo = context.getPackageManager().getPackageInfo(
	                     context.getPackageName(), 0);
	             int lastVersionCode = sharedPreferences.getInt(
	                     LAST_APP_VERSION, -1);
	           
	             int currentVersionCode = pInfo.versionCode;
	             appStart = checkAppStart(currentVersionCode, lastVersionCode);
	         
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
	



	
