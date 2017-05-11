package com.example.dsm2015.findRestArea;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dsm2015 on 2017-04-29.
 */

public class DbManager extends SQLiteOpenHelper{
    static final int DB_VERSION=1;
    static final String REST_AREA_TABLE="RestArea";
    static final String DB_NAME="HighWah";
    static final String FOOD_TABLE="Food";

    public DbManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    /* 애플리케이션 최초 실행 */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+REST_AREA_TABLE+ " (" +
                "restId TEXT primary key, " +
                "name text, " +
                //주유소 휘발유, 경유, LPG 가격
                "gasoilne INTEGER  " +
                "diesel INTEGER  " +
                "lpg INTEGER  " +
                "gas_station_number TEXT  " +
                //장애인(구축o 1, x 0)
                "Jeomji_block INTEGER  " +
                "guidance INTEGER  " +
                "alarm_evacuation INTEGER  " +
                //만족도, (좋음2, 중간 1, 작음 0)
                "evaluation INTEGER  " +
                "scale INTEGER  " +

                ");";

        String foodSql = "create table "+FOOD_TABLE+ " (" +
                "restId TEXT , " + //휴게소 코드
                "food_name TEXT, " + //음식이름
                "price INTEGER  " + //가격
                "etc TEXT  " + //상세내역(설명)
                "represent INTEGER  " +  //대표메뉴(맞으면 1, 아니면 0)
                "recommand INTEGER  " + //추천메뉴(맞으면 1, 아니면 0)
                "premium INTEGER  " + //프리미엄메뉴(맞으면 1, 아니면 0)
                ");";

        db.execSQL(sql);
        db.execSQL(foodSql);

        //insert도 여기서 할것~
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
