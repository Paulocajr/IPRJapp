package iprj.app.main;

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

	private int mTitleRes;
	protected Fragment mFrag;
	 private Intent intent;

	public BaseActivity(int titleRes) {
		mTitleRes = titleRes;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(mTitleRes);
		

		// set the Behind View
		setBehindContentView(R.layout.menu_1);
		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			mFrag = new Left_Menu();
			t.replace(R.id.menu_frame, mFrag);
			t.commit();
		} else {
			mFrag = (Fragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
		}

		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;
		case R.id.github:
			
			 intent = new Intent(this, ListUsersActivity.class);
			 intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP); 
             startActivity(intent);
			//Util.goToGitHub(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}

