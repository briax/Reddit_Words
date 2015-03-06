package source;

import interfaces.Game;
import interfaces.Player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameImpl implements Game  {
	
	private Scanner scanner;
	private Player player;
	private StringBuilder sb;
	private int playerScore = 0;
	private int computerScore = 0;
	private int turn = 1;	
	private final int SIZE_OF_LETTER_ARRAY = 12;
	private ArrayList<String> wordsForAI;
	private String playerString;
	
	public GameImpl(Player player) {
		this.player = player;
	}

	@Override
	public void assignLettersToPlayer() {
		Random random = new Random();
		char[] letters = new char[SIZE_OF_LETTER_ARRAY];

		for(int i = 0; i < SIZE_OF_LETTER_ARRAY; i++){
			char c = (char)(random.nextInt(26) + 'a');
			letters[i] = c;
		}
		player.addLetters(letters);
	}

	@Override
	public void checkPlayersString() {	
		sb = new StringBuilder();
		scanner = new Scanner(System.in);
		System.out.print("Your word --> ");
		String word = scanner.nextLine();
				
		char[] lettersFromWord = word.toCharArray();
		char[] lettersFromPlayer = player.arrayLetters().clone();
		char[] lettersFromPlayerTemp = lettersFromPlayer.clone();
		
		for(int i = 0; i < lettersFromWord.length; i++){
			for(int j = 0; j < lettersFromPlayer.length; j++){
				if(lettersFromPlayer[j] == lettersFromWord[i]){
					sb.append(lettersFromPlayer[j]);
					lettersFromPlayer[j] = 'X';
					break;
				}
			}
		}
				
		if(!sb.toString().equalsIgnoreCase(word)){
			System.out.println("I am sorry but you cannot spell " + word + " with your letters. Try again.");
			lettersFromPlayer = lettersFromPlayerTemp;
			player.getLetters();
			checkPlayersString();
		}else{
			System.out.println("Valid word! Open fire!");
			//turn++;
			playerString = word;
			playerScore = playerScore + word.length();
			//easyAI();
		}
	}

	@Override
	public void welcomeMessage() {
		scanner = new Scanner(System.in);
		System.out.println("Welcome to Words with enemies!");
		System.out.println("Select an AI difficulty: ");
		System.out.println("1) Easy");
		System.out.print("--> ");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			System.out.println("You've selected Easy! - Lets begin!");
			break;
		default:
			System.out.println("Please select a viable choice - run the program again.");
			break;
		}
		//initializeWordArray();
	}
	
	@Override
	public void turn() {
		System.out.println("Turn " + turn + " -- " + " Points you: " + playerScore + 
				" Computer: " + computerScore);
		System.out.println("-----------------------------------------");
	}
	
	// Not really needed..
	@Override
	public void clearPlayersLetters() {
		char[] emptyArray = {};
		player.addLetters(emptyArray);
	}

	@Override
	public void easyAI() {
		
		sb = new StringBuilder();
		
		char[] lettersFromPlayer = player.arrayLetters().clone();
		char[] lettersFromPlayerTemp = lettersFromPlayer.clone();
		char[] lettersFromWord = null;
		String wordAI = null;
		
		for(int k = 0; k < wordsForAI.size(); k++){
			wordAI = wordsForAI.get(k);
			lettersFromWord = wordsForAI.get(k).toCharArray();
			for(int i = 0; i < lettersFromWord.length; i++){
				for(int j = 0; j < lettersFromPlayer.length; j++){
					if(lettersFromPlayer[j] == lettersFromWord[i]){
						sb.append(lettersFromPlayer[j]);
						lettersFromPlayer[j] = 'X';
						break;
					}
				}
			}
		}
		
		if(!sb.toString().equalsIgnoreCase(wordAI)){
			lettersFromPlayer = lettersFromPlayerTemp;
			easyAI();
		}else{
			System.out.println("AI selects: " + wordAI);
			turn++;
			computerScore = computerScore + wordAI.length();;
		}
		
		
	}

	@Override
	public void hardAI() {
		
		
	}

	@Override
	public void initializeWordArray()  {
		wordsForAI = new ArrayList<String>();
		String fileName = System.getProperty("user.dir") + "/Files/Words.txt";
		try {
			FileReader reader = new FileReader(fileName);
			BufferedReader bfReader = new BufferedReader(reader);
			
			String line;
			
			try {
				while((line = bfReader.readLine()) != null){
					wordsForAI.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}