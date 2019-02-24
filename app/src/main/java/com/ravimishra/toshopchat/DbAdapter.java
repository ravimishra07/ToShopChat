package com.ravimishra.toshopchat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DbAdapter {
    private Context context;
    private DbHelper dbHelper;
    SQLiteDatabase db;
    ArrayList<UserListModel> userListModels = new ArrayList<>();
    UserListRvAdapter userListRvAdapter = new UserListRvAdapter(context, userListModels);

    public DbAdapter(Context context) {
        this.context = context;
        userListRvAdapter = new UserListRvAdapter(context, userListModels);
        dbHelper=new DbHelper(context);
    }

    public DbAdapter openDB() {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void close() {
        try {
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
 public  long addUser(String user){
     try {
         ContentValues cv = new ContentValues();
         cv.put(Constants.USER_NAME,user);

         return db.insert(Constants.TB_NAME, Constants.ROW_ID, cv);
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return 0;
 }
   public long deleteUser(int id){
       try
       {

           long del= db.delete(Constants.TB_NAME,Constants.ROW_ID+" =?",new String[]{String.valueOf(id)});
           Log.d("DEL","record deleted");
           userListRvAdapter.notifyDataSetChanged();


           return del;
       }catch (SQLException e)
       {
           e.printStackTrace();
       }

       return 0;
   }
    public Cursor getAllData() {

        String[] column = {Constants.ROW_ID, Constants.USER_NAME};
        return db.query(Constants.TB_NAME, column, null, null, null, null, null);
    }
}
