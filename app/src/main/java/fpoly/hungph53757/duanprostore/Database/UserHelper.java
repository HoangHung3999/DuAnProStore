package fpoly.hungph53757.duanprostore.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "user.db";
    public static final int DB_VERSION = 2;

    public UserHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, " +
                "email TEXT, " +
                "password TEXT, " +
                "role TEXT)";
        db.execSQL(sql);

        String insertAdmin = "INSERT INTO users (username, email, password, role) " +
                "VALUES ('Admin', 'admin@gmail.com', '123456', 'admin')";
        db.execSQL(insertAdmin);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
