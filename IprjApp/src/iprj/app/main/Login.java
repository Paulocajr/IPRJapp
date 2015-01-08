package iprj.app.main;

import iprj.app.fragments.FragmentChangeActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.SignUpCallback;

import android.app.Application;
import net.simonvt.numberpicker.NumberPicker;
import net.simonvt.numberpicker.NumberPicker.OnValueChangeListener;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.model.GraphUser;
import com.iprjappteste.data.Atividade;
import com.iprjappteste.data.Avisos_Caip;
import com.iprjappteste.data.Avisos_Prof;
import com.iprjappteste.data.Curso;
import com.iprjappteste.data.MySQLiteHelper;
import com.iprjappteste.data.Periodos;
import com.iprjappteste.data.Usuario;
import com.parse.PushService;
import com.sinch.messagingtutorial.app.MessageService;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Login extends Activity  {
	
	 public String num_per;
	 public String nome_per;
	 public String num_per2;
	 public String nome_per2;
	 public String num_per3;
	 public String nome_per3;
	 public String num_per4;
	 public String nome_per4;
	 public CheckBox check1;
	 public CheckBox check2;
	 public String Userid;  	
	 private String[] id;
	 private static String APP_ID ="1458439531078582";
	  @SuppressWarnings("deprecation")
     private Facebook facebook = new Facebook(APP_ID);
	 public String User_name;
	 public CheckBox check3;
	  private Intent serviceIntent;
 	 
 	
    
	 private Calendar now = Calendar.getInstance();
	 int year = now.get(Calendar.YEAR);
	 
     @Override
     public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     
		    final MySQLiteHelper db = new MySQLiteHelper(Login.this);
		    
		    Parse.initialize(this, "8FttRlqaAUPabxXzuLeTMbmgAA389L7oVbFaSqjj", "6sFwktakiPYSDg1NKzgP18yanSjlB9CQKFGUOShs");
			
		 	 ParseInstallation.getCurrentInstallation().saveInBackground();	
		 	 
		 	serviceIntent = new Intent(this, MessageService.class);
		 	
		 	  ParseUser currentUser = ParseUser.getCurrentUser();
		        if (currentUser != null) {
		            startService(serviceIntent);
		        }
		  
	        overridePendingTransition(R.anim.fadein, R.anim.fadeout);	     
		    requestWindowFeature(Window.FEATURE_NO_TITLE);
		    setContentView(R.layout.login);		  
		  
			
			db.addAviso(new Avisos_Caip("Sem novos avisos!",""));			
			db.addAviso_Prof(new Avisos_Prof("Sem novos avisos!",""));
			db.addAtividade(new Atividade("Adicione um Lembrete!",""));
			
			final Button  Button = (Button) findViewById(R.id.button1);
			final NumberPicker np = (NumberPicker)findViewById(R.id.numberPicker);
			final NumberPicker np_2 = (NumberPicker)findViewById(R.id.numberPicker_2);
			
			np.setMaxValue(10);
		    np.setMinValue(1);
		    np.setFocusable(true);
		    np.setFocusableInTouchMode(true);
		    
		    np_2.setMaxValue(10);
		    np_2.setMinValue(1);
		    np_2.setFocusable(true);
		    np_2.setFocusableInTouchMode(true);

           	num_per2 = Integer.toString(np.getMinValue());
           	num_per2 = "0"+num_per2;
           	nome_per = num_per2+"° periodo";
           	nome_per2 = nome_per; 
           	
        
	         		  	            
	        np.setOnValueChangedListener(new OnValueChangeListener() {
	                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
	                    // do something here
	               
	                	 final int pickedValue = np.getValue();
	                	  num_per = Integer.toString(pickedValue);
	                	 	 //Toast.makeText(Login.this,num_per, Toast.LENGTH_SHORT).show();
		                  if(num_per.equals("10")) {	
		                	  
		                	  num_per2 = num_per;
		                	  nome_per = num_per+"° periodo";
		                	  nome_per2 = nome_per;

		                      }
		                  
		                         else {
		                            num_per = "0"+num_per;
	       	                        nome_per = num_per+"° periodo";	
	       	        	            num_per2 = num_per;
	       	        	            nome_per2 = nome_per;
		                            }
	       	           	      }
	                	                
	                });
	           
	           
	           np_2.setOnValueChangedListener(new OnValueChangeListener() {
	                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
	                    // do something here
	               
	                	 final int pickedValue_2 = np_2.getValue();
	                	  num_per3 = Integer.toString(pickedValue_2);
	                	 	 //Toast.makeText(Login.this,num_per, Toast.LENGTH_SHORT).show();
		                	 
	                	  if(num_per3.equals("10")){ 	 
		                	   
	                		  num_per4 = num_per3;
	                		  nome_per3 = num_per3+"° periodo";
	                		  nome_per4 = nome_per3;
	                	          }
	                	   
	                	             else {
	                             	  num_per3 = "0"+num_per3;
	       	                          nome_per3 = num_per3+"° periodo";	
	       	        	              num_per4 = num_per3;
	       	        	              nome_per4 = nome_per3;
	                	           }
	       	           
	                           }
	                
	                
	                        });
	           
	        check1 = (CheckBox) findViewById(R.id.checkBox1);
	        check2 = (CheckBox) findViewById(R.id.checkBox2);
	        check3 = (CheckBox) findViewById(R.id.checkBox3);
	        check3.setOnClickListener(new View.OnClickListener() {
	                public void onClick(View v) {
	                    // Perform action on clicks, depending on whether it's now checked
	                    if (((CheckBox) v).isChecked()) {
	                    	
	                      np_2.setVisibility(View.VISIBLE);
	                      
	                        num_per4 = Integer.toString(np.getMinValue());
	                        num_per4 = "0"+num_per4;
	              	        nome_per3 = num_per4+"° periodo";
	              	        nome_per4 = nome_per3;
	              	        
	                                 }	                    
	                    
	       	        	           else {
	       	        		  
	       	        		        np_2.setVisibility(View.GONE);
		                            num_per4 = null;
		              	            nome_per4 = null;
	       	        	        }
	                          }
	                     });
	            
	     

	        Button.setOnClickListener(new View.OnClickListener() {
				    
				                @Override
				               public void onClick(View v) {
				                	

				                	  if (check1.isChecked() && !check2.isChecked()){
				           	    	   

			                		    	  Intent intent = new Intent();
			                		    	  db.addCurso(new Curso("Engenharia Mecânica"));  			                		    	
			                		    	  db.addPeriodo(new Periodos(nome_per2,num_per2));
			                		    	  db.addPeriodo(new Periodos(nome_per4,num_per4));
			                		    	  ParsePush.subscribeInBackground("Mecanica");
						                      intent.setClass(Login.this,FragmentChangeActivity.class);
						                      intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
						                      startActivity(intent);
						                      finish();		
						                      
				                	             }
				                	  
				                	                else {
				                		                   if (check2.isChecked() && !check1.isChecked() ) {
				                		    
				                		       Intent intent = new Intent();

				                		       db.addCurso(new Curso("Engenharia de Computação"));
				                	           db.addPeriodo(new Periodos(nome_per2,num_per2));
				                	           db.addPeriodo(new Periodos(nome_per4,num_per4));
				                	           ParsePush.subscribeInBackground("Computacao");
							                   intent.setClass(Login.this,FragmentChangeActivity.class);
							                   intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);							    
							                   startActivity(intent);
							                   finish();
							                   
				                		         }
				                		    	                                           
				                		            else if (check2.isChecked() && check1.isChecked()) {
				                		    				                		        
				                		    	   Toast.makeText(getApplicationContext(), "Selecione apenas um Curso", Toast.LENGTH_SHORT).show();
				                		    	   
				                		                   }
				                		    			              						                		    
				                		    			               		    
				                		                       else {
				                		    	
				                		    	               Toast.makeText(getApplicationContext(), "Selecione o Curso", Toast.LENGTH_SHORT).show();
				                		                    }

				                		                  }
				                                       }   
				                                   });
       
			
			 String url = "http://www.uerj.br/comunidade/arquivos/ca"+year+"/ca"+year+".pdf";
			 WebView webView = (WebView) findViewById(R.id.webView1);
	         webView.setWebViewClient(new WebViewClient());
	          

				
			 final String url_2 = "http://www.iprj.uerj.br/images/arquivos/coapo/Alfab%C3%A9tica.pdf";
			 WebView webView_2 = (WebView) findViewById(R.id.webView2);
	         webView_2.setWebViewClient(new WebViewClient());
	 
	            webView.setDownloadListener(new DownloadListener() {
	            @Override
	            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {             
	                //start download
	                DownloadPDF downloadPDF = new DownloadPDF();
	                downloadPDF.execute(url,userAgent,contentDisposition);
	            }                     
	        });
	      
	            webView.getSettings().setJavaScriptEnabled(true);
	            webView.loadUrl(url);
	            webView_2.setDownloadListener(new DownloadListener() {
	               @Override
	              public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {             
	                //start download
	            	Download_Ramal downloadPDF = new Download_Ramal();
	                downloadPDF.execute(url_2,userAgent,contentDisposition);
	            }                     
	        });
	      
	        webView_2.getSettings().setJavaScriptEnabled(true);	    
	        webView_2.loadUrl(url_2);    
	        

     }

   
     
    private class DownloadPDF extends AsyncTask<String, Integer, String> {                       
        @Override
        protected String doInBackground(String... sUrl) {     
            try {
                URL url = new URL(sUrl[0]);       
                
                File myDir = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS).toString()+"/myPDF");
              
                // create the directory if it does not exist
                if (!myDir.exists()) myDir.mkdirs();           

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);         
                connection.connect();           

                //get filename from the contentDisposition
                
                Pattern p = Pattern.compile("\"([^\"]*)\"");
                Matcher m = p.matcher(sUrl[2]);
                while (m.find()) {
                }       
               

                File outputFile = new File(myDir, "calendario.pdf");
                InputStream input   = new BufferedInputStream(connection.getInputStream());
                OutputStream output = new FileOutputStream(outputFile);
                
                
                            
                byte data[] = new byte[1024];
                int count;
                while ((count = input.read(data)) != -1) {
                    output.write(data, 0, count);
                }    
                
                connection.disconnect();
                output.flush();
                output.close();
                input.close();
                
                        	
            
            } catch (MalformedURLException e) {
                 e.printStackTrace();
            } catch (IOException e) {
                 e.printStackTrace();
            }
            return null;
        }

		private void displayPdf() {
			// TODO Auto-generated method stub
			
		}
    }
	
    
    
    private class Download_Ramal extends AsyncTask<String, Integer, String> {                       
        @Override
        protected String doInBackground(String... sUrl) {     
            try {
                URL url = new URL(sUrl[0]);       
                
                File myDir = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS).toString()+"/myPDF");
              
                // create the directory if it does not exist
                if (!myDir.exists()) myDir.mkdirs();           

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);         
                connection.connect();           

                //get filename from the contentDisposition
                
                Pattern p = Pattern.compile("\"([^\"]*)\"");
                Matcher m = p.matcher(sUrl[2]);
                while (m.find()) {
                }       
               

                File outputFile = new File(myDir, "Alfabética.pdf");
                InputStream input   = new BufferedInputStream(connection.getInputStream());
                OutputStream output = new FileOutputStream(outputFile);
                
                
                            
                byte data[] = new byte[1024];
                int count;
                while ((count = input.read(data)) != -1) {
                    output.write(data, 0, count);
                }               
                connection.disconnect();
                output.flush();
                output.close();
                input.close();
                
                        	
            
            } catch (MalformedURLException e) {
                 e.printStackTrace();
            } catch (IOException e) {
                 e.printStackTrace();
            }
            return null;
        }

		
    }
    
}
