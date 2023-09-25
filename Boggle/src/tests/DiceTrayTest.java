package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.DiceTray;

/**
 * Grader tests for BoggleTray. Just tests word searches. We are not seeking
 * words in a dictionary, just strings for now.
 *
 * @author mercer, michaels, Khang
 * @version 1.2
 */

public class DiceTrayTest {

	private char[][] longWords = { 
			{ 'A', 'B', 'S', 'E' }, 
			{ 'I', 'M', 'T', 'N' }, 
			{ 'N', 'D', 'E', 'D' },
			{ 'S', 'S', 'E', 'N' } };

	private DiceTray tray = new DiceTray(longWords);

	@Test
	public void testForSmallStringsNotRealWords() {
		// We are not looking for words in a dictionary now, just strings.
		//
		// searchBoard must return false for strings < length() of 3
		// asserts can take a string argument that prints when the assert fails.
		//
		assertFalse(tray.found(""));
		assertFalse(tray.found("TS")); // Not a word, but the sequence exists
		assertTrue(tray.found("TMI"));
		assertTrue(tray.found("aBs")); // Case insensitive
		assertTrue(tray.found("AbS"));
		assertFalse(tray.found("AND"));
		assertFalse(tray.found("Asb"));
		assertFalse(tray.found("NND"));
		assertFalse(tray.found("ASE"));
		assertFalse(tray.found("sin"));
		assertFalse(tray.found("EBS"));
		assertTrue(tray.found("EDN"));
		assertFalse(tray.found("SES"));
		assertTrue(tray.found("tes"));
		assertTrue(tray.found("ten"));
		assertTrue(tray.found("tsm"));
		assertTrue(tray.found("tbm"));
		assertTrue(tray.found("AbS"));
		assertTrue(tray.found("tse"));

	}

	@Test
	public void testFound2() {
		assertTrue(tray.found("ESBA"));
		assertTrue(tray.found("NTMI"));
		assertTrue(tray.found("DEDN"));
		assertTrue(tray.found("NESS"));
		assertTrue(tray.found("IMTN"));
		assertTrue(tray.found("sent"));
		assertTrue(tray.found("SENT"));
		assertTrue(tray.found("minded"));
		assertTrue(tray.found("teen"));
		assertTrue(tray.found("dibtd"));
		assertFalse(tray.found("HELLO"));
		assertTrue(tray.found("MBID"));
		assertFalse(tray.found("edct"));
		assertFalse(tray.found("bark"));
		assertTrue(tray.found("dmtes"));

	}

	@Test
	public void testForLongerStrings() {
		assertTrue(tray.found("NTMINDED")); // Not a word, but the sequence exists
		assertTrue(tray.found("ABSENTMINDEDNESS"));
		assertFalse(tray.found("ABSEIMTNNDDESEN"));
		assertFalse(tray.found("ABSEtnMINDEDNESS"));
		assertTrue(tray.found("nesdmb"));
		assertTrue(tray.found("neetsend"));
		assertFalse(tray.found("absesba"));
		assertFalse(tray.found("sssssss"));
		assertFalse(tray.found("bmtsbmts"));
		assertFalse(tray.found("amedma"));
		assertFalse(tray.found("abceim"));

	}

	// Add many many more test cases
	@Test
	public void testEmptyBoard() {
		// test empty board
		char[][] emptyBoard = {};
		DiceTray emptyTray = new DiceTray(emptyBoard);
		assertFalse(emptyTray.found("NTMINDED"));
	}

	@Test
	public void testRandomBoard() {
		// test random board using a fix board
		char[][] fixedWords = { { 'T', 'E', 'I', 'Z' }, { 'E', 'O', 'T', 'R' }, { 'L', 'D', 'G', 'H' },
				{ 'A', 'T', 'N', 'G' } };
		DiceTray fixedBoard = new DiceTray(fixedWords);
		DiceTray randomBoard = new DiceTray();
		assertNotEquals(fixedBoard, randomBoard);
	}

	@Test
	public void testBoardQ() {
		// test 'Q'
		char[][] fixedWords = { { 'B', 'S', 'Q', 'A' }, { 'Q', 'O', 'T', 'R' }, { 'L', 'D', 'G', 'H' },
				{ 'A', 'T', 'N', 'G' } };
		DiceTray fixedBoard = new DiceTray(fixedWords);
		assertTrue(fixedBoard.found("BSQU"));
		assertTrue(fixedBoard.found("BSQ"));
		assertTrue(fixedBoard.found("BSQTOQ"));
	}

	@Test
	public void testPrintBoard() {
		// test print method
		String expectedBoard = " B S D A\n D O T R\n L D G H\n A T N G\n";
		char[][] words = { { 'B', 'S', 'D', 'A' }, { 'D', 'O', 'T', 'R' }, { 'L', 'D', 'G', 'H' },
				{ 'A', 'T', 'N', 'G' } };
		DiceTray finalBoard = new DiceTray(words); 
		assertEquals(expectedBoard, finalBoard.printBoard());
	}
}
