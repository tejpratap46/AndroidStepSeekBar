# AndroidStepSeekBar
Android Step SeekBar

[![](https://jitpack.io/v/tejpratap46/AndroidStepSeekBar.svg)](https://jitpack.io/#tejpratap46/AndroidStepSeekBar)

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

### Install


1. Add it in your root settings.gradle at the end of repositories:

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add this to your module level dependency

```
dependencies {
        implementation 'com.github.tejpratap46:AndroidStepSeekBar:Tag'
}
```
