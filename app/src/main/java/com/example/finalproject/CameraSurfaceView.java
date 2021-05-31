package com.example.finalproject;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 카메라 미리보기를 위한 서피스뷰 정의
 */
public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera camera = null;
    private int usingCamera;

    public CameraSurfaceView(Context context) {
        super(context);

        usingCamera = Camera.CameraInfo.CAMERA_FACING_BACK;//디폴트는 후면
        mHolder = getHolder();
        mHolder.addCallback(this);

    }

    public CameraSurfaceView(Context context, int cameraFacing) {
        super(context);

        mHolder = getHolder();
        mHolder.addCallback(this);

        usingCamera = cameraFacing;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open(usingCamera);

        try {

            //오토 포커싱. 이거 없으면 초점 안맞음
            Camera.Parameters params = camera.getParameters();
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            camera.setParameters(params);

            camera.setDisplayOrientation(90);//왠지 90도 돌아가있음


            camera.setPreviewDisplay(mHolder);
        } catch (Exception e) {
            Log.e("CameraSurfaceView", "Failed to set camera preview.", e);
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        camera.startPreview();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        //camera.release();
        camera = null;
    }

    public boolean capture(Camera.PictureCallback handler) {
        if (camera != null) {
            camera.takePicture(null, null, handler);
            return true;
        } else {
            return false;
        }
    }

}