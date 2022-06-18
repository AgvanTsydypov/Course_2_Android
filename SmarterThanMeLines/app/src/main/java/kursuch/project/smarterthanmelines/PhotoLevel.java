package kursuch.project.smarterthanmelines;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PhotoLevel extends AppCompatActivity {

    public static ImageView mImageView;
    Button mChooseBtn;
    Button back_btn;
    Button photo_btn;
    Button solution_btn;

    public static int oldWidth;
    public static int oldHeight;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private static final int CAMERA_REQUEST = 0;

    public static boolean isOpened = false;
    public static boolean photo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_level);

        AlertDialog.Builder builder = new AlertDialog.Builder(PhotoLevel.this);
        builder.setTitle("Обратите внимание!")
                .setMessage("Перед тем как получить решение лабиринта, вам следует выбрать лабиринт из галереи или сделать его фото " +
                        "(кнопки Gallery/Photo). Далее нажмите кнопку Answer." +
                        " Кнопка Answer будет не активна пока вы не выберите фото!")
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                isOpened = true;
                            }
                        });
        AlertDialog alert = builder.create();

        AlertDialog.Builder builderPhoto = new AlertDialog.Builder(PhotoLevel.this);
        builderPhoto.setTitle("Обратите внимание!")
                .setMessage("Чтобы добиться лучшего результата, фотографию необходимо сделать в хорошо освещенном помещении или со вспышкой, " +
                        "также избегайте теней на фото и резких перепадов света.")
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                photo = true;
                            }
                        });
        AlertDialog alertPhoto = builderPhoto.create();

        if(photo == false)
            alertPhoto.show();

        if(isOpened == false)
            alert.show();

        System.out.println("photolevel loaded");

        mImageView = findViewById(R.id.image_view);
        mChooseBtn = findViewById(R.id.galery_btn);

        mChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        pickImageFromGallery();
                    }
                } else {
                    pickImageFromGallery();
                }
            }
        });

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        solution_btn = findViewById(R.id.solution_btn);
        solution_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mImageView.getDrawable() != null) {
                        oldHeight = mImageView.getDrawable().getIntrinsicHeight();
                        oldWidth = mImageView.getDrawable().getIntrinsicWidth();
                        Intent intent = new Intent(PhotoLevel.this, AnswerLevel.class);
                        startActivity(intent);
                        finish();
                        System.out.println("go to answer level");
                    } else {
                        alert.show();
                        System.out.println("answer level error");
                    }
                } catch (Exception e) {
                    System.out.println("answer level error");
                }
            }
        });

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(PhotoLevel.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    System.out.println("back");
                }catch (Exception e) {

                }
            }
        });

        photo_btn = findViewById(R.id.photo_btn);
        photo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
                System.out.println("photoTaked");
            }
        });
    }

    private void takePicture() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
        System.out.println("photoTaked228");
    }

    @Override
    public void onBackPressed()
    {
        try {
            Intent intent = new Intent(PhotoLevel.this, MainActivity.class);
            startActivity(intent);
            finish();
            System.out.println("back");
        }catch (Exception e) {

        }
    }
    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
        System.out.println("selected");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this, "Permission denied. . . !", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            mImageView.setImageURI(data.getData());
            System.out.println("Photo in imageView");
        }
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            // Фотка сделана, извлекаем картинку
            Bitmap thumbnailBitmap = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(thumbnailBitmap);
        }
    }
}