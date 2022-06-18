package kursuch.project.smarterthanmelines;

import android.app.Activity;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.View;

import org.opencv.core.Mat;

import java.io.IOException;
//import javax.swing.*;
//import java.awt.*;


public class MouseDemo extends Activity implements View.OnTouchListener {
    float touchX;
    float touchY;
    boolean isTouchDown;
    boolean isTouchUp;
    int sceneWidth;
    int sceneHeight;

    public MouseDemo(View view, int sceneWidth, int sceneHeight) {
        view.setOnTouchListener(this);
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
    }


    @Override
    public boolean onTouch(View view, MotionEvent event)
    {
        synchronized (this){
            isTouchDown = false;
            isTouchUp = false;

            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                   //touchX = event.getX();// * (1080 / MainComputer.to_draw.rows());// * (AnswerLevel.answerImage.getDrawable().getIntrinsicWidth() / PhotoLevel.oldWidth);// / view.getWidth() ;
                   //touchY = event.getY();// * (1920 / MainComputer.to_draw.cols());// * (AnswerLevel.answerImage.getDrawable().getIntrinsicHeight() / PhotoLevel.oldHeight);// / view.getHeight();
                   ////touchX = view.getLeft();
                   ////touchY = view.getTop();
                   ////int[] posXY = new int[2];
                   ////view.getLocationOnScreen(posXY);
                   ////touchX = (int) event.getX();
                   ////touchY = (int) event.getY();
                   ////touchX = touchX - posXY[0]; // posXY[0] is the X coordinate
                   ////touchY = touchY - posXY[1]; // posXY[1] is the y coordinate
                   //isTouchDown = true;
                   //isTouchUp = false;
                    float x = event.getX();
                    float y = event.getY();
                    // The coordinates of the target point
                    float dst[] = new float[2];
                    // Get the matrix of ImageView
                    Matrix imageMatrix = AnswerLevel.answerImage.getImageMatrix();
                    // Create an inverse matrix
                    Matrix inverseMatrix = new Matrix();
                    // Inverse, the inverse matrix is ​​assigned
                    imageMatrix.invert(inverseMatrix);
                    // Get the value of the target point dst through the inverse matrix mapping
                    inverseMatrix.mapPoints(dst, new float[]{x, y});
                    float dstX = dst[0];
                    float dstY = dst[1];
                    touchX = (int)(dstX);
                    touchY = (int)(dstY);
                    // Determine the position of dstX, dstY on the Bitmap

                    try {
                        MainComputer.mouse_event((int)(touchX * 2.65), (int)(touchY * 2.65));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                //case MotionEvent.ACTION_UP:
                //    touchX = (int)event.getX() * sceneWidth;
                //    touchY = event.getY() * sceneHeight;
                //    isTouchDown = false;
                //    isTouchUp = true;
                //    break;
            }
        }
        return true;
    }

   // public boolean getTouchUp(int x, int y, int touchWidth, int touchHeight) {
   //     if (isTouchUp) {
   //         if (touchX >= x && touchX <= x + touchWidth - 1 && touchY <= y && touchY >= y - (touchHeight - 1)) {
   //             isTouchUp = false;
   //             return true;
   //         }
   //     }
   //     return false;
   // }
}

//@Override
//public void mouseClicked(MouseEvent e) {
//    x = e.getX();
//    y = e.getY();
//    try {
//        Main.mouse_event(x, y);
//    } catch (IOException ioException) {
//    }
//}

// MouseDemo(JFrame frame1) {
//     //label = new JLabel(new ImageIcon("maze.png"));
//     //label.setBounds(0,0,600,600);
//     //label = new JLabel();
//     //frame = new JFrame();
//     frame = frame1;
//     //frame.setSize(840, 840);
//     //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//     //frame.setLayout(null);
//     frame.setVisible(true);
//     frame.addMouseListener(this);
//     //frame.add(label);
// }