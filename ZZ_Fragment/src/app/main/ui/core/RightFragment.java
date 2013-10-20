package app.main.ui.core;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import app.main.R;
import app.main.adapter.RightAdapter;

public class RightFragment extends Fragment {
	private Context context;

	public RightFragment() {
	}

	public RightFragment(Context context) {
		this.context = context;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_right, null);
		RightAdapter menuListAdapter = new RightAdapter(context, 0);
		ListView menuList = (ListView) view
				.findViewById(R.id.right_menuList);
		menuList.setAdapter(menuListAdapter);

		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

}
