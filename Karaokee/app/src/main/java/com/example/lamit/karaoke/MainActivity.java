package com.example.lamit.karaoke;

import android.content.ClipData;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class MainActivity extends AppCompatActivity {
    String DATABASE_NAME="SongDB";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;
    SearchView searchView;
    ListView lvkaraoke;
    ArrayList<KaraItem> mangKaraItem= new ArrayList<>();
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saoChepCSDLTuAssets();


        lvkaraoke=(ListView)findViewById(R.id.lvKaraoke);
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery("select * from song",null);
        mangKaraItem.clear();
        while (cursor.moveToNext())
        {
            String id = cursor.getString(0);
            String song_name = cursor.getString(1);
//            String song_name2 = cursor.getString(2);
            String song_lyric = cursor.getString(3);
//            String song_lyric2 = cursor.getString(4);
//            String artist = cursor.getString(5);
//            String artist2 = cursor.getString(6);
          // String imge = cursor.getString(7);

            mangKaraItem.add(new KaraItem(id,song_name,song_lyric));
        }




           adapter=new ListAdapter(MainActivity.this, (ArrayList<KaraItem>) mangKaraItem);

        lvkaraoke.setAdapter(adapter);

    }


    private void saoChepCSDLTuAssets() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                SaoChep();
                Toast.makeText(this, "Sao chép thành công dữ liệu từ Assets vào hệ thống",
                        Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    private String layDuongDanLuuTru()
    {
        // Trả về đường dẫn cần lưu trữ
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }

    private void SaoChep() {
        try{
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFileName = layDuongDanLuuTru();
            // Nếu đường dẫn chưa tồn tại thì tạo
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()) {
                f.mkdir();
            }
            // Mở 1 CSDL
            OutputStream myOutput = new FileOutputStream(outFileName);
            //Truyền file từ dữ liệu nhập ra ngoài
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0)
            {
                myOutput.write(buffer, 0, length);
            }
            // Đóng các thao tác thực thi
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }catch (Exception ex)
        {
            Log.e("Lỗi sao chép",ex.toString());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        final MenuItem myActionMenuItem=menu.findItem(R.id.search);
        changeSearchViewTextColor(searchView);
        searchView=(SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final ArrayList<KaraItem> filtermodelist=filter(mangKaraItem,newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });


        return true;
    }

    private ArrayList<KaraItem> filter(List<KaraItem> pl, String query) {
        query=query.toLowerCase();
        final ArrayList<KaraItem> filteredModelList= new ArrayList<>();
        for (KaraItem model:pl) {
            final String text=model.getTenBaiHat().toLowerCase();
            if (text.startsWith(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    private void changeSearchViewTextColor(View view) {
        if (view!=null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
            }else if (view instanceof ViewGroup) {
                ViewGroup viewGroup =(ViewGroup) view;
                for (int i=0;i<viewGroup.getChildCount();i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));



                }
            }
        }
    }
}
