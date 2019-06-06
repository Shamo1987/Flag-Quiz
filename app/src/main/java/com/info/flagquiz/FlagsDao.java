package com.info.flagquiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;



public class FlagsDao {

    public ArrayList<Flags> random5get(myDatabase vt){
        ArrayList<Flags> flagsArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM()  LIMIT 5",null);

        while (c.moveToNext()){
            Flags b = new Flags(c.getInt(c.getColumnIndex("bayrak_id"))
                    ,c.getString(c.getColumnIndex("bayrak_ad"))
                    ,c.getString(c.getColumnIndex("bayrak_resim")));

            flagsArrayList.add(b);
        }

        return flagsArrayList;

    }

    public ArrayList<Flags> random3FelSelectGet(myDatabase vt, int flag_id){
        ArrayList<Flags> flagsArrayList = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id != "+flag_id+" ORDER BY RANDOM()  LIMIT 3",null);

        while (c.moveToNext()){
            Flags b = new Flags(c.getInt(c.getColumnIndex("bayrak_id"))
                    ,c.getString(c.getColumnIndex("bayrak_ad"))
                    ,c.getString(c.getColumnIndex("bayrak_resim")));

            flagsArrayList.add(b);
        }

        return flagsArrayList;

    }


}
