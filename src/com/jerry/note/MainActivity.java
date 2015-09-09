package com.jerry.note;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jerry.note.adapter.ListViewAdapter;
import com.jerry.note.adapter.ListViewAdapter.RefreshCallBack;
import com.jerry.note.db.DBManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener{

	List<Map<String,String>>data;
	DBManager manager;
	ListViewAdapter adapter;
	ListView listView;
	View newNote;
	View noNote;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }
	private void initView() {
		// TODO Auto-generated method stub
		listView=(ListView) findViewById(R.id.id_list_note);
		newNote=findViewById(R.id.id_new_note);
		noNote=findViewById(R.id.id_no_note);
		newNote.setOnClickListener(this);
		adapter=new ListViewAdapter(this, data, manager,new RefreshCallBack() {
			
			@Override
			public void refresh() {
				// TODO Auto-generated method stub
				refreshData();
			}
		});
		listView.setAdapter(adapter);
		
	}
	private void initData()
	{
		MyApp app=(MyApp) this.getApplicationContext();
        manager=app.getDBManager();
       data=new ArrayList<Map<String,String>>();
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		refreshData();
	}
	private void refreshData() {
		data.clear();
		List<Map<String, String>> tmp = manager.findNoteCount();
		data.addAll(tmp);
		if(data.size()==0)
		{
			noNote.setVisibility(View.VISIBLE);
		}
		else
		{
			noNote.setVisibility(View.GONE);
		}
		adapter.notifyDataSetChanged();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.id_new_note:		
			Log.i("onNewNoteClick", "onNewNoteClick");
			Intent intent=new Intent(this, NewNoteActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
