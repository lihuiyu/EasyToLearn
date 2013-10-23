package app.main.ui.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import app.main.R;
import app.main.ui.memo.NewActivity;
import app.main.ui.memo.SeeActivity;
import app.main.util.FileUtility;

public class RightFragment extends Fragment {
	private MainActivity parent;
	private SimpleAdapter RightListAdapter;
	private ListView RightList;
	private ArrayList<Map<String, Object>> data;
	private String subs[] = new String[] { "ѧϰ�ĵ�", "��������", "���ñʼ�" };
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
				switch (arg2) {
				case 0:
					openLearn(subs[arg2]);
					break;
				case 1:
					openWrong();
					break;
				case 2:
					openClass();
					break;
				}
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
	}

	private void openLearn(final String detail) {
		if (root.equals("")) {
			Toast.makeText(parent, "����û��ѡ��ѧ��", Toast.LENGTH_SHORT).show();
			return;
		}
		if (sub.equals("")) {
			Toast.makeText(parent, "����û��ѡ��֪ʶ��Ŀ", Toast.LENGTH_SHORT).show();
			return;
		}
		FileUtility fileModule = MainActivity.fileModule;
		fileModule.reset();
		fileModule.createDirectory(root);
		fileModule.createDirectory(sub);
		fileModule.createDirectory(detail);
		ArrayList<String> dirs = fileModule.getSubFolder();
		if (dirs.size() == 0) {
			AlertDialog.Builder builder = new Builder(parent);
			builder.setMessage("�ף������������ĵð�");
			builder.setTitle("��ʾ");
			builder.setPositiveButton("ȷ��", new OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(getActivity(), NewActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("root", root);
					bundle.putString("sub", sub);
					bundle.putString("detail", detail);
					intent.putExtras(bundle);
					parent.startActivity(intent);
					dialog.dismiss();
				}
			});
			builder.setNegativeButton("ȡ��", new OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
		} else {
			fileModule.reset();
			fileModule.createDirectory(root);
			fileModule.createDirectory(sub);
			fileModule.createDirectory(detail);
			String name = fileModule.getSubFolder().get(0);
			String data = fileModule.readText(name);

			Intent intent = new Intent(getActivity(), SeeActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("name", name);
			bundle.putString("data", data);
			intent.putExtras(bundle);
			parent.startActivity(intent);
		}

	}

	private void openWrong() {

	}

	private void openClass() {

	}
}
