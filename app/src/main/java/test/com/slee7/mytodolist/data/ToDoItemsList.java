package test.com.slee7.mytodolist.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by slee7 on 3/14/2016.
 */
public class ToDoItemsList implements Parcelable {
    private List<ToDoListItem> mItems;

    public List<ToDoListItem> getmItems() {
        return mItems;
    }

    public void setmItems(List<ToDoListItem> mItems) {
        this.mItems = mItems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mItems);
    }

    public ToDoItemsList() {
    }

    private ToDoItemsList(Parcel in) {
        in.readTypedList(mItems, ToDoListItem.CREATOR);
    }

    public static final Parcelable.Creator<ToDoItemsList> CREATOR = new Parcelable.Creator<ToDoItemsList>() {
        public ToDoItemsList createFromParcel(Parcel source) {
            return new ToDoItemsList(source);
        }

        public ToDoItemsList[] newArray(int size) {
            return new ToDoItemsList[size];
        }
    };
}
