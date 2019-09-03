package com.example.opencv_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.*;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.dnn.*;
import org.opencv.utils.Converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    CameraBridgeViewBase cameraBridgeViewBase;
    BaseLoaderCallback baseLoaderCallback;
    int counter = 0;


    boolean startYolo = false;
    boolean firstTimeYolo = false;

    Net tinyYolo;

    // function that make !Canny with the button that is created in the view
    public void YOLO(View Button) {
        if (startYolo == false) {



            startYolo = true;


            if (firstTimeYolo == false) {


                firstTimeYolo = true;
                String tinyYoloCfg = Environment.getExternalStorageDirectory() +
                                                                "/dnns/yolov3-tiny.cfg" ;
                String tinyYoloWeights = Environment.getExternalStorageDirectory() +
                                                                "/dnns/yolov3-tiny.weights";

                tinyYolo = Dnn.readNetFromDarknet(tinyYoloCfg, tinyYoloWeights);




            }
        }
        else {
            startYolo = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up cameraBridgeView
        cameraBridgeViewBase = (JavaCameraView)findViewById(R.id.CameraView);
        //set the visibility
        cameraBridgeViewBase.setVisibility(SurfaceView.VISIBLE);
        //set the listener
        cameraBridgeViewBase.setCvCameraViewListener(this);


        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        baseLoaderCallback = new BaseLoaderCallback(this) {
            @Override
            public void onManagerConnected(int status) {
                super.onManagerConnected(status);

                switch (status){

                    //successfully connect
                    case BaseLoaderCallback.SUCCESS: {
                        cameraBridgeViewBase.enableView();
                        break;
                    }
                    //default setting
                    default: {
                        super.onManagerConnected(status);
                        break;
                    }
                }
            }
        };



    } // end of onCreate()

    @Override
    public void onCameraViewStarted(int width, int height) {

    } // end of onCameraViewStarted()

    @Override
    public void onCameraViewStopped() {

    } // end of onCameraViewStopped()

    //OPTIONS SETTINGS 20~30 frame/sec
    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {

        /*******************************************************************************************
         *                                                                                         *
         *                          FLIP LEFT AND RIGHT + TURN INTO GRAY                           *
         *                                                                                         *
         ******************************************************************************************/
//        Mat frame = inputFrame.rgba();

//        if (counter % 2 == 0) {
//            //                    flip left and right
//            Core.flip(frame, frame, 1);
//            //                          change the image to gray
//            Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGBA2GRAY);
//
//
//        }
//
//        counter++;

        /******************************************************************************************/


        /*******************************************************************************************
         *                                                                                         *
         *                                     EDGE DETECTION                                      *
         *                                                                                         *
         ******************************************************************************************/
//        Mat frame = inputFrame.rgba();
//
//        if (startYolo == true) {
//            // CHANGE THE IMAGE TO GRAY
//            Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGBA2GRAY);
//
//            // Image blur
////        Imgproc.blur(frame, frame, new Size(3, 3));
//
//
//            Imgproc.Canny(frame, frame, 100, 80);
//        }





        /******************************************************************************************/


        /*******************************************************************************************
         *                                                                                         *
         *                                   YOLO IMPLEMENTATION                                   *
         *                                                                                         *
         ******************************************************************************************/


        Mat frame = inputFrame.rgba();

        if (startYolo == true) {

            //CONVERT RGB -> EX> [255, 0, 125] / 255 = [1, 0, .5]
            Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGBA2RGB);


            // BGR -> RGB
            Mat imageBlob = Dnn.blobFromImage(frame,
                    0.00392,
                    new Size(416,416),
                    new Scalar(0, 0, 0),
                    false,
                    false);


            tinyYolo.setInput(imageBlob);


            java.util.List<Mat> result = new java.util.ArrayList<Mat>(2);

            List<String> outBlobNames = new java.util.ArrayList<>();
            outBlobNames.add(0, "yolo_16");
            outBlobNames.add(1, "yolo_23");

            tinyYolo.forward(result, outBlobNames);


            float confThreshold = 0.3f;


            List<Integer> clsIds = new ArrayList<>();
            List<Float> confs = new ArrayList<>();
            List<Rect> rects = new ArrayList<>();




            for (int i = 0; i < result.size(); ++i) {

                //yolo layer
                Mat level = result.get(i);

                for (int j = 0; j < level.rows(); ++j) {
                    Mat row = level.row(j);
                    Mat scores = row.colRange(5, level.cols());

                    Core.MinMaxLocResult mm = Core.minMaxLoc(scores);



                    float confidence = (float)mm.maxVal;


                    Point classIdPoint = mm.maxLoc;



                    if (confidence > confThreshold) {
                        int centerX = (int)(row.get(0,0)[0] * frame.cols());
                        int centerY = (int)(row.get(0,1)[0] * frame.rows());
                        int width   = (int)(row.get(0,2)[0] * frame.cols());
                        int height  = (int)(row.get(0,3)[0] * frame.rows());


                        int left    = centerX - width  / 2;
                        int top     = centerY - height / 2;

                        clsIds.add((int)classIdPoint.x);
                        confs.add((float)confidence);




                        rects.add(new Rect(left, top, width, height));
                    }
                }
            }
            int ArrayLength = confs.size();

            if (ArrayLength>=1) {
                // Apply non-maximum suppression procedure.
                float nmsThresh = 0.2f;




                MatOfFloat confidences = new MatOfFloat(Converters.vector_float_to_Mat(confs));


                Rect[] boxesArray = rects.toArray(new Rect[0]);

                MatOfRect boxes = new MatOfRect(boxesArray);

                MatOfInt indices = new MatOfInt();



                Dnn.NMSBoxes(boxes, confidences, confThreshold, nmsThresh, indices);


                // Draw result boxes:
                int[] ind = indices.toArray();
                for (int i = 0; i < ind.length; ++i) {

                    int idx = ind[i];
                    Rect box = boxesArray[idx];

                    int idGuy = clsIds.get(idx);

                    float conf = confs.get(idx);


                    List<String> cocoNames = Arrays.asList("a person", "a bicycle", "a motorbike", "an airplane", "a bus", "a train", "a truck", "a boat", "a traffic light", "a fire hydrant", "a stop sign", "a parking meter", "a car", "a bench", "a bird", "a cat", "a dog", "a horse", "a sheep", "a cow", "an elephant", "a bear", "a zebra", "a giraffe", "a backpack", "an umbrella", "a handbag", "a tie", "a suitcase", "a frisbee", "skis", "a snowboard", "a sports ball", "a kite", "a baseball bat", "a baseball glove", "a skateboard", "a surfboard", "a tennis racket", "a bottle", "a wine glass", "a cup", "a fork", "a knife", "a spoon", "a bowl", "a banana", "an apple", "a sandwich", "an orange", "broccoli", "a carrot", "a hot dog", "a pizza", "a doughnut", "a cake", "a chair", "a sofa", "a potted plant", "a bed", "a dining table", "a toilet", "a TV monitor", "a laptop", "a computer mouse", "a remote control", "a keyboard", "a cell phone", "a microwave", "an oven", "a toaster", "a sink", "a refrigerator", "a book", "a clock", "a vase", "a pair of scissors", "a teddy bear", "a hair drier", "a toothbrush");



                    int intConf = (int) (conf * 100);



                    Imgproc.putText(frame,cocoNames.get(idGuy) + " " + intConf + "%",box.tl(),Core.FONT_HERSHEY_SIMPLEX, 2, new Scalar(255,255,0),2);




                    Imgproc.rectangle(frame, box.tl(), box.br(), new Scalar(255, 0, 0), 2);





                }
            }


        }// end of if statement




        return frame;
    }// end of onCameraFrame()



    //
    //
    // life cycles
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onResume() {
        super.onResume();

        //fail to load OpenCVLoader and make toast's
        if(!OpenCVLoader.initDebug()) {
            Toast.makeText(getApplicationContext(),
                    "baseLoaderCallback.FAIL",
                    Toast.LENGTH_LONG)
                    .show();
        }
        // successfully connected
        else {
            baseLoaderCallback.onManagerConnected(baseLoaderCallback.SUCCESS);
        }

    }// end of onResume()



    @Override
    protected void onPause() {
        super.onPause();
        // when it is the state of onPause
        // || and cameraBridgeViewBase is not null then disable the view
        if(cameraBridgeViewBase!=null) {
            cameraBridgeViewBase.disableView();
        }
    }// end of onPause()



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //when the app is killed
        // || and cameraBridgeViewBase is still working then disable the view
        if(cameraBridgeViewBase!=null) {
            cameraBridgeViewBase.disableView();
        }
    }// end of onDestory()


}// end of Activity
