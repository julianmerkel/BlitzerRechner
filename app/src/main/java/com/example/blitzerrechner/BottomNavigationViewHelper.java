package com.example.blitzerrechner;




import android.content.Context;
import android.content.Intent;
import android.support.v4.app.INotificationSideChannel;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {


    public static void setUpBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
        bottomNavigationViewEx.setCurrentItem(1);
    }

    public static void enableNav(final Context context, BottomNavigationViewEx v){
        v.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.HistoryIcon:
                        Intent intent1 = new Intent(context,History.class);
                        context.startActivity(intent1);
                        break;

                    case R.id.MainIcon:
                        Intent intent2 = new Intent(context,MainActivity.class);
                        context.startActivity(intent2);
                        break;

                    case R.id.Info:
                        Intent intent3 = new Intent(context, Information.class);
                        context.startActivity(intent3);
                        break;
                }

                return false;
            }
        });
    }
}
