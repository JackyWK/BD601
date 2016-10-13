package com.beidou.bd601.view;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.view.View;
/**
 * Created by wangkuan on 2016/10/11.
 */
public class BarCharView extends View {
        public static final String RDSS_STYLE = "RDSS_STYLE";
        public static final String RNSS_STYLE = "RNSS_STYLE";
        //  坐标轴 轴线 画笔：
        private Paint mAxisLinePaint;
        //  坐标轴水平内部 虚线画笔
        private Paint mHLinePaint;
        //  绘制文本的画笔
        private Paint mTitlePaint;
        //  矩形画笔 柱状图的样式信息
        private Paint mRecPaint;
        //  显示样式设置标志，用于RD与RN之间的切换
        private String mShowStyle = RDSS_STYLE;

        public BarCharView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
            init(context, null);
        }

        public BarCharView(Context context, AttributeSet attrs) {
            super(context, attrs);
            // TODO Auto-generated constructor stub
            init(context, attrs);
        }

        public void setmShowStyle(String showstyle){
        mShowStyle = showstyle;
        }

        private void init(Context context, AttributeSet attrs)
        {
            mAxisLinePaint = new Paint();
            mHLinePaint = new Paint();
            mTitlePaint = new Paint();
            mRecPaint = new Paint();
            mAxisLinePaint.setColor(Color.DKGRAY);
            mHLinePaint.setColor(Color.LTGRAY);
            mTitlePaint.setColor(Color.BLACK);
        }

        //柱状图应该显示的数据
        private int[] mShowData;

        /**
         * 更新需要显示的数据 需要View子类重绘。
         * */
        public void updateShowData(int[] thisData)
        {
            mShowData = thisData;
            this.postInvalidate();  //可以子线程 更新视图的方法调用。
        }

        private String[] yTitlesStrings =
                new String[]{"4","3","2","1","0"};
        private String[] xTitlesRDSS =
                new String[]{"1","2","3","4","5","6","7","8","9","10"};
        private String[] xTitlesRNSS =
                new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};

        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);

            int width = getWidth();
            int height = getHeight()/2+300;

            // 1 绘制坐标线：
            canvas.drawLine(100, 20, 100, height-100, mAxisLinePaint);
            canvas.drawLine(100, height-100, width-20 , height-100, mAxisLinePaint);

            // 2 绘制坐标内部的水平线
            int leftHeight = height-100;// 左侧外周的 需要划分的高度：
            int hPerHeight = leftHeight/4;
            mHLinePaint.setTextAlign(Align.CENTER);
            for(int i=0;i<4;i++)
            {
                canvas.drawLine(100, 20+i*hPerHeight, width-10, 20+i*hPerHeight, mHLinePaint);
            }

            // 3 绘制 Y 轴坐标
            FontMetrics metrics = mTitlePaint.getFontMetrics();
            int descent = (int)metrics.descent;
            mTitlePaint.setTextAlign(Align.RIGHT);
            mTitlePaint.setTextSize(36);
            for(int i=0;i<yTitlesStrings.length;i++)
            {
                canvas.drawText(yTitlesStrings[i], 80, 20+i*hPerHeight+descent, mTitlePaint);
            }

            // 4  绘制 X 轴做坐标
            // 注意RDSS与RNSS信号的转换
            int step = 0;
            if(mShowStyle.equals(RDSS_STYLE)) {
                int xAxisLength = width - 110;
                int columCount = xTitlesRDSS.length + 1;
                step = xAxisLength / columCount;
                for (int i = 0; i < columCount - 1; i++) {
                    canvas.drawText(xTitlesRDSS[i], 100 + step * (i + 1), height-70, mTitlePaint);
                }
            }
            if(mShowStyle.equals(RNSS_STYLE)) {
                int xAxisLength = width - 110;
                int columCount = xTitlesRNSS.length + 1;
                step = xAxisLength / columCount;
                for (int i = 0; i < columCount - 1; i++) {
                    canvas.drawText(xTitlesRNSS[i], 100 + step * (i + 1), height -70, mTitlePaint);
                }
            }

            // 5 绘制矩形
            if(mShowData != null && mShowData.length >0)
            {
                int thisCount = mShowData.length;
                for(int i=0;i<thisCount;i++)
                {
                    int value = 4-mShowData[i];
                    mRecPaint.setColor(0xFF1078CF);
                    Rect rect = new Rect();
                    rect.left = 100 + step * (i + 1) - 30;
                    rect.right = 100 + step * (i + 1) + 30;
                    //当前的相对高度：
                    int rh = (leftHeight * value) / 4;
                    rect.top = rh + 20;
                    rect.bottom = height - 100;
                    canvas.drawRect(rect, mRecPaint);
                }
            }



        }
    }
