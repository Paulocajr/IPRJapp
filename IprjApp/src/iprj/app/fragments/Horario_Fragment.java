package iprj.app.fragments;

import iprj.app.main.R;

import java.util.List;

import com.artifex.mupdf.MuPDFCore;
import com.artifex.mupdf.MuPDFPageAdapter;
import com.artifex.mupdf.view.ReaderView;
import com.iprjappteste.data.Curso;
import com.iprjappteste.data.MySQLiteHelper;
import com.iprjappteste.data.Periodos;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class Horario_Fragment extends Fragment {
	
	public String path;
	public String path1;
	private MuPDFCore core;
	private ReaderView docView;
	public String nome_curso = null; 
	public String nome_curso2 = null;
	public String url1 ;
	public String nome =null;
	public String numero_periodo;
	public String numero_periodo2;
	public String periodo =null;
	public String z;
	private static final String TAG = "HorarioFragment";
	private MuPDFCore cores[];
	String paths[]={ Environment.getExternalStorageDirectory().getAbsolutePath()+ path};
	String paths2[]={ Environment.getExternalStorageDirectory().getAbsolutePath()+ path, Environment.getExternalStorageDirectory().getAbsolutePath()+ path1};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        final MySQLiteHelper db = new MySQLiteHelper(getActivity());   	 
   	    List<Curso> cursos = db.getAllCursos();
   	    List<Periodos> periodos = db.getAllPeriodos();
  
   	    int listcounter=0;
   	    Curso cn=cursos.get(listcounter);
   	    Periodos per =periodos.get(0);
   	    Periodos per2 =periodos.get(1);
        nome = cn.getTitle();
        periodo = per.getTitle();
        numero_periodo  = per.getNum_per();
        numero_periodo2  = per2.getNum_per();
	    //numero_periodo = bundle.getString("position");
	    //String a = bundle.getString("z");
	    
	    /*if (bundle != null) {
	    	  
	    	   numero_periodo = bundle.getString("position");
	    }
	    */
	   if(nome.equals("Engenharia de Computação")){
	    
	    	//nome_curso = "e"
	    		//	+ "EngComp";
			nome_curso = "EngComp";
       	    	    }
	    
	    else {
	    	
		      nome_curso = "EngMec";
	    } 
	    
	 
	 

  //   path= (Environment.getExternalStorageDirectory().getAbsolutePath()+ "/Download/myPDF/horario-"+periodo+"-"+nome_curso+".pdf");
     
     path= (Environment.getExternalStorageDirectory().getAbsolutePath()+ "/Download/myPDF/UERJ_IPRJ_"+nome_curso+"_Disc_2014_2_p"+numero_periodo+".pdf");
     path1= (Environment.getExternalStorageDirectory().getAbsolutePath()+ "/Download/myPDF/UERJ_IPRJ_"+nome_curso+"_Disc_2014_2_p"+numero_periodo2+".pdf");
     View  rootView = null;
        
        if(numero_periodo2 == null) {
                	
          	Horario_Fragment.this.ViewPDF();
        }
        
            else {
            	
            	  Horario_Fragment.this.ViewPDF2();
    	        	      
            }
        rootView = inflater.inflate(R.layout.mupdf_wrapper, container, false);
        RelativeLayout mupdfWrapper = (RelativeLayout) rootView.findViewById(R.id.mupdf_wrapper);
        //path = "path/To/Your/PDF/File.pdf";
        //MuPDFCore core_1 = new MuPDFCore(path);
        ReaderView mDocView = new ReaderView(getActivity());
        mDocView.setAdapter(new MuPDFPageAdapter(getActivity().getApplicationContext(), cores));
        mupdfWrapper.addView(mDocView);
        return rootView;
    }
    
    
    private void ViewPDF(){
    	
        cores= new MuPDFCore[paths.length];
        
		for(int i=0;i<paths.length;i++)
		{

			cores[i] = openFile(paths[i]);
			///cores[1] = openFile_2(paths[i]);
			
		}
        
    }
    
    
    private void ViewPDF2(){
    	
        cores= new MuPDFCore[paths2.length];
        
		for(int i=0;i<paths2.length;i++)
		{
			
			cores[0] = openFile(paths2[i]);
			cores[1] = openFile_2(paths2[i]);
			
		}
        
    }
    
    private MuPDFCore openFile_2(String paths2){
		try
		{
			//path = path_1; 
			core = new MuPDFCore(path1);
		
		} catch (Exception e) {
			Log.e(TAG, "get core failed", e);
			return null;
		}
		
		return (core);
	}
    
    private MuPDFCore openFile(String paths2){
		try
		{
			//path = path_1; 
			
			
			//core = new MuPDFCore(path_1);
			core = new MuPDFCore(path);
  			
			
		} catch (Exception e) {
			Log.e(TAG, "get core failed", e);
			return null;
		}
		return (core);
	}
}
    
   /* private MuPDFCore openFile_1(String paths2){
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
/*public class HorarioFragment extends Fragment{
	
	public String semestre_corrente;
	
    public HorarioFragment(){}
    public static ListView mDrawerList;
    Calendar now = Calendar.getInstance();
    int month = now.get(Calendar.MONTH);
    String mes = Integer.toString(month) ;
    int semestre;
    public String nome_curso = null; 
    public String nome_curso2 = null;
    public String url1 ;
    public String nome =null;
    public String numero_periodo;
    public String periodo =null;
    public String z;
     public String path;
    
    File f = null;
  
    
    
           
    String[] pdflist;
    File[] imagelist;
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        final MySQLiteHelper db = new MySQLiteHelper(getActivity());
    	 
    	 List<Curso> cursos = db.getAllCursos();
    	 List<Periodos> periodos = db.getAllPeriodos();
    	ActionBar actionBar = getActivity().getActionBar();
    	View rootView = inflater.inflate(R.layout.fragment_horario, container, false);
        
  
        
        Bundle bundle = getActivity().getIntent().getExtras();
       
     int listcounter=0;
   	 Curso cn=cursos.get(listcounter);
   	 Periodos per =periodos.get(listcounter);
   	 
   	 
   	// Atividade atv=atividades.get(listcounter);
   	  
   	        nome = cn.getTitle();
   	        periodo = per.getTitle();
    	    numero_periodo = bundle.getString("position");
    	    String a = bundle.getString("z");
    	    
    	   if(nome.equals("Engenharia de Computação")){
    	    
    	    	nome_curso = "engcomp";
    	    	
    	    }
    	    
    	    else
    		nome_curso = "engmec";
    	       	    
    	    
    	    
    	 
    	   //path = f.getAbsolutePath();

	    	//Toast.makeText(getActivity().getApplicationContext(), a, Toast.LENGTH_SHORT).show();
            path= (Environment.getExternalStorageDirectory().getAbsolutePath()+ "/Download/myPDF/horario-"+periodo+"-"+nome_curso+".pdf");
    	    //String caminho = path;
    	    try {
    	    	
    	    	MuPDFActivity pdf = new MuPDFActivity();
    	    	pdf.createUI(savedInstanceState);
               /* final Intent intent = new Intent(getActivity(), this.MuPDFActivity);
                intent.putExtra("path",caminho);
                getActivity().startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
    	    
    	       
		
   	    
        			return rootView;
    	  
    
	
    	    		}    		
    	     

    private class MuPDFActivity extends Activity
    {
    	private static final String TAG = "MuPDFActivity";
    	private MuPDFCore core;
    	private MuPDFCore cores[];
    	private ReaderView docView;
    	//private String path_1;
    	private MuPDFPageAdapter mDocViewAdapter;
    	
    	String paths[]={ Environment.getExternalStorageDirectory().getAbsolutePath()+ path};

    	@Override
    	public void onCreate(Bundle savedInstanceState) 
    	{
    		
    		
    		
    		super.onCreate(savedInstanceState);
    		cores= new MuPDFCore[paths.length];
    		for(int i=0;i<paths.length;i++)
    		{
    			
    			cores[i] = openFile(paths[i]);
    			
    		}
    		createUI(savedInstanceState);
    	}
    	private void createUI(Bundle savedInstanceState) 
    	{
    		docView = new DocumentReaderView(this)
    		{
    			@Override
    			protected void onMoveToChild(View view, int i) 
    			{
    				super.onMoveToChild(view, i);
    			}

    			@Override
    			public boolean onScroll(MotionEvent e1, MotionEvent e2,float distanceX, float distanceY)
    			{
    				return super.onScroll(e1, e2, distanceX, distanceY);
    			}

    			@Override
    			protected void onContextMenuClick() 
    			{

    			}

    			@Override
    			protected void onBuy(String path) 
    			{
    			
    			}

    		};
    		
    		mDocViewAdapter = new MuPDFPageAdapter(this, cores);
    		docView.setAdapter(mDocViewAdapter);
    		RelativeLayout layout = new RelativeLayout(this);
    		layout.addView(docView);
    		layout.setBackgroundColor(Color.BLACK);
    		setContentView(layout);
    	}
    	

    	private MuPDFCore openFile(String path) 
    	{
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
    
}*/

