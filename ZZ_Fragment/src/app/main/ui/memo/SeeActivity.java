package app.main.ui.memo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.TextView;
import app.main.R;

public class SeeActivity extends Activity {

	private TextView tv_see;
	private long _id;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_browse);
		tv_see = (TextView) findViewById(R.id.tv_see);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		loadData(_id);
		super.onResume();
	}

	public void loadData(long _id) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		MenuItem item1 = menu.add("删除");
		MenuItem item2 = menu.add("取消");

		item1.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub

				android.app.AlertDialog.Builder builder = new AlertDialog.Builder(
						SeeActivity.this);
				builder.setIcon(R.drawable.png0652);
				builder.setTitle("删除？");
				builder.setMessage("是否删除？");

				builder.setPositiveButton("是",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								loadData(_id);
								AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
								System.out.println("传过来的_id=" + _id);
								finish();
							}
						});
				builder.setNegativeButton("取消", null);
				builder.create().show();
				return false;
			}
		});
		item2.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				finish();
				return false;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}

}
