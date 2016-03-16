package test.com.slee7.mytodolist.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by slee7 on 3/14/2016.
 */
public class ToDoListItem implements Parcelable, Comparable<ToDoListItem>{
    private Date timeAdded;
    private boolean isChecked;
    private String title;
    private String toDoDescription;

    public ToDoListItem(boolean isChecked, String title, String toDoDescription){
        this.isChecked = isChecked;
        this.title = title;
        this.toDoDescription = toDoDescription;
        this.timeAdded = Calendar.getInstance().getTime();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getToDoDescription() {
        return toDoDescription;
    }

    public void setToDoDescription(String todoDescription) {
        this.toDoDescription = todoDescription;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(isChecked ? (byte) 1 : (byte) 0);
        dest.writeString(this.title);
        dest.writeString(this.toDoDescription);
    }

    private ToDoListItem(Parcel in) {
        this.isChecked = in.readByte() != 0;
        this.title = in.readString();
        this.toDoDescription = in.readString();
    }

    public static final Creator<ToDoListItem> CREATOR = new Creator<ToDoListItem>() {
        public ToDoListItem createFromParcel(Parcel source) {
            return new ToDoListItem(source);
        }

        public ToDoListItem[] newArray(int size) {
            return new ToDoListItem[size];
        }
    };

    @Override
    public int compareTo(ToDoListItem another) {
        return this.timeAdded.compareTo(another.timeAdded);
    }

    public Date getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
    }
}
