import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Animals {
    public static void main(String[] args) {
    
        try {
            File file = new File("Animals.txt");
            Scanner fileScanner = new Scanner(file);
            Scanner input = new Scanner(System.in);
            
            System.out.println("Welcome to the animal guessing game! You will get a random letter, and you have to guess animals that start with that letter. If you guess an animal that does not start with that letter, guess an animal you have already guessed, or try to make up an animal, you are out!");
            char randLetter = (char) ((Math.random() * 26) + 65);
            System.out.println("The letter is " + randLetter);

            int count = 0;
            while(fileScanner.hasNext()) {
                fileScanner.nextLine();
                count++;
            }
            System.out.println(count);

            String[] playerGuesses = new String[count];
            int numGuesses = 0;
            boolean playing = true;

            while(playing) {
                System.out.println("What is your guess? ");
                String guess = input.nextLine();
                boolean badGuess = false;

                if (!(guess == null || guess.isEmpty())) {
                    guess = guess.substring(0, 1).toUpperCase() + guess.substring(1).toLowerCase();
                }
                

                if(Character.toUpperCase(guess.charAt(0)) != randLetter) {
                    badGuess = true;
                    System.out.println("That animal does not start with the letter " + randLetter + ".");
                }
                
                for(int i = 0; i < numGuesses; i++) {
                    if(playerGuesses[i].equals(guess)) {
                        badGuess = true;
                        System.out.println("You already guessed that animal.");
                    }
                }

                boolean validAnimal = false;
                fileScanner = new Scanner(file);
                while(fileScanner.hasNext()) {
                    String temp = fileScanner.nextLine();
                    if(guess.equals(temp)) {
                        validAnimal = true;
                        break;
                    }
                }

                if(!validAnimal) {
                    System.out.println("That is not a valid animal.");
                    badGuess = true;
                }

                if(!badGuess) {
                    playerGuesses[numGuesses] = guess;
                    numGuesses++;
                    System.out.println("That is a real animal that starts with " + randLetter + ". You are at " + numGuesses + " correct guesses.");
                }
                else {
                    System.out.println("You guessed " + numGuesses + " correct animals.");
                    playing = false;
                }
            }


            fileScanner.close();
            input.close();
        }
        catch(FileNotFoundException e) {
            System.out.println(e);
        }

    }
}