package com.student.kppc.service_report01_lotto;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    //TODO(study) 외부로 데이터를 전달하려면 바인더를 사용
    //TODO(study) Binder 객체는 IBinder 인터페이스 상속구현 객체입니다
    Util util = new Util();
    IBinder mBinder = new MyBinder();
    static String a, b, c, d;
    static int[] c1;

    class MyBinder extends Binder {
        MyService getService() {  //서비스 객체를 리턴
            return MyService.this;
        }
    }

    @Override
    public void onCreate() {
        c1 = util.unReturnNumber();
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        b = "onDestroy 호출";

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        a = "onBind() 호출";
        intent.putExtra("log_a", a);
        for (int i = 0; i < c1.length; i++) {
            intent.putExtra("number", c1[i]);
        }
        intent.putExtra("log_b", b);
        return mBinder;
    }
}