package com.jerry.note;

import com.jerry.note.bean.Note;
import com.jerry.note.db.DBManager;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewNoteActivity extends Activity {

	DBManager manager;
	EditText edtTitle;
	EditText edtContent;
	boolean isEditMode;
	Note note;
	View back;
	TextView title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.createnoteactivity);
		initData();
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		edtTitle=(EditText) findViewById(R.id.newTitle);
		edtContent=(EditText) findViewById(R.id.newContent);
		title=(TextView) findViewById(R.id.id_title);
		back=findViewById(R.id.id_back);
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		if(isEditMode)
		{
			title.setText("�༭�ʼ�");
			edtTitle.setText(note.getTitle());
			edtContent.setText(note.getContent());
		}
		else
		{
			title.setText("�½��ʼ�");
		}
	}

	private void initData() {
		// TODO Auto-generated method stub
		MyApp app=(MyApp) getApplicationContext();
		manager=app.getDBManager();

		isEditMode=getIntent().getBooleanExtra("edit", false);
		if(isEditMode)
		{
			note=(Note) getIntent().getExtras().getSerializable("note");
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		String title=edtTitle.getText().toString().trim();
		String content=edtContent.getText().toString().trim();
		if(TextUtils.isEmpty(title)||TextUtils.isEmpty(content))
		{
			//do nothing
		}
		else
		{
			if(!isEditMode)
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
			else
			{
				Time t=new Time();
				t.setToNow();
				String lastEditTime=t.year+"��"+(t.month+1)+"��"+t.monthDay+"��"+"  "+t.hour+":"+t.minute+":"+t.second;
				note.setLastModifyTime(lastEditTime);
				note.setContent(content);
				note.setTitle(title);
				boolean result=manager.updateNote(note);
				if(result)
				{
					Toast.makeText(this, "���³ɹ�", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(this, "����ʧ��", Toast.LENGTH_SHORT).show();
				}
			}
		}
		super.onBackPressed();
	}
}
