package com.student.kppc.a20220503_report03;

import static android.os.Build.VERSION_CODES.M;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    Switch state_switch;
    EditText input_str;
    Button change_style;
    int checked_state1 = 10;
    int checked_state2 = 10;
    boolean switch_state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutInit();
        state_switch.setOnCheckedChangeListener(new stateSwitch());
        change_style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });
    }

    class stateSwitch implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (!b) {
                s_setText("초보자 모드");
                switch_state = false;
            } else {
                s_setText("전문가 모드");
                switch_state = true;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        checked(null, menu, checked_state1, checked_state2);
        if (switch_state) {
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
            menu.getItem(R.id.ic_add).setVisible(false);
            menu.getItem(R.id.ic_search).setVisible(false);
            menu.getItem(R.id.ic_beginner).setVisible(true);
        } else {
            Toast.makeText(this, "unclick", Toast.LENGTH_SHORT).show();
            menu.getItem(R.id.ic_add).setVisible(true);
            menu.getItem(R.id.ic_search).setVisible(true);
            menu.getItem(R.id.ic_beginner).setVisible(false);

        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.color_gray:
                change_style.setBackgroundColor(Color.GRAY);
                checked_state1 = 0;
                item.setChecked(true);
                break;
            case R.id.color_sian:
                change_style.setBackgroundColor(Color.CYAN);
                checked_state1 = 1;
                item.setChecked(true);
                break;
            case R.id.color_red:
                change_style.setBackgroundColor(Color.RED);
                checked_state1 = 2;
                item.setChecked(true);
                break;
            case R.id.color_white:
                change_style.setBackgroundColor(Color.WHITE);
                checked_state1 = 3;
                item.setChecked(true);
                break;
            case R.id.text_blue:
                change_style.setTextColor(Color.BLUE);
                checked_state2 = 0;
                checkedUn(item);
                if (item.isChecked()) {
                    change_style.setTextColor(Color.BLUE);
                } else {
                    change_style.setTextColor(Color.BLACK);
                }
                break;
            case R.id.text_size_up:
                change_style.setTextSize(20);
                checked_state2 = 1;
                if (!item.isChecked()) {
                    change_style.setTextSize(20);
                } else {
                    change_style.setTextSize(12);
                }
                checkedUn(item);
                break;
            case R.id.text_size_reset:
                change_style.setTextSize(12);
                checked_state2 = 2;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void checkedUn(MenuItem item) {
        if (item.isChecked()) {
            item.setChecked(false);
            switch_state = false;
        } else {
            item.setChecked(true);
            switch_state = true;
        }
    }
    //  버튼 클릭시 contextMenu 호출

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    void showPopup(View v) {
        PopupMenu menu = new PopupMenu(this, v);
        menu.setOnMenuItemClickListener(this);
        menu.inflate(R.menu.context_menu);
        checked(menu, null, checked_state1, checked_state2);
        menu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.c_color_gray:
                change_style.setBackgroundColor(Color.GRAY);
                checked_state1 = 0;
                menuItem.setChecked(true);
                break;
            case R.id.c_color_sian:
                change_style.setBackgroundColor(Color.CYAN);
                checked_state1 = 1;
                menuItem.setChecked(true);
                break;
            case R.id.c_color_red:
                change_style.setBackgroundColor(Color.RED);
                checked_state1 = 2;
                menuItem.setChecked(true);
                break;
            case R.id.c_color_white:
                change_style.setBackgroundColor(Color.WHITE);
                checked_state1 = 3;
                menuItem.setChecked(true);
                break;
            case R.id.c_text_blue:
                checked_state2 = 0;
                checkedUn(menuItem);
                if (menuItem.isChecked()) {
                    change_style.setTextColor(Color.BLUE);
                } else {
                    change_style.setTextColor(Color.BLACK);
                }
                break;
            case R.id.c_text_size_up:
                if (!menuItem.isChecked()) {
                    change_style.setTextSize(20);
                } else {
                    change_style.setTextSize(12);
                }
                checked_state2 = 1;
                checkedUn(menuItem);
                break;
            case R.id.c_text_size_reset:
                change_style.setTextSize(12);
                checked_state2 = 2;
                break;
        }
        return false;
    }

    void s_setText(String s) {
        state_switch.setText(s);
    }

    void checked(PopupMenu c_menu, Menu menu, int s1, int s2) {
        if (c_menu == null) {
            switch (s1) {
                case 0:
                    menu.findItem(R.id.color_gray).setChecked(true);
                    break;
                case 1:
                    menu.findItem(R.id.color_sian).setChecked(true);
                    break;
                case 2:
                    menu.findItem(R.id.color_red).setChecked(true);
                    break;
                case 3:
                    menu.findItem(R.id.color_white).setChecked(true);
                    break;
            }
            switch (s2) {
                case 0:
                    menu.findItem(R.id.text_blue).setChecked(true);
                    break;
                case 1:
                    menu.findItem(R.id.text_size_up).setChecked(true);
                    break;
                case 2:
                    menu.findItem(R.id.text_size_reset).setChecked(true);
            }
        }
        if (menu == null) {
            switch (s1) {
                case 0:
                    c_menu.getMenu().findItem(R.id.c_color_gray).setChecked(true);
                    break;
                case 1:
                    c_menu.getMenu().findItem(R.id.c_color_sian).setChecked(true);
                    break;
                case 2:
                    c_menu.getMenu().findItem(R.id.c_color_red).setChecked(true);
                    break;
                case 3:
                    c_menu.getMenu().findItem(R.id.c_color_white).setChecked(true);
                    break;
            }
            switch (s2) {
                case 0:
                    c_menu.getMenu().findItem(R.id.c_text_blue).setChecked(true);
                    break;
                case 1:
                    c_menu.getMenu().findItem(R.id.c_text_size_up).setChecked(true);
                    break;
                case 2:
                    c_menu.getMenu().findItem(R.id.c_text_size_reset).setChecked(true);
                    break;
            }
        }

    }

    void layoutInit() {

        state_switch = findViewById(R.id.state_switch);
        input_str = findViewById(R.id.input_str);
        change_style = findViewById(R.id.change_style);
    }
}