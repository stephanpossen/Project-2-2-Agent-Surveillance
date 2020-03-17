package Agent;

import java.util.Random;
public class Individual {
    private String[] stringArray = new String[11];
    private int fit;
    private static String[] goalString = {"h", "e", "l", "l", "o", " ", "w", "o", "r", "l", "d"};
    private static String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", " "};

    public Individual() {
        Random rnd = new Random();
        for (int i = 0; i < 11; i++) {
            int letterNb = rnd.nextInt(27);
            stringArray[i] = alphabet[letterNb];

            if (stringArray[i].equals(goalString[i]))
                fit++;
        }
    }

    public Individual(String[] newStringArray) {
        stringArray = newStringArray;
        for (int i = 0; i < 11; i++) {
            if (stringArray[i].equals(goalString[i]))
                fit++;
        }
    }

    public String getLetter() {
        Random rnd = new Random();
        int letterNb = rnd.nextInt(27);
        return alphabet[letterNb];
    }

    public void newFit() {
        fit = 0;
        for (int i = 0; i < 11; i++) {
            if (stringArray[i].equals(goalString[i]))
                fit++;
        }
    }

    public int getFitness() {
        return fit;
    }

    public String[] getStringArray() {
        return stringArray;
    }

    protected Individual clone() {
        String[] newStringarea = new String[stringArray.length];
        for (int i = 0; i < newStringarea.length; i++) {
            newStringarea[i] = stringArray[i];
        }
        return new Individual(newStringarea);
    }
}
