package exo3;

import java.util.Random;

public class RandomName {
    
    private String letters = "abcdefghijklmnopqrstuvwxyz";
    
    public String generateName() {
        Random random = new Random();
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            name.append(letters.charAt(random.nextInt(letters.length())));
        }
        return name.toString(); 
    }
}