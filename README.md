# AndroidStepSeekBar
Android Step SeekBar

### Features
1. Customisable step size, duh :P
2. Customisable range

### How to use
```kotlin
findViewById<StepSeekBar>(R.id.step_seek_bar).apply {
            setSelectableRange(30, 70, true)
        }.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                Log.d(TAG, "onProgressChanged: $progress")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d(TAG, "onStartTrackingTouch: ")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d(TAG, "onStopTrackingTouch: ")
            }

        })
```