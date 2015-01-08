package iprj.app.main;

/*
  * BaseActivity.java
  * Versão: <v2.0>
  * Todos os direitos reservados.
  * Autor:jeremyfeinstein
  * Classe pertencente a biblioteca da interace gráfica do menu
  * lateral, dos fragments. 
  * Fiz algumas alterações para se adequar ao presente projeto
 */

// Imports
import iprj.app.main.Util;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import iprj.app.fragments.AlunoOnlineFragment;
import iprj.app.fragments.Biblioteca_Fragment;
import iprj.app.fragments.Iprj_Fragment;
import iprj.app.fragments.Moodle_Fragment;
import iprj.app.fragments.Uerj_tk_Fragment;
import iprj.app.main.Left_Menu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.sinch.messagingtutorial.app.ListUsersActivity;
import com.sinch.messagingtutorial.app.MessageService;

public class BaseActivity extends SlidingFragmentActivity {
	
	
//************Variáves**************//

	private int mTitleRes;
	protected Fragment mFrag;
	private Intent intent;
	public BaseActivity(int titleRes) {
		mTitleRes = titleRes;
	}

//Inicio do método onCreate

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(mTitleRes);
		

		// seta o menu lateral
		setBehindContentView(R.layout.menu_1);
		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			mFrag = new Left_Menu();
			t.replace(R.id.menu_frame, mFrag);
			t.commit();
		} else {
			mFrag = (Fragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
		}

		// customizando o menu, sombras, dimensões
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

 /**
  *Metodo responsável pela acão do botão do chat na ActionBar
  * */

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;
			
		case R.id.github:
			//ao ser selecionado o icone do chat a Activity que lista os usuários é aberta
			 intent = new Intent(this, ListUsersActivity.class);
			 intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP); 
                         startActivity(intent);
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
 /**
  *Metodo responsável por acrescentar um botão a AcrionBar
  *Foi adicionado o botão do chat em todas as telas a partir desse método
  * */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}

