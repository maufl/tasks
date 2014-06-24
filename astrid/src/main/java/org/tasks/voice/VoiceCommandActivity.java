package org.tasks.voice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.todoroo.astrid.gcal.GCalHelper;
import com.todoroo.astrid.service.TaskService;

import org.tasks.R;
import org.tasks.injection.ForApplication;
import org.tasks.injection.InjectingActivity;

import javax.inject.Inject;

import static com.todoroo.astrid.ui.QuickAddBar.basicQuickAddTask;

public class VoiceCommandActivity extends InjectingActivity {

    @Inject GCalHelper gcalHelper;
    @Inject TaskService taskService;
    @Inject @ForApplication Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        switch (intent.getAction()) {
            case "com.google.android.gm.action.AUTO_SEND":
                final String text = intent.getStringExtra(Intent.EXTRA_TEXT);
                basicQuickAddTask(context, gcalHelper, taskService, text);
                Context context = getApplicationContext();
                if (context != null) {
                    Toast.makeText(context, getString(R.string.voice_command_added_task), Toast.LENGTH_LONG).show();
                }
                finish();
        }
    }
}
