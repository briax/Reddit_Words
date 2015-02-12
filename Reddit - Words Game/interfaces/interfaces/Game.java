package interfaces;

import java.io.IOException;

public interface Game {
	void welcomeMessage();
	void assignLettersToPlayer();
	void clearPlayersLetters();
	void checkPlayersString();
	void turn();
	void easyAI();
	void hardAI();
	void initializeWordArray() throws IOException;
}
