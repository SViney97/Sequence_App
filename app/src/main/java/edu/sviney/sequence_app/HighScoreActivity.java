package edu.sviney.sequence_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HighScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        final ListView lv = (ListView) findViewById(R.id.LVList);
        DatabaseHandler db = new DatabaseHandler(this);

        db.emptyHiScores();     // empty table

        // Inserting hi scores
        Log.i("Insert: ", "Inserting ..");


        db.addHiScore(new HiScore("20 NOV 2020", "Fred", 1));
        db.addHiScore(new HiScore("20 NOV 2020", "John", 2));
        db.addHiScore(new HiScore("22 NOV 2020", "Gemma", 7));
        db.addHiScore(new HiScore("30 NOV 2020", "Ava", 8));
        db.addHiScore(new HiScore("02 DEC 2020", "Cassie", 10));


        // Reading all scores
        Log.i("Reading: ", "Reading all scores..");
        List<HiScore> hiScores = db.getAllHiScores();


        for (HiScore hs : hiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // Writing HiScore to log
            Log.i("Score: ", log);
        }

        Log.i("divider", "==============================================================");

        HiScore singleScore = db.getHiScore(5);
        Log.i("High Score 5 is by ", singleScore.getPlayer_name() + " with a score of " +
                singleScore.getScore());

        Log.i("divider", "==============================================================");

        // Calling SQL statement
        List<HiScore> top5HiScores = db.getTopFiveScores();

        for (HiScore hs : top5HiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // Writing HiScore to log
            Log.i("Score: ", log);
        }
        //hs contains the 5th highest score
        Log.i("divider", "==============================================================");
        HiScore hiScore = top5HiScores.get(top5HiScores.size() -1);
        Log.i("fifth highest score: ", String.valueOf(hiScore.getScore()));


        String PlayerName = getIntent().getStringExtra("playername");
        int Score = getIntent().getIntExtra("Score",0);

        int myCurrentScore = 40;
        //if the 5th high score < current score then insert new score
        if(hiScore.getScore() < myCurrentScore){
            db.addHiScore(new HiScore("17 DEC 2020", PlayerName, Score));
        }

        Log.i("divider", "==============================================================");

        // Calling SQL statement
        //List<HiScore> top5HiScores = db.getTopFiveScores();

        top5HiScores = db.getTopFiveScores();
        for (HiScore hs : top5HiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // Writing HiScore to log

            Log.i("Score: ", log);
        }


        Log.i("divider", "==============================================================");

        // Calling SQL statement
        //List<HiScore> top5HiScores = db.getTopFiveScores();

        top5HiScores = db.getTopFiveScores();
        for (HiScore hs : top5HiScores) {
            String log =
                    "Player: " + hs.getPlayer_name() + "    -+-    " +
                            "Score: " + hs.getScore();

            // Writing HiScore to log
            itemsList.add(log);
            Log.i("Score: ", log);
        }

        ArrayAdapter<HiScore> adapter = new ArrayAdapter<HiScore>(this, android.R.layout.simple_list_item_1,itemsList );
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }public List itemsList = new ArrayList();
    public void doPlayAgain(View view) {
        Intent Activity = new Intent(view.getContext(),MainActivity.class);
        startActivity(Activity);
    }
}