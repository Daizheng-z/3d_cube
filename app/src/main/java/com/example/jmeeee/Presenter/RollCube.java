package com.example.jmeeee.Presenter;

import java.util.Random;

public class RollCube {

    //打乱魔方

    public static void rollcube() {
        String formulas = "RLUFBDXYZrlufbdxyz";
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            char formula = formulas.charAt(random.nextInt(12));
            for (int j = 0; j < 30; j++) {
                RotateMethod.rotate(formula);
            }
            RotateMethod.rotateArray(formula);

        }
    }

}
