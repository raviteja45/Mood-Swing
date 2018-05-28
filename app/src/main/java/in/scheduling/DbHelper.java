package in.scheduling;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi on 19-05-2018.
 */

public class DbHelper extends SQLiteOpenHelper {


    private Context context;
    private SQLiteDatabase database;

    public DbHelper(Context context) {
        super(context,"test",null,1);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void closeIt(){
        if(database!=null){
            database.close();
        }
    }


    public boolean insertRecords(TaskHolder taskHolder) throws JSONException {
        //String CREATE_CONTACTS_TABLE = "create table if not exists UserRecords(owner text, message text, dateTime text, imageUrl text,withWhom text)";
        String CREATE_CONTACTS_TABLE = "create table if not exists MoodTracker(mood text, reason text, date text, time text,attachment text,comments text)";
        SQLiteDatabase dbInsert = this.getWritableDatabase();
        dbInsert.execSQL(CREATE_CONTACTS_TABLE);
        ContentValues values = new ContentValues();
        values.put("mood",taskHolder.getMood());
        values.put("date", taskHolder.getDate());
        values.put("time", taskHolder.getTime());
        values.put("attachment",taskHolder.getAttachment());
        JSONArray json = new JSONArray();
        json.put(taskHolder.getReason());
        values.put("reason", json.toString());
        values.put("comments",taskHolder.getComments());
        long i = dbInsert.insert("MoodTracker", null, values);
        dbInsert.close();
        if (i != -1) {
            Toast.makeText(context,"Here is ",Toast.LENGTH_LONG).show();
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<TaskHolder> getHistory() throws JSONException {
        String CREATE_CONTACTS_TABLE = "create table if not exists MoodTracker(mood text, reason text, date text, time text,attachment text,comments text)";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CREATE_CONTACTS_TABLE);
        ArrayList<TaskHolder> moodList = new ArrayList<TaskHolder>();
        String selectQuery = "SELECT  * FROM MoodTracker";
        SQLiteDatabase db1 = this.getWritableDatabase();
        System.out.println(db1.getPath());
        Cursor cursor = db1.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                TaskHolder bean = new TaskHolder();
                bean.setMood(cursor.getString(0));
                JSONArray json = new JSONArray(cursor.getString(1));
                List<String> arrayList = new ArrayList<>();
                for(int i=0;i<json.length();i++){
                      arrayList.add(json.getString(i));
                }
                bean.setReason(arrayList);
                bean.setDate(cursor.getString(2));
                bean.setTime(cursor.getString(3));
                bean.setAttachment(cursor.getString(4));
                bean.setComments(cursor.getString(5));
                moodList.add(bean);
            } while (cursor.moveToNext());
        }
        //Toast.makeText(context,"Here is "+languageList.get(0).getMessage(),Toast.LENGTH_LONG).show();
        return moodList;
    }

}
