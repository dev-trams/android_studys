package com.student.kppc.service_report01_lotto;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class Util {

    public Util() {

    }

    public int[] unReturnNumber(){
        int min = 1;
        int max = 45;

        int num[] = new int[6];
        Random random = new Random();

        for (int i = 0; i <  num.length; i++) {
            //nextInt(45)이면 해당 범위가 0~44이므로 1~45를 구하기 위해 마지막에 최소값이 min을 더해주면 된다.
            num[i] = (random.nextInt((max - min) + 1) + min);
            Log.d("log_dev", "firstPhase::" + num[i]);

            //중복처리 로직
            //앞의 숫자와 뒤의 숫자가 같으면 중복된 숫자를 알려주고 다시 한번돈다.
            for (int j = 0; j < i; j++) {
                if (num[i] == num[j]) {
                    Log.d("log_dev", "DuplicateProcessingPhase::" + num[i]);
                    //중복건수를 처리하기위해 반복문 i번째를 다시 내린다.
                    i--;
                }
                //오름차순 처리 로직
                //부등호를 반대로 해주면 큰 수부터 차례대로 나열한다.
                if (num[i] < num[j]) {
                    int numTemp = num[i];
                    num[i] = num[j];
                    num[j] = numTemp;
                }
            }
        }
        //중복발생시 그 번호를 다시 돌림
        for (int i = 0; i < num.length; i++) {
            Log.d("log_dev", "RestartingPhase::" + num[i]);
        }
        return num;
    }

    public void logTextView(Context c, LinearLayout ll, String s) {
        TextView view = new TextView(c);
        view.setText(s);
        view.setTextSize(10);
        view.setTextColor(Color.BLACK);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.START;
        view.setLayoutParams(lp);

        ll.addView(view);
    }

}
