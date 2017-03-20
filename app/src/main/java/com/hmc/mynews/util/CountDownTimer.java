package com.hmc.mynews.util;

import android.os.Handler;
import android.os.Message;

/**
 * 倒计时
 * Created by hmcxunxi on 2017/3/18.
 */

public class CountDownTimer {

    private static final String TAG = CountDownTimer.class.getSimpleName();

    private OnCountDownListener mListener;

    private long mTotalTime;
    private long mRemainTime;

    private boolean mInPause = false;
    private boolean mCancelled = false;
    private static final long TIME_INTERVAL = 1;

    private static final int MSG_RUN = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            synchronized (CountDownTimer.this) {
                if (mCancelled) {
                    return;
                }
                mTotalTime--;
                if (mTotalTime > 0) {
                    mListener.onTick(mTotalTime);
                    mHandler.sendEmptyMessageDelayed(MSG_RUN, TIME_INTERVAL);
                } else {
                    mListener.onFinish();
                }
            }
        }
    };

    public CountDownTimer(long totalTime) {
        mTotalTime = totalTime;
    }

    public void setListener(OnCountDownListener listener) {
        mListener = listener;
    }

    public synchronized CountDownTimer start() {
        mCancelled = false;
        if (mListener == null) {
            throw new NullPointerException("The CountDownLister Cannot return null");
        }
        if (mTotalTime <= 0) {
            mListener.onFinish();
        }

        mListener.onStart();
        mHandler.sendEmptyMessage(MSG_RUN);
        return this;
    }

    public void pause() {
        if (!mInPause) {
            return;
        }
        mInPause = true;
        mHandler.removeCallbacksAndMessages(null);
    }

    public void resume() {
        if (!mInPause) {
            return;
        }
        mInPause = false;
        mHandler.sendEmptyMessageDelayed(MSG_RUN, TIME_INTERVAL);
    }

    public void cancel() {
        mInPause = false;
        mHandler.removeCallbacksAndMessages(null);
    }

    /**
     * 计时状态回调
     */
    public interface OnCountDownListener {

        // 开始计时
        void onStart();

        // 当前时间
        void onTick(long remainTime);

        // 结束计时
        void onFinish();
    }
}
