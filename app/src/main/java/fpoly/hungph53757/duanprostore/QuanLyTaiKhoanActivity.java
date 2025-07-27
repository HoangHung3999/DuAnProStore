package fpoly.hungph53757.duanprostore;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import fpoly.hungph53757.duanprostore.Adapter.UserAdapter;
import fpoly.hungph53757.duanprostore.Dao.UserDao;
import fpoly.hungph53757.duanprostore.Database.UserHelper;
import fpoly.hungph53757.duanprostore.Model.User;

public class QuanLyTaiKhoanActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<User> userList;
    UserDao dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quan_ly_tai_khoan);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ImageView icMenu = findViewById(R.id.icMenu);

        icMenu.setOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });


        recyclerView = findViewById(R.id.recyclerTaiKhoan);
        dbHelper = new UserDao(this); // hoặc new UserDao(this)
        userList = dbHelper.getAllUsers(); // Lấy dữ liệu từ SQLite

        userAdapter = new UserAdapter(this, userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);


    }

    // Xử lý khi nhấn nút back trên toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // hoặc finish()
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
