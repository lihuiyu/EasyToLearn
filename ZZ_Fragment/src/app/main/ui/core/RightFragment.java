package app.main.ui.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import app.main.R;

public class RightFragment extends Fragment {
	private MainActivity parent;
	private SimpleAdapter RightListAdapter;
	private ListView RightList;
	private ArrayList<Map<String, Object>> data;
	private String subs[] = new String[] { "学习心得", "错题整理", "课堂笔记" };
	private String root = "";
	private String sub = "";

	public RightFragment() {
	}

	public RightFragment(MainActivity parent) {
		this.parent = parent;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_right, null);
		initAdapter();
		RightList = (ListView) view.findViewById(R.id.right_menuList);
		RightList.setAdapter(RightListAdapter);
		RightList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				System.out.println(subs[arg2]);
			}
		});
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	private void initAdapter() {
		data = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < subs.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("menuIcon", R.drawable.lxqg);
			map.put("menuText", subs[i]);
			data.add(map);
		}
		String[] from = new String[] { "menuIcon", "menuText" };
		int[] to = new int[] { R.id.right_list_icon, R.id.right_list_text };
		RightListAdapter = new SimpleAdapter(parent, data,
				R.layout.main_right_list_item, from, to);
	}

	public void setRootAndSub(String root, String sub) {
		this.root = root;
		this.sub = sub;
		System.out.println(root + "   " + sub);
	}

}
