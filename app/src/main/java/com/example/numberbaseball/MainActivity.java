package com.example.numberbaseball;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.params.TonemapCurve;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int[] comNumbers = new int[3];
    int inputTextCount = 0;
    int hitCount = 1;

    TextView[] inputTextView = new TextView[3];
    Button[] numButton = new Button[10];

    ImageButton backSpaceButton;
    ImageButton hitButton;

    TextView resultTextView;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        comNumbers = getComNumbers();

        for (int i = 0; i < inputTextView.length; i++) {
            inputTextView[i] = findViewById(R.id.input_text_0 + i);

        }

        for (int i = 0; i < numButton.length; i++) {
            numButton[i] = findViewById(R.id.num_button_0 + i);
        }

        backSpaceButton = findViewById(R.id.back_space_button);
        hitButton = findViewById(R.id.hit_button);
        resultTextView = findViewById(R.id.result_text_view);
        scrollView = findViewById(R.id.scroll_view);

        for(Button getNumButton : numButton){
            getNumButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(inputTextCount < 3) {
                        Button button = findViewById(view.getId());
                        inputTextView[inputTextCount].setText(button.getText().toString());
                        button.setEnabled(false);
                        inputTextCount++;
                    }else {
                        Toast.makeText(getApplicationContext(), "히트 버튼을 눌러주세요", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        backSpaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputTextCount > 0){
                    int buttonEnableCount = Integer.parseInt(inputTextView[inputTextCount - 1].getText().toString());
                    numButton[buttonEnableCount].setEnabled(true);
                    inputTextView[inputTextCount-1].setText("");
                    inputTextCount--;
                 }else {
                    Toast.makeText(getApplicationContext(), "숫자를 입력을 해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputTextCount < 3){
                    Toast.makeText(getApplicationContext(), "숫자를 입력을 해주세요", Toast.LENGTH_SHORT).show();
                }else{
                    int[] userNumbers = new int[3];
                    for (int i = 0; i <userNumbers.length ; i++) {
                        userNumbers[i] = Integer.parseInt(inputTextView[i].getText().toString());
                    }
                    int[] countCheck = new int[2];
                    countCheck = getCountCheck(comNumbers, userNumbers);
                    Log.e("hitButton","countCheck = S : " + countCheck[0] + "  B : " + countCheck[1]);

                    String resultCount;
                    if(countCheck[0] == 3){
                        resultCount = "1 [" + userNumbers[0] + " " + userNumbers[1] + " " +  userNumbers[2] + "] 아웃 - 축하합니다." ;
                    } else {
                        resultCount = "1 [" + userNumbers[0] + " " + userNumbers[1] + " " +  userNumbers[2] + "] S : "
                                + countCheck[0] + " B : " + countCheck[1];

                    }
                    if(hitCount == 1){
                        resultTextView.setText(resultCount  + "\n");
                    } else {
                        resultTextView.append(resultCount + "\n");
                    }
                    if(countCheck[0] == 3){
                        hitCount = 1;
                        comNumbers = getComNumbers();
                    }else {
                        hitCount++;
                    }
                    

                    scrollView.fullScroll(View.FOCUS_DOWN);

                    inputTextCount = 0;
                    for (TextView textView : inputTextView) {
                        textView.setText("");
                    }

                    for(Button button : numButton){
                        button.setEnabled(true);
                    }
                }
            }
        });

    }

    private int[] getCountCheck(int[] comNumbers, int[] userNumbers) {
        int[] setCount = new int[2];
        for (int i = 0; i <comNumbers.length ; i++) {
            for (int j = 0; j < userNumbers.length; j++) {
                if(comNumbers[i] == userNumbers[j] && i == j){
                    setCount[0]++;
                }else if(comNumbers[i] == userNumbers[j] && i != j){
                    setCount[1]++;
                }
            }
        }

        return setCount;
    }

    public int[] getComNumbers(){
        int[] setComNumbers = new int[3];

        for (int i = 0; i < setComNumbers.length; i++) {
            setComNumbers[i] = new Random().nextInt(10);
            for (int j = 0; j < i; j++) {

                if(setComNumbers[i] == setComNumbers[j]){
                    i--;
                    break;
                }
            }
        }
        Log.e("setComNumbers","setComNumbers" + setComNumbers[0] + "," + setComNumbers[1] + "," + setComNumbers[2]);
        return setComNumbers;
    }
}
