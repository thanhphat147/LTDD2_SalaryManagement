package tdc.edu.vn.drawingfun;


import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    DrawView drawingView;
    ImageButton btnNew, btnDraw, btnErase, btnSave, btnSmall, btnMedium, btnLarge;
    Button btnRed, btnGreen, btnBlue, btnYellow, btnRadian, btnBlack, btnPurple, btnOrange;
    float smallBrush, mediumBrush, largeBrush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);
        final Dialog brushDialog = new Dialog(MainActivity.this);
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brushDialog.setContentView(R.layout.brush_choose);
                brushDialog.setTitle("Brush size:");
                btnSmall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drawingView.setBrushSize(smallBrush);
                        drawingView.setLastBrushSize(smallBrush);
                        brushDialog.dismiss();
                    }
                });

                btnMedium.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drawingView.setBrushSize(mediumBrush);
                        drawingView.setLastBrushSize(mediumBrush);
                        brushDialog.dismiss();
                    }
                });

                btnLarge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drawingView.setBrushSize(largeBrush);
                        drawingView.setLastBrushSize(largeBrush);
                        brushDialog.dismiss();
                    }
                });
                brushDialog.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Bạn muốn lưu hình ảnh?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        drawingView.setDrawingCacheEnabled(true);
                        String imageSaved = MediaStore.Images.Media.insertImage(getContentResolver(), drawingView.getDrawingCache(), UUID.randomUUID().toString() + ".png", "drawing");
                        if (imageSaved != null) {
                            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Opp, Image not saved", Toast.LENGTH_LONG).show();
                        }
                        drawingView.destroyDrawingCache();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Yellow", Toast.LENGTH_SHORT).show();
            }
        });

        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Green", Toast.LENGTH_SHORT).show();
            }
        });

        btnPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Purple", Toast.LENGTH_SHORT).show();
            }
        });

        btnRadian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Radian", Toast.LENGTH_SHORT).show();
            }
        });

        btnOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Orange", Toast.LENGTH_SHORT).show();
            }
        });

        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Blue", Toast.LENGTH_SHORT).show();
            }
        });

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Red", Toast.LENGTH_SHORT).show();
            }
        });

        btnBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Black", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl() {
        drawingView = findViewById(R.id.drawing1);
        btnNew = findViewById(R.id.btnNew);
        btnDraw = findViewById(R.id.btnDraw);
        btnErase = findViewById(R.id.btnErase);
        btnSave = findViewById(R.id.btnSave);
        btnSmall = findViewById(R.id.small_brush);
        btnMedium = findViewById(R.id.medium_brush);
        btnLarge = findViewById(R.id.large_brush);
        btnBlack = findViewById(R.id.btnBlack);
        btnRed = findViewById(R.id.btnred);
        btnBlue = findViewById(R.id.btnblue);
        btnOrange = findViewById(R.id.btnOrange);
        btnRadian = findViewById(R.id.btnRadian);
        btnPurple = findViewById(R.id.btnPurple);
        btnGreen = findViewById(R.id.btngreen);
        btnYellow = findViewById(R.id.btnyellow);
    }

}
