package uk.ac.gcu.mondayschild.mondayschild;

/**
 * Created by Administrator on 13/12/2014.
 */

import android.content.Context;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class mcBiorhythmsSurfaceView extends SurfaceView implements SurfaceHolder.Callback
    {
        private SurfaceHolder shBioSurface;

        mcBiorhythmsThread drawingThread = null;


        public mcBiorhythmsSurfaceView(Context context)
        {
            super(context);
            shBioSurface = getHolder();
            shBioSurface.addCallback(this);
            drawingThread = new mcBiorhythmsThread(getHolder(), this);
            setFocusable(true);
        }

        public mcBiorhythmsThread getThread()
        {
            return drawingThread;
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder)
        {
            drawingThread.setRunning(true);
            drawingThread.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
        {
            drawingThread.setSurfaceSize(width,height);
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder)
        {
            boolean retry = true;
            drawingThread.setRunning(false);
            while(retry)
            {
                try {
                    drawingThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
