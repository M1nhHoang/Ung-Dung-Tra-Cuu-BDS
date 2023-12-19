package com.minhhoang.nhom8_ltdd_tracuubds.menu.UserDatabase;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;


public class DangKyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user";
    private static final int DATABASE_VERSION = 2;


    private static final String TABLE_NAME = "user_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_NAME = "name";

    public DangKyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng trong cơ sở dữ liệu
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_NAME + " TEXT)"; // Include the new column
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Nâng cấp cơ sở dữ liệu khi có sự thay đổi trong DATABASE_VERSION
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String username,String password ,String name, String email  ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);



        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_USERNAME,
                COLUMN_PASSWORD
        };

        String selection = COLUMN_USERNAME + " = ? AND " +
                COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean loginSuccess = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return loginSuccess;
    }

    public String getEmailByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_EMAIL};
        String selection = COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        String email = "";

        // Kiểm tra xem Cursor có dữ liệu hay không
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(COLUMN_EMAIL);

            // Kiểm tra xem cột tồn tại trong kết quả truy vấn hay không
            if (columnIndex != -1) {
                email = cursor.getString(columnIndex);
            }

            cursor.close();
        }

        db.close();
        return email;
    }

    public boolean updateUser(String username, String email, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, newPassword);

        int affectedRows = db.update(TABLE_NAME, values, COLUMN_USERNAME + "=?", new String[]{username});
        db.close();

        // Kiểm tra xem có hàng nào bị ảnh hưởng không (có được cập nhật hay không)
        return affectedRows > 0;
    }

}
