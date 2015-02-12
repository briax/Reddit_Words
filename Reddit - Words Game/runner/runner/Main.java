package runner;


import source.*;
import interfaces.*;

public class Main {
	public static void main(String[] args) {
		
		Player player1 = new PlayerImpl();
		Game game = new GameImpl(player1);
		
		game.welcomeMessage();
		while(true){
			game.assignLettersToPlayer();
			game.turn();
			player1.getLetters();
			game.checkPlayersString();
			
		}
	}
}
