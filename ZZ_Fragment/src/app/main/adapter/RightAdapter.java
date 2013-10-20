package app.main.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.main.R;
import app.main.ui.wrong.WrongActivity;

public class RightAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String, Object>> listItems;
	private LayoutInflater listInflater;
	private String textContent = "课堂笔记";
	private final int COUNT = 3;
	private int pressedId;
	private int itemCount;
	private int imageId = R.drawable.lxqg;
	private boolean isPressed[];

	public final class ListItemsView {
		public ImageView menuIcon;
		public TextView menuText;
	}

	public RightAdapter(Context context, int pressedId) {

		this.context = context;
		this.pressedId = pressedId;
		this.init();
	}

	private void init() {
		this.itemCount = this.COUNT;
		this.listItems = new ArrayList<Map<String, Object>>();
		this.isPressed = new boolean[this.itemCount];
		for (int i = 0; i < this.itemCount; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("menuIcon", imageId);
			if (i == 0) {
				map.put("menuText", "学习心得");
			} else if (i == 1) {
				map.put("menuText", "错题整理");
			} else {
				map.put("menuText", textContent);
			}
			this.listItems.add(map);
			this.isPressed[i] = false;
		}
		this.isPressed[this.pressedId] = true;
		this.listInflater = LayoutInflater.from(context);
	}

	private void changeState(int position) {

		for (int i = 0; i < this.itemCount; i++) {
			isPressed[i] = false;
		}
		isPressed[position] = true;
	}

	public int getCount() {

		return this.itemCount;
	}

	public Object getItem(int position) {

		return position;
	}

	public long getItemId(int position) {

		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		final int po = position;
		ListItemsView listItemsView;
		if (convertView == null) {
			listItemsView = new ListItemsView();
			convertView = this.listInflater.inflate(
					R.layout.main_right_list_item, null);
			listItemsView.menuIcon = (ImageView) convertView
					.findViewById(R.id.right_list_icon);
			listItemsView.menuText = (TextView) convertView
					.findViewById(R.id.right_list_text);
			convertView.setTag(listItemsView);
		} else {
			listItemsView = (ListItemsView) convertView.getTag();
		}

		listItemsView.menuIcon.setBackgroundResource((Integer) listItems.get(
				position).get("menuIcon"));
		listItemsView.menuText.setText((String) listItems.get(position).get(
				"menuText"));

		if (this.isPressed[position] == true)
			convertView
					.setBackgroundResource(R.drawable.main_question_item_bg_sel);
		else
			convertView.setBackgroundColor(Color.TRANSPARENT);

		convertView.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				// TODO Auto-generated method stub
				changeState(po);
				notifyDataSetInvalidated();
				new Handler().post(new Runnable() {

					public void run() {
						// TODO Auto-generated method stub
					}

				});
				Intent intent = new Intent(context, WrongActivity.class);
				context.startActivity(intent);

			}
		});

		return convertView;
	}

}
