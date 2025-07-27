package fpoly.hungph53757.duanprostore.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fpoly.hungph53757.duanprostore.Database.UserHelper;
import fpoly.hungph53757.duanprostore.Model.User;

public class UserDao {
    private UserHelper userHelper;
    public UserDao(Context context) {
        userHelper = new UserHelper(context);
    }

    public boolean insertUser(String username,String email, String password, String role) {
        SQLiteDatabase db = userHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        if (cursor.getCount() > 0){
            cursor.close();
            return false;
        }
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("email",email);
        values.put("password",password);
        values.put("role",role);

        long result = db.insert("users", null, values);
        return result != -1;
    }
    public User checkLogin(String email, String password) {
        SQLiteDatabase db = userHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM users WHERE email = ? AND password = ?",
                new String[]{email, password}
        );
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            String username = cursor.getString(1);
            String userEmail = cursor.getString(2);
            String userPassword = cursor.getString(3);
            String role = cursor.getString(4);

            cursor.close();
            return new User(id,username,userEmail,userPassword,role);
        }
        cursor.close();
        return null;
    }
}
