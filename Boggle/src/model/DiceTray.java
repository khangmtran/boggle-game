package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Model the tray of dice in the game Boggle. A DiceTray can be constructed with
 * a 4x4 array of characters for testing.
 *
 * @author Khang Tran
 */
public class DiceTray {

	private char[][] theBoard;
	private Random random = new Random();

	/**
	 * Construct a DiceTray object using a hard-coded 2D array of chars. One is
	 * provided in the given unit test. You can create others.
	 */
	public DiceTray(char[][] newBoard) {
		theBoard = newBoard;
	}

	/**
	 * 2nd constructor that rolls and randomly places all 16 Boggle dice in the tray
	 */
	public DiceTray() {
		theBoard = randomBoard();
	}

	// Create a randomBoard
	public char[][] randomBoard() {
		ArrayList<String> letters = new ArrayList<>();
		letters.add("LRYTTE");
		letters.add("ANAEEG");
		letters.add("AFPKFS");
		letters.add("YLLDEVR");
		letters.add("VTHRWE");
		letters.add("IDSYTT");
		letters.add("XLDERI");
		letters.add("ZNRNHL");
		letters.add("EGHWNE");
		letters.add("OATTOW");
		letters.add("HCPOAS");
		letters.add("NMIHUQ");
		letters.add("SEOTIS");
		letters.add("MTOICU");
		letters.add("ENSIEU");
		letters.add("OBBAOJ");
		Collections.shuffle(letters);
		char[][] newBoard = new char[4][4];
		int index = 0;
		for (int i = 0; i < newBoard.length; i++) {
			for (int j = 0; j < newBoard[i].length; j++) {
				newBoard[i][j] = letters.get(index).charAt(random.nextInt(6));
				index++;
			}
		}
		return newBoard;
	}

	// print board
	public String printBoard() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append(" ");
		for (int i = 0; i < theBoard.length; i++) {
			for (int j = 0; j < theBoard[i].length; j++) {
					sb.append("  " + theBoard[i][j]);
			}
			sb.append("\n\n");
			sb.append(" ");
		}
		return sb.toString();
	}

	/**
	 * Return true if attempt can be found on the DiceTray following the rules of
	 * Boggle, like a die can only be used once.
	 *
	 * @param attempt A word that may be in the DiceTray by connecting consecutive
	 *                letters
	 *
	 * @return True if search is found in the DiceTray or false if not. You need not
	 *         check the dictionary now.
	 */
	public boolean found(String attempt) {
		attempt = attempt.toUpperCase(); // Case-insensitive
		boolean haveFound = false;

		// create new board to restore theBoard
		char newBoard[][] = new char[4][4];
		for (int x = 0; x < theBoard.length; x++) {
			for (int y = 0; y < theBoard[x].length; y++) {
				newBoard[x][y] = theBoard[x][y];
			}
		}
		// if a word contains less than 3 letters then return false
		if (attempt.length() < 3)
			return false;
		// Treat QU as Q
		attempt = attempt.replaceAll("QU", "Q");
		// Find the starting letter of the word to be found
		// then proceed to find if the word exist
		for (int i = 0; i < theBoard.length; i++) {
			for (int j = 0; j < theBoard[i].length; j++) {
				if (theBoard[i][j] == attempt.charAt(0)) {
					haveFound = findWord(attempt, i, j, 1);
					for (int m = 0; m < newBoard.length; m++) {
						for (int k = 0; k < newBoard[m].length; k++) {
							theBoard[m][k] = newBoard[m][k];
						}
					}
					if (haveFound == true)
						return haveFound;
				}
			}
		}
		return haveFound;
	}

	/**
	 * Helper function to recursively find words on the board Use * represents as a
	 * cell to help preventing repetition
	 * 
	 * @param attempt A word that may be in the DiceTray by connecting consecutive
	 *                letters
	 * @param row     Current row in the board
	 * @param col     Current col in the board
	 * @param index   Keep track of the letter in attempt
	 * @return True if search is found in the DiceTray or false if not
	 */
	private boolean findWord(String attempt, int row, int col, int index) {
		boolean done = false;
		char temp = theBoard[row][col]; // store letter to compare with the letter of attempt
		theBoard[row][col] = '.'; // cell
		if (index == attempt.length() && temp == attempt.charAt(index - 1)) {
			done = true; // set done to true if found the word
		} else {
			// move to the right
			if (col + 1 < theBoard[row].length && theBoard[row][col + 1] == attempt.charAt(index) && !done) {
				done = findWord(attempt, row, col + 1, index + 1);
			}

			// move to the left
			if (col - 1 >= 0 && theBoard[row][col - 1] == attempt.charAt(index) && !done) {
				done = findWord(attempt, row, col - 1, index + 1);
			}

			// move up
			if (row + 1 < theBoard.length && theBoard[row + 1][col] == attempt.charAt(index) && !done) {
				done = findWord(attempt, row + 1, col, index + 1);
			}

			// move down
			if (row - 1 >= 0 && theBoard[row - 1][col] == attempt.charAt(index) && !done) {
				done = findWord(attempt, row - 1, col, index + 1);
			}

			// move upright
			if (col + 1 < theBoard[row].length && row + 1 < theBoard.length
					&& theBoard[row + 1][col + 1] == attempt.charAt(index) && !done) {
				done = findWord(attempt, row + 1, col + 1, index + 1);
			}

			// move down-left
			if (col - 1 >= 0 && row - 1 >= 0 && theBoard[row - 1][col - 1] == attempt.charAt(index) && !done) {
				done = findWord(attempt, row - 1, col - 1, index + 1);
			}

			// move down and right
			if (col + 1 < theBoard[row].length && row - 1 >= 0 && theBoard[row - 1][col + 1] == attempt.charAt(index)
					&& !done) {
				done = findWord(attempt, row - 1, col + 1, index + 1);
			}

			// move up and left
			if (col - 1 >= 0 && row + 1 < theBoard.length && theBoard[row + 1][col - 1] == attempt.charAt(index)
					&& !done) {
				done = findWord(attempt, row + 1, col - 1, index + 1);
			}

		}
		if (done == true)
			return true;
		return done;
	}
}
