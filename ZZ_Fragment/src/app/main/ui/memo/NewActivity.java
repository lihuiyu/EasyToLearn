package app.main.ui.memo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import app.main.R;

public class NewActivity extends Activity {
	private EditText et_title;
	private EditText et_content;
	private TextView tiem;
	private TextView tv_tiem;
	private Button but_set;
	private String set_date = "";
	private String date = "";
	private Calendar calendar, ca;
	private long one, two;
	private long _id;
	private int count = 0;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memo_new);
		et_title = (EditText) findViewById(R.id.et_title);
		et_content = (EditText) findViewById(R.id.et_content);
		tiem = (TextView) findViewById(R.id.tiem);
		tv_tiem = (TextView) findViewById(R.id.tv_tiem);
		but_set = (Button) findViewById(R.id.but_set);

		final Calendar c = Calendar.getInstance();

		calendar = Calendar.getInstance();
		ca = Calendar.getInstance();

		// 获取当前的时间
		set_date = new SimpleDateFormat("yyyy/MM/dd  hh:mm").format(new Date());
		tiem.setText(set_date);
		// 获取触发时间
		but_set.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				DatePickerDialog dialog = new DatePickerDialog(
						NewActivity.this, new OnDateSetListener() {

							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								// TODO Auto-generated method stub
								// 把用户设置的时间存储
								calendar.set(Calendar.YEAR, year);
								calendar.set(Calendar.MONTH, monthOfYear);
								calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
								date = year + "/" + monthOfYear + "/"
										+ dayOfMonth;
								TimePickerDialog time_dialog = new TimePickerDialog(
										NewActivity.this,
										new OnTimeSetListener() {

											public void onTimeSet(
													TimePicker view,
													int hourOfDay, int minute) {
												// TODO Auto-generated method
												// stub
												calendar.set(
														Calendar.HOUR_OF_DAY,
														hourOfDay);
												calendar.set(Calendar.MINUTE,
														minute);
												calendar.set(Calendar.SECOND, 0);
												date = new SimpleDateFormat(
														"yyyy/MM/dd  hh:mm").format(calendar
														.getTimeInMillis());
												tv_tiem.setText(date);
												ca.set(Calendar.YEAR,
														c.get(Calendar.YEAR));
												ca.set(Calendar.MONTH,
														c.get(Calendar.MONTH));
												ca.set(Calendar.DAY_OF_MONTH,
														c.get(Calendar.DATE));
												ca.set(Calendar.HOUR_OF_DAY,
														c.get(Calendar.HOUR_OF_DAY));
												ca.set(Calendar.MINUTE,
														c.get(Calendar.MINUTE));
												ca.set(Calendar.SECOND, 0);
												one = calendar
														.getTimeInMillis();
												two = ca.getTimeInMillis();
											}
										}, c.get(Calendar.HOUR_OF_DAY), c
												.get(Calendar.MINUTE), true);
								time_dialog.show();

							}
						}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
								.get(Calendar.DATE));
				dialog.show();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuItem item1 = menu.add("保存");
		MenuItem item2 = menu.add("取消");
		item1.setIcon(R.drawable.png_0044);
		item2.setIcon(R.drawable.png_0652);
		// 保存
		item1.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				if (one < two) {
					Toast.makeText(NewActivity.this, "请设置正确的时间",
							Toast.LENGTH_SHORT).show();
				} else {

					// 判断是否保存成功
					if (_id != -1) {
						Toast.makeText(NewActivity.this, "添加成功 ",
								Toast.LENGTH_SHORT).show();
						//
						AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

						finish();
					} else {
						Toast.makeText(NewActivity.this, "添加失败",
								Toast.LENGTH_SHORT).show();
					}

				}

				return false;
			}
		});

		return super.onCreateOptionsMenu(menu);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		System.out.println("" + _id);
		loadData(_id);
		super.onResume();
	}

	public void loadData(long _id) {

	}

}
