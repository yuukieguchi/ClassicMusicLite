package net.classicmusiclite.eguchi;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.nobot.libAdMaker.AdMakerListener;
import jp.co.nobot.libAdMaker.libAdMaker;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Scr010 extends Activity implements OnClickListener,
		AdMakerListener {

	private static final int ITEM_NUM = 3;
	private static final int SUBITEM_NUM = 4;
	private libAdMaker AdMaker;

	private String[] arr_artist_name;
	private String[] arr_artist_wiki;
	private int[] arr_artist_image = { R.drawable.dvorak, R.drawable.elgar,
			R.drawable.mozart, R.drawable.ravel, R.drawable.sarasate,
			R.drawable.tschaikowski, R.drawable.beethoven };

	private int getId;
	private ImageView artistImage = null;

	private ListView lstView_ArtistMusic;
	private ArrayList<ArtistInfoId> arrList_ArtistMusicView = new ArrayList<ArtistInfoId>();

	private String[] arr_MusicName;
	private ArrayList<MusicId> mMusicView = new ArrayList<MusicId>();

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.scr010);

		// ======================== Advertisement
		// =======================================
		AdMaker = (libAdMaker) findViewById(R.id.admakerview);
		AdMaker.setAdMakerListener(this);
		AdMaker.siteId = "111";
		AdMaker.zoneId = "1111";
		AdMaker.setUrl("http://images.ad-maker.info/apps/25883fb4e3b365bb723a.html");
		AdMaker.start();
		// ==============================================================================

		arr_artist_name = getResources()
				.getStringArray(R.array.arr_artist_name);
		arr_artist_wiki = getResources()
				.getStringArray(R.array.arr_artist_wiki);
		// arr_artist_image =
		// getResources().getStringArray(R.array.arr_artist_image);
		// 取得したアーティストのIDから名前とWikiを表示
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			getId = extras.getInt("ARTIST_ID");
			TextView txt_artistName = (TextView) findViewById(R.id.txt_artist_name);
			TextView txt_artistContents = (TextView) findViewById(R.id.txt_artist_contents);
			txt_artistName.setText(arr_artist_name[getId]);
			txt_artistContents.setText(arr_artist_wiki[getId]);

			// 画像の設置
			artistImage = new ImageView(this);
			artistImage = (ImageView) findViewById(R.id.artist_image);
			// artistImage.setImageResource(R.drawable.no_image);
			artistImage.setImageResource(arr_artist_image[getId]);
			// getImagefromURL();

		}

		// アイテムのリスト
		List<Map<String, String>> itemList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < ITEM_NUM; i++) {
			Map<String, String> item = new HashMap<String, String>();
			item.put("ITEM", "Item " + (i + 1));
			itemList.add(item);
		}

		// 各アイテムのサブアイテムのリスト
		List<List<Map<String, String>>> allSubItemList = new ArrayList<List<Map<String, String>>>();
		// サブアイテム, 詳細のリスト
		for (int i = 0; i < ITEM_NUM; i++) {
			List<Map<String, String>> subItemList = new ArrayList<Map<String, String>>();
			for (int j = 0; j < SUBITEM_NUM; j++) {
				Map<String, String> subItem = new HashMap<String, String>();
				subItem.put("SUBITEM", "SubItem " + (i + 1) + "-" + (j + 1));
				subItem.put("DETAIL", "Detail " + (i + 1) + "-" + (j + 1));
				subItemList.add(subItem);
			}
			allSubItemList.add(subItemList);
		}

		// アーティストに対応する曲のリストを取得し表示する
		arr_MusicName = getResources().getStringArray(R.array.id_001_music);
		lstView_ArtistMusic = (ListView) this.findViewById(R.id.list);

		/*
		 * // アダプタを作成 SimpleExpandableListAdapter adapter = new
		 * SimpleExpandableListAdapter(this, itemList,
		 * android.R.layout.simple_expandable_list_item_1,new String[] {"ITEM"},
		 * new int[]{android.R.id.text1}, allSubItemList,
		 * android.R.layout.simple_expandable_list_item_2, new
		 * String[]{"SUBITEM", "DETAIL"}, new int[]{android.R.id.text1,
		 * android.R.id.text2} ); // アダプタを設定 // setListAdapter(adapter);
		 */

		ArrayAdapter<MusicId> adapter = new ArrayAdapter<MusicId>(this,
				R.layout.row_artist_info, R.id.txt_music_name, mMusicView);
		lstView_ArtistMusic.setAdapter(adapter);

		for (int i = 0; i < arr_MusicName.length; i++) {
			mMusicView.add(new MusicId(i, arr_MusicName[i]));
		}
	}

	/*
	 * // サブアイテムが選択されたときの処理 public boolean onChildClick(ExpandableListView
	 * parent, View v, int groupPosition, int childPosition, long id) { // TODO
	 * Auto-generated method stub // アダプタを取得 ExpandableListAdapter adapter =
	 * parent.getExpandableListAdapter(); // アダプタから選択されたサブアイテムのデータを取得
	 * Map<String, String> subItem = (Map<String,
	 * String>)adapter.getChild(groupPosition, childPosition);
	 * Log.v("ExpListView", subItem.get("SUBITEM") + " / " +
	 * subItem.get("DETAIL")); return super.onChildClick(parent, v,
	 * groupPosition, childPosition, id); }
	 */

	/*
	 * // アーティストに対応する曲のリストを取得し表示する arr_MusicName =
	 * getResources().getStringArray(R.array.id_001_music); lstView_ArtistMusic
	 * = (ListView)this.findViewById(R.id.list);
	 * 
	 * // リストの曲を押した時の処理 lstView_ArtistMusic.setOnItemClickListener( new
	 * AdapterView.OnItemClickListener() { public void
	 * onItemClick(AdapterView<?> parent, View view, int position, long id) { //
	 * ListView listView = (ListView)parent; // ArtistId artist =
	 * (ArtistId)listView.getItemAtPosition(position); } });
	 * 
	 * ArrayAdapter<MusicId> adapter = new ArrayAdapter<MusicId>( this,
	 * R.layout.row_artist_info, R.id.txt_music_name, mMusicView );
	 * lstView_ArtistMusic.setAdapter(adapter);
	 * 
	 * for(int i = 0; i < arr_MusicName.length; i++ ){ mMusicView.add(new
	 * MusicId(i, arr_MusicName[i]) ); }
	 */

	/**
	 * サーバ上に設置してある画像を取得し、R.id.artist_imageにセット 引数：画像URL
	 * */
	@SuppressWarnings("unused")
	private String getImagefromURL(String urlString) {

		// urlString =
		// "http://183.181.50.207/public/Classic/Image/beethoven.jpg";
		try {
			URL url = new URL(urlString);
			InputStream istream = url.openStream();
			// 画像をDrawableで取得
			Drawable draw = Drawable.createFromStream(istream, "webimg");
			// 入力ストリームを閉じる
			istream.close();
			artistImage = new ImageView(this);
			artistImage = (ImageView) findViewById(R.id.artist_image);
			artistImage.setImageResource(R.drawable.no_image);
			artistImage.setImageDrawable(draw);
		} catch (MalformedURLException e) {
			// URL ERROR
			e.printStackTrace();
			// artistImage = BitmapFactory.decodeResource(getResources(),
			// R.drawable.dvorak);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return urlString;
	}

	// AdMaker用 unimplemented method
	public void onFailedToReceiveAdMaker(String arg0) {
		// TODO Auto-generated method stub

	}

	public void onReceiveAdMaker() {
		// TODO Auto-generated method stub

	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
