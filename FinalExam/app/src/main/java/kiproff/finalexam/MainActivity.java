package kiproff.finalexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView editName = (TextView)findViewById(R.id.editName);
    TextView outputText = (TextView) findViewById(R.id.outputText);

    String pizzaName = "";
    String crustType = "";
    String pizzaUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void makePizza() {

        pizzaName = editName.getText().toString();

        RadioGroup crustGroup = (RadioGroup) findViewById(R.id.crustGroup);
        int crustId = crustGroup.getCheckedRadioButtonId();
        switch (crustId) {
            case -1:
                crustType = "N/A";
                pizzaUrl = "http://localeboulder.com/";
                break;
            case R.id.thinBtn:
                crustType = "thin";
                pizzaUrl = "http://beaujos.com/";
                break;
            case R.id.thickBtn:
                crustType = "thick";
                pizzaUrl = "http://oldchicago.com/";
                break;
            default:
                crustType = "N/A";
                pizzaUrl = "http://localeboulder.com/";
        }

        crustType = editName.getText().toString();

        outputText.setText(pizzaName + " is a" + crustType + " crust pizza!");

    }

    public void getPizzaName(){
        pizzaName = editName.getText().toString();
    }

    public void findPizza(){
        Intent intent = new Intent(this, GetPizza.class);

        intent.putExtra("pizzaUrl", pizzaUrl);

        startActivity(intent);
    }
}
