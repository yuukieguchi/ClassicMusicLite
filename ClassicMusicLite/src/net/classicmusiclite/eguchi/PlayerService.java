package net.classicmusiclite.eguchi;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlayerService extends Service // (3)
		implements MediaPlayer.OnCompletionListener {
	private MediaPlayer player;

	@Override
	public void onStart(Intent intent, int startID) {
		showNotification(this, R.drawable.ic_launcher, "CLASSIC", "CLASSIC",
				"AA, Op. 36.mp3.filepart	");

		playSound();
	}

	@Override
	public void onDestroy() {
		NotificationManager nm;
		nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		nm.cancel(0);

		stopSound();

		Util.showToast(this, R.string.stop);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	public void playSound() {
		try {
			// player=MediaPlayer.create(this,R.raw.sample);
			player = new MediaPlayer();
			// サンプル用　サーバ上のパブリックドメインファイル.mp3
			player.setDataSource("http://183.181.50.207/public/Classic/%83A%83%93%83g%83j%83%93%81E%83h%83%94%83H%83%8b%83U%81%5b%83N/%8c%f0%8b%bf%8b%c8%91%e68%94%d4%83g%92%b7%92%b2,%20Op.%2088/%91%e62%8ay%8f%cd%20Adagio.mp3");
			player.prepare();
			player.start();
			player.setOnCompletionListener(this);
		} catch (Exception e) {
		}
	}

	public void stopSound() {
		try {
			player.stop();
			player.setOnCompletionListener(null);
			player.release();
			player = null;
		} catch (Exception e) {
		}
	}

	public void onCompletion(MediaPlayer mediaPlayer) {
		stopSound();
	}

	private void showNotification(Context context, int iconID, String ticker,
			String title, String message) {
		NotificationManager nm;
		nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		Notification notification = new Notification(iconID, ticker,
				System.currentTimeMillis());
		PendingIntent intent = PendingIntent.getActivity(context, 0,
				new Intent(context, ClassicMusicLiteActivity.class), 0);
		notification.setLatestEventInfo(context, title, message, intent);

		nm.cancel(0);

		nm.notify(0, notification);
	}

}