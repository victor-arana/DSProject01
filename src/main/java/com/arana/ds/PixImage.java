package com.arana.ds;

/* PixImage.java */

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

  /**
   *  Define any variables associated with a PixImage object here.  These
   *  variables MUST be private.
   */	
	private int width;
	private int height;
	private int[][] image; 


  /**
   * PixImage() constructs an empty PixImage with a specified width and height.
   * Every pixel has red, green, and blue intensities of zero (solid black).
   *
   * @param width the width of the image.
   * @param height the height of the image.
   */
  public PixImage(int width, int height) {
	  this.width = width;
	  this.height = height;
	  this.image = new int[width*height][5];
  }

  /**
   * getWidth() returns the width of the image.
   *
   * @return the width of the image.
   */
  public int getWidth() {
    return width;
  }

  /**
   * getHeight() returns the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    return height;
  }

  /**
   * getRed() returns the red intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the red intensity of the pixel at coordinate (x, y).
   */
  /**
 * @param x
 * @param y
 * @return
 */
public short getRed(int x, int y) {
    return (short)image[(width)*y + x][2];
  }

  /**
   * getGreen() returns the green intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the green intensity of the pixel at coordinate (x, y).
   */
  public short getGreen(int x, int y) {
    return (short)image[(width)*y + x][3];
  }

  /**
   * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the blue intensity of the pixel at coordinate (x, y).
   */
  public short getBlue(int x, int y) {
    return (short)image[(width)*y + x][4];
  }

  /**
   * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
   * and blue intensities.
   *
   * If any of the three color intensities is NOT in the range 0...255, then
   * this method does NOT change any of the pixel intensities.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @param red the new red intensity for the pixel at coordinate (x, y).
   * @param green the new green intensity for the pixel at coordinate (x, y).
   * @param blue the new blue intensity for the pixel at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
	int location = (width)*y + x;
	this.image[location][0] = x;
	this.image[location][1] = y;
    this.image[location][2] = red;
    this.image[location][3] = green;
    this.image[location][4] = blue;
  }

  /**
   * toString() returns a String representation of this PixImage.
   *
   * This method isn't required, but it should be very useful to you when
   * you're debugging your code.  It's up to you how you represent a PixImage
   * as a String.
   *
   * @return a String representation of this PixImage.
   */
  public String toString() {
    // Replace the following line with your solution.
    return "";
  }

  /**
   * boxBlur() returns a blurred version of "this" PixImage.
   *
   * If numIterations == 1, each pixel in the output PixImage is assigned
   * a value equal to the average of its neighboring pixels in "this" PixImage,
   * INCLUDING the pixel itself.
   *
   * A pixel not on the image boundary has nine neighbors--the pixel itself and
   * the eight pixels surrounding it.  A pixel on the boundary has six
   * neighbors if it is not a corner pixel; only four neighbors if it is
   * a corner pixel.  The average of the neighbors is the sum of all the
   * neighbor pixel values (including the pixel itself) divided by the number
   * of neighbors, with non-integer quotients rounded toward zero (as Java does
   * naturally when you divide two integers).
   *
   * Each color (red, green, blue) is blurred separately.  The red input should
   * have NO effect on the green or blue outputs, etc.
   *
   * The parameter numIterations specifies a number of repeated iterations of
   * box blurring to perform.  If numIterations is zero or negative, "this"
   * PixImage is returned (not a copy).  If numIterations is positive, the
   * return value is a newly constructed PixImage.
   *
   * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
   * appear in the new, output PixImage only.
   *
   * @param numIterations the number of iterations of box blurring.
   * @return a blurred version of "this" PixImage.
   */
  public PixImage boxBlur(int numIterations) {
	  
	  PixImage inputPixImage = this;
	  PixImage outputPixImage = inputPixImage.boxBlur();
	  
	  while(numIterations > 1){
		  inputPixImage = outputPixImage;
		  outputPixImage = inputPixImage.boxBlur();
		  numIterations--;
	  }
 
	  return outputPixImage;
  }
  
  public PixImage boxBlur(){
	  PixImage outPutPixImage = new PixImage(width, height);
	  for(int i = 0; i < width; i ++){
		  for(int j = 0; j < height; j++){			   
			  
			  outPutPixImage.setPixel(i, j, (short)this.avgColors(i, j)[0], (short)this.avgColors(i, j)[1], (short)this.avgColors(i, j)[2]);
		  }
	  }	  
	  return outPutPixImage; 
  }
  
public int[][] neighbors(int x, int y, boolean reflection){
	  // Initialize neighbor array
	  int[][] neighbors = new int[9][2];
	  for(int i = 0; i < 9; i++){
		  neighbors[i][0] = -2;
		  neighbors[i][1] = -2;
	  }
	  if(x==0){
		  if(y == 0){
			  // Esquina superior izquierda
			  neighbors[0][0] = 0;  neighbors[0][1] = 0;
			  neighbors[1][0] = 0;  neighbors[1][1] = 0;
			  neighbors[2][0] = 1;  neighbors[2][1] = 0;
			  neighbors[3][0] = 0;  neighbors[3][1] = 0;
			  neighbors[4][0] = 0;  neighbors[4][1] = 0;
			  neighbors[5][0] = 1;  neighbors[5][1] = 0;
			  neighbors[6][0] = 0;  neighbors[6][1] = 1;
			  neighbors[7][0] = 0;  neighbors[7][1] = 1;
			  neighbors[8][0] = 1;  neighbors[8][1] = 1;
		  } else if(y == height - 1){
			  // Esquina inferior izquierda
			  neighbors[0][0] = 0;  neighbors[0][1] = height - 2;
			  neighbors[1][0] = 0;  neighbors[1][1] = height - 2;
			  neighbors[2][0] = 1;  neighbors[2][1] = height - 2;
			  neighbors[3][0] = 0;  neighbors[3][1] = height - 1;
			  neighbors[4][0] = 0;  neighbors[4][1] = height - 1;
			  neighbors[5][0] = 1;  neighbors[5][1] = height - 1;
			  neighbors[6][0] = 0;  neighbors[6][1] = height - 1;
			  neighbors[7][0] = 0;  neighbors[7][1] = height - 1;
			  neighbors[8][0] = 1;  neighbors[8][1] = height - 1;
		  } else {
			  // Borde izquierdo
			  neighbors[0][0] = 0;  neighbors[0][1] = y - 1;
			  neighbors[1][0] = 0;  neighbors[1][1] = y - 1;
			  neighbors[2][0] = 1;  neighbors[2][1] = y - 1;
			  neighbors[3][0] = 0;  neighbors[3][1] = y;
			  neighbors[4][0] = 0;  neighbors[4][1] = y;
			  neighbors[5][0] = 1;  neighbors[5][1] = y;
			  neighbors[6][0] = 0;  neighbors[6][1] = y + 1;
			  neighbors[7][0] = 0;  neighbors[7][1] = y + 1;
			  neighbors[8][0] = 1;  neighbors[8][1] = y + 1;
		  }
	  } else if(x == width - 1){
		  if(y == 0){
			  // Esquina superior derecha
			  neighbors[0][0] = width - 2;  neighbors[0][1] = 0;
			  neighbors[1][0] = width - 1;  neighbors[1][1] = 0;
			  neighbors[2][0] = width - 1;  neighbors[2][1] = 0;
			  neighbors[3][0] = width - 2;  neighbors[3][1] = 0;
			  neighbors[4][0] = width - 1;  neighbors[4][1] = 0;
			  neighbors[5][0] = width - 1;  neighbors[5][1] = 0;
			  neighbors[6][0] = width - 2;  neighbors[6][1] = 1;
			  neighbors[7][0] = width - 1;  neighbors[7][1] = 1;
			  neighbors[8][0] = width - 1;  neighbors[8][1] = 1;
		  }else if(y == height - 1 ){
			  // Esquina inferior derecha
			  neighbors[0][0] = width - 2;  neighbors[0][1] = height - 2;
			  neighbors[1][0] = width - 1;  neighbors[1][1] = height - 2;
			  neighbors[2][0] = width - 1;  neighbors[2][1] = height - 2;
			  neighbors[3][0] =	width - 2;  neighbors[3][1] = height - 1;
			  neighbors[4][0] = width - 1;  neighbors[4][1] = height - 1;
			  neighbors[5][0] = width - 1;  neighbors[5][1] = height - 1;
			  neighbors[6][0] = width - 2;  neighbors[6][1] = height - 1;
			  neighbors[7][0] = width - 1;  neighbors[7][1] = height - 1;
			  neighbors[8][0] = width - 1;  neighbors[8][1] = height - 1;
		  }else{
			  //  Borde derecho
			  neighbors[0][0] = width - 2;  neighbors[0][1] = y - 1;
			  neighbors[1][0] = width - 1;  neighbors[1][1] = y - 1;
			  neighbors[2][0] = width - 1;  neighbors[2][1] = y - 1;
			  neighbors[3][0] = width - 2;  neighbors[3][1] = 	  y;
			  neighbors[4][0] = width - 1;  neighbors[4][1] =     y;
			  neighbors[5][0] =	width - 1;  neighbors[5][1] =     y;
			  neighbors[6][0] = width - 2;  neighbors[6][1] = y + 1;
			  neighbors[7][0] = width - 1;  neighbors[7][1] = y + 1;
			  neighbors[8][0] = width - 1;  neighbors[8][1] = y + 1;
		  }
	  } else if(y == 0){
		  // Borde superior
		  neighbors[0][0] = x - 1;  neighbors[0][1] = 0;
		  neighbors[1][0] = 	x;  neighbors[1][1] = 0;
		  neighbors[2][0] = x + 1;  neighbors[2][1] = 0;
		  neighbors[3][0] = x - 1;  neighbors[3][1] = 0;
		  neighbors[4][0] = 	x;  neighbors[4][1] = 0;
		  neighbors[5][0] = x + 1;  neighbors[5][1] = 0;
		  neighbors[6][0] = x - 1;  neighbors[6][1] = 1;
		  neighbors[7][0] =     x;  neighbors[7][1] = 1;
		  neighbors[8][0] = x + 1;  neighbors[8][1] = 1;
	  } else if(y == height - 1){
		  // Borde inferior
		  neighbors[0][0] = x - 1;  neighbors[0][1] = height - 2;
		  neighbors[1][0] = 	x;  neighbors[1][1] = height - 2;
		  neighbors[2][0] = x + 1;  neighbors[2][1] = height - 2;
		  neighbors[3][0] = x - 1;  neighbors[3][1] = height - 1;
		  neighbors[4][0] = 	x;  neighbors[4][1] = height - 1;
		  neighbors[5][0] = x + 1;  neighbors[5][1] = height - 1;
		  neighbors[6][0] = x - 1;  neighbors[6][1] = height - 1;
		  neighbors[7][0] =     x;  neighbors[7][1] = height - 1;
		  neighbors[8][0] = x + 1;  neighbors[8][1] = height - 1;
	  } else {
		  // Centro
		  neighbors[0][0] = x - 1;  neighbors[0][1] = y - 1;
		  neighbors[1][0] = 	x;  neighbors[1][1] = y - 1;
		  neighbors[2][0] = x + 1;  neighbors[2][1] = y - 1;
		  neighbors[3][0] = x - 1;  neighbors[3][1] = y;
		  neighbors[4][0] = 	x;  neighbors[4][1] = y;
		  neighbors[5][0] = x + 1;  neighbors[5][1] = y;
		  neighbors[6][0] = x - 1;  neighbors[6][1] = y + 1;
		  neighbors[7][0] = 	x;  neighbors[7][1] = y + 1;
		  neighbors[8][0] = x + 1;  neighbors[8][1] = y + 1;
	  }	
	  return neighbors;
}
  
  /**
   * neighbors(int x, int y) returns a list containing the coordinates of each of the pixel neighborgs. With the location of the pixel at (x,y)
   * 
 * @param x 
 * @param y
 * @return a list of the neighbors of the pixel at (x,y)
 */
public int[][] neighbors(int x, int y){
	  // Initialize neighbor array
	  int[][] neighbors = new int[9][2];
	  for(int i = 0; i < 9; i++){
		  neighbors[i][0] = -2;
		  neighbors[i][1] = -2;
	  }
	  if(x==0){
		  if(y == 0){
			  // Esquina superior izquierda
			  neighbors[0][0] = 0;  neighbors[0][1] = 0;
			  neighbors[1][0] = 1;  neighbors[1][1] = 0;
			  neighbors[2][0] = 0;  neighbors[2][1] = 1;
			  neighbors[3][0] = 1;  neighbors[3][1] = 1;
		  } else if(y == height - 1){
			  // Esquina inferior izquierda
			  neighbors[0][0] = 0;  neighbors[0][1] = height - 1 - 1;
			  neighbors[1][0] = 1;  neighbors[1][1] = height - 1 - 1;
			  neighbors[2][0] = 0;  neighbors[2][1] = height - 1;
			  neighbors[3][0] = 1;  neighbors[3][1] = height - 1;
		  } else {
			  // Borde izquierdo
			  neighbors[0][0] = 0;  neighbors[0][1] = y - 1;
			  neighbors[1][0] = 1;  neighbors[1][1] = y - 1;
			  neighbors[2][0] = 0;  neighbors[2][1] = y;
			  neighbors[3][0] = 1;  neighbors[3][1] = y;
			  neighbors[4][0] = 0;  neighbors[4][1] = y + 1;
			  neighbors[5][0] = 1;  neighbors[5][1] = y + 1;
		  }
	  } else if(x == width - 1){
		  if(y == 0){
			  // Esquina superior derecha
			  neighbors[0][0] = width - 1 - 1;  neighbors[0][1] = 0;
			  neighbors[1][0] = 	width - 1;  neighbors[1][1] = 0;
			  neighbors[2][0] = width - 1 - 1;  neighbors[2][1] = 1;
			  neighbors[3][0] = 	width - 1;  neighbors[3][1] = 1;
		  }else if(y == height - 1 ){
			  // Esquina inferior derecha
			  neighbors[0][0] = width - 1 - 1;  neighbors[0][1] = height - 1 - 1;
			  neighbors[1][0] = 	width - 1;  neighbors[1][1] = height - 1 - 1;
			  neighbors[2][0] = width - 1 - 1;  neighbors[2][1] = height - 1;
			  neighbors[3][0] = 	width - 1;  neighbors[3][1] = height - 1;
		  }else{
			  //  Borde derecho
			  neighbors[0][0] = width - 1 - 1;  neighbors[0][1] = y - 1;
			  neighbors[1][0] = 	width - 1;  neighbors[1][1] = y - 1;
			  neighbors[2][0] = width - 1 - 1;  neighbors[2][1] = 	  y;
			  neighbors[3][0] = 	width - 1;  neighbors[3][1] = 	  y;
			  neighbors[4][0] = width - 1 - 1;  neighbors[4][1] = y + 1;
			  neighbors[5][0] = 	width - 1;  neighbors[5][1] = y + 1;
		  }
	  } else if(y == 0){
		  // Borde superior
		  neighbors[0][0] = x - 1;  neighbors[0][1] = 0;
		  neighbors[1][0] = 	x;  neighbors[1][1] = 0;
		  neighbors[2][0] = x + 1;  neighbors[2][1] = 0;
		  neighbors[3][0] = x - 1;  neighbors[3][1] = 1;
		  neighbors[4][0] = 	x;  neighbors[4][1] = 1;
		  neighbors[5][0] = x + 1;  neighbors[5][1] = 1;
	  } else if(y == height - 1){
		  // Borde inferior
		  neighbors[0][0] = x - 1;  neighbors[0][1] = height - 1 - 1;
		  neighbors[1][0] = 	x;  neighbors[1][1] = height - 1 - 1;
		  neighbors[2][0] = x + 1;  neighbors[2][1] = height - 1 - 1;
		  neighbors[3][0] = x - 1;  neighbors[3][1] = height - 1;
		  neighbors[4][0] = 	x;  neighbors[4][1] = height - 1;
		  neighbors[5][0] = x + 1;  neighbors[5][1] = height - 1;
	  } else {
		  // Centro
		  neighbors[0][0] = x - 1;  neighbors[0][1] = y - 1;
		  neighbors[1][0] = 	x;  neighbors[1][1] = y - 1;
		  neighbors[2][0] = x + 1;  neighbors[2][1] = y - 1;
		  neighbors[3][0] = x - 1;  neighbors[3][1] = y;
		  neighbors[4][0] = 	x;  neighbors[4][1] = y;
		  neighbors[5][0] = x + 1;  neighbors[5][1] = y;
		  neighbors[6][0] = x - 1;  neighbors[6][1] = y + 1;
		  neighbors[7][0] = 	x;  neighbors[7][1] = y + 1;
		  neighbors[8][0] = x + 1;  neighbors[8][1] = y + 1;
	  }	
	  return neighbors;
  }
  
  public int[] avgColors(int x, int y){
	  	  
	  // Array to store the three averages for each pixel  color.
	  int[] avgResult = new int[3];
	  
	  // Red average
	  avgResult[0] = 0;
	  // Green average	  
	  avgResult[1] = 0;
	  // Blue average
	  avgResult[2] = 0;
	  
	  // Get a list of the locations of the neighbors of the pixel located
	  // at position (x,y)
	  int[][] vecinos = this.neighbors(x, y);
	  
	  // Calculates the sum of the values for each color (red, green, blue) 
	  // for the list of neighbors
	  int[] sums = new int[3];
	  
	  int k = 0;			  
	  while(k < vecinos.length && vecinos[k][0] != -2 ){
		  sums[0] = sums[0] + this.getRed(vecinos[k][0], vecinos[k][1]);
		  sums[1] = sums[1] + this.getGreen(vecinos[k][0], vecinos[k][1]);
		  sums[2] = sums[2] + this.getBlue(vecinos[k][0], vecinos[k][1]);
		  k++;
	  }  
	  
	  // Calculates the averages for each color.	  
	  avgResult[0] = sums[0]/k;
	  avgResult[1] = sums[1]/k;
	  avgResult[2] = sums[2]/k;	  
	  	  
	  return avgResult;
  }

  /**
   * mag2gray() maps an energy (squared vector magnitude) in the range
   * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
   * is logarithmic, but shifted so that values of 5,080 and below map to zero.
   *
   * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
   * correct images and pass the autograder.
   *
   * @param mag the energy (squared vector magnitude) of the pixel whose
   * intensity we want to compute.
   * @return the intensity of the output pixel.
   */
  private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }

  /**
   * sobelEdges() applies the Sobel operator, identifying edges in "this"
   * image.  The Sobel operator computes a magnitude that represents how
   * strong the edge is.  We compute separate gradients for the red, blue, and
   * green components at each pixel, then sum the squares of the three
   * gradients at each pixel.  We convert the squared magnitude at each pixel
   * into a grayscale pixel intensity in the range 0...255 with the logarithmic
   * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
   * pixel intensities reflect the strength of the edges.
   *
   * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
   *
   * @return a grayscale PixImage representing the edges of the input image.
   * Whiter pixels represent stronger edges.
   */
  public PixImage sobelEdges() {	  	
	  PixImage outPutPixImage = new PixImage(width, height);
	  long energy = 0L;
	  
	  for(int i = 0; i < width; i ++){
		  for(int j = 0; j < height; j++){
			  energy = (long)gradientRed(i,j)[0]   * (long)gradientRed(i,j)[0]   +
					   (long)gradientRed(i,j)[1]   * (long)gradientRed(i,j)[1]   +
					   (long)gradientBlue(i,j)[0]  * (long)gradientBlue(i,j)[0]  +
					   (long)gradientBlue(i,j)[1]  * (long)gradientBlue(i,j)[1]  +
					   (long)gradientGreen(i,j)[0] * (long)gradientGreen(i,j)[0] +
					   (long)gradientGreen(i,j)[1] * (long)gradientGreen(i,j)[1];		 
			  outPutPixImage.setPixel(i, j, mag2gray(energy) ,mag2gray(energy), mag2gray(energy));
		  }
	  }	  
	  return outPutPixImage;
    // Don't forget to use the method mag2gray() above to convert energies to
    // pixel intensities.
  }
  
  public int[] gradientRed(int x, int y){
	  // S_x: Sobel x	 
	  int[][] S_x = {{1,0,-1},{2,0,-2},{1,0,-1}};
	  // S_y: Sobel y
	  int[][] S_y = {{1,2,1},{0,0,0},{-1,-2,-1}};
	  
	  int[] grad = {0,0};
	  
	  grad[0] = S_x[0][0]*getRed(neighbors(x,y,true)[0][0],neighbors(x,y,true)[0][1]) +
			    S_x[0][1]*getRed(neighbors(x,y,true)[1][0],neighbors(x,y,true)[1][1]) +
			    S_x[0][2]*getRed(neighbors(x,y,true)[2][0],neighbors(x,y,true)[2][1]) +
			    S_x[1][0]*getRed(neighbors(x,y,true)[3][0],neighbors(x,y,true)[3][1]) +
			    S_x[1][1]*getRed(neighbors(x,y,true)[4][0],neighbors(x,y,true)[4][1]) +
			    S_x[1][2]*getRed(neighbors(x,y,true)[5][0],neighbors(x,y,true)[5][1]) +
			    S_x[2][0]*getRed(neighbors(x,y,true)[6][0],neighbors(x,y,true)[6][1]) +
			    S_x[2][1]*getRed(neighbors(x,y,true)[7][0],neighbors(x,y,true)[7][1]) +
			    S_x[2][2]*getRed(neighbors(x,y,true)[8][0],neighbors(x,y,true)[8][1]);
	  
	  grad[1] = S_y[0][0]*getRed(neighbors(x,y,true)[0][0],neighbors(x,y,true)[0][1]) +
			    S_y[0][1]*getRed(neighbors(x,y,true)[1][0],neighbors(x,y,true)[1][1]) +
			    S_y[0][2]*getRed(neighbors(x,y,true)[2][0],neighbors(x,y,true)[2][1]) +
			    S_y[1][0]*getRed(neighbors(x,y,true)[3][0],neighbors(x,y,true)[3][1]) +
			    S_y[1][1]*getRed(neighbors(x,y,true)[4][0],neighbors(x,y,true)[4][1]) +
			    S_y[1][2]*getRed(neighbors(x,y,true)[5][0],neighbors(x,y,true)[5][1]) +
			    S_y[2][0]*getRed(neighbors(x,y,true)[6][0],neighbors(x,y,true)[6][1]) +
			    S_y[2][1]*getRed(neighbors(x,y,true)[7][0],neighbors(x,y,true)[7][1]) +
			    S_y[2][2]*getRed(neighbors(x,y,true)[8][0],neighbors(x,y,true)[8][1]);
	  
	  return grad;
  }
  
  public int[] gradientBlue(int x, int y){
	  // S_x: Sobel x	 
	  int[][] S_x = {{1,0,-1},{2,0,-2},{1,0,-1}};
	  // S_y: Sobel y
	  int[][] S_y = {{1,2,1},{0,0,0},{-1,-2,-1}};
	  
	  int[] grad = {0,0};
	  
	  grad[0] = S_x[0][0]*getBlue(neighbors(x,y,true)[0][0],neighbors(x,y,true)[0][1]) +
			    S_x[0][1]*getBlue(neighbors(x,y,true)[1][0],neighbors(x,y,true)[1][1]) +
			    S_x[0][2]*getBlue(neighbors(x,y,true)[2][0],neighbors(x,y,true)[2][1]) +
			    S_x[1][0]*getBlue(neighbors(x,y,true)[3][0],neighbors(x,y,true)[3][1]) +
			    S_x[1][1]*getBlue(neighbors(x,y,true)[4][0],neighbors(x,y,true)[4][1]) +
			    S_x[1][2]*getBlue(neighbors(x,y,true)[5][0],neighbors(x,y,true)[5][1]) +
			    S_x[2][0]*getBlue(neighbors(x,y,true)[6][0],neighbors(x,y,true)[6][1]) +
			    S_x[2][1]*getBlue(neighbors(x,y,true)[7][0],neighbors(x,y,true)[7][1]) +
			    S_x[2][2]*getBlue(neighbors(x,y,true)[8][0],neighbors(x,y,true)[8][1]);
	  
	  grad[1] = S_y[0][0]*getBlue(neighbors(x,y,true)[0][0],neighbors(x,y,true)[0][1]) +
			    S_y[0][1]*getBlue(neighbors(x,y,true)[1][0],neighbors(x,y,true)[1][1]) +
			    S_y[0][2]*getBlue(neighbors(x,y,true)[2][0],neighbors(x,y,true)[2][1]) +
			    S_y[1][0]*getBlue(neighbors(x,y,true)[3][0],neighbors(x,y,true)[3][1]) +
			    S_y[1][1]*getBlue(neighbors(x,y,true)[4][0],neighbors(x,y,true)[4][1]) +
			    S_y[1][2]*getBlue(neighbors(x,y,true)[5][0],neighbors(x,y,true)[5][1]) +
			    S_y[2][0]*getBlue(neighbors(x,y,true)[6][0],neighbors(x,y,true)[6][1]) +
			    S_y[2][1]*getBlue(neighbors(x,y,true)[7][0],neighbors(x,y,true)[7][1]) +
			    S_y[2][2]*getBlue(neighbors(x,y,true)[8][0],neighbors(x,y,true)[8][1]);
	  
	  return grad;
  }
  
  public int[] gradientGreen(int x, int y){
	  // S_x: Sobel x	 
	  int[][] S_x = {{1,0,-1},{2,0,-2},{1,0,-1}};
	  // S_y: Sobel y
	  int[][] S_y = {{1,2,1},{0,0,0},{-1,-2,-1}};
	  
	  int[] grad = {0,0};
	  
	  grad[0] = S_x[0][0]*getGreen(neighbors(x,y,true)[0][0],neighbors(x,y,true)[0][1]) +
			    S_x[0][1]*getGreen(neighbors(x,y,true)[1][0],neighbors(x,y,true)[1][1]) +
			    S_x[0][2]*getGreen(neighbors(x,y,true)[2][0],neighbors(x,y,true)[2][1]) +
			    S_x[1][0]*getGreen(neighbors(x,y,true)[3][0],neighbors(x,y,true)[3][1]) +
			    S_x[1][1]*getGreen(neighbors(x,y,true)[4][0],neighbors(x,y,true)[4][1]) +
			    S_x[1][2]*getGreen(neighbors(x,y,true)[5][0],neighbors(x,y,true)[5][1]) +
			    S_x[2][0]*getGreen(neighbors(x,y,true)[6][0],neighbors(x,y,true)[6][1]) +
			    S_x[2][1]*getGreen(neighbors(x,y,true)[7][0],neighbors(x,y,true)[7][1]) +
			    S_x[2][2]*getGreen(neighbors(x,y,true)[8][0],neighbors(x,y,true)[8][1]);
	  
	  grad[1] = S_y[0][0]*getGreen(neighbors(x,y,true)[0][0],neighbors(x,y,true)[0][1]) +
			    S_y[0][1]*getGreen(neighbors(x,y,true)[1][0],neighbors(x,y,true)[1][1]) +
			    S_y[0][2]*getGreen(neighbors(x,y,true)[2][0],neighbors(x,y,true)[2][1]) +
			    S_y[1][0]*getGreen(neighbors(x,y,true)[3][0],neighbors(x,y,true)[3][1]) +
			    S_y[1][1]*getGreen(neighbors(x,y,true)[4][0],neighbors(x,y,true)[4][1]) +
			    S_y[1][2]*getGreen(neighbors(x,y,true)[5][0],neighbors(x,y,true)[5][1]) +
			    S_y[2][0]*getGreen(neighbors(x,y,true)[6][0],neighbors(x,y,true)[6][1]) +
			    S_y[2][1]*getGreen(neighbors(x,y,true)[7][0],neighbors(x,y,true)[7][1]) +
			    S_y[2][2]*getGreen(neighbors(x,y,true)[8][0],neighbors(x,y,true)[8][1]);
	  
	  return grad;
  }


  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);
    
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y], (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * equals() checks whether two images are the same, i.e. have the same
   * dimensions and pixels.
   *
   * @param image a PixImage to compare with "this" PixImage.
   * @return true if the specified PixImage is identical to "this" PixImage.
   */
  public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * main() runs a series of tests to ensure that the convolutions (box blur
   * and Sobel) are correct.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                                                   { 30, 120, 250 },
                                                   { 80, 250, 255 } });
    System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                       "Input image:");
    System.out.print(image1);
    doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 3x3 image.");
    doTest(image1.boxBlur(1).equals(
           array2PixImage(new int[][] { { 40, 108, 155 },
                                        { 81, 137, 187 },
                                        { 120, 164, 218 } })),
           "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
    doTest(image1.boxBlur(2).equals(
           array2PixImage(new int[][] { { 91, 118, 146 },
                                        { 108, 134, 161 },
                                        { 125, 151, 176 } })),
           "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
    doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
           "Incorrect box blur (1 rep + 1 rep):\n" +
           image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

    System.out.println("Testing edge detection on a 3x3 image.");
    doTest(image1.sobelEdges().equals(
           array2PixImage(new int[][] { { 104, 189, 180 },
                                        { 160, 193, 157 },
                                        { 166, 178, 96 } })),
           "Incorrect Sobel:\n" + image1.sobelEdges());


    PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                                                   { 0, 0, 100 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image2);
    doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 2x3 image.");
    doTest(image2.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 50, 75 },
                                        { 25, 50, 75 } })),
           "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));

    System.out.println("Testing edge detection on a 2x3 image.");
    doTest(image2.sobelEdges().equals(
           array2PixImage(new int[][] { { 122, 143, 74 },
                                        { 74, 143, 122 } })),
           "Incorrect Sobel:\n" + image2.sobelEdges());
  }
}