package kiproff.lab10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class find extends AppCompatActivity {

    private fpsFinder myFpsFinder = new fpsFinder();

    public void findGame(View view){
        Spinner genreSpinner = (Spinner)findViewById(R.id.spinner);
        String genre = String.valueOf(genreSpinner.getSelectedItem());
        myFpsFinder.setGameGenre(genre);
        String suggestedGameTitle = myFpsFinder.getGameGenre();
        String suggestedGameUrl = myFpsFinder.getGameUrl();
        System.out.println(suggestedGameTitle);
        System.out.println(suggestedGameUrl);

        Intent intent = new Intent(this, receive_game.class);

        intent.putExtra("gameTitle", suggestedGameTitle);
        intent.putExtra("gameUrl", suggestedGameUrl);

        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
    }
}
