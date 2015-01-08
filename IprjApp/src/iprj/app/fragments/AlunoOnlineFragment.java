package iprj.app.fragments;

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
  
        View rootView = inflater.inflate(R.layout.fragment_horario, container, false);
        ActionBar actionBar = getActivity().getActionBar();
        
      //  actionBar.hide();
        
        
        webView = (WebView) rootView.findViewById(R.id.webView1);
        progress = (ProgressBar) rootView.findViewById(R.id.progressBar1);		
		webView.setWebViewClient(new myWebClient());        
		WebSettings settings = webView.getSettings();
	   	settings.setUseWideViewPort(true);
	    settings.setLoadWithOverviewMode(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.loadUrl("https://www.alunoonline.uerj.br/requisicaoaluno/requisicaoacesso.php?requisicao=LoginAlunoOnline");
		/*webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
			          view.loadUrl(url);
			          return true;
			           }});*/
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
