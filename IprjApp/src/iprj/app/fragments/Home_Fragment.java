package iprj.app.fragments;

import iprj.app.main.NoteEdit;
import iprj.app.main.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;








import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.WebDialog;
import com.iprjappteste.data.Atividade;
import com.iprjappteste.data.Avisos_Caip;
import com.iprjappteste.data.Avisos_Prof;
import com.iprjappteste.data.Curso;
import com.iprjappteste.data.MySQLiteHelper;
import com.iprjappteste.data.Periodos;
import com.iprjappteste.data.Usuario;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.PushService;
import com.sinch.messagingtutorial.app.ListUsersActivity;
import com.sinch.messagingtutorial.app.MessageService;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.InputFilter;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Home_Fragment extends Fragment {
	
	protected static final String TAG = null;
	private int HomeRes = -1;
	public String aviso;
	public String lembretes;
	public View rootView;
	public String text1;
	public String text2; 
	public TextView lembrete;
	public static TextView aviso_caip;
	public static TextView aviso_prof;
	public static TextView nome_prof;
	public String periodo;
	public String numero_periodo;
	public String numero_periodo2;
	public String nome_curso2;
	public String url1;
	public TextView output;
	public String username;
	public String aviso_professor;
	public String url2;
	public String nome_curso;
	 private Intent intent;
	    private Intent serviceIntent;

	    	  	    
        
	  
@Override
public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
				if (savedInstanceState != null)
	
		 HomeRes = savedInstanceState.getInt("HomeRes");
				 
	    setHasOptionsMenu(true);
	 
		Parse.initialize(this.getActivity(), "8FttRlqaAUPabxXzuLeTMbmgAA389L7oVbFaSqjj", "6sFwktakiPYSDg1NKzgP18yanSjlB9CQKFGUOShs");
		
	 	ParseInstallation.getCurrentInstallation().saveInBackground();
	 	//ParsePush.subscribeInBackground("Giants");
	  
	    
		 final MySQLiteHelper db = new MySQLiteHelper(getActivity());
	     List<Avisos_Caip> avisos = db.getAllAvisos();  
		 List<Curso> cursos = db.getAllCursos();
	     List<Usuario> usuarios = db.getAllUsuario();
	     List<Periodos> periodos = db.getAllPeriodos();
	     List<Avisos_Prof> avisos_prof = db.getAllAvisos_prof();
	     final List<Atividade> atividades = db.getAllAtividades();
		
		  //intent = new Intent(getActivity(), ListUsersActivity.class);
	       serviceIntent = new Intent(getActivity(), MessageService.class);

           ParseUser currentUser = ParseUser.getCurrentUser();
           if (currentUser != null) {
         	  getActivity(). startService(serviceIntent);
           }
           
    	 rootView = inflater.inflate(R.layout.home, container, false);
    	  
       	 Session.openActiveSession(this.getActivity(), true, new Session.StatusCallback() {

    		    
    		    @Override
    		    public void call(final Session session, SessionState state, Exception exception) {
    		    	if (session.isOpened()) {
    		    		  	
    		    		Request.newMeRequest(session, new Request.GraphUserCallback() {

    		    			  @Override
    		    			  public void onCompleted(GraphUser user, Response response) {
    		    				      		    				
    		    			  }
    		    			}).executeAsync();
    		    		}
    		    }
    		  });  
    	
    	  
    	 int listcounter=0;
     	 Curso cn=cursos.get(listcounter);
     	 Periodos per =periodos.get(0);
    	 Periodos per2 =periodos.get(1);
     	 Atividade atv=atividades.get(listcounter);
         Avisos_Caip avi = avisos.get(listcounter);
         Avisos_Prof avi_prof = avisos_prof.get(listcounter);
     	  
     	 lembretes = atv.getTitle();
     	 periodo = per.getTitle();
     	 username = avi_prof.get_User();
         numero_periodo = per.getNum_per();
         numero_periodo2 = per2.getNum_per();
         aviso = avi.getTitle();
    	 nome_curso = cn.getTitle();
    	 aviso_professor = avi_prof.getTitle();
    	 

   	  if(nome_curso.equals("Engenharia Mecânica")){
 	    			    	    			
 	    			
 	    	     Home_Fragment.this.Download_Horario_mec();
 	    	    Home_Fragment.this.Download_Horario_mec2();
 	    			  	    		  
 	    		//if(nome_curso.equals("Engenharia Mecânica")){
 	    			  
 	    		   }
    	 
    	  if(nome_curso.equals("Engenharia de Computação")){
    		  
    		 
    		  Home_Fragment.this.Download_Horario_comp();
    		  Home_Fragment.this.Download_Horario_comp2();
    		 	    	  
    	  }
  		  	    	  	    		  	    		
    	 
    	  aviso_caip = (TextView) rootView.findViewById(R.id.textView6);
    	  aviso_caip.setText(aviso);    	  
    	  aviso_caip.setClickable(true);
    	  aviso_caip.setOnClickListener(new View.OnClickListener() {
            @Override
			   public void onClick(View view) {
            	
            List<Avisos_Caip> avisos = db.getAllAvisos();  
           	 
           	int listcounter=0;

           	Avisos_Caip avi=avisos.get(listcounter);           	 
           	final String atividad = avi.getTitle();
        	final String corpo  = avi.get_User();
        	   
        	AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());        	  
           	alert.setTitle(corpo);
       		alert.setMessage(atividad);
       		alert.show();
       	         
            }
            
        });
    	    	  

    	  aviso_prof = (TextView) rootView.findViewById(R.id.textView8);
    	  aviso_prof.setText(aviso_professor);    	  
    	  aviso_prof.setClickable(true);
    	  aviso_prof.setOnClickListener(new View.OnClickListener() {

            @Override
			public void onClick(View view) {
            	
            	   List<Avisos_Prof> avisos = db.getAllAvisos_prof();  
           	 
           	int listcounter=0;

           	Avisos_Prof avi=avisos.get(listcounter);           	 
           	final String atividad = avi.getTitle();
        	final String corpo  = avi.get_User();
        	   
        	AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());           	            	 
       		alert.setTitle(corpo);
       		alert.setMessage(atividad);
       		alert.show();
       	         
            }
            
        });
    	      	  
    	  nome_prof = (TextView) rootView.findViewById(R.id.textView2);
    	  nome_prof.setText(username);
    	  
    	  
    	  lembrete = (TextView) rootView.findViewById(R.id.textView7);
    	  lembrete.setText(lembretes);
    	  
    	  Button  compartilha_caip = (Button) rootView.findViewById(R.id.button1);
        	  
    	  Button  exclui_caip = (Button) rootView.findViewById(R.id.button3);
    	  
    	  Button  exclui_lembrete = (Button) rootView.findViewById(R.id.button6);
    	  Button  add_lembrete = (Button) rootView.findViewById(R.id.button5);    	  
    	  Button  compartilha_lembrete = (Button) rootView.findViewById(R.id.button4);
    	  
    	  Button  exclui_prof = (Button) rootView.findViewById(R.id.button9);
    	    	  
    	  Button  compartilha_prof = (Button) rootView.findViewById(R.id.button7);
    	 
    	  
         	
    	  compartilha_caip.setOnClickListener(new View.OnClickListener() {
  	    		    
  	                @Override
  	               public void onClick(View v) {
  	                	
  	                	Home_Fragment.this.share_caip();
  	                }
  	                
  	    		});	
    	  
    	 	
    	  compartilha_lembrete.setOnClickListener(new View.OnClickListener() {
  	    		    
  	                @Override
  	               public void onClick(View v) {
  	                	
  	                	Home_Fragment.this.share_lembrete();
  	                }
  	                
  	    		});	
    	  
    	  compartilha_prof.setOnClickListener(new View.OnClickListener() {
    		    
                @Override
               public void onClick(View v) {
                	
                	Home_Fragment.this.share_aviso_prof();
                }
                
    		});	
    	  
    	  exclui_lembrete.setOnClickListener(new View.OnClickListener() {
    		    
                @Override
               public void onClick(View v) {
                	
                	Home_Fragment.this. deleteAtividade();
                }
                
    		});	
    	 
    	  
    	  exclui_caip.setOnClickListener(new View.OnClickListener() {
    		    
                @Override
               public void onClick(View v) {
                	
                	Home_Fragment.this. deleteAviso();
                }
                
    		});	
    	      	  
    	  exclui_prof.setOnClickListener(new View.OnClickListener() {
    		    
                @Override
               public void onClick(View v) {
                	
                	Home_Fragment.this. deleteAviso_prof();
                }
                
    		});	
    	  
    	  add_lembrete.setOnClickListener(new View.OnClickListener() {
    		    
                @Override
               public void onClick(View v) {
                	  
             	   Intent intent = new Intent();
             	   intent.setClass(Home_Fragment.this.getActivity(),NoteEdit.class);
             	   intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP); 
                   startActivity(intent);
                }
                
    		});	
    	         	  
    	    	  
    	 return rootView;
    	 
	}
	

	
private void Download_Horario_comp() {
		 //  if (month < 8) {
	 			
			   // z= "";
				//semestre = 1;
			  //  semestre_corrente = Integer.toString(semestre);
			    
			  //  nome_curso = "engcomputacao";
	   	      nome_curso2 = "EngComp";
	          //  url1 ="http://iprj.uerj.br/images/arquivos/horarios/"+nome_curso+"/"+year+"-"+semestre_corrente+"/horario-"+numero_periodo+"%C2%BA%20periodo-"+nome_curso2+".pdf";

	          url1 = "http://www.iprj.uerj.br/images/images/UERJ_IPRJ_"+nome_curso2+"_Disc_2014_2_p"+numero_periodo+".pdf";      
	          //Toast.makeText(getActivity().getApplicationContext(), url1, Toast.LENGTH_SHORT).show();
	          
	            WebView webview = (WebView) rootView.findViewById(R.id.webView2);
	            webview.setWebViewClient(new WebViewClient());
	            webview.setDownloadListener(new DownloadListener() {
	               @Override
	               public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {             
	                   //start download
	                   DownloadPDF downloadPDF = new DownloadPDF();
	                   downloadPDF.execute(url1,userAgent,contentDisposition);
	               }                     
	           });
	         
	            webview.getSettings().setJavaScriptEnabled(true);
	       
	            webview.loadUrl(url1);             
		   //  }
		   
		   //else {
	 			
			   
				/*semestre = 2;
				semestre_corrente = Integer.toString(semestre) ;
	            nome_curso = "engcomputacao";
	   	        nome_curso2 = "engcomp";
	            url1 ="http://iprj.uerj.br/images/arquivos/horarios/"+nome_curso+"/"+year+"-"+semestre_corrente+"/horario-"+numero_periodo+"%C2%BA%20periodo-"+nome_curso2+".pdf";
	*/
	           
	         /* WebView  webview = (WebView) rootView.findViewById(R.id.webView2);
	            webview.setWebViewClient(new WebViewClient());
	            webview.setDownloadListener(new DownloadListener() {
	               @Override
	               public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {             
	                   //start download
	                   DownloadPDF downloadPDF = new DownloadPDF();
	                   downloadPDF.execute(url1,userAgent,contentDisposition);
	               }                     
	           });
	         
	            webview.getSettings().setJavaScriptEnabled(true);
	       
	            webview.loadUrl(url1); */            
		//   }
	     }
	
private void Download_Horario_comp2() {

		 //  if (month < 8) {
 			
			   // z= "";
				//semestre = 1;
			  //  semestre_corrente = Integer.toString(semestre);
			    
			  //  nome_curso = "engcomputacao";
	   	      nome_curso2 = "EngComp";
	          //  url1 ="http://iprj.uerj.br/images/arquivos/horarios/"+nome_curso+"/"+year+"-"+semestre_corrente+"/horario-"+numero_periodo+"%C2%BA%20periodo-"+nome_curso2+".pdf";

	          url2 = "http://www.iprj.uerj.br/images/images/UERJ_IPRJ_"+nome_curso2+"_Disc_2014_2_p"+numero_periodo2+".pdf";      
	          //Toast.makeText(getActivity().getApplicationContext(), url1, Toast.LENGTH_SHORT).show();
	          
	            WebView webview = (WebView) rootView.findViewById(R.id.webView1);
	            webview.setWebViewClient(new WebViewClient());
	            webview.setDownloadListener(new DownloadListener() {
	               @Override
	               public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {             
	                   //start download
	                   DownloadPDF_2 downloadPDF = new DownloadPDF_2();
	                   downloadPDF.execute(url2,userAgent,contentDisposition);
	               }                     
	           });
	         
	            webview.getSettings().setJavaScriptEnabled(true);
	       
	            webview.loadUrl(url2);             
		   //  }
		   
		   //else {
	 			
			   
				/*semestre = 2;
				semestre_corrente = Integer.toString(semestre) ;
	            nome_curso = "engcomputacao";
	   	        nome_curso2 = "engcomp";
	            url1 ="http://iprj.uerj.br/images/arquivos/horarios/"+nome_curso+"/"+year+"-"+semestre_corrente+"/horario-"+numero_periodo+"%C2%BA%20periodo-"+nome_curso2+".pdf";
	*/
	           
	         /* WebView  webview = (WebView) rootView.findViewById(R.id.webView2);
	            webview.setWebViewClient(new WebViewClient());
	            webview.setDownloadListener(new DownloadListener() {
	               @Override
	               public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {             
	                   //start download
	                   DownloadPDF downloadPDF = new DownloadPDF();
	                   downloadPDF.execute(url1,userAgent,contentDisposition);
	               }                     
	           });
	         
	            webview.getSettings().setJavaScriptEnabled(true);
	       
	            webview.loadUrl(url1); */            
		//   }
	     }

@SuppressLint("SetJavaScriptEnabled")
private void Download_Horario_mec() {
			// TODO Auto-generated method stub
		   	  	 
		   //if (month < 8){
		   
				//semestre = 1;
				//semestre_corrente = Integer.toString(semestre);
				//nome_curso = "engmecanica";
		  	   nome_curso2 = "EngMec";
		      //  url1 ="http://iprj.uerj.br/images/arquivos/horarios/"+nome_curso+"/"+year+"-"+semestre_corrente+"/m%20-%20horario-"+numero_periodo+"%C2%BA%20periodo-"+nome_curso2+".pdf";

		   url1 = "http://www.iprj.uerj.br/images/images/UERJ_IPRJ_"+nome_curso2+"_Disc_2014_2_p"+numero_periodo+".pdf";
		  	   
		  	// url1 = "http://www.iprj.uerj.br/images/images/UERJ_IPRJ_EngMec_Disc_2014_2_p02.pdf";
		  // Toast.makeText(getActivity().getApplicationContext(), url1, Toast.LENGTH_SHORT).show();
		  	 
		   
		      WebView webview = (WebView) rootView.findViewById(R.id.webView2);
		       webview.setWebViewClient(new WebViewClient());
		       
		       webview.setDownloadListener(new DownloadListener() {
		              @Override
		              public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {             
		                  //start download
		                  DownloadPDF downloadPDF = new DownloadPDF();
		                  downloadPDF.execute(url1,userAgent,contentDisposition);
		              }                     
		          });
		        
		       webview.getSettings().setJavaScriptEnabled(true);
		      
		       webview.loadUrl(url1); 		
		 //  }
				
				//else{
				
			   //z= "m - ";
			   //semestre = 2;
			  // semestre_corrente = Integer.toString(semestre);
			 //  nome_curso = "engmecanica";
	  	      //nome_curso2 = "EngMec";
	           //url1 ="http://iprj.uerj.br/images/arquivos/horarios/"+nome_curso+"/"+year+"-"+semestre_corrente+"/m%20-%20horario-"+numero_periodo+"%C2%BA%20periodo-"+nome_curso2+".pdf";
	         
		       
	        //  WebView webview = (WebView) rootView.findViewById(R.id.webView2);
	          /* webview.setWebViewClient(new WebViewClient());
	           webview.setDownloadListener(new DownloadListener() {
	              @Override
	              public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {             
	                  //start download
	                  DownloadPDF downloadPDF = new DownloadPDF();
	                  downloadPDF.execute(url1,userAgent,contentDisposition);
	              }                     
	          });
	        
	           webview.getSettings().setJavaScriptEnabled(true);
	      
	           webview.loadUrl(url1); */
			
		    //}
	   }
	   
@SuppressLint("SetJavaScriptEnabled")
private void Download_Horario_mec2() {
			// TODO Auto-generated method stub
		   	  	 
		   //if (month < 8){
		   
				//semestre = 1;
				//semestre_corrente = Integer.toString(semestre);
				//nome_curso = "engmecanica";
		  	   nome_curso2 = "EngMec";
		      //  url1 ="http://iprj.uerj.br/images/arquivos/horarios/"+nome_curso+"/"+year+"-"+semestre_corrente+"/m%20-%20horario-"+numero_periodo+"%C2%BA%20periodo-"+nome_curso2+".pdf";

		   url2 = "http://www.iprj.uerj.br/images/images/UERJ_IPRJ_"+nome_curso2+"_Disc_2014_2_p"+numero_periodo2+".pdf";
		  	   
		  	// url1 = "http://www.iprj.uerj.br/images/images/UERJ_IPRJ_EngMec_Disc_2014_2_p02.pdf";
		  // Toast.makeText(getActivity().getApplicationContext(), url1, Toast.LENGTH_SHORT).show();
		  	 
		   
		      WebView webview = (WebView) rootView.findViewById(R.id.webView1);
		       webview.setWebViewClient(new WebViewClient());
		       
		       webview.setDownloadListener(new DownloadListener() {
		              @Override
		              public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {             
		                  //start download
		                  DownloadPDF_2 downloadPDF = new DownloadPDF_2();
		                  downloadPDF.execute(url2,userAgent,contentDisposition);
		              }                     
		          });
		        
		       webview.getSettings().setJavaScriptEnabled(true);
		      
		       webview.loadUrl(url2); 		
		 //  }
				
				//else{
				
			   //z= "m - ";
			   //semestre = 2;
			  // semestre_corrente = Integer.toString(semestre);
			 //  nome_curso = "engmecanica";
	  	      //nome_curso2 = "EngMec";
	           //url1 ="http://iprj.uerj.br/images/arquivos/horarios/"+nome_curso+"/"+year+"-"+semestre_corrente+"/m%20-%20horario-"+numero_periodo+"%C2%BA%20periodo-"+nome_curso2+".pdf";
	         
		       
	        //  WebView webview = (WebView) rootView.findViewById(R.id.webView2);
	          /* webview.setWebViewClient(new WebViewClient());
	           webview.setDownloadListener(new DownloadListener() {
	              @Override
	              public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {             
	                  //start download
	                  DownloadPDF downloadPDF = new DownloadPDF();
	                  downloadPDF.execute(url1,userAgent,contentDisposition);
	              }                     
	          });
	        
	           webview.getSettings().setJavaScriptEnabled(true);
	      
	           webview.loadUrl(url1); */
			
		    //}
	   }

class DownloadPDF extends AsyncTask<String, Integer, String> { 
    	
    	
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

                Pattern p = Pattern.compile("\"([^\"]*)\"");
                Matcher m = p.matcher(sUrl[2]);
                while (m.find()) {
                       m.group(1);
                }       
                
          
              //File outputFile = new File(myDir, ""+""+"horario-"+periodo+"-"+nome_curso2+".pdf");//"horario-"+periodo+"-"+nome_curso2+".pdf");

                File outputFile = new File(myDir, "UERJ_IPRJ_"+nome_curso2+"_Disc_2014_2_p"+numero_periodo+".pdf");
                
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

class DownloadPDF_2 extends AsyncTask<String, Integer, String> { 
	
	
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

            Pattern p = Pattern.compile("\"([^\"]*)\"");
            Matcher m = p.matcher(sUrl[2]);
            while (m.find()) {
                   m.group(1);
            }       
            
      
          //File outputFile = new File(myDir, ""+""+"horario-"+periodo+"-"+nome_curso2+".pdf");//"horario-"+periodo+"-"+nome_curso2+".pdf");

            File outputFile = new File(myDir, "UERJ_IPRJ_"+nome_curso2+"_Disc_2014_2_p"+numero_periodo2+".pdf");
            
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


protected void deleteAtividade() {
	
		 
	  AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
      alert.setTitle("Deseja  excluir o Lembrete?");			
	  alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		 @Override
	  public void onClick(DialogInterface dialog, int whichButton) {
			 
			 			    
			    final MySQLiteHelper db = new MySQLiteHelper(getActivity());
			    final List<Atividade> atividades = db.getAllAtividades();
			    int listcounter=0;
			    Atividade atv =atividades.get(listcounter);
		        	
		  	    text1 = atv.getTitle();
				db.deleteAtividade(new Atividade(text1, null));
				
				text1 = "Adicione um Lembrete!";
		        db.addAtividade(new Atividade(text1, null));				 
				lembrete.setText(text1);
				 				 
				Toast.makeText(Home_Fragment.this.getActivity(), "Lembrete excluído!", Toast.LENGTH_SHORT).show();
		 
			 }
			   
		   
		 });		     	
		 
 
	   alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
	   @Override
	public void onClick(DialogInterface dialog, int whichButton) {
	     // Canceled.
	   }
	 });

	   alert.show();
	   
	  	//atividade_toque.setText("");
			
	}


private boolean isEmpty(EditText etText) {
	
    if (etText.getText().toString().trim().length() > 0) {
        return false;
    } else {
        return true;
    }
}

public class myWebClient extends WebViewClient{
	
 @Override
 public void onPageStarted(WebView view, String url, Bitmap favicon) {
     // TODO Auto-generated method stub
     super.onPageStarted(view, url, favicon);
 }

 @Override
 public boolean shouldOverrideUrlLoading(WebView view, String url) {
     // TODO Auto-generated method stub

    // view.loadUrl(url);
     return true;

 }

 @Override
 public void onPageFinished(WebView view, String url) {
     // TODO Auto-generated method stub
     super.onPageFinished(view, url);
     
       }
 
 }

protected void deleteAviso() {
	
	
	 
	 AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
     alert.setTitle("Deseja  excluir o Aviso?");				
	 alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		 @Override
	 public void onClick(DialogInterface dialog, int whichButton) {
			 
			 			    
			    final MySQLiteHelper db = new MySQLiteHelper(getActivity());
				final List<Avisos_Caip> avisos = db.getAllAvisos();  
			    int listcounter=0;
			    Avisos_Caip atv =avisos.get(listcounter);
		        	
		  	    text1 = atv.getTitle();
				db.deleteAviso(new Avisos_Caip(text1, null));
			    text1 = "Sem novos Avisos!";
			    db.addAviso(new Avisos_Caip(text1, null));
				 aviso_caip.setText(text1);
				 
				Toast.makeText(Home_Fragment.this.getActivity(), "Aviso excluído!", Toast.LENGTH_SHORT).show();
		      	  		  
			   }
			  
			     
		 });
		 
  	
		 
	   alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
	   @Override
	public void onClick(DialogInterface dialog, int whichButton) {
	     // Canceled.
	   }
	 });

	   alert.show();
		
	}


protected void deleteAviso_prof() {
	
	
	 
	  AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
      alert.setTitle("Deseja  excluir o Aviso?");
	  alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		 @Override
	public void onClick(DialogInterface dialog, int whichButton) {
			 
			 
			    
			    final MySQLiteHelper db = new MySQLiteHelper(getActivity());
				final List<Avisos_Prof> avisos_prof = db.getAllAvisos_prof();  
			    int listcounter=0;
			    Avisos_Prof atv =avisos_prof.get(listcounter);
		        	
		  	    text1 = atv.getTitle();
				db.deleteAviso_prof(new Avisos_Prof(text1, null));
				text1 = "Sem novos Avisos!";
				text2 = null;			
				
				db.addAviso_Prof(new Avisos_Prof(text1, text2));
				 
				aviso_prof.setText(text1);
				nome_prof.setText(text2);
				 
				 Toast.makeText(Home_Fragment.this.getActivity(), "Aviso excluído!", Toast.LENGTH_SHORT).show();
		      	   			  
			 }
			     			     
		 });
		 
  		
		 

	  alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
	   @Override
	public void onClick(DialogInterface dialog, int whichButton) {
	     // Canceled.
	      }
	 });
 
	  alert.show();
	   		
	}
	
	 public void sair(){
	    	
    	 AlertDialog.Builder alert = new AlertDialog.Builder(this.getActivity());
		 alert.setTitle("Encerrar IPRJapp");
		 alert.setMessage("Deseja desconectar do IPRJapp?");

		
		  new MySQLiteHelper(this.getActivity());
	    			 
   			
		 alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
   		  @Override
		public void onClick(DialogInterface dialog, int whichButton) {
   			
   		   		
   			Home_Fragment.this.getActivity().finish();
        		     
   			     }
   			     
   			    
   		 });
	 }
	 
	 public void onBackPressed() {
	    	
		 this.sair();
	 }
	 
	    private void share_caip() {
	    	
	    	Session session = Session.getActiveSession();
		    if (session != null){
		    String sesija = session.toString();
		    Log.w ("ss", sesija);}

			 final MySQLiteHelper db = new MySQLiteHelper(getActivity());
		    
		    List<Avisos_Caip> avisos = db.getAllAvisos();  
	    	int listcounter=0;
		    Avisos_Caip avi = avisos.get(listcounter);
		    aviso = avi.getTitle();

	        Bundle bundle = new Bundle();
	        bundle.putString("caption", "Fique informado!");
	        bundle.putString("description", aviso);
	        bundle.putString("name", "Aviso do Caip IPRJ");
	        bundle.putString("picture", "http://www.labcor.iprj.uerj.br/imagens/logo_uerj.jpg");
	     
	        new WebDialog.FeedDialogBuilder(this.getActivity(), session, bundle).build().show();
	    }
	    
	    public static class MyWebView extends WebView
		{
		    public MyWebView(Context context)
		    {
		        super(context);
		    }

		    // Note this!
		    @Override
		    public boolean onCheckIsTextEditor()
		    {
		        return true;
		    }

		    @Override
		    public boolean onTouchEvent(MotionEvent ev)
		    {
		        switch (ev.getAction())
		        {
		            case MotionEvent.ACTION_DOWN:
		            case MotionEvent.ACTION_UP:
		                if (!hasFocus())
		                    requestFocus();
		            break;
		        }

		        return super.onTouchEvent(ev);
		    }
		}
	 
private void share_lembrete() {
	    	
	    	Session session = Session.getActiveSession();
		    if (session != null){
		    String sesija = session.toString();
		    Log.w ("ss", sesija);}

			final MySQLiteHelper db = new MySQLiteHelper(getActivity());
			List<Atividade> ativ = db.getAllAtividades(); 
			int listcounter=0;
			Atividade atv = ativ.get(listcounter);			  
			lembretes = atv.getTitle();

	        Bundle bundle = new Bundle();
	        bundle.putString("caption", "Lembrete Importante!");
	        bundle.putString("description", lembretes);
	        bundle.putString("name", "Minhas Atividades");
	        bundle.putString("picture", "http://www.labcor.iprj.uerj.br/imagens/logo_uerj.jpg");
	     
	        new WebDialog.FeedDialogBuilder(this.getActivity(), session, bundle).build().show();
	    }
	 
private void share_aviso_prof() {
	
	Session session = Session.getActiveSession();
    if (session != null){
    String sesija = session.toString();
    Log.w ("ss", sesija);}

	final MySQLiteHelper db = new MySQLiteHelper(getActivity());
	List<Atividade> ativ = db.getAllAtividades(); 
	int listcounter=0;
	Atividade atv = ativ.get(listcounter);			  
	lembretes = atv.getTitle();

    Bundle bundle = new Bundle();
    bundle.putString("caption", "Fique Informado!");
    bundle.putString("description", aviso_professor);
    bundle.putString("name", "Mural do Professor");
    bundle.putString("picture", "http://www.labcor.iprj.uerj.br/imagens/logo_uerj.jpg");
 
    new WebDialog.FeedDialogBuilder(this.getActivity(), session, bundle).build().show();
}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("HomeRes", HomeRes);
	}

}
