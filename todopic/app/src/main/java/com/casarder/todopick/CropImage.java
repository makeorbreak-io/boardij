package com.casarder.todopick;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class CropImage extends AppCompatActivity {
    public static final int REQUEST_CODE_CROP_IMAGE   = 0x3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);
       // runCropImage();
    }


    @Override
    protected void onResume() {
        super.onResume();
        runCropImage();

    }

    private void runCropImage() {
        Bitmap image = getIntent().getParcelableExtra("BITMAP");
        // create explicit intent
        Intent intent = new Intent(this, com.casarder.todopick.crop.CropImage.class);

        // tell CropImage activity to look for image to crop
        intent.putExtra("BITMAP", image);
        // allow CropImage activity to rescale image
        intent.putExtra(com.casarder.todopick.crop.CropImage.SCALE, true);

        // if the aspect ratio is fixed to ratio 3/2
        intent.putExtra(com.casarder.todopick.crop.CropImage.ASPECT_X, 3);
        intent.putExtra(com.casarder.todopick.crop.CropImage.ASPECT_Y, 2);

        // start activity CropImage with certain request code and listen
        // for result
        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode!= RESULT_OK){
           finish();
        }
        else {
            TextRecognizer txtRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
            if (!txtRecognizer.isOperational()) {
                // Shows if your Google Play services is not up to date or OCR is not supported for the device
            } else {
                Bitmap imageBitmap = (Bitmap) data.getExtras().getParcelable("data");
                // Set the bitmap taken to the frame to perform OCR Operations.
                Frame frame = new Frame.Builder().setBitmap(imageBitmap).build();
                SparseArray items = txtRecognizer.detect(frame);
                StringBuilder strBuilder = new StringBuilder();
                for (int i = 0; i < items.size(); i++) {
                    TextBlock item = (TextBlock) items.valueAt(i);
                    strBuilder.append(item.getValue());
                    strBuilder.append("\n");
                    // The following Process is used to show how to use lines & elements as well
                    for (Text line : item.getComponents()) {
                        //extract scanned text lines here
                        Log.v("lines", line.getValue());
                        for (Text element : line.getComponents()) {
                            //extract scanned text words here
                            Log.v("element", element.getValue());
                        }
                    }
                }
                Intent intent = new Intent(this, ConfirmTextActivity.class);
                intent.putExtra("result", strBuilder.toString());
                startActivity(intent);
            }
        }

        }

}
