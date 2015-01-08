package iprj.app.main;


/*
  * Left_menu.java
  * Versão: <v2.0>
  * Data de Criação : 19/09/2014
  * Copyright (C) 2014 Paulo cabral
  * Instituto Politécnico do Estado do Rio de Janeiro
  * IPRJ - http://www.iprj.uerj.br
  * Classe do Menu lateral, responsável pela parte gráfica
  * Exibe a lista com os itens do menu,a foto do usuário e o nome do curso
  * Todos os direitos reservados.
 */
 
 //Imports

import java.util.List;
import com.facebook.FacebookException;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.ProfilePictureView;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
import iprj.app.fragments.AlunoOnlineFragment;
import iprj.app.fragments.Biblioteca_Fragment;
import iprj.app.fragments.Calendario_Fragment;
import iprj.app.fragments.Guia_de_Ramais;
import iprj.app.fragments.Home_Fragment;
import iprj.app.fragments.Horario_Fragment;
import iprj.app.fragments.Iprj_Fragment;
import iprj.app.fragments.Moodle_Fragment;
import iprj.app.fragments.Uerj_tk_Fragment;
import com.iprjappteste.data.Curso;
import com.iprjappteste.data.MySQLiteHelper;
import com.iprjappteste.data.Periodos;
import com.iprjappteste.data.Usuario;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import iprj.app.fragments.FragmentChangeActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Left_Menu extends Fragment implements OnItemClickListener{
	
    private static final String TAG_MY_CLASS = null;
	ListView listView ;
    public View rootView;
    public String usuario;
    public String nome;
    private int LeftRes = -1;
    public String channel;
    //public Bitmap bitmap;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		

    	rootView = inflater.inflate(R.layout.menu_1, container, false);    	    			   
    	// Get ListView object from xml
        listView = (ListView) rootView.findViewById(R.id.listView1);
        
        Parse.initialize(this.getActivity(), "8FttRlqaAUPabxXzuLeTMbmgAA389L7oVbFaSqjj", "6sFwktakiPYSDg1NKzgP18yanSjlB9CQKFGUOShs");
        ParseInstallation.getCurrentInstallation().saveInBackground();
       
        final MySQLiteHelper db = new MySQLiteHelper(getActivity());
        List<Usuario> usuarios = db.getAllUsuario();
    	List<Periodos> periodos = db.getAllPeriodos();
    	List<Curso> cursos = db.getAllCursos();
    	
    	   Session.openActiveSession(this.getActivity(), true, new Session.StatusCallback() {

   		    // callback when session changes state
   		    @Override
   		    public void call(final Session session, SessionState state, Exception exception) {
   		    	if (session.isOpened()) {
   		    		  	
   		    		Request.newMeRequest(session, new Request.GraphUserCallback() {

   		    			  // callback after Graph API response with user object
   		    			  @Override
   		    			  public void onCompleted(GraphUser user, Response response) {
   		    				  
   		    				  if (user != null) { 	    					  
   		    				  	    		
   		    				 }
   		    			  }
   		    			}).executeAsync();
   		    		}
   		    }
   		  });  
      
    	
  	  int listcounter=0;
  	  Curso cn=cursos.get(listcounter);
  	  Periodos per =periodos.get(listcounter);
  	  Usuario user =usuarios.get(listcounter);

  	  String nome1 = user.getTitle();
          String  curso = cn.getTitle();
          String nome_periodo = per.getTitle();
          String  periodo = per.getTitle();
          String id = user.getId_Facebook();
          
          if(curso.equals("Engenharia de Computação")){
        	  
        	  channel = "Computacao";
                }
            else {
            	
            	channel = "Mecanica";
               }
          
        // Array para os itens da ListView
          String[] values = new String[] { "Home", 
                                         "Horário",
                                         "Calendário Acadêmico",
                                         "Guia de Ramais",
                                         "Biblioteca", 
                                         "Uerj.tk", 
                                         "AlunoOnline", 
                                         "Iprj",
                                         "Moodle", 
                                         "Convidar Amigos" ,
                                         "Sobre" ,
                                         "Logout"
                                        };
             
   
   // Definindos elementos do XML
             TextView nome_user = (TextView) rootView.findViewById(R.id.textView1);
   	     nome_user.setText(nome1);   	     

   	     TextView nome_curso = (TextView) rootView.findViewById(R.id.textView2);
   	     nome_curso.setText(curso);
   	     //nome_user.setText(nome);   	     

   	   
  // Carregando a  ListView a partir do arquivo XML      
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
         android.R.layout.simple_list_item_1, android.R.id.text1, values);

 //Definindo a imagem do perfil do usuário
         ProfilePictureView profilePictureView;
	 profilePictureView = (ProfilePictureView)rootView.findViewById(R.id.profile_picture);
	 profilePictureView.setProfileId(id);		

         listView.setAdapter(adapter);         
         listView.setOnItemClickListener(new OnItemClickListener() {
        	 
            @Override
			public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
              
             // index do item selecionado
             int itemPosition  = position;
             
             // valor do item selecionado
             String  itemValue    = (String) listView.getItemAtPosition(position);
             Fragment newContent = null;
             switch (position) {
     		case 0:
     			newContent = new Home_Fragment();
     			break;
     		case 1:
     			newContent = new Horario_Fragment();
     			break;
     		case 2:
     			newContent = new Calendario_Fragment();
     			break;
     			
     		case 3:
     			newContent = new Guia_de_Ramais();
     			break;
     			
     		case 4 :
     			newContent = new Biblioteca_Fragment();
     			break;
     			
     		case 5 :
     			newContent = new Uerj_tk_Fragment();
     			break;
     			
     		case 6 :
     			newContent = new AlunoOnlineFragment();
     			break;
     			
     			
     		case 7 :
     			newContent = new Iprj_Fragment();
     			break;
     			
     		case 8:
     			newContent = new Moodle_Fragment();
     			break;
     			
     		case 9:
     			 Left_Menu.this.sendRequestDialog();
     			break;
     			
     		case 10:
     			 Left_Menu.this.sobre();
     			 
     			break;
     			
     		case 11:
     			 Left_Menu.this.logout();
     			break;
     	
             }
             
             
             if (newContent != null)
     			switchFragment(newContent);
            }
    });    
       				
	return rootView;
		       		
	}
	
	
// Método que envia o convite para o facebook do amigo selecionado a fim de fazer o download do app

	 private void sendRequestDialog() {
	 	
	        Bundle params = new Bundle();
	        params.putString("title", "Solicitação de Aplicativo");
	        params.putString("message", "Experimente o IPRJapp");
	        params.putString("link","https://play.google.com/store/apps/details?id=com.wb.goog.batman.brawler2013");
	        params.putString("data",
	            "{\"badge_of_awesomeness\":\"1\"," +
	            "\"social_karma\":\"5\"}");  

	        WebDialog requestsDialog = (
	            new WebDialog.RequestsDialogBuilder(this.getActivity(), Session.getActiveSession(), params))
	                .setOnCompleteListener(new OnCompleteListener() {

	                    @Override
	                    public void onComplete(Bundle values, FacebookException error) {
	                    }

	                })
	                .build();
	        requestsDialog.show();
	    }
	
// Método que exibe uma caixa de dialogo com informações do aplicativo
// Mostra a versão do aplivativo e alguns elementos esátivos de texto

public void sobre(){
	    	
	    	PackageInfo pInfo = null;
			try {
				pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	String version = pInfo.versionName;
	    	AlertDialog.Builder alert = new AlertDialog.Builder(this.getActivity()); 
	   	alert.setMessage("Software desenvolvido por Paulo Cabral.\n" +
	   	 		           "Versão:"+version+".\n" +
	   	 				   "Contato: paulocajr@live.com.\n" +
	   	 		           "Agradecimentos ao colaborador e amigo Hamilton Assunção.");
	   	 alert.setTitle("Sobre");
	   	 
	   	 
	   	 alert.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
	   	     @Override
	   	     public void onClick(DialogInterface dialog, int id) {
	   	         dialog.dismiss();
	   	     }
	   	 });
	   	 alert.show();
	   	
			 
		 }
	


// Método que faz a troca dos Fragments
private void switchFragment(Fragment fragment) {
	
		if (getActivity() == null)
			return;
		
		if (getActivity() instanceof FragmentChangeActivity) {
			FragmentChangeActivity fca = (FragmentChangeActivity) getActivity();
			fca.switchContent(fragment);
                	} 
		}

// Método que faz o logout da conta do usuário no aplicativo
// Mostra uma caixa de diálogo pedindo confirmação
//Fecha o aplivativo após confirmação e limpa as informações salvas no banco

public void logout(){
	    	
	    	//caixa de dialogo
    	         AlertDialog.Builder alert = new AlertDialog.Builder(this.getActivity());
		 alert.setTitle("Deseja  fazer Logout do IPRJapp?");
		 alert.setMessage("Atenção! Ao fazer logout do aplicativo, seus dados de Curso e Período serão perdidos.");

                // inicializando banco de dados
		 final MySQLiteHelper db = new MySQLiteHelper(this.getActivity());    	 
		 

		
		 alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
   		 @Override
		 public void onClick(DialogInterface dialog, int whichButton) {
   			
   		 db.deleteCurso();
   		 db.deletePeriodo();
   		 ParsePush.unsubscribeInBackground(channel);
   		 getActivity().finish();
    			     
   			     }
   			  });
   		 
	   		
   		    
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		   @Override
		public void onClick(DialogInterface dialog, int whichButton) {
		     // Canceled.
		   }
		 });

		 alert.show();

    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
	

}
