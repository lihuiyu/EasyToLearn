package app.main.ui.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import app.main.R;

public class LeftFragment extends Fragment {
	private Context context;
	private SimpleAdapter LeftListAdapter;
	private ListView LeftList;
	private Button LeftButton;
	private ArrayList<Map<String, Object>> listItems;
	private int itemCount = 3;

	public LeftFragment() {

	}

	public LeftFragment(Context context) {
		this.context = context;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_left, null);
		initAdapter();
		LeftList = (ListView) view.findViewById(R.id.leftList);
		LeftList.setAdapter(LeftListAdapter);
		LeftList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

			}
		});

		LeftButton = (Button) view.findViewById(R.id.leftButton);
		LeftButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String s = "呵呵";
				addItem(s);
				
			}
		});
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private void initAdapter() {
		listItems = new ArrayList<Map<String, Object>>();
		String[] from = new String[] { "menuIcon", "menuText" };
		int[] to = new int[] { R.id.left_list_item_icon,
				R.id.left_list_item_title };
		LeftListAdapter = new SimpleAdapter(context, listItems,
				R.layout.main_left_list_item, from, to);
		getData();
	}

	private void getData() {
		for (int i = 0; i < this.itemCount; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("menuIcon", R.drawable.main_left_item_icon);
			if (i == 0) {
				map.put("menuText", "数学");
			} else if (i == 1) {
				map.put("menuText", "英语");
			} else {
				map.put("menuText", "物理");
			}
			listItems.add(map);

		}

	}

	public void addItem(String title) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuIcon", R.drawable.main_left_item_icon);
		map.put("menuText", title);
		listItems.add(map);
		LeftListAdapter.notifyDataSetChanged();
	}

	public void removeItem(int postion) {
		int size = listItems.size();
		if (size > 0 && postion < size) {
			listItems.remove(1);
			LeftListAdapter.notifyDataSetChanged();
		}
	}

	public final class ListItemsView {
		public ImageView menuIcon;
		public TextView menuText;
	}
}
