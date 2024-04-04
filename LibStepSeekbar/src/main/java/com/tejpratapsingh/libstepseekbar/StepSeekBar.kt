package com.tejpratapsingh.libstepseekbar

import android.content.Context
import android.util.AttributeSet
import android.util.Range
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import java.lang.ref.WeakReference


class StepSeekBar : AppCompatSeekBar {
    var stepSize = 1
    var mSeekBarSelectableRange: Range<Int> = Range(min, max)
    private var mSeekBarChangeListenerRef: WeakReference<OnSeekBarChangeListener>? = null
    private val mLocalSeekBarChangeListener: OnSeekBarChangeListener =
        object : OnSeekBarChangeListener {
            private var mPreviousProgress = 0
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val lower: Int = mSeekBarSelectableRange.lower
                val upper: Int = mSeekBarSelectableRange.upper
                if (fromUser) {
                    /*
                    * If dragged by user, stop at set upper and lower limit
                    * Do not notify upper layer about selection,
                    * as we already notified when lower/upper limit was reached
                    */
                    val stepProgress: Int = progress / stepSize * stepSize
                    if (progress < lower) {
                        setProgress(lower / stepSize * stepSize)
                        return
                    } else if (progress > upper) {
                        setProgress(upper / stepSize * stepSize)
                        return
                    }
                    if (progress != stepProgress) {
                        // If we are re-setting progress to step-value
                        setProgress(stepProgress)
                        return
                    }

                    mSeekBarChangeListenerRef?.run {
                        when {
                            mPreviousProgress != stepProgress -> this
                            else -> null
                        }
                    }?.let {
                        mPreviousProgress = stepProgress
                        it.get()
                    }?.onProgressChanged(seekBar, progress, false)
                } else {
                    /*
                    * In this section, we tackle lower and higher inputs directly from Program
                    * We set to value given by Program
                    */
                    setProgress(progress)
                    mSeekBarChangeListenerRef?.run {
                        when {
                            mPreviousProgress != progress -> this
                            else -> null
                        }
                    }?.let {
                        mPreviousProgress = progress
                        it.get()
                    }?.onProgressChanged(seekBar, progress, false)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                mSeekBarChangeListenerRef?.get()?.onStartTrackingTouch(seekBar)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                mSeekBarChangeListenerRef?.get()?.onStopTrackingTouch(seekBar)
            }
        }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(attrs, defStyleAttr)
    }

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int = 0) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.StepSeekBar,
            0,
            defStyleAttr
        ).apply {
            stepSize = getFloat(R.styleable.StepSeekBar_android_stepSize, 1F).toInt()
            recycle()
        }

        super.setOnSeekBarChangeListener(mLocalSeekBarChangeListener)
    }

    fun setSelectableRange(from: Int, to: Int, updateSeekBar: Boolean = true) {
        mSeekBarSelectableRange = Range(from, to)
        if (!mSeekBarSelectableRange.contains(progress) && updateSeekBar) {
            progress = from
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        visibility = if (enabled) {
            VISIBLE
        } else {
            INVISIBLE
        }
    }

    override fun setOnSeekBarChangeListener(l: OnSeekBarChangeListener) {
        mSeekBarChangeListenerRef = WeakReference(l)
    }
}