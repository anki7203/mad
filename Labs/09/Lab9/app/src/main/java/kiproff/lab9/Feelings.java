package kiproff.lab9;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Feelings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings);
    }


    public void findMood(View view) {
        //textView
        TextView myOutput = (TextView) findViewById(R.id.output_text);

        //spinner
        Spinner moodSpinner = (Spinner) findViewById(R.id.spinner);
        String moodValue = String.valueOf(moodSpinner.getSelectedItem());

        //editText
        EditText name = (EditText) findViewById(R.id.name_text);
        final String nameValue = name.getText().toString();

        //toggle button
        ToggleButton toggle = (ToggleButton) findViewById(R.id.chi_toggle);
        boolean chi = toggle.isChecked();
        final String chiString;
        if (chi) {
            chiString = "n up";
        } else {
            chiString = " down";
        }

        //radio buttons
        RadioGroup yoga = (RadioGroup) findViewById(R.id.yoga_type);
        final String yogaType;
        int yoga_id = yoga.getCheckedRadioButtonId();
        switch (yoga_id) {
            case -1:
                yogaType = "Naan";
                break;
            case R.id.yoga_btn1:
                yogaType = "Yin";
                break;
            case R.id.yoga_btn2:
                yogaType = "Bikram";
                break;
            case R.id.yoga_btn3:
                yogaType = "Hatha";
                break;
            default:
                yogaType = "Naan";
        }
        //check boxes
        String checkbox_string = "";

        CheckBox check1 = (CheckBox) findViewById(R.id.checkBox1);
        boolean checked1 = check1.isChecked();
        if (checked1) {
            checkbox_string += " sarcastic";
        }

        CheckBox check2 = (CheckBox) findViewById(R.id.checkBox2);
        boolean checked2 = check2.isChecked();
        if (checked2) {
            checkbox_string += " conservative";
        }

        CheckBox check3 = (CheckBox) findViewById(R.id.checkBox3);
        boolean checked3 = check3.isChecked();
        if (checked3) {
            checkbox_string += " secretive";
        }

        CheckBox check4 = (CheckBox) findViewById(R.id.checkBox4);
        boolean checked4 = check4.isChecked();
        if (checked4) {
            checkbox_string += " enlightened";
        }


        //switch
        String meditate_string = "";
        Switch meditate_switch = (Switch) findViewById(R.id.switch1);
        boolean meditate = meditate_switch.isChecked();
        if (meditate) {
            meditate_string = " and meditates";
        }


        //set text in textView
        myOutput.setText(nameValue + " is a" + chiString + checkbox_string + " person" + " that does " + yogaType + " yoga" + meditate_string);

        // image
        ImageView emotion = (ImageView) findViewById(R.id.output_image);
        int image = R.drawable.happy;
        if (moodValue.equals("happy")) {
            image = R.drawable.happy;
        } else if (moodValue.equals("sad")) {
            image = R.drawable.sad;
        } else if (moodValue.equals("confused")) {
            image = R.drawable.confused;
        } else if (moodValue.equals("angry")) {
            image = R.drawable.angry;
        } else image = R.drawable.happy;
        emotion.setImageResource(image);

    }
}
