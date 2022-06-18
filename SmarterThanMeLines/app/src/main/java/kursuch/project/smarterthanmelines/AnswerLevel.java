package kursuch.project.smarterthanmelines;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
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

import androidx.appcompat.app.AppCompatActivity;

public class AnswerLevel extends AppCompatActivity {

    public static ImageView answerImage;
    Button back_btn;
    Button answer_btn;

    AlertDialog.Builder ad;
    Context context;

    public static boolean helpTrue = false;
    public static boolean helpFalse = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_level);

        answerImage = findViewById(R.id.image_answer);
        answerImage.setImageDrawable(PhotoLevel.mImageView.getDrawable());

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(AnswerLevel.this, PhotoLevel.class);
                    startActivity(intent);
                    finish();
                    System.out.println("back");
                }catch (Exception e) {

                }
            }
        });

        answer_btn = findViewById(R.id.solution_btn);
        answer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainComputer.startProgram();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(AnswerLevel.this);
        builder.setTitle("Обратите внимание!")
                .setMessage("Сначала выберите начальную и конечную точку, между которыми надо найти путь. " +
                        "Затем для отображения решения по частями повторно нажимайте в любое место на картинке.")
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                helpTrue = true;
                            }
                        });
        AlertDialog alert = builder.create();

        AlertDialog.Builder builderFalse = new AlertDialog.Builder(AnswerLevel.this);
        builderFalse.setTitle("Обратите внимание!")
                .setMessage("Сначала выберите начальную и конечную точку, между которыми надо найти путь. " +
                        "Затем для отображения полного решения повторно нажмите в любое место на картинке.")
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                helpTrue = false;
                            }
                        });
        AlertDialog alertFalse = builder.create();

        context = AnswerLevel.this;
        String title = "Подсказки";
        String message = "Включить подсказки по решению лабиринта? Подсказки - отображение лабиринта по частям";
        String button1String = "Да";
        String button2String = "Нет";

        ad = new AlertDialog.Builder(context);
        ad.setTitle(title);  // заголовок
        ad.setMessage(message); // сообщение
        ad.setPositiveButton(button1String, new OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                MainComputer.helpSolMaze = true;
                Toast.makeText(context, "Подсказки включены",
                        Toast.LENGTH_LONG).show();
                System.out.println(MainComputer.helpSolMaze);
                if(MainComputer.helpSolMaze == true && helpTrue == false){
                    alert.show();
                }
            }
        });
        ad.setNegativeButton(button2String, new OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                MainComputer.helpSolMaze = false;
                Toast.makeText(context, "Подсказки отключены", Toast.LENGTH_LONG)
                        .show();
                if(MainComputer.helpSolMaze == false && helpFalse == false){
                    alertFalse.show();
                }
            }
        });
        ad.setCancelable(true);
        ad.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                MainComputer.helpSolMaze = false;
                Toast.makeText(context, "Подсказки отключены",
                        Toast.LENGTH_LONG).show();
            }
        });

        ad.show();

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void errorPath()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Не удалось построить путь!")
                .setMessage("Попробуйте еще раз.")
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}