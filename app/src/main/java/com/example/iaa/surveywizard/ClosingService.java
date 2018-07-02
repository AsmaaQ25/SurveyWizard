package com.example.iaa.surveywizard;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ClosingService extends Service {
    public ClosingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

        // Handle application closing
        fireClosingNotification();

        // Destroy the service
        stopSelf();
    }

    private void fireClosingNotification() {
        MyQuestionsSecond myquestion = new MyQuestionsSecond();

        myquestion.ondestroy();
    }
}
