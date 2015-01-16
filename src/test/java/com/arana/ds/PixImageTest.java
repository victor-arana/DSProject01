package com.arana.ds;

import java.util.Arrays;

import junit.framework.TestCase;

public class PixImageTest extends TestCase {

	public void testNeighbors() {
		PixImage image1 = new PixImage(4,4);
		// Corners
		int[][] vecinosExpected_00 = null;
		int[][] vecinosExpected_30 = null;
		int[][] vecinosExpected_03 = null;
		int[][] vecinosExpected_33 = null;
		// Sides
//		int[][] vecinosExpected_01 = null;
		
		// Expected corners
		vecinosExpected_00 = new int[][] { { 0,  0}, { 1,  0}, { 0,  1}, { 1,  1}, {-1, -1},{-1, -1}, {-1, -1},	{-1, -1},{-1, -1} };
		vecinosExpected_30 = new int[][] { { 2,  0}, { 3,  0}, { 2,  1}, { 3,  1}, {-1, -1},{-1, -1}, {-1, -1},	{-1, -1},{-1, -1} };
		vecinosExpected_03 = new int[][] { { 0,  2}, { 1,  2}, { 0,  3}, { 1,  3}, {-1, -1},{-1, -1}, {-1, -1},	{-1, -1},{-1, -1} };
		vecinosExpected_33 = new int[][] { { 2,  2}, { 3,  2}, { 2,  3}, { 3,  3}, {-1, -1},{-1, -1}, {-1, -1},	{-1, -1},{-1, -1} };
		
		// Expected sides
//		vecinosExpected_01 = new int[][] { { 0,  0}, { 1,  0}, { 0,  1}, { 1,  1}, {0, 2},{1, 2}, {-1, -1},	{-1, -1},{-1, -1} };
		
		// Testing corners
		assertTrue(Arrays.deepEquals(image1.neighbors(0, 0), vecinosExpected_00));
		assertTrue(Arrays.deepEquals(image1.neighbors(3, 0), vecinosExpected_30));
		assertTrue(Arrays.deepEquals(image1.neighbors(0, 3), vecinosExpected_03));
		assertTrue(Arrays.deepEquals(image1.neighbors(3, 3), vecinosExpected_33));
		
		// Testing sides
//		assertTrue(Arrays.deepEquals(image1.neighbors(0, 1), vecinosExpected_01));
	}
	
}