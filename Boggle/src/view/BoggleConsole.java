package view;

import java.util.Scanner;

import model.Boggle;

/**
 * Console that plays by showing a randomly generated DiceTray
 * and a  report follows that shows the score and all words guessed correctly. 
 * The report also shows the incorrect guesses and all words not found in the dictionary
 * @author Khang Tran
 */
public class BoggleConsole {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Play one game of Boggle");
		Boggle game = new Boggle();
		System.out.println(game.playGame());
		game.getDictionary();
		boolean stop = true;
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter words or ZZ to quit");
		while (stop) {
			String userEnter = kb.next();
			if (userEnter.equals("ZZ"))
				stop = false;
			else
				game.addWord(userEnter);
		}
		System.out.println("Your score: " + game.getScore());
		System.out.println("Words you found:\n================\n" + game.getListOfCorrectAttempts());
		System.out.println("Incorrect words:\n================\n" + game.getListofWrongAttempts());
		System.out.println("You could have found these " + game.getNumberofExtraWords() + " more words:");
		System.out.println("================\n" + game.getListofExtraWords());
	}

}
