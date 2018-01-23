package com.example.lamit.karaoke;

import android.content.ClipData;
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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class MainActivity extends AppCompatActivity {
    SearchView searchView;
    ListView lvkaraoke;
    ArrayList<KaraItem> mangKaraItem= new ArrayList<>();
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvkaraoke=(ListView)findViewById(R.id.lvKaraoke);

            mangKaraItem.add(new KaraItem("000000","Tên bài hát","Lời bài hát"));
            mangKaraItem.add(new KaraItem("000001","Tên bài hát1","Lời bài hát1"));
            mangKaraItem.add(new KaraItem("000002","ten bài há2t","Lời bài hát2"));
            mangKaraItem.add(new KaraItem("000003","Tên bài hát3","Lời bài hát3"));
            mangKaraItem.add(new KaraItem("000004","Tên bài hát4","Lời bài hát4"));
            mangKaraItem.add(new KaraItem("000005","Tên bài hát5","Lời bài hát5"));
            mangKaraItem.add(new KaraItem("000006","Tên bài hát6","Lời bài hát6"));
            mangKaraItem.add(new KaraItem("000000","Tên bài hát","Lời bài hát"));
            mangKaraItem.add(new KaraItem("000001","Tên bài hát1","Lời bài hát1"));
            mangKaraItem.add(new KaraItem("000002","Tên bài há2t","Lời bài hát2"));
            mangKaraItem.add(new KaraItem("000003","Tên bài hát3","Lời bài hát3"));
            mangKaraItem.add(new KaraItem("000004","Tên bài hát4","Lời bài hát4"));
            mangKaraItem.add(new KaraItem("000005","Tên bài hát5","Lời bài hát5"));
            mangKaraItem.add(new KaraItem("000006","Tên bài hát6","Lời bài hát6"));
            mangKaraItem.add(new KaraItem("000000","Tên bài hát","Lời bài hát"));
            mangKaraItem.add(new KaraItem("000001","Tên bài hát1","Lời bài hát1"));
            mangKaraItem.add(new KaraItem("000002","Tên bài há2t","Lời bài hát2"));
            mangKaraItem.add(new KaraItem("000003","Tên bài hát3","Lời bài hát3"));
            mangKaraItem.add(new KaraItem("000004","Tên bài hát4","Lời bài hát4"));
            mangKaraItem.add(new KaraItem("000005","Tên bài hát5","Lời bài hát5"));
            mangKaraItem.add(new KaraItem("000006","Tên bài hát6","Lời bài hát6"));
            mangKaraItem.add(new KaraItem("000000","Tên bài hát","Lời bài hát"));
            mangKaraItem.add(new KaraItem("000001","Tên bài hát1","Lời bài hát1"));
            mangKaraItem.add(new KaraItem("000002","Tên bài há2t","Lời bài hát2"));
            mangKaraItem.add(new KaraItem("000003","Tên bài hát3","Lời bài hát3"));
            mangKaraItem.add(new KaraItem("000004","Tên bài hát4","Lời bài hát4"));
            mangKaraItem.add(new KaraItem("000005","Tên bài hát5","Lời bài hát5"));
            mangKaraItem.add(new KaraItem("000006","Tên bài hát6","Lời bài hát6"));

           adapter=new ListAdapter(MainActivity.this, (ArrayList<KaraItem>) mangKaraItem);

        lvkaraoke.setAdapter(adapter);

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
