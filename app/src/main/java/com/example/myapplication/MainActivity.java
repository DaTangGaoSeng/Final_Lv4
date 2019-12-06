package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private List<Fragment> fragments = new ArrayList<>();
    //三个List装呼入 呼出 未接电话
    static private List<Map<String, String>> list_callin = new ArrayList<>();
    static private List<Map<String, String>> list_callout= new ArrayList<>();
    static private List<Map<String,String>> list_unreceived = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataList();
        TabLayout tabLayout = findViewById(R.id.xix);
        fragments.add(new fragment_1());
        fragments.add(new fragment_2());
        fragments.add(new fragment_3());
        String[] a = {"呼入电话", "呼出电话", "未接来电"};
        ViewPager viewPager = findViewById(R.id.viewpager);
        ViewPagerAdator viewPagerAdator = new ViewPagerAdator(getSupportFragmentManager(), fragments, a);
        viewPager.setAdapter(viewPagerAdator);
        tabLayout.setupWithViewPager(viewPager);

    }
    //得到数据 网上cv 加修改
    private void getDataList() {
        ContentResolver contentResolver = getContentResolver();
        if (MainActivity.this.checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CALL_LOG}, 1);
        } else {
            Cursor cursor = contentResolver.query(CallLog.Calls.CONTENT_URI, // 查询通话记录的URI
                    new String[]{CallLog.Calls.CACHED_NAME// 通话记录的联系人
                            , CallLog.Calls.NUMBER// 通话记录的电话号码
                            , CallLog.Calls.DATE// 通话记录的日期
                            , CallLog.Calls.DURATION// 通话时长
                            , CallLog.Calls.TYPE}// 通话类型
                    , null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                long dateLong = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
                String date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date(dateLong));
                int duration = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION));
                int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
                String typeString = "";
                Map<String, String> map = new HashMap<>();
                if (type == CallLog.Calls.INCOMING_TYPE) {
                    typeString = "打入";
                    map.put("name", (name == null) ? "未备注联系人" : name);
                    map.put("number", number);
                    map.put("date", date);
                    map.put("duration", (duration / 60) + "分钟");
                    map.put("type", typeString);
                    list_callin.add(map);
                }
                else if(type == CallLog.Calls.OUTGOING_TYPE){
                    typeString = "呼出";
                    map.put("name", (name == null) ? "未备注联系人" : name);
                    map.put("number", number);
                    map.put("date", date);
                    map.put("duration", (duration / 60) + "分钟");
                    map.put("type", typeString);
                    list_callout.add(map);
                }
                else {
                    typeString = "未接";
                    map.put("name", (name == null) ? "未备注联系人" : name);
                    map.put("number", number);
                    map.put("date", date);
                    map.put("duration", (duration / 60) + "分钟");
                    map.put("type", typeString);
                    list_unreceived.add(map);
                }
            }
            cursor.close();
        }
    }

   static public List<Map<String, String>> getList_callin() {
        return list_callin;
    }

    static public List<Map<String, String>> getList_callout() {
        return list_callout;
    }

    static public List<Map<String, String>> getList_unreceived() {
        return list_unreceived;
    }
}

