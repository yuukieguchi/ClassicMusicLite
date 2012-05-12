package net.classicmusiclite.eguchi;

import java.util.ArrayList;

import jp.co.nobot.libAdMaker.AdMakerListener;
import jp.co.nobot.libAdMaker.libAdMaker;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ClassicMusicLiteActivity extends Activity implements
		OnClickListener, AdMakerListener {

	private libAdMaker AdMaker;

	private static final int MENU_ITEM0 = 0, MENU_ITEM1 = 1, MENU_ITEM2 = 2;

	private ListView lstView_Artist;
	private ArrayList<ArtistId> arrList_ArtistView = new ArrayList<ArtistId>();

	private String[] arr_ArtistId;
	private String[] arr_ArtistName;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		// ========================== Advertisement ==========================
		AdMaker = (libAdMaker) findViewById(R.id.admakerview);
		AdMaker.setAdMakerListener(this);
		AdMaker.siteId = "111";
		AdMaker.zoneId = "1111";
		AdMaker.setUrl("http://images.ad-maker.info/apps/25883fb4e3b365bb723a.html");
		AdMaker.start();
		// =================================================================

		arr_ArtistName = getResources().getStringArray(R.array.arr_artist_name);

		// list from layout
		lstView_Artist = (ListView) this.findViewById(R.id.list);

		// push artistName and intent next Class
		lstView_Artist
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						ListView listView = (ListView) parent;
						ArtistId artist = (ArtistId) listView
								.getItemAtPosition(position);
						Intent intent = new Intent(
								ClassicMusicLiteActivity.this, Scr010.class);
						intent.putExtra("ARTIST_ID", artist.getId());
						startActivity(intent);
					}
				});

		ArrayAdapter<ArtistId> adapter = new ArrayAdapter<ArtistId>(this,
				R.layout.row_artist, R.id.txtArtistName, arrList_ArtistView);
		lstView_Artist.setAdapter(adapter);

		for (int i = 0; i < arr_ArtistName.length; i++) {
			arrList_ArtistView.add(new ArtistId(i, arr_ArtistName[i]));
		}

		// ============ Play and Stop Music Button =============
		// Button btn_play = (Button)this.findViewById(R.id.btn_play);
		// btn_play.setOnClickListener(this);
		//
		// Button btn_stop= (Button)this.findViewById(R.id.btn_stop);
		// btn_stop.setOnClickListener(this);

	}

	// ============= Play and Stop Music ==========================
	public void onClick(View view) {

		// Intent intent=new Intent();
		//
		// switch(view.getId()){
		// case R.id.btn_play:
		// intent.setClass(this,PlayerService.class);
		// startService(intent);
		// break;
		// case R.id.btn_stop:
		// intent.setClass(this,PlayerService.class);
		// stopService(intent);
		// break;
		// }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu item) {
		super.onCreateOptionsMenu(item);

		MenuItem item0 = item.add(0, MENU_ITEM0, 0, R.string.info);
		item0.setIcon(android.R.drawable.ic_dialog_info);

		MenuItem item1 = item.add(0, MENU_ITEM1, 0, R.string.setup);
		item1.setIcon(android.R.drawable.ic_menu_manage);

		MenuItem item2 = item.add(0, MENU_ITEM2, 0, R.string.cancel);
		item2.setIcon(android.R.drawable.ic_menu_close_clear_cancel);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case MENU_ITEM0:
			return true;

		case MENU_ITEM1:
			return true;

		case MENU_ITEM2:
			finish();
			return true;
		}
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	// AdMaker.destroy when Appli destroy
	@Override
	protected void onDestroy() {
		super.onDestroy();
		AdMaker.destroy();
		AdMaker = null;
	}

	@Override
	protected void onPause() {
		super.onPause();
		AdMaker.stop();
	}

	@Override
	public void onRestart() {
		super.onRestart();
		AdMaker.start();
	}

	public void onFailedToReceiveAdMaker(String arg0) {

	}

	public void onReceiveAdMaker() {

	}

}