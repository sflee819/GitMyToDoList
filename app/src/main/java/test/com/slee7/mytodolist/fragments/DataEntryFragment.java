package test.com.slee7.mytodolist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import test.com.slee7.mytodolist.R;
import test.com.slee7.mytodolist.data.ToDoListItem;

/**
 * Created by slee7 on 3/14/2016.
 */
public class DataEntryFragment extends Fragment {

    private EditText mTitle, mDescription;
    private static final String[] errorMessages = {"Please enter a title", "Please enter a description", "Please enter a title and a description"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_entry, container, false);
        mTitle = (EditText) rootView.findViewById(R.id.titleEditText);
        mDescription = (EditText) rootView.findViewById(R.id.descEditText);
        return rootView;
    }

    public ToDoListItem getInfoFromEntry(){
        ToDoListItem mItem = null;
        if (mTitle != null && mDescription != null){
            int flag = 0;
            if (mTitle.getText().toString().trim().length() == 0){
                // returns 1
                flag = 1;
            }
            if (mDescription.getText().toString().trim().length() == 0){
                // returns 2. 3 if both are empty
                flag = flag + 2;
            }
            if (flag == 0){
                mItem = new ToDoListItem(false, mTitle.getText().toString(), mDescription.getText().toString());
                mTitle.setText("");
                mDescription.setText("");
            } else {
                Snackbar.make(mTitle, errorMessages[flag - 1], Snackbar.LENGTH_LONG).show();
            }
        }
        return mItem;
    }
}
