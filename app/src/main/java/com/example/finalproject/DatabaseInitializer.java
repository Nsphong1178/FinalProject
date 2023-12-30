package com.example.finalproject;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseInitializer {

    public void copyDatabaseFromAssets(Context context) {
        String dbName = "questions.db";
        File databasePath = context.getDatabasePath(dbName);

        if (!databasePath.exists()) {
            try {
                InputStream inputStream = context.getAssets().open(dbName);
                OutputStream outputStream = new FileOutputStream(databasePath);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copyDatabaseFromAssetsScore(Context context) {
        String dbName = "score.db";
        File databasePath = context.getDatabasePath(dbName);

        if (!databasePath.exists()) {
            try {
                InputStream inputStream = context.getAssets().open(dbName);
                OutputStream outputStream = new FileOutputStream(databasePath);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
