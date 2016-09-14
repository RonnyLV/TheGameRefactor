package lv.autentica.training.game.domain.factory;

import java.util.Random;

public abstract class Factory<T> {
    public abstract T construct();

    protected String getRandomStringFromArray(String[] array) {
        int idx = new Random().nextInt(array.length);
        return (array[idx]);
    }

    protected int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
