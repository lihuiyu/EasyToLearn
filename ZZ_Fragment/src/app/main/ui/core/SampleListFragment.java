package app.main.ui.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import app.main.R;
import app.main.util.FileUtility;

public class SampleListFragment extends ListFragment {
	private MainActivity parent;
	private ImageView iv_left;
	private ImageView iv_right;
	private SimpleAdapter adapter;
	private String rootDir = "";
	private ArrayList<Map<String, Object>> data;

	public SampleListFragment(MainActivity parent) {
		this.parent = parent;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.main_center, null);
		iv_left = (ImageView) mView.findViewById(R.id.main_iv_left);
		iv_right = (ImageView) mView.findViewById(R.id.main_iv_right);
		return mView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		data = new ArrayList<Map<String, Object>>();
		getList(rootDir);
		String[] from = new String[] { "list_title", "list_image",
				"list_contect" };
		int[] to = new int[] { R.id.list_title, R.id.list_image,
				R.id.list_contect };
		adapter = new SimpleAdapter(parent, data,
				R.layout.main_center_list_item, from, to);
		setListAdapter(adapter);

		iv_left.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				((MainActivity) getActivity()).showLeft();
			}
		});

		iv_right.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
			}
		});
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Log.d("----->", position + "");
		// Intent intent = new Intent(getActivity(), test.class);
		// startActivity(intent);
		((MainActivity) getActivity()).showRight();
	}

	public void getList(String subName) {
		if (subName.equals("")) {
			data.clear();
			return;
		}
		FileUtility fileModule = parent.useFileModule();
		fileModule.reset();
		fileModule.createDirectory(subName);
		ArrayList<String> dirs = fileModule.getSubFolder();
		data.clear();
		for (int i = 0; i < dirs.size(); i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("list_title", dirs.get(i));
			item.put("list_image", R.drawable.booklist_menu_about);
			item.put("list_contect", getString(R.string.test));
			data.add(item);
		}
	}

	public void setRootDirAndNofiy(String dir) {
		this.rootDir = dir;
		this.getList(rootDir);
		this.adapter.notifyDataSetChanged();
	}
}
