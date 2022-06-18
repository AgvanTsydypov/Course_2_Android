package kursuch.project.smarterthanmelines;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;

public class MainComputer {

    static int p = 0;
    static Point start;
    static Point end;
    public static ArrayList<Pair> path;
    public static boolean helpSolMaze = false;

    public static Mat to_draw;

    public static int find_width_end() {
        int width = 1;
        while (true) {
            if (to_draw.get((int)end.y - width, (int)end.x)[0] == 0
                    && to_draw.get((int)end.y - width, (int)end.x)[1] == 255
                    && to_draw.get((int)end.y - width, (int)end.x)[2] == 0) {
                return width;
            }
            if (to_draw.get((int)end.y + width, (int)end.x)[0] == 0
                    && to_draw.get((int)end.y + width, (int)end.x)[1] == 255
                    && to_draw.get((int)end.y + width, (int)end.x)[2] == 0) {
                return width;
            }
            if (to_draw.get((int)end.y, (int)end.x + width)[0] == 0
                    && to_draw.get((int)end.y, (int)end.x + width)[1] == 255
                    && to_draw.get((int)end.y, (int)end.x + width)[2] == 0) {
                return width;
            }
            if (to_draw.get((int)end.y, (int)end.x - width)[0] == 0
                    && to_draw.get((int)end.y, (int)end.x - width)[1] == 255
                    && to_draw.get((int)end.y, (int)end.x - width)[2] == 0) {
                return width;
            }
            if (to_draw.get((int)end.y - width, (int)end.x - width)[0] == 0
                    && to_draw.get((int)end.y - width, (int)end.x - width)[1] == 255
                    && to_draw.get((int)end.y - width, (int)end.x - width)[2] == 0) {
                return width;
            }
            if (to_draw.get((int)end.y + width, (int)end.x - width)[0] == 0
                    && to_draw.get((int)end.y + width, (int)end.x - width)[1] == 255
                    && to_draw.get((int)end.y + width, (int)end.x - width)[2] == 0) {
                return width;
            }
            if (to_draw.get((int)end.y - width, (int)end.x + width)[0] == 0
                    && to_draw.get((int)end.y - width, (int)end.x + width)[1] == 255
                    && to_draw.get((int)end.y - width, (int)end.x + width)[2] == 0) {
                return width;
            }
            if (to_draw.get((int)end.y + width, (int)end.x + width)[0] == 0
                    && to_draw.get((int)end.y + width, (int)end.x + width)[1] == 255
                    && to_draw.get((int)end.y + width, (int)end.x + width)[2] == 0) {
                return width;
            }
            ++width;
        }
    }

    public static int find_width_start() {
        int width = 1;
        while (true) {
            if (to_draw.get((int)start.y - width, (int)start.x)[0] == 0
                    && to_draw.get((int)start.y - width, (int)start.x)[1] == 255
                    && to_draw.get((int)start.y - width, (int)start.x)[2] == 0) {
                return width;
            }
            if (to_draw.get((int)start.y + width, (int)start.x)[0] == 0
                    && to_draw.get((int)start.y + width, (int)start.x)[1] == 255
                    && to_draw.get((int)start.y + width, (int)start.x)[2] == 0) {
                return width;
            }
            if (to_draw.get((int)start.y, (int)start.x + width)[0] == 0
                    && to_draw.get((int)start.y, (int)start.x + width)[1] == 255
                    && to_draw.get((int)start.y, (int)start.x + width)[2] == 0) {
                return width;
            }
            if (to_draw.get((int)start.y, (int)start.x - width)[0] == 0
                    && to_draw.get((int)start.y, (int)start.x - width)[1] == 255
                    && to_draw.get((int)start.y, (int)start.x - width)[2] == 0) {
                return width;
            }
            if (to_draw.get((int)start.y - width, (int)start.x - width)[0] == 0
                    && to_draw.get((int)start.y - width, (int)start.x - width)[1] == 255
                    && to_draw.get((int)start.y - width, (int)start.x - width)[2] == 0) {
                return width;
            }
            if (to_draw.get((int)start.y + width, (int)start.x - width)[0] == 0
                    && to_draw.get((int)start.y + width, (int)start.x - width)[1] == 255
                    && to_draw.get((int)start.y + width, (int)start.x - width)[2] == 0) {
                return width;
            }
            if (to_draw.get((int)start.y - width, (int)start.x + width)[0] == 0
                    && to_draw.get((int)start.y - width, (int)start.x + width)[1] == 255
                    && to_draw.get((int)start.y - width, (int)start.x + width)[2] == 0) {
                return width;
            }
            if (to_draw.get((int)start.y + width, (int)start.x + width)[0] == 0
                    && to_draw.get((int)start.y + width, (int)start.x + width)[1] == 255
                    && to_draw.get((int)start.y + width, (int)start.x + width)[2] == 0) {
                return width;
            }
            ++width;
        }
    }

    public static int find_width() {
        return Math.min(find_width_end(), find_width_start());
    }

    public static boolean is_way(int curr_i, int curr_j) {
        return (to_draw.get(curr_i, curr_j)[0] == 255 && to_draw.get(curr_i, curr_j)[1] == 255
                && to_draw.get(curr_i, curr_j)[2] == 255);
    }

    public static boolean is_wall(int curr_i, int curr_j) {
        return (to_draw.get(curr_i, curr_j)[0] == 0 && to_draw.get(curr_i, curr_j)[1] == 255
                && to_draw.get(curr_i, curr_j)[2] == 0) || (to_draw.get(curr_i, curr_j)[0] == 0 && to_draw.get(curr_i, curr_j)[1] == 0
                && to_draw.get(curr_i, curr_j)[2] == 255);
    }

    public static void drawFirst()
    {
        for (int i = 0; i < path.size() / 4 ; i++) {
            Scalar colour = new Scalar(255, 255, 255);
            Point p = new Point();
            p.x = path.get(i).second;
            p.y = path.get(i).first;
            Imgproc.circle(to_draw, p, 2, colour, 2);
        }
    }
    public static void drawSecond()
    {
        for (int i = path.size() / 4; i < path.size() * 2 / 4; i++) {
            Scalar colour = new Scalar(255, 255, 255);
            Point p = new Point();
            p.x = path.get(i).second;
            p.y = path.get(i).first;
            Imgproc.circle(to_draw, p, 2, colour, 2);
        }
    }
    public static void drawThird()
    {
        for (int i = path.size() * 2 / 4; i < path.size() * 3 / 4; i++) {
            Scalar colour = new Scalar(255, 255, 255);
            Point p = new Point();
            p.x = path.get(i).second;
            p.y = path.get(i).first;
            Imgproc.circle(to_draw, p, 2, colour, 2);
        }
    }
    public static void drawFourth()
    {
        for (int i = path.size() * 3 / 4; i < path.size(); i++) {
            Scalar colour = new Scalar(255, 255, 255);
            Point p = new Point();
            p.x = path.get(i).second;
            p.y = path.get(i).first;
            Imgproc.circle(to_draw, p, 2, colour, 2);
        }
    }
    public static void drawAll()
    {
        for (int i = 0; i < path.size(); i++) {
            Scalar colour = new Scalar(255, 255, 255);
            Point p = new Point();
            p.x = path.get(i).second;
            p.y = path.get(i).first;
            Imgproc.circle(to_draw, p, 2, colour, 2);
        }
    }

    public static void ВFS() throws IOException {
        int rows = to_draw.rows();
        int cols = to_draw.cols();
        int curr_i = (int)start.y;
        int curr_j = (int)start.x;
        int start_i = curr_i;
        int start_j = curr_j;
        int end_j = (int)end.x;
        int end_i = (int)end.y;
        int width = find_width() - 3;
        int[][] visited = new int[rows][cols];
        path = new ArrayList<Pair>();
        Node[][] matrix = new Node[rows][cols];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                matrix[i][j] = new Node(0, 0, false);
                boolean check = is_wall(i, j);
                matrix[i][j].setIs_wall(check);
                matrix[i][j].blue = (Math.abs(end_j - j) + Math.abs(end_i - i)) * 10;
                visited[i][j] = 0;
            }
        }
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (matrix[i][j].isIs_wall()) {
                    for (int k = 0; k < width; ++k) {
                        if (i > k) {
                            matrix[i - k - 1][j].close_wall = true;
                        }
                        if (j > k) {
                            matrix[i][j - k - 1].close_wall = true;
                        }
                        if (i < rows - k - 1) {
                            matrix[i + k + 1][j].close_wall = true;
                        }
                        if (j < cols - k - 1) {
                            matrix[i][j + k + 1].close_wall = true;
                        }
                    }
                }
            }
        }

        Pair[][] parent = new Pair[rows][cols];
        ArrayList<Pair> queue = new ArrayList<Pair>();
        ArrayList<Pair> dir4 = new ArrayList<Pair>();
        dir4.add(new Pair(-1, 0));
        dir4.add(new Pair(1, 0));
        dir4.add(new Pair(0, 1));
        dir4.add(new Pair(0, -1));
        dir4.add(new Pair(-1, 1));
        dir4.add(new Pair(1, 1));
        dir4.add(new Pair(-1, -1));
        dir4.add(new Pair(1, -1));
        Pair pnt = new Pair(start_i, start_j);
        queue.add(pnt);
        visited[start_i][start_j] = 1;
        Pair par = null;
        boolean found = false;
        while (queue.size() > 0) {
            par = queue.get(0);
            queue.remove(0);
            for (Pair p : dir4) {
                curr_i = p.first + par.first;
                curr_j = p.second + par.second;
                if (curr_i >= 0 && curr_i < rows && curr_j >= 0 && curr_j < cols && !is_wall(curr_i, curr_j)
                        && visited[curr_i][curr_j] == 0 && !matrix[curr_i][curr_j].close_wall) {
                    queue.add(new Pair(curr_i, curr_j));
                    visited[curr_i][curr_j] = visited[par.first][par.second] + 1;
                    parent[curr_i][curr_j] = par;
                    if (curr_i == end_i && curr_j == end_j) {
                        found = true;
                        break;
                    }
                }
            }
        }
        if (found) {
            par.first = end_i;
            par.second = end_j;
            while (!(par.first == start_i && par.second == start_j)) {
                path.add(par);
                par = parent[par.first][par.second];
            }
            path.add(par);
        } else {
            //AnswerLevel.errorPath();
            System.out.println("Path not found");
        }

        System.out.println("ready BFS");

        //MatOfByte matOfByte = new MatOfByte();
        //Imgcodecs.imencode(".jpg", to_draw, matOfByte);        //перевод изображения в байтовую матрицу
        //byte[] byteArray = matOfByte.toArray();                //перевод байтовой матрицы в массив
        //InputStream in = new ByteArrayInputStream(byteArray);  //перевод в буферизованное изображени
        //BufferedImage bufImage = ImageIO.read(in);
        //JFrame frame = new JFrame();                           //создаем окно
        //frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));   //показвваем изображение
        //frame.pack();
        //frame.setVisible(true);
    }

    public static void mouse_event(int pX, int pY) throws IOException {// int flags, Pointer param) {
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        if (p == 0) {
            start = new Point();
            start.x = pX;// + (int)AnswerLevel.answerImage.getX(); // -7;
            start.y = pY;// + (int)AnswerLevel.answerImage.getY(); // - 28;
            System.out.println(start.x);
            System.out.print(" " + start.y);
            System.out.println();
            Scalar colour = new Scalar(255, 255, 255);
            Imgproc.circle(to_draw, start, 3, colour, 3);
            p = 1;

            Drawable drawable = convertMatToDrawable(to_draw);
            AnswerLevel.answerImage.setImageDrawable(drawable);

            System.out.println("ready 1");

            //MatOfByte matOfByte = new MatOfByte();
            //Imgcodecs.imencode(".jpg", to_draw, matOfByte);        //перевод изображения в байтовую матрицу
            //byte[] byteArray = matOfByte.toArray();                //перевод байтовой матрицы в массив
            //InputStream in = new ByteArrayInputStream(byteArray);  //перевод в буферизованное изображение
            //BufferedImage bufImage = ImageIO.read(in);
            //JFrame frame = new JFrame();                           //создаем окно
            //frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));   //показвваем изображение
            //frame.pack();
            //frame.setVisible(true);
        } else if (p == 1) {
            end = new Point();
            end.x = pX;// - 7;
            end.y = pY;// - 28;
            System.out.println(end.x);
            System.out.print(" " + end.y);
            System.out.println();
            Scalar colour = new Scalar(255, 255, 255);
            Imgproc.circle(to_draw, end, 3, colour, 3);
            p = 2;

            Drawable drawable = convertMatToDrawable(to_draw);
            AnswerLevel.answerImage.setImageDrawable(drawable);

            System.out.println("ready 2");
            //MatOfByte matOfByte = new MatOfByte();
            //Imgcodecs.imencode(".jpg", to_draw, matOfByte);        //перевод изображения в байтовую матрицу
            //byte[] byteArray = matOfByte.toArray();                //перевод байтовой матрицы в массив
            //InputStream in = new ByteArrayInputStream(byteArray);  //перевод в буферизованное изображение
            //BufferedImage bufImage = ImageIO.read(in);
            //JFrame frame = new JFrame();                           //создаем окно
            //frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));   //показвваем изображение
            //frame.pack();
            //frame.setVisible(true);
        }
        else if (p == 2) {
         ВFS();
         p = 3;
         if (helpSolMaze == false)
             p = 7;
        }
        else if (p == 3 && helpSolMaze == true)
        {
            drawFourth();
            Drawable drawable = convertMatToDrawable(to_draw);
            AnswerLevel.answerImage.setImageDrawable(drawable);
            p = 4;
        }
        else if (p == 4 && helpSolMaze == true)
        {
            drawThird();
            Drawable drawable = convertMatToDrawable(to_draw);
            AnswerLevel.answerImage.setImageDrawable(drawable);
            p = 5;
        }
        else if (p == 5 && helpSolMaze == true)
        {
            drawSecond();
            Drawable drawable = convertMatToDrawable(to_draw);
            AnswerLevel.answerImage.setImageDrawable(drawable);
            p = 6;
        }
        else if (p == 6 && helpSolMaze == true)
        {
            drawFirst();
            Drawable drawable = convertMatToDrawable(to_draw);
            AnswerLevel.answerImage.setImageDrawable(drawable);
            p = 0;
        }
        else if (p == 7 && helpSolMaze == false)
        {
            drawAll();
            Drawable drawable = convertMatToDrawable(to_draw);
            AnswerLevel.answerImage.setImageDrawable(drawable);
            p = 0;
        }
    }

    public static Mat convertImageViewMat(ImageView v){
        Bitmap bm=((BitmapDrawable)v.getDrawable()).getBitmap();
        Mat mat = new Mat();
        Bitmap bmp32 = bm.copy(Bitmap.Config.ARGB_8888, true);
        Utils.bitmapToMat(bmp32, mat);
        return mat;
    }

    public static Drawable convertMatToDrawable(Mat tmp) {
        Bitmap bmp = null;
        bmp = Bitmap.createBitmap(tmp.cols(), tmp.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(tmp, bmp);
        Drawable d = new BitmapDrawable(bmp);
        return d;
    }

    public static void startProgram() {
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);    //Загружаем библиотеку OpenCV

        Imgcodecs imageCodecs = new Imgcodecs();         //Создаем экземпляр класса imagecodecs

        Mat src_gray = new Mat();                        //бинарное изображение
        Mat src;                                         //исходное изображение
        int thresh = 100;                                //пороговое значение

        //String file = "C://Users//79852//Desktop//Screens//maze.jpg"; //путь до изображения
        //src = Imgcodecs.imread(file);                  //загружаем изображение из файла
        // image to byte array
        src = convertImageViewMat(AnswerLevel.answerImage);

        if (src.empty()) {                               //обработка ошибки загрузки
            System.out.println("Could not open or find the image!\n");
            // предложить загрузить заново
        }
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2RGB); //конвертирование изображения
        Imgproc.cvtColor(src, src, Imgproc.COLOR_RGB2GRAY);
        Imgproc.GaussianBlur(src, src, new Size(15, 15), 0);
//Imgproc.threshold(src, src, 0, 255, Imgproc.THRESH_OTSU);
        Imgproc.adaptiveThreshold(src, src, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY_INV, 7, 2);
        Imgproc.cvtColor(src, src_gray, Imgproc.COLOR_GRAY2BGR);

        Mat canny_output = new Mat();
        Imgproc.Canny(src_gray, canny_output, thresh, thresh * 2);              //поиск границ
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();                         //массив контуров
        Mat hierarchy = new Mat();                                                       //иерархия контуров
        Imgproc.findContours(canny_output, contours, hierarchy, 0, 1);      //поиск контуров
        Mat res = Mat.zeros(canny_output.size(), CvType.CV_8UC3);                        //итоговое изображение с контурами

        double[] green = {0, 255, 0};

        List<Point> all_contours = new ArrayList<Point>();       //массив всех контуров
        for (MatOfPoint current_contour : contours) {            //заполнение массива
            all_contours.addAll(current_contour.toList());
        }

        for (Point p : all_contours) {
            res.put((int) p.y, (int) p.x, green);
            res.put((int) p.y - 1, (int) p.x, green);
            res.put((int) p.y - 1, (int) p.x - 1, green);
            res.put((int) p.y - 1, (int) p.x + 1, green);
            res.put((int) p.y + 1, (int) p.x, green);
            res.put((int) p.y + 1, (int) p.x - 1, green);
            res.put((int) p.y + 1, (int) p.x + 1, green);
            res.put((int) p.y, (int) p.x + 1, green);
            res.put((int) p.y, (int) p.x - 1, green);//прорисовка контуров
        }

        MatOfPoint all_cont_mat = new MatOfPoint();
        all_cont_mat.fromList(all_contours);

        Rect boundingRect = Imgproc.boundingRect(all_cont_mat);                //прямоугольник вокруг контура
        //Imgproc.rectangle(res, boundingRect, new Scalar(255, 0, 0));

        List<MatOfPoint> hull_list = new ArrayList<>();                        //выпуклая оболочка
        MatOfPoint contour = all_cont_mat;
        MatOfInt hull = new MatOfInt();
        Imgproc.convexHull(contour, hull);                                     //поиск выпуклой оболочки

        Point[] points_of_hull = new Point[hull.rows()];
        Point[] array_of_contour = contour.toArray();
        List<Integer> hull_ind = hull.toList();
        for (int i = 0; i < hull_ind.size(); i++) {
            points_of_hull[i] = array_of_contour[hull_ind.get(i)];
        }
        hull_list.add(new MatOfPoint(points_of_hull));

        for (int i = 0; i < hull_list.size(); i++) {
            Scalar color = new Scalar(0, 0, 255);
            Imgproc.drawContours(res, hull_list, i, color);                   //прорисовка оболочки
        }
        to_draw = res;

        Drawable drawable = convertMatToDrawable(to_draw);
        AnswerLevel.answerImage.setImageDrawable(drawable);

        System.out.println("ready Main");

        //MatOfByte matOfByte = new MatOfByte();
        //Imgcodecs.imencode(".jpg", res, matOfByte);        //перевод изображения в байтовую матрицу
        //byte[] byteArray = matOfByte.toArray();                //перевод байтовой матрицы в массив
        //InputStream in = new ByteArrayInputStream(byteArray);  //перевод в буферизованное изображение
        //BufferedImage bufImage = ImageIO.read(in);
        //JFrame frame = new JFrame();                           //создаем окно
        //frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));   //показвваем изображение
        MouseDemo md = new MouseDemo(AnswerLevel.answerImage, 400, 400);
        //frame.pack();
        //frame.setVisible(true);
    }
};