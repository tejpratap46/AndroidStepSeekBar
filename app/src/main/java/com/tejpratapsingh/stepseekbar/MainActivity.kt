package com.tejpratapsingh.stepseekbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tejpratapsingh.libstepseekbar.StepSeekBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<StepSeekBar>(R.id.step_seek_bar).apply {
            stepValue = 10
        }
    }
}