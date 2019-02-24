package com.ravimishra.toshopchat;

public class Constants {
    public static final String DB_NAME = "TOSHOP_CHAT_DB";
    public static final String TB_NAME = "TOSHOP_CHAT_TB";
    public static final String ROW_ID= "id";
    public static final String USER_NAME = "username";
    public static final int DB_VERSION='1';

    public static final String CREATE_TABLE="CREATE TABLE TOSHOP_CHAT_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT NOT NULL);";
}