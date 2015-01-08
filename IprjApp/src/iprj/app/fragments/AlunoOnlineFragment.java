package iprj.app.fragments;

/*
  * AlunoOnlineFragment.java
  * Versão: <v2.0>
  * Data de Criação : 10/09/2014
  * Copyright (C) 2014 Paulo cabral
  * Instituto Politécnico do Estado do Rio de Janeiro
  * IPRJ - http://www.iprj.uerj.br
  * Classe responsável pelo framgent que exibe a página do Aluno Online
  * Todos os direitos reservados.
 */
 
 //Imports
import iprj.app.main.R;
import java.util.Calendar;
import android.app.ActionBar;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnKeyListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.ProgressBar;

public class AlunoOnlineFragment extends Fragment{

//**********Declaração de Variáveis****************

	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	String ano = Integer.toString(year) ;
	public static  WebView webView;
        public static ListView mDrawerList;
        public static ProgressBar progress;
     
    
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:{
                	canGoBack();
                }break;
            }
        }
    };
    
    @Override
    public void onStart() { 
        super.onStart();
                
        }
           
    
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
       //inflando layout reusável
        View rootView = inflater.inflate(R.layout.fragment_horario, container, false);
        
        ActionBar actionBar = getActivity().getActionBar();
        
        //declarando a webview
        webView = (WebView) rootView.findViewById(R.id.webView1);
        progress = (ProgressBar) rootView.findViewById(R.id.progressBar1);		
		webView.setWebViewClient(new myWebClient());        
		WebSettings settings = webView.getSettings();
	   	settings.setUseWideViewPort(true);
	        settings.setLoadWithOverviewMode(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.loadUrl("https://www.alunoonline.uerj.br/requisicaoaluno/requisicaoacesso.php?requisicao=LoginAlunoOnline");
	
		webView.setOnKeyListener(new OnKeyListener(){

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                  if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
                        handler.sendEmptyMessage(1);
                        return true;
                    }
                    return true;
            }

		

        });
	
          
        return rootView;
    }
    
    
	public boolean canGoBack() {
	
		webView.goBack();
		// TODO Auto-generated method stub
		return true;
	}
	
	   public class myWebClient extends WebViewClient
	     {
	     @Override
	     public void onPageStarted(WebView view, String url, Bitmap favicon) {
	         // TODO Auto-generated method stub
	         super.onPageStarted(view, url, favicon);
	     }

	     @Override
	     public boolean shouldOverrideUrlLoading(WebView view, String url) {
	         // TODO Auto-generated method stub

	         view.loadUrl(url);
	         return true;

	     }

	     @Override
	     public void onPageFinished(WebView view, String url) {
	         // TODO Auto-generated method stub
	         super.onPageFinished(view, url);
	         							
		         progress.setVisibility(View.GONE);

	        }
	     
	    }
         
}
