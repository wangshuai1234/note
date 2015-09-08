package com.jerry.note.adapter;

import java.util.List;
import java.util.Map;

import com.jerry.note.R;
import com.jerry.note.bean.Note;
import com.jerry.note.db.DBManager;


import android.content.Context;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import android.widget.TextView;


public class ListViewAdapter extends BaseAdapter {

	private LayoutInflater mInflater; 
	private Context mContext;
	private List<Map<String, String>> mList;
	private DBManager dbManager;
	public ListViewAdapter(Context context, List<Map<String, String>> list,DBManager dbManager)
	{
		this.mContext = context;
		this.mList = list;
		this.mInflater = LayoutInflater.from(mContext); 
		this.dbManager=dbManager;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(mList==null)
			return 0;
		else
			return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if(mList==null)
			return null;
		else
			return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup group) {
		// TODO Auto-generated method stub
		ViewHolder holder=null;
		if(convertView==null)
		{
			holder=new ViewHolder();
			convertView = mInflater.inflate(R.layout.innerlistview, group,false); 
			holder.txtTime=(TextView)convertView.findViewById(R.id.txtTime);
			holder.txtCount=(TextView)convertView.findViewById(R.id.txtCount);
			holder.gridView=(GridView) convertView.findViewById(R.id.id_grid_note);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}
		Map<String, String> item=mList.get(position);

		holder.txtTime.setText((String)item.get("monthoftime"));
		holder.txtCount.setText((String)item.get("notecount"));

		holder.gridView.setNumColumns(3);// 设置每行列数
		holder.gridView.setGravity(Gravity.CENTER);// 位置居中
		holder.gridView.setHorizontalSpacing(10);// 水平间隔
		holder.gridView.setVerticalSpacing(10);
		
		List<Note> gridViewList=dbManager.findNotesByTime((String)item.get("monthoftime"));
		
		GridViewAdapter adapter=new GridViewAdapter(mContext, gridViewList);
		holder.gridView.setAdapter(adapter);
		holder.gridView.setOnItemClickListener(new MyOnItemClickListener(position) );

		holder.gridView.setOnItemLongClickListener(new MyLonClickListener(position));	
		return convertView;
	}

	private class MyLonClickListener implements AdapterView.OnItemLongClickListener
	{

		private int dataPosition;
		public MyLonClickListener(int dataPosition)
		{
			this.dataPosition=dataPosition;
		}
		@Override
		public boolean onItemLongClick(AdapterView<?> adapterView, View view,
				int position, long id) {
			Log.i("onItemLongClick", "position"+dataPosition);
			return true;
		}
	}
	private class MyOnItemClickListener implements AdapterView.OnItemClickListener
	{
		private int dataPosition;
		public MyOnItemClickListener(int dataPosition)
		{
			this.dataPosition=dataPosition;
		}
		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Log.i("onItemClick", "position"+dataPosition);
		}

	}
	private static class ViewHolder
	{
		public ViewHolder(){}
		public TextView txtTime;
		public TextView txtCount;
		public GridView gridView;
	}
}
