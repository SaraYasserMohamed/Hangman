package eg.edu.alexu.csd.datastructure.hangman.cs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String player;
        int max;
        char c;
        System.out.println("Your Name: ");
        player = scanner.nextLine();
        System.out.println("Maximum number of guesses: ");
        max = scanner.nextInt();
        Hangman hangman = new Hangman();
        hangman.file();
        hangman.setMaxWrongGuesses(max);
        while (hangman.leftGuesses != 0 && hangman.win == false) {
            System.out.println("Guess a character");
            c = scanner.next().charAt(0);
            hangman.guess(c);
        }
        if (hangman.win==true){
            System.out.println("You Win!! \n Congratulations "+player+"!");
        }

    }

    }

