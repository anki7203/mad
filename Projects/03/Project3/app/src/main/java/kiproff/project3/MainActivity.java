package kiproff.project3;

        import android.app.Activity;
        import android.content.Context;
        import android.graphics.Color;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.TextView;
        import android.widget.Toast;

        import kiproff.project3.R;


public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView count;
    boolean activityRunning;
    boolean reset = false;
    boolean stopCounting = false;
    Integer zeroed = 0;
    Integer feetValue = 0;
    Integer inchValue = 0;
    Integer totSteps = 0;
    Integer curSteps = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = (TextView) findViewById(R.id.count);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Step sensor not detected on device.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (activityRunning && !reset && !stopCounting) {
            curSteps = Math.round(event.values[0] - zeroed);
            count.setText(String.valueOf(Math.round(event.values[0] - zeroed)));
            calcPercentage();
        }else if(stopCounting){
            count.setText(String.valueOf(totSteps));
        }else{
            zeroed = Math.round(event.values[0]);
            reset = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void footballClicked(View view) {
        ImageButton footballBtn = (ImageButton) findViewById(R.id.footballBtn);
        footballBtn.setBackgroundColor(Color.parseColor("#cbcbcb"));
        ImageButton pyramidBtn = (ImageButton) findViewById(R.id.pyramidBtn);
        pyramidBtn.setBackgroundColor(Color.parseColor("#dddddd"));
        ImageButton earthBtn = (ImageButton) findViewById(R.id.earthBtn);
        earthBtn.setBackgroundColor(Color.parseColor("#dddddd"));

        EditText feetText = (EditText) findViewById(R.id.feetInput);
        if (!feetText.getText().toString().equals("")) {
            feetValue = Integer.parseInt(feetText.getText().toString());
        }
        EditText inchText = (EditText) findViewById(R.id.inchInput);
        if (!feetText.getText().toString().equals("")) {
            inchValue = Integer.parseInt(inchText.getText().toString());
        }


        if (feetValue != 0) {
            Double strideDub = (((feetValue * 12) + inchValue) * 2.54) * 0.414;

            Integer strideLength = strideDub.intValue();
            Integer distance = 9144;

            totSteps = distance / strideLength;

            TextView totalSteps = (TextView) findViewById(R.id.totalSteps);
            totalSteps.setText(totSteps.toString());

            reset = true;
            stopCounting = false;
            Toast.makeText(getApplicationContext(), "Initializing...", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please enter your height!", Toast.LENGTH_LONG).show();
        }
    }

    public void pyramidClicked(View view) {
        ImageButton footballBtn = (ImageButton) findViewById(R.id.footballBtn);
        footballBtn.setBackgroundColor(Color.parseColor("#dddddd"));
        ImageButton pyramidBtn = (ImageButton) findViewById(R.id.pyramidBtn);
        pyramidBtn.setBackgroundColor(Color.parseColor("#cbcbcb"));
        ImageButton earthBtn = (ImageButton) findViewById(R.id.earthBtn);
        earthBtn.setBackgroundColor(Color.parseColor("#dddddd"));


        EditText feetText = (EditText) findViewById(R.id.feetInput);
        if (!feetText.getText().toString().equals("")) {
            feetValue = Integer.parseInt(feetText.getText().toString());
        }
        EditText inchText = (EditText) findViewById(R.id.inchInput);
        if (!feetText.getText().toString().equals("")) {
            inchValue = Integer.parseInt(inchText.getText().toString());
        }


        if (feetValue != 0) {
            Double strideDub = (((feetValue * 12) + inchValue) * 2.54) * 0.414;

            Integer strideLength = strideDub.intValue();
            Integer distance = 92160;

            totSteps = distance / strideLength;

            TextView totalSteps = (TextView) findViewById(R.id.totalSteps);
            totalSteps.setText(totSteps.toString());

            reset = true;
            stopCounting = false;
            Toast.makeText(getApplicationContext(), "Initializing...", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please enter your height!", Toast.LENGTH_LONG).show();
        }
    }

    public void earthClicked(View view) {
        ImageButton footballBtn = (ImageButton) findViewById(R.id.footballBtn);
        footballBtn.setBackgroundColor(Color.parseColor("#dddddd"));
        ImageButton pyramidBtn = (ImageButton) findViewById(R.id.pyramidBtn);
        pyramidBtn.setBackgroundColor(Color.parseColor("#dddddd"));
        ImageButton earthBtn = (ImageButton) findViewById(R.id.earthBtn);
        earthBtn.setBackgroundColor(Color.parseColor("#cbcbcb"));

        EditText feetText = (EditText) findViewById(R.id.feetInput);
        if (!feetText.getText().toString().equals("")) {
            feetValue = Integer.parseInt(feetText.getText().toString());
        }
        EditText inchText = (EditText) findViewById(R.id.inchInput);
        if (!feetText.getText().toString().equals("")) {
            inchValue = Integer.parseInt(inchText.getText().toString());
        }


        if (feetValue != 0) {
            Double strideDub = (((feetValue * 12) + inchValue) * 2.54) * 0.414;

            Integer strideLength = strideDub.intValue();
            strideLength /= 10; //compensation for massive distance integer
            Integer distance = 400750000;

            totSteps = distance / strideLength;

            TextView totalSteps = (TextView) findViewById(R.id.totalSteps);
            totalSteps.setText(totSteps.toString());

            reset = true;
            stopCounting = false;
            Toast.makeText(getApplicationContext(), "Initializing...", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please enter your height!", Toast.LENGTH_LONG).show();
        }
    }

    public void calcPercentage(){
        Long percentage = Math.round(curSteps * 100.0/totSteps);
        TextView percentText = (TextView) findViewById(R.id.percentText);
        if( percentage < 100){
            percentText.setText(String.valueOf(percentage));
        }else{
            percentText.setText("Done!");
            stopCounting = true;
        }
    }
}
