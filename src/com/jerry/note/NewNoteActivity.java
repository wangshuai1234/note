package com.jerry.note;

import com.jerry.note.bean.Note;
import com.jerry.note.db.DBManager;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.widget.EditText;
import android.widget.Toast;

public class NewNoteActivity extends Activity {

	DBManager manager;
	EditText edtTitle;
	EditText edtContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createnoteactivity);
		initData();
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		edtTitle=(EditText) findViewById(R.id.newTitle);
		edtContent=(EditText) findViewById(R.id.newContent);
	}

	private void initData() {
		// TODO Auto-generated method stub
		MyApp app=(MyApp) getApplicationContext();
		manager=app.getDBManager();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		String title=edtTitle.getText().toString().trim();
		String content=edtContent.getText().toString().trim();
		if(TextUtils.isEmpty(title)||TextUtils.isEmpty(content))
		{
			
		}
		else
		{
			Time t=new Time();
			t.setToNow();
			String createTime=t.year+"��"+(t.month+1)+"��"+t.monthDay+"��"+"  "+t.hour+":"+t.minute+":"+t.second;
			String monthOfTime=t.year+"��"+(t.month+1)+"��";
			Note note=new Note();
			note.setTitle(title);
			note.setType("δ����");
			note.setContent(content);
			note.setCreateTime(createTime);
			note.setMonthOfTime(monthOfTime);
			note.setLastModifyTime(createTime);
			boolean result=manager.insertNote(note);
			if(result)
			{
				Toast.makeText(this, "����ɹ�", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(this, "����ʧ��", Toast.LENGTH_SHORT).show();
			}
		}
		super.onBackPressed();
	}
}
