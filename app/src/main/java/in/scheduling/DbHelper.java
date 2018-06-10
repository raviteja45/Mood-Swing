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
        if(taskHolder.getAttachment()!=null&&!taskHolder.getAttachment().isEmpty()){
            JSONArray json1 = new JSONArray();
            json1.put(taskHolder.getAttachment());
            values.put("attachment",json1.toString());
        }
        else{

        }
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
        String selectQuery = "SELECT  * FROM MoodTracker ORDER BY date DESC";
        SQLiteDatabase db1 = this.getWritableDatabase();
        System.out.println(db1.getPath());
        Cursor cursor = db1.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                TaskHolder bean = new TaskHolder();
                bean.setMood(cursor.getString(cursor.getColumnIndex("mood")));
                JSONArray json = new JSONArray(cursor.getString(cursor.getColumnIndex("reason")));
                List<String> arrayList = new ArrayList<>();
                for(int i=0;i<json.length();i++){
                      arrayList.add(json.getString(i));
                }
                bean.setReason(arrayList);
                bean.setDate(cursor.getString(cursor.getColumnIndex("date")));
                bean.setTime(cursor.getString(cursor.getColumnIndex("time")));
              //  bean.setAttachment(cursor.getString(4));
                if(cursor.getString(cursor.getColumnIndex("attachment"))!=null){
                    JSONArray json1 = new JSONArray(cursor.getString(cursor.getColumnIndex("attachment")));
                    List<String> arrayList1 = new ArrayList<>();
                    for(int i=0;json1!=null&&i<json1.length();i++){
                        arrayList1.add(json1.getString(i));
                    }
                    bean.setAttachment(arrayList1);
                }

                bean.setComments(cursor.getString(cursor.getColumnIndex("comments")));
                moodList.add(bean);
            } while (cursor.moveToNext());
        }
        //Toast.makeText(context,"Here is "+languageList.get(0).getMessage(),Toast.LENGTH_LONG).show();
        return moodList;
    }

}
