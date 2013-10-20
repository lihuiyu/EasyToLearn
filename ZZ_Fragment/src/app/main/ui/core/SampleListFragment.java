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

public class SampleListFragment extends ListFragment {
	ImageView iv_left;
	ImageView iv_right;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mView = inflater.inflate(R.layout.main_center, null);
		iv_left = (ImageView) mView.findViewById(R.id.main_iv_left);
		iv_right = (ImageView) mView.findViewById(R.id.main_iv_right);
		return mView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		List<Map<String, Object>> data = getList();

		String[] from = new String[] { "list_title", "list_image",
				"list_contect" };
		int[] to = new int[] { R.id.list_title, R.id.list_image,
				R.id.list_contect };
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
				R.layout.main_center_list_item, from, to);
		setListAdapter(adapter);

		iv_left.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				((MainActivity) getActivity()).showLeft();
			}
		});

		iv_right.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				((MainActivity) getActivity()).showRight();
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

	public List<Map<String, Object>> getList() {
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("list_title", getString(R.string.title1));
		item1.put("list_image", R.drawable.booklist_menu_about);
		item1.put("list_contect", getString(R.string.test));
		Map<String, Object> item2 = new HashMap<String, Object>();
		item2.put("list_title", getString(R.string.title1));
		item2.put("list_image", R.drawable.booklist_menu_about);
		item2.put("list_contect", getString(R.string.test));
		Map<String, Object> item3 = new HashMap<String, Object>();
		item3.put("list_title", getString(R.string.title1));
		item3.put("list_image", R.drawable.booklist_menu_about);
		item3.put("list_contect", getString(R.string.test));
		Map<String, Object> item4 = new HashMap<String, Object>();
		item4.put("list_title", getString(R.string.title1));
		item4.put("list_image", R.drawable.booklist_menu_about);
		item4.put("list_contect", getString(R.string.test));
		Map<String, Object> item5 = new HashMap<String, Object>();
		item5.put("list_title", getString(R.string.title1));
		item5.put("list_image", R.drawable.booklist_menu_about);
		item5.put("list_contect", getString(R.string.test));
		Map<String, Object> item6 = new HashMap<String, Object>();
		item6.put("list_title", getString(R.string.title1));
		item6.put("list_image", R.drawable.booklist_menu_about);
		item6.put("list_contect", getString(R.string.test));
		Map<String, Object> item7 = new HashMap<String, Object>();
		item7.put("list_title", getString(R.string.title1));
		item7.put("list_image", R.drawable.p7);
		item7.put("list_contect", getString(R.string.test));
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		data.add(item1);
		data.add(item2);
		data.add(item3);
		data.add(item4);
		data.add(item5);
		data.add(item6);
		data.add(item7);

		return data;
	}
}