package com.example.numberbaseball;

public class arrayBasic {
    public static void main(String[] args) {
        int score1 = 100;
        int score2 = 90;
        int score3 = 80;
        int score4 = 70;
        int score5 = 60;

        int tot = score1 +score2+score3+score4+score5;
        int ave = tot/5;

        int [] score = {100,90,80,70,60,50};
        int [] scoreArray = new int[5];
        scoreArray[0] = 100;
        scoreArray[1] = 90;
        scoreArray[2] = 80;
        scoreArray[3] = 70;
        scoreArray[4] = 60;

        int [] number;
        number = new int[]{100,90,80,70,60};

        int total=0;
        for( int i = 0; i < scoreArray.length; i++){
            total += scoreArray[i];
        }
        int ave1 = total /scoreArray.length;
    }
}
