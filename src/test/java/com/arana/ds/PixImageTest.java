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
		int[][] vecinosExpected_01 = null;
		int[][] vecinosExpected_10 = null;
		int[][] vecinosExpected_31 = null;
		int[][] vecinosExpected_13 = null;
		
		// Expected corners
		vecinosExpected_00 = new int[][] { { 0,  0}, { 1,  0}, { 0,  1}, { 1,  1}, {-2, -2},{-2, -2}, {-2, -2},	{-2, -2},{-2, -2} };
		vecinosExpected_30 = new int[][] { { 2,  0}, { 3,  0}, { 2,  1}, { 3,  1}, {-2, -2},{-2, -2}, {-2, -2},	{-2, -2},{-2, -2} };
		vecinosExpected_03 = new int[][] { { 0,  2}, { 1,  2}, { 0,  3}, { 1,  3}, {-2, -2},{-2, -2}, {-2, -2},	{-2, -2},{-2, -2} };
		vecinosExpected_33 = new int[][] { { 2,  2}, { 3,  2}, { 2,  3}, { 3,  3}, {-2, -2},{-2, -2}, {-2, -2},	{-2, -2},{-2, -2} };
		
		// Expected sides
		// Left edge
		vecinosExpected_01 = new int[][] { { 0,  0}, { 1,  0}, { 0,  1}, { 1,  1}, {0, 2},{1, 2}, {-2, -2},	{-2, -2},{-2, -2} };
		// Right edge
		vecinosExpected_31 = new int[][] { { 2,  0}, { 3,  0}, { 2,  1}, { 3,  1}, {2, 2},{3, 2}, {-2, -2},	{-2, -2},{-2, -2} };
		// Top edge
		vecinosExpected_10 = new int[][] { { 0,  0}, { 1,  0}, { 2,  0}, { 0,  1}, {1, 1},{2, 1}, {-2, -2},	{-2, -2},{-2, -2} };
		// Bottom edge
		vecinosExpected_13 = new int[][] { { 0,  2}, { 1,  2}, { 2,  2}, { 0,  3}, {1, 3},{2, 3}, {-2, -2},	{-2, -2},{-2, -2} };
		
		// Testing corners
		assertTrue(Arrays.deepEquals(image1.neighbors(0, 0), vecinosExpected_00));
		assertTrue(Arrays.deepEquals(image1.neighbors(3, 0), vecinosExpected_30));
		assertTrue(Arrays.deepEquals(image1.neighbors(0, 3), vecinosExpected_03));
		assertTrue(Arrays.deepEquals(image1.neighbors(3, 3), vecinosExpected_33));
		
		// Testing sides
		assertTrue(Arrays.deepEquals(image1.neighbors(0, 1), vecinosExpected_01));
		assertTrue(Arrays.deepEquals(image1.neighbors(3, 1), vecinosExpected_31));
		// Top edge
		assertTrue(Arrays.deepEquals(image1.neighbors(1, 0), vecinosExpected_10));
		// Bottom edge
		assertTrue(Arrays.deepEquals(image1.neighbors(1, 3), vecinosExpected_13));
		
		assertTrue(Arrays.deepEquals(image1.neighbors(3, 1), vecinosExpected_31));		
	}
	
	public void testNeighborsB(){
		// Create a new Image
		PixImage image1 = new PixImage(4,4);
		
		// Corners
		int[][] vecinosExpected_00 = null;
		int[][] vecinosExpected_30 = null;
		int[][] vecinosExpected_03 = null;
		int[][] vecinosExpected_33 = null;
		
		// Sides
		int[][] vecinosExpected_01 = null;
		//int[][] vecinosExpected_10 = null;
		int[][] vecinosExpected_31 = null;
		//int[][] vecinosExpected_13 = null;
		
		// Expected corners
		vecinosExpected_00 = new int[][] { { 0,  0}, { 0,  0}, { 1,  0}, { 0,  0}, { 0,  0}, { 1,  0}, { 0,  1}, { 0,  1}, { 1,  1} };
		vecinosExpected_30 = new int[][] { { 2,  0}, { 3,  0}, { 3,  0}, { 2,  0}, { 3,  0}, { 3,  0}, { 2,  1}, { 3,  1}, { 3,  1} };
		vecinosExpected_03 = new int[][] { { 0,  2}, { 0,  2}, { 1,  2}, { 0,  3}, { 0,  3}, { 1,  3}, { 0,  3}, { 0,  3}, { 1,  3} };
		vecinosExpected_33 = new int[][] { { 2,  2}, { 3,  2}, { 3,  2}, { 2,  3}, { 3,  3}, { 3,  3}, { 2,  3}, { 3,  3}, { 3,  3} };
		
		// Expected sides
		// Left edge
		vecinosExpected_01 = new int[][] { { 0,  0}, { 0,  0}, { 1,  0}, { 0,  1}, { 0,  1}, { 1,  1}, { 0,  2}, { 0,  2}, { 1,  2} };
		// Right edge
		vecinosExpected_31 = new int[][] { { 2,  0}, { 3,  0}, { 3,  0}, { 2,  1}, { 3,  1}, { 3,  1}, { 2,  2}, { 3,  2}, { 3,  2} };
		// Top edge
		vecinosExpected_10 = new int[][] { { 0,  0}, { 1,  0}, { 2,  0}, { 0,  0}, { 1,  0}, { 2,  0}, { 0,  1}, { 1,  1}, { 2,  1} };
		// Bottom edge
		vecinosExpected_13 = new int[][] { { 0,  2}, { 1,  2}, { 2,  2}, { 0,  3}, { 1,  3}, { 2,  3}, { 0,  3}, { 1,  3}, { 2,  3} };
		
		// Testing corners
		assertTrue(Arrays.deepEquals(image1.neighbors(0, 0, true), vecinosExpected_00));
		assertTrue(Arrays.deepEquals(image1.neighbors(3, 0, true), vecinosExpected_30));
		assertTrue(Arrays.deepEquals(image1.neighbors(0, 3, true), vecinosExpected_03));
		assertTrue(Arrays.deepEquals(image1.neighbors(3, 3, true), vecinosExpected_33));
		
		// Testing sides
		assertTrue(Arrays.deepEquals(image1.neighbors(0, 1, true), vecinosExpected_01));
		assertTrue(Arrays.deepEquals(image1.neighbors(3, 1, true), vecinosExpected_31));
	}

	public void testGradient(){
		PixImage image1 = new PixImage(4,4);
		
		// Set all the pixels intensities to 1
		for(int i = 0; i < image1.getWidth(); i++){
			for(int j = 0; j < image1.getHeight(); j++){
				image1.setPixel(i, j, (short)1, (short)1, (short)1);
			}
		}
		
		// Calculate the gradient for each red pixel intensity
		// Compare the results from 
		
	}
	public void testAvgColors(){
		
	}
}