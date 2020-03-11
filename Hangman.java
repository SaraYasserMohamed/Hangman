package eg.edu.alexu.csd.datastructure.hangman.cs;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hangman implements IHangman {
    String[] dictionary = new String[50];
    String secretWord;
    int maxOfGuesses;
    int leftGuesses;
    char[] displayedWord;
    boolean win = false;

    public void file()throws Exception {
        FileReader fr = new FileReader("dictionary.txt");
        BufferedReader br = new BufferedReader(fr);
        String[] words = new String[50];
        int i;
        for (i = 0; i < 50; i++) {
            words[i] = br.readLine();
        }
        br.close();
        fr.close();
        setDictionary(words);
    }
    @Override
    public void setDictionary(String[] words) {
        int i;
        for (i=0;i<50;i++){
            dictionary[i]=words[i];
        }
        selectRandomSecretWord();
    }

    @Override
    public String selectRandomSecretWord() {
        secretWord = dictionary[(int) (Math.random() * 50)];
        displayedWord = new char[secretWord.length()];
        Arrays.fill(displayedWord, '-');
        System.out.println(displayedWord);
        return (secretWord);
    }

    @Override
    public String guess(Character c) throws Exception {
        Pattern pattern = Pattern.compile("[^A-Za-z]");
        Matcher match = pattern.matcher(secretWord);
        if(match.find()){
            throw new Exception("Buggy word!");
        }
        c=Character.toLowerCase(c);
        int index = secretWord.toLowerCase().indexOf(c);
        if(index==-1)
            leftGuesses--;

        for (int i = 0; i < secretWord.length() ; i++) {
            if(i==index) {
                displayedWord[i]= secretWord.charAt(index) ;
                index = secretWord.toLowerCase().indexOf(c, index + 1);
            }
        }
        System.out.println(displayedWord);
        System.out.println("Left Gesses: "+leftGuesses);
        if(leftGuesses ==0)
            throw new Exception("You reached maximum number of wrong guesses!\n You lose!");

        if(String.valueOf(displayedWord).indexOf('-')==-1)
            win=true;
        return String.valueOf(displayedWord);
    }

    @Override
    public void setMaxWrongGuesses(Integer max) {
    maxOfGuesses  = leftGuesses = max;
    if (max==0){
        maxOfGuesses =leftGuesses =1;
    }
    }
}

