package source;

import interfaces.Player;


public class PlayerImpl implements Player{
	
	private char[] letters;

	@Override
	public void getLetters() {
		if(letters.length < 1){
			System.out.println("Nothing in array.");
		}else{
			System.out.print("Your letters: ");
			for(char letter : letters){
				System.out.print(letter + " ");
			}
			System.out.println("");
		}
	}

	@Override
	public void addLetters(char[] cArray) {
		letters = cArray;
	}

	@Override
	public char[] arrayLetters() {
		return letters;
	}
}

