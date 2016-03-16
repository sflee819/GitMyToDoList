package test.com.slee7.mytodolist.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import test.com.slee7.mytodolist.R;
import test.com.slee7.mytodolist.adapter.ToDoListAdapter;
import test.com.slee7.mytodolist.data.ToDoItemsList;
import test.com.slee7.mytodolist.data.ToDoListItem;

/**
 * A placeholder fragment containing a simple view.
 */
public class ToDoListFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private ListView mListView;
    private ToDoListAdapter mAdapter;
    private ToDoItemsList myData;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public ToDoListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        mListView = (ListView) rootView.findViewById(R.id.toDoListView);
        Button mButton = (Button) rootView.findViewById(R.id.deleteButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCheckedItems();
                updateAdapter();
            }
        });
        return rootView;
    }

    public void setMyData(List<ToDoListItem> newData){
        myData.setmItems(newData);
    }

    public void addNewItem(ToDoListItem newItem){
        if (myData != null) {
            myData.getmItems().add(newItem);
        }
    }

    public void sortItems(){
        if (myData != null){
            Collections.sort(myData.getmItems());
        }
    }

    public void deleteCheckedItems(){
        if (myData != null){
            for (Iterator<ToDoListItem> iterator = myData.getmItems().iterator(); iterator.hasNext();) {
                ToDoListItem item = iterator.next();
                if (item.isChecked()) {
                    iterator.remove();
                }
            }
        }
    }

    public void updateAdapter(){
        if (myData != null && mAdapter != null){
            mAdapter.updateData(myData.getmItems());
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("list", (Parcelable) myData);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            // orientation change, or other config changes
            myData = (ToDoItemsList) savedInstanceState.getParcelable("list");
        }
        if (myData == null){
            myData = new ToDoItemsList();
            myData.setmItems(new ArrayList<ToDoListItem>());
        }
        mAdapter = new ToDoListAdapter(myData.getmItems());
        mListView.setAdapter(mAdapter);
    }
}