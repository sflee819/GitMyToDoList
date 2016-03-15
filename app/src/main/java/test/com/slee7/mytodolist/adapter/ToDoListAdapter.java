package test.com.slee7.mytodolist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

import test.com.slee7.mytodolist.R;
import test.com.slee7.mytodolist.data.ToDoListItem;

/**
 * Created by slee7 on 3/14/2016.
 */
public class ToDoListAdapter extends BaseAdapter{

    List<ToDoListItem> mData;

    public ToDoListAdapter(List<ToDoListItem> toDoListItems){
        mData = toDoListItems;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class MyViewHolder {
        public CheckBox mCheckBox;
        public TextView title;
        public TextView description;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.to_do_list_item, null);

            holder = new MyViewHolder();
            holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.toDoCheckBox);
            holder.title = (TextView) convertView.findViewById(R.id.toDoTitle);
            holder.description = (TextView) convertView.findViewById(R.id.toDoDesc);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        final ToDoListItem currItem = (ToDoListItem) getItem(position);
        holder.mCheckBox.setChecked(currItem.isChecked());
        holder.title.setText(currItem.getTitle());
        holder.description.setText(currItem.getToDoDescription());
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currItem.setIsChecked(isChecked);
            }
        });
        return convertView;
    }

    public void updateData(List<ToDoListItem> newData){
        mData = newData;
    }

}
