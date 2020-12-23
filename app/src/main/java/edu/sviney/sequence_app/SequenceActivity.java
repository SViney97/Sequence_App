package edu.sviney.sequence_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SequenceActivity extends AppCompatActivity {
    private final int BLUE = 1;
    private final int RED = 2;
    private final int YELLOW = 3;
    private final int GREEN = 4;

    Button bRed, bBlue, bGreen, bYellow,fb , checkseq;
    TextView seqLength ;
    int score =0;
    int seqlengthcounter = 0;

    int[] gameSequence = new int[120];
    int[] selectedSequence = new int[120];
    int arrayIndex = 0, SeqLen = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence);

        bRed = findViewById(R.id.btnRed);
        bBlue = findViewById(R.id.btnBlue);
        bGreen = findViewById(R.id.btnGreen);
        bYellow = findViewById(R.id.btnYellow);
        seqLength = findViewById(R.id.tvSequenceLength);
        checkseq = findViewById(R.id.btnCheckSequence);

        //length of the sequence
        SeqLen = getIntent().getIntExtra("sequencelength",0);
        seqLength.setText( seqlengthcounter+"/"+SeqLen);

        //adding the sequence to the array & logging it in the logcat
        Bundle bundle = getIntent().getExtras();
        gameSequence = bundle.getIntArray("key");
        for (int i = 0; i< SeqLen; i++)
            Log.d("game sequence in activty 2", String.valueOf(gameSequence[i]));

    }

    public void DoCheck(View view) {
        //boolean check = selectedSequence.equals(gameSequence);
        if(seqlengthcounter < SeqLen )
        {
            Toast.makeText(this, "there is more to the sequence", Toast.LENGTH_SHORT).show();
        }
        else {
            int PlayerScore = getIntent().getIntExtra("GameScore", 0);
            score = score + PlayerScore;
            for (int i = 0; i < arrayIndex; i++) {
                if (gameSequence[i] == selectedSequence[i]) {

                    score = score + 1;
                    Intent Activity = new Intent(view.getContext(), MainActivity.class);

                    int nextLevel = Integer.valueOf(score);
                    Activity.putExtra("nextlevelscore", nextLevel);

                    startActivity(Activity);
                } else {

                    Intent Activity = new Intent(view.getContext(), GameOverActivity.class);
                    int FinalScore = Integer.valueOf(score);
                    Activity.putExtra("score", FinalScore);
                    startActivity(Activity);
                    break;
                }

            }
        }
    }
    public void doGreen(View view) {
        selectedSequence[arrayIndex++] = GREEN;
        seqlengthcounter = seqlengthcounter +1;
        seqLength.setText( seqlengthcounter+"/"+SeqLen);
    }

    public void doYellow(View view) {
        selectedSequence[arrayIndex++] = YELLOW;
        seqlengthcounter = seqlengthcounter +1;
        seqLength.setText( seqlengthcounter+"/"+SeqLen);
    }

    public void doRed(View view) {
        selectedSequence[arrayIndex++] = RED;
        seqlengthcounter = seqlengthcounter +1;
        seqLength.setText( seqlengthcounter+"/"+SeqLen);
    }

    public void DoBlue(View view) {
        selectedSequence[arrayIndex++] = BLUE;
        seqlengthcounter = seqlengthcounter +1;
        seqLength.setText( seqlengthcounter+"/"+SeqLen);
    }
}