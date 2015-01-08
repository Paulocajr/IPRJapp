package iprj.app.fragments;

import iprj.app.main.R;

import java.util.Calendar;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.artifex.mupdf.MuPDFCore;
import com.artifex.mupdf.MuPDFPageAdapter;
import com.artifex.mupdf.view.ReaderView;




public class Guia_de_Ramais extends Fragment {
    
	Calendar now = Calendar.getInstance();
	public String path;
	private MuPDFCore core;
	int year = now.get(Calendar.YEAR);
	String ano = Integer.toString(year) ;
	private MuPDFCore cores[];
	private static final String TAG = "Guia_de_Ramais";
	String paths[]={ Environment.getExternalStorageDirectory().getAbsolutePath()+ path};
   
     
   
    
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
    	 View  rootView = null;
    	 
    	path= (Environment.getExternalStorageDirectory().getAbsolutePath()+ "/Download/myPDF/Alfabética.pdf");
         
        cores= new MuPDFCore[paths.length];
        
 		   for(int i=0;i<paths.length;i++)
 		                         {
 			
 			cores[i] = openFile(paths[i]);
 			
 		}
         

         rootView = inflater.inflate(R.layout.mupdf_wrapper_1, container, false);
         RelativeLayout mupdfWrapper = (RelativeLayout) rootView.findViewById(R.id.mupdf_wrapper);
         //path = "path/To/Your/PDF/File.pdf";
         //MuPDFCore core_1 = new MuPDFCore(path);
         ReaderView mDocView = new ReaderView(getActivity());
         mDocView.setAdapter(new MuPDFPageAdapter(getActivity().getApplicationContext(), cores));
         mupdfWrapper.addView(mDocView);
         return rootView;
     }
     
 
        
    private MuPDFCore openFile(String paths2){
		try
		{
			//path = path_1; 
			core = new MuPDFCore(path);
		} catch (Exception e) {
			Log.e(TAG, "get core failed", e);
			return null;
		}
		return core;
	}
   
   
} 