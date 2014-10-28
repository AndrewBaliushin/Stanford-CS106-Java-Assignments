/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Hangman extends ConsoleProgram {

	public static final String FILE_NAME = "ShorterLexicon.txt";
	
	public static final int NMAX_MISTAKES = 6;
	
	private ArrayList<String> wordsFromFileList;
	
	//selected word after .toCharArray();
	private char[] charsOfTheWord;
	
	//spaces instead of letters (------); size of the word
	private char[] maskedWord;
	
	private ArrayList<Character> listOfFoundChars = new ArrayList<Character>();
	
	private HangmanCanvas hangmanCanvas = new HangmanCanvas();
	
	private int mistakesCount = 0;
	
	public void init() {
		add(hangmanCanvas);
		setSize(800, 600);
		
		this.wordsFromFileList = getWordsFromFile(FILE_NAME);	
	}
	
    public void run() {
    	
    	//init for word
		kickstartWord();		
		
		while (true) {
			
			hangmanCanvas.displayWord(charArrToString(maskedWord));
			
			for (int i = 0; i < maskedWord.length; i++) {
				print(maskedWord[i]);
			}
			println();
			print("Enter letter: ");
			String userInput = readLine();
			
			char userGuess;
			try {
				userGuess = Character.toUpperCase(userInput.charAt(0)); //takes 1st char
			} catch (IndexOutOfBoundsException e) { //user send empty string
				println("Don't send empty string. Enter something!");
				continue;
			}
			
			if (listOfFoundChars.contains(userGuess)) {
				println("You already found this letter. Try another one.");
				continue;
			}
			
			boolean foundMarker = false;
			for (int i = 0; i < charsOfTheWord.length; i++) {
				if (userGuess == charsOfTheWord[i]) {
					listOfFoundChars.add(userGuess);
					maskedWord[i] = userGuess;
					foundMarker = true;
				}
			}//for
			
			if (!foundMarker) {
				hangmanCanvas.noteIncorrectGuess(userGuess);
				mistakesCount++;
			} 
			
			if (listOfFoundChars.size() == charsOfTheWord.length) { //win
				println("GRATS! You did it!");
				println("Lets do it again.");
				hangmanCanvas.reset();
				kickstartWord();
			}
			
			if (mistakesCount >= NMAX_MISTAKES) { //loss
				println("You have lost the game.");
				println("Lets try again.");
				hangmanCanvas.reset();
				kickstartWord();
				
			}
			
		} //while
		
		
		
		
	}
    
    private void kickstartWord() {
		// now we have a word
		String randomWord = chooseRandomWord(wordsFromFileList);

		charsOfTheWord = charArrToUpperCase(randomWord.toCharArray());

		maskedWord = new char[charsOfTheWord.length];
		Arrays.fill(maskedWord, '-');
		
		mistakesCount = 0;
		
		listOfFoundChars = new ArrayList<Character>();
    }
    
    private ArrayList<String> getWordsFromFile(String fileName) {
    	ArrayList<String> wordsList = new ArrayList<String>();
    	
		try {
			BufferedReader rd = new BufferedReader(new FileReader(fileName));
			while (true) {
				String line = rd.readLine();
				if (line == null) {
					break;
				}
				wordsList.add(line);
			}
			rd.close(); // close when you’re done!
		} catch (IOException ex) {
			// do something in response to exception
			throw new ErrorException(ex);
		}
		
		return wordsList;
    }
    
    private String chooseRandomWord(ArrayList<String> wordsList) {
    	Random rd = new Random(); //fixed number for consistent random; 47 -- SLITHER
    	return wordsList.get(rd.nextInt(wordsList.size()));
    }
    
    private char[] charArrToUpperCase(char[] cArr) {
    	for (int i = 0; i < cArr.length; i++) {
    		cArr[i] = Character.toUpperCase(cArr[i]);			
		}
    	return cArr;
    }
    
    private String charArrToString(char[] arr) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}
    	return sb.toString();
    }

}
