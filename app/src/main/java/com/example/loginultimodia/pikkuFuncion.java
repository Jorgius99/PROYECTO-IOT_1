package com.example.loginultimodia;


import android.util.Log;

import timber.log.Timber;

public class pikkuFuncion {
    public interface MovementListener {

        void onCaida(int caida);

        void onAccelX(float accelX);

        void onAccelY(float accelY);

        void onAccelZ(float accelZ);

        void onRest();

    }

    private boolean isMoving;
    private boolean isRest;

    private boolean isOverThreshold = false;
    private long lastTimeMovementDetected = System.currentTimeMillis();
    private final MovementListener movementListener;
    private final float thresholdStep;
    private  float thresholdTime = 300f;
    private final float thresholdCaida;
    private final long timeBeforeDeclaringStationary;
    private final long timeBeforeDeclaringRest;
    private float EWMA = 0.1f;
    private int steps = 0;
    private int jumps = 0;
    private int caidas = 0;
    private float accelCaida = 0;
    private float accelX = 0;
    private float accelY = 0;
    private float accelZ = 0;
    private final float averageStepDistance = 0.70f;
    private final float[] gravity = new float[]{1, 1, 1};
    private final float[] linear_acceleration = new float[3];
    private int calibratedCountData = 0;
    private String csvData = "";
    private static final int TIME_BEFORE_DECLARING_STATIONARY = 2000;
    private static final int TIME_BEFORE_DECLARING_REST= 2000;

    public pikkuFuncion(MovementListener movementListener) {
        this(0.15f, 0.5f, TIME_BEFORE_DECLARING_STATIONARY, TIME_BEFORE_DECLARING_REST, movementListener);
    }

    public pikkuFuncion(float thresholdStep, float thresholdCaida, long timeBeforeDeclaringStationary,
                        long timeBeforeDeclaringRest, MovementListener movementListener) {
        this.movementListener = movementListener;
        this.thresholdStep = thresholdStep;
        this.thresholdCaida = thresholdCaida;
        this.timeBeforeDeclaringStationary = timeBeforeDeclaringStationary;
        this.timeBeforeDeclaringRest = timeBeforeDeclaringRest;
    }

    public void setDataAccelerometer(float x, float y, float z) {
        setLinearAcceleration(x, y, z);

        float accelCurrent = (float) Math.sqrt(Math.pow(linear_acceleration[0], 2)
                + Math.pow(linear_acceleration[1], 2) + Math.pow(linear_acceleration[2], 2));
        accelX=linear_acceleration[0];
        accelY=linear_acceleration[1];
        accelZ=linear_acceleration[2];

        movementListener.onAccelX(accelX);
        movementListener.onAccelY(accelY);
        movementListener.onAccelZ(accelZ);
        accelCaida=accelCurrent; //media de aceleración al caer (tomando 5 medidas con precision de 4 cifras decimales)==1.5m/s^2;

        /*if(accelCaida>){
            caidas++;
            movementListener.onCaida(caidas);
        }*/

        float beta = 0.2f;
        EWMA = (1 - beta) * EWMA + beta * accelCurrent;

        if (calibratedCountData < 20) {
            calibratedCountData++;
            return;
        }

        float delta = accelCurrent - EWMA;
<<<<<<< HEAD
        if (delta > thresholdStep && !isOverThreshold && (System.currentTimeMillis() - lastTimeMovementDetected) > thresholdTime) {
=======
        if (delta > thresholdStep /* && !isOverThreshold */&& (System.currentTimeMillis() - lastTimeMovementDetected) > thresholdTime) {
>>>>>>> weonao
            isOverThreshold = true;
            lastTimeMovementDetected = System.currentTimeMillis();
            isMoving = true;
            if (delta > thresholdCaida) {
                thresholdTime = 1000f;
                caidas++;
<<<<<<< HEAD
                Log.d("CAIDASCONTADAS", ""+caidas);
=======
                Log.d("CAIDAASSSSS", ""+caidas);
>>>>>>> weonao
                movementListener.onCaida(caidas);
            }
        }
    }

    public void onCaidaServicio(){

    }

    private void setLinearAcceleration(float x, float y, float z) {
        final float alpha = 0.8f;
        gravity[0] = alpha * gravity[0] + (1 - alpha) * x;
        gravity[1] = alpha * gravity[1] + (1 - alpha) * y;
        gravity[2] = alpha * gravity[2] + (1 - alpha) * z;
        linear_acceleration[0] = x - gravity[0];
        linear_acceleration[1] = y - gravity[1];
        linear_acceleration[2] = z - gravity[2];
    }

    public void setDataAngles(float xy, float zy, float xz) {
        if (Math.abs(xy) > 30 || Math.abs(zy) > 30 ) {
            long timeDelta = (System.currentTimeMillis() - lastTimeMovementDetected);
            if (timeDelta > timeBeforeDeclaringRest) {
                isRest = true;
                movementListener.onRest();
            }
        } else {
            //  movementListener.onStand();
            isRest = false;
        }
    }

}
