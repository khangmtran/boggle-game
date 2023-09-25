package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 *  class Boggle that can provide the needed methods for a console-based game
 * @author Khang Tran
 */
public class Boggle {
	private Set<String> boggleSet;
	private Set<String> dictionarySet;
	private DiceTray theBoard;

	/**
	 * default constructor
	 */
	public Boggle() {
		boggleSet = new HashSet<>();
		dictionarySet = new HashSet<>();
		theBoard = new DiceTray();
	}

	/**
	 * 2nd constructor for using fixed board purposes
	 * @param newBoard
	 */
	public Boggle(char[][] newBoard) {
		boggleSet = new HashSet<>();
		dictionarySet = new HashSet<>();
		theBoard = new DiceTray(newBoard);
	}

	/**
	 * print out the random board
	 * @return a random Board
	 */
	public String playGame() {
		String print = theBoard.printBoard();
		return print;
	}

	/**
	 * use file reader to read the dictionary
	 * and store it in the dictionarySet
	 */
	public void getDictionary() {
		try {
			// Create FileReader and BufferedReader objects
			FileReader fileReader = new FileReader("BoggleWords.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// Read the file line by line
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				dictionarySet.add(line);
			}

			// Close the BufferedReader
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * add each word that the user enters to the set
	 * @param word
	 */
	public void addWord(String word) {
		int index = 0;
		// if user enter multiple words on one line
		// then store each word in set seperated by a whitespace
		if (word.contains(" ")) {
			for (int i = 0; i < word.length() - 1; i++) {
				if (word.charAt(i) == ' ') {
					boggleSet.add(word.substring(index, i));
					index = i + 1;
				}
				if (i == word.length() - 2)
					boggleSet.add(word.substring(index, word.length()));
			}
			// else if user enter single word, simply store using add
		} else {
			boggleSet.add(word);
		}
	}

	/**
	 * check if the set contains the word
	 * @param str
	 * @return true if set contains
	 */
	public boolean contain(String str) {
		boolean contain = false;
		// loop through the set, if it contains the specific word
		// then return true
		for (int i = 0; i < boggleSet.size(); i++) {
			if (boggleSet.contains(str)) {
				contain = true;
			}
		}
		return contain;
	}

	/**
	 * Calculate score of user by adding each correct word
	 * with the respective score
	 * @return
	 */
	public int getScore() {
		int score = 0;
		//loop through the set
		for (String word : boggleSet) {
			//if the word is in the dictionary and exist on the board
			//then user gets points
			if (dictionarySet.contains(word) && theBoard.found(word)) {
				if (word.length() <= 4)
					score += 1;
				else if (word.length() == 5)
					score += 2;
				else if (word.length() == 6)
					score += 3;
				else if (word.length() == 7)
					score += 5;
				else if (word.length() >= 8)
					score += 11;
			}
		}
		return score;
	}

	/**
	 * Return the correct words that the user enters
	 * @return a list of correct words
	 */
	public String getListOfCorrectAttempts() {
		List<String> correctWords = new ArrayList<>();
		for (String word : boggleSet) {
			//if the word is in the dictionary and exist on the board
			//then add to the correctWord list
			if (dictionarySet.contains(word) && theBoard.found(word)) {
				correctWords.add(word);
			}
		}
		Collections.sort(correctWords);
		String result = String.join(" ", correctWords);
		return result;
	}

	/**
	 * Return the wrong words that user enters
	 * @return list of wrong words
	 */
	public String getListofWrongAttempts() {
		List<String> wrongAttempts = new ArrayList<>();
		for (String word : boggleSet) {
			//if the word is not in the dictionary or not exist on the board
			//then add to the wrongAttempts list
			if (!dictionarySet.contains(word) || !theBoard.found(word)) {
				wrongAttempts.add(word);
			}
		}
		Collections.sort(wrongAttempts);
		String result = String.join(" ", wrongAttempts);
		return result;
	}

	/**
	 * Return number of extra words that user could have found
	 * @return
	 */
	public int getNumberofExtraWords() {
		List<String> correctWords = getCorrectWords();
		int count = 0;
		for (String word : dictionarySet) {
			//if the words are not found by the user
			//and exist on the board then count++
			if (theBoard.found(word) && !correctWords.contains(word))
				count++;
		}
		return count;
	}

	/**
	 * Return a list of words that user could have found
	 * @return
	 */
	public String getListofExtraWords() {
		List<String> correctWords = getCorrectWords();
		List<String> extraWords = new ArrayList<>();
		//if the word exists on the board and not found by the user
		//then add word to the list of words could've been found
		for (String word : dictionarySet) {
			if (theBoard.found(word) && !correctWords.contains(word)) {
				extraWords.add(word);
			}
		}
		Collections.sort(extraWords);
		String result = String.join(" ", extraWords);
		return result;
	}

	/**
	 * helper method 
	 * @return
	 */
	private List<String> getCorrectWords() {
		List<String> correctWords = new ArrayList<>();
		for (String word : boggleSet) {
			if (dictionarySet.contains(word) && theBoard.found(word)) {
				correctWords.add(word);
			}
		}
		return correctWords;
	}

}
