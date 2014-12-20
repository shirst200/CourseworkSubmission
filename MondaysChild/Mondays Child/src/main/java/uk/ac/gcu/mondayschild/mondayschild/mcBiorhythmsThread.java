package uk.ac.gcu.mondayschild.mondayschild;

/**
 * Created by Administrator on 13/12/2014.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class mcBiorhythmsThread extends Thread
{
    private int canvasWidth;
    private int canvasHeight;
    private float xPos = 0.0f;
    private float yPos = 0.0f;
    private int i;

    private Calendar cToday = new GregorianCalendar();
    private int cCurrentDay = cToday.get(Calendar.DAY_OF_MONTH);
    private int cCurrentMonth = cToday.get(Calendar.MONTH);
    private int cCurrentYear = cToday.get(Calendar.YEAR);

    private float HalfAppletHeight;
    private float HalfAppletWidth;

    private int cCalcDays;
    private int dDate;

    private boolean first = true;
    private boolean run = false;

    private SurfaceHolder shBioSurface;
    private Paint paintBio;
    private mcBiorhythmsSurfaceView bioSF;

    public mcBiorhythmsThread(SurfaceHolder surfaceHolder, mcBiorhythmsSurfaceView bioSurfV) {
        this.shBioSurface = surfaceHolder;
        this.bioSF = bioSurfV;
        paintBio = new Paint();
        cCalcDays = calcDays(cCurrentDay, cCurrentMonth+1, cCurrentYear);
    }

    public void doStart() {
        synchronized (shBioSurface) {
            int aDays = 1;
            int aMonth = 1;
            int aYear = 1990;
            int aCalcDays = calcDays(aDays, aMonth, aYear);
            dDate = cCalcDays - aCalcDays;
            first = false;

        }
    }

    public void run() {
        while (run) {
            Canvas c = null;
            try {
                c = shBioSurface.lockCanvas(null);
                synchronized (shBioSurface) {
                    svDraw(c);
                }
            } finally {
                if (c != null) {
                    shBioSurface.unlockCanvasAndPost(c);
                }
            }
        }
    }

    public void setRunning(boolean b) {
        run = b;
    }
    public void setSurfaceSize(int width, int height) {
        synchronized (shBioSurface) {
            canvasWidth = width;
            canvasHeight = height;
            HalfAppletHeight = canvasHeight / 2;
            HalfAppletWidth  = canvasWidth / 32;
            doStart();
        }
    }


    private void svDraw(Canvas canvas) {
        if(run) {
            //The inputs for the draw functions
            canvas.save();
            canvas.restore();
            canvas.drawColor(Color.GRAY);
            paintBio.setStyle(Paint.Style.FILL);
            drawAxes(canvas);
            paintBio.setColor(Color.RED);
            drawWave(canvas, 1);
            paintBio.setColor(Color.GREEN);
            drawWave(canvas, 60);
            paintBio.setColor(Color.BLUE);
            drawWave(canvas, 33);
        }
    }

    private int calcDays(int cdDaysIn, int cdMonthIn, int cdYearIn)
    {
        int iNoLeapYears, iNoYears, iNoYearsAsDays, iCurrentDays, iNumDays, iDaysSince;
        int aTotMonth[] = {31,59,90,120,151,181,212,243,273,304,334,365};
        iNoYears = cdYearIn - 1900;
        iNoLeapYears = (iNoYears/4);
        iNumDays = aTotMonth[(cdMonthIn - 1)];
        iNoYearsAsDays = iNoYears * 365;
        return iDaysSince = iNoYearsAsDays + iNumDays + cdDaysIn + iNoLeapYears;
    }

    public void drawWave(Canvas theCanvas, int period)
    {
        float xPosOld = 0.0f;
        float yPosOld = 0.0f;
        int dStart = -15;
        int sDate = 0;
        int tDate = 0;

        sDate = dDate + dStart;
        //This draws 30 small lines to look like a curving line
        for (i=0;i<=30;i++)
        {
            //The x Position is based on how many times the loop has looped
            xPos = i * HalfAppletWidth;
            tDate = sDate + i;
            //the y Position is based on the tangent function
            yPos = (float)(-100.0f * (Math.tan((tDate%period)*2*Math.PI/period)));
            //The canvas is drawn from the old point to the new point
            if ( i == 0)
                paintBio.setStyle(Paint.Style.FILL);
            else
                theCanvas.drawLine(xPosOld, (yPosOld + HalfAppletHeight), xPos, (yPos + HalfAppletHeight), paintBio);
            xPosOld = xPos;
            yPosOld = yPos;
        }
    }

    public void drawAxes(Canvas theCanvas)
    {
        paintBio.setColor(Color.BLACK);
        theCanvas.drawLine(0,HalfAppletHeight,30*HalfAppletWidth,HalfAppletHeight, paintBio); // Horizontal X Axes
        theCanvas.drawLine(15* HalfAppletWidth,0,15* HalfAppletWidth,canvasHeight, paintBio); // Vertical Y Axes
    }
}