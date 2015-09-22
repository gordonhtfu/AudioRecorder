package com.blackberry.gofu.audiorecorder;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {
    private static final int REQUEST_ID = 1337;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showMe(this.findViewById(android.R.id.content));
        Intent i = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);

        startActivityForResult(i, REQUEST_ID);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == REQUEST_ID && resultCode == RESULT_OK) {
            Uri savedUri = data.getData();
            Toast.makeText(this, "Recording finished!" + savedUri.getPath(), Toast.LENGTH_LONG)
                    .show();
        }
        finish();
    }

    private String getAudioFilePathFromUri(Uri uri) {
        Cursor cursor = getContentResolver()
                .query(uri, null, null, null, null);
        cursor.moveToFirst();
        int index = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
        return cursor.getString(index);
    }

    //private void copyFile(String fileName) throws IOException {
    //    File.copy(new File(fileName),
    //            new File(Environment.getExternalStorageDirectory(), fileName));
    //}

    public void showMe(View v) {
        new SampleDialogFragment().show(getFragmentManager(), "sample");
    }

}