package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Boggle;

/**
 * Boggle Tester
 * @author Khang Tran
 * 
 */
class BoggleTest {
	private char[][] longWords = { { 'A', 'B', 'B', 'E' }, { 'S', 'E', 'S', 'S' }, { 'I', 'A', 'B', 'A' },
			{ 'T', 'I', 'N', 'G' } };
	private Boggle test = new Boggle(longWords);

	@Test
	void testAddSet() {
		//Test adding words to the set
		//See if the word is added by using contain method
		test.addWord("ant");
		assertTrue(test.contain("ant"));
		test.addWord("little queen");
		assertTrue(test.contain("little"));
		assertTrue(test.contain("queen"));
		assertFalse(test.contain("little queen"));
	}

	@Test
	void testScore() {
		//test getScore method
		//by adding each word and calculate the total score
		test.getDictionary();
		assertEquals(test.getScore(), 0);
		test.addWord("ban");// 3 letters = 0+1
		assertEquals(test.getScore(), 1);
		test.addWord("abbe"); // 4 letters = 1+1
		assertEquals(test.getScore(), 2);
		test.addWord("abbes"); // 5 letters = 2+2
		assertEquals(test.getScore(), 4);
		test.addWord("abbess"); // 6 letters = 3+4
		assertEquals(test.getScore(), 7);
		test.addWord("abating");// 7 letters = 5+7
		assertEquals(test.getScore(), 12);
		test.addWord("abbesses");// 8 letters = 11+12
		assertEquals(test.getScore(), 23);
		test.addWord("que");// not in the boggle so score is 23
		assertEquals(test.getScore(), 23);
		test.addWord("abb");// not in the dictionary so score is 23
		assertEquals(test.getScore(), 23);
	}

	@Test
	void testCorrectAttempts() {
		//test list of correct attempts by adding word to the set
		//and compare specific words with the returned word from the list
		test.getDictionary();
		test.addWord("ban");
		test.addWord("abbe");
		test.addWord("abbes");
		test.addWord("queen");
		test.addWord("knight");
		test.addWord("abb");
		assertNotEquals(test.getListOfCorrectAttempts(), "ban abbe abbes queen knight abb");
		assertNotEquals(test.getListOfCorrectAttempts(), "abb abbe abbes ban knight queen");
		assertEquals(test.getListOfCorrectAttempts(), "abbe abbes ban");
		test.addWord("abbesses");
		assertEquals(test.getListOfCorrectAttempts(), "abbe abbes abbesses ban");
	}

	@Test
	void testWrongAttempts() {
		//test list of wrong attempts by adding word to the set
		//and compare specific words with the returned words from the list
		test.getDictionary();
		test.addWord("ban");
		test.addWord("abbe");
		test.addWord("abbes");
		test.addWord("queen");
		test.addWord("knight");
		test.addWord("abb");
		assertNotEquals(test.getListofWrongAttempts(), "ban abbe abbes queen knight abb");
		assertEquals(test.getListofWrongAttempts(), "abb knight queen");
		test.addWord("abbesses");// correct attempt so not change
		assertEquals(test.getListofWrongAttempts(), "abb knight queen");
	}

	@Test
	//test both methods getNumberofExtraWords and  getListofExtraWords
	//by using an easy board to test with a few words
	void testGetExtraWords() {
		char[][] easyWords = { { 'E', 'A', 'T', 'E' }, { 'A', 'A', 'A', 'A' }, { 'A', 'A', 'A', 'A' },
				{ 'A', 'A', 'A', 'A' } };
		Boggle easyBoard = new Boggle(easyWords);
		easyBoard.getDictionary();
		easyBoard.addWord("eat");
		assertNotEquals(easyBoard.getNumberofExtraWords(), 2);
		assertEquals(easyBoard.getNumberofExtraWords(), 3);
		assertEquals(easyBoard.getListofExtraWords(), "ate eta tea");
	}
	
	@Test
	void testPrint() {
		String expected = " E A T E\n A A A A\n A A A A\n A A A A\n";
		char[][] easyWords = { { 'E', 'A', 'T', 'E' }, { 'A', 'A', 'A', 'A' }, { 'A', 'A', 'A', 'A' },
				{ 'A', 'A', 'A', 'A' } };
		Boggle easyBoard = new Boggle(easyWords);
		assertEquals(expected, easyBoard.playGame());
	}
	
	@Test
	public void testRandomBoard() {
		// test random board using a fix board
		char[][] fixedWords = { { 'T', 'E', 'I', 'Z' }, { 'E', 'O', 'T', 'R' }, { 'L', 'D', 'G', 'H' },
				{ 'A', 'T', 'N', 'G' } };
		Boggle fixedBoard = new Boggle(fixedWords);
		Boggle randomBoard = new Boggle();
		assertNotEquals(fixedBoard, randomBoard);
	}

}
