package com.joevess.selectcutpicture.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.joevess.selectcutpicture.R;
import com.joevess.selectcutpicture.utils.ImageUtils;

public class MainActivity extends Activity implements View.OnClickListener{
    private ImageView iv_addphoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_addphoto = (ImageView) findViewById(R.id.iv_addphoto);
        iv_addphoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_addphoto:
                ImageUtils.showImagePickDialog(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case ImageUtils.REQUEST_CODE_FROM_ALBUM: {

                if (resultCode == RESULT_CANCELED) {   //取消操作
                    return;
                }

                Uri imageUri = data.getData();
                if(imageUri!=null){
                    ImageUtils.copyImageUri(this,imageUri);
                    ImageUtils.cropImageUri(this, ImageUtils.getCurrentUri(), 200, 200);
                }
                break;
            }
            case ImageUtils.REQUEST_CODE_FROM_CAMERA: {

                if (resultCode == RESULT_CANCELED) {     //取消操作
//                    if(ImageUtils.getCurrentUri()!=null){
//                        ImageUtils.deleteImageUri(this, ImageUtils.getCurrentUri());   //删除Uri
//                    }
                    return;
                }
                if(ImageUtils.getCurrentUri()!=null){
                    ImageUtils.cropImageUri(this, ImageUtils.getCurrentUri(), 200, 200);
                }
                break;
            }
            case ImageUtils.REQUEST_CODE_CROP: {

                if (resultCode == RESULT_CANCELED) {     //取消操作
                    return;
                }

                Uri imageUri = ImageUtils.getCurrentUri();
                if (imageUri != null) {
                    iv_addphoto.setImageURI(imageUri);
                }
                break;
            }
            default:
                break;
        }
    }
}
