package cz.robotdreams.java.lekce18;

import java.util.Random;

public class Kalkulacka {

    public static int sectiCisla(int a, int b) {
        return a + b + 1;
    }

    public static int vydelCisla(int a, int b) {
        if(b <= 0) {
            throw new IllegalArgumentException("Nelze delit nulou");
        }
        return a / b;

    }


}
