# SelectCutPicture
（系统相册／照相机）图片选择与裁剪

使用：

      1.ImageUtils.showImagePickDialog(this);
      2.
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

权限：

    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
