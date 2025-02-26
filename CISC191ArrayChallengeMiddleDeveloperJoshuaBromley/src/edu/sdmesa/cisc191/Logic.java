package edu.sdmesa.cisc191;

import java.awt.Color;

import edu.gatech.cc.DigitalPicture;
import edu.gatech.cc.Picture;
import edu.gatech.cc.Pixel;
/**
 * Lead Author(s):
 * 
 * @author
 * @author
 *         <<add additional lead authors here, with a full first and last name>>
 * 
 * Other contributors:
 *         <<add additional contributors (mentors, tutors, friends) here, with
 *         contact information>>
 * 
 * References:
 *         Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 *         Retrieved from
 *         https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 *         <<add more references here>>
 * 
 * Version/date:
 * 
 * Responsibilities of class:
 * 
 */
public class Logic
{


	/*
	 * Use the main method in this file to visually inspect the "filters" that you are
	 * applying to the image(s)! Please see the comment(s) for examples.
	 * 
	 */
	public static void main(String[] arg) {
		
		// Create a myPicture object from the provided file name. 
		// If you use your own image, be sure to place it in the images folder of this project 
		DigitalPicture myPicture = new Picture("fireFall.png");
		myPicture.setTitle("Original Image");
		
		//Use the explore method of the object to view the picture.
		myPicture.explore();
		
		/*
		 * Apply the filter then invoke explorer again to view the changes. :)
		 * ------------------------------------------------------------------------------------
		 */
		
		//Apply one of the filters then view the image again with explore!
		flipVertical(myPicture); //<----- Change this to one of the other filters that you have written
		myPicture.setTitle("After Filter"); //change the title of the JFrame
		myPicture.explore();
		
	}
	
	
	/**
	 * Alters the picture so that each pixel's blue channel has been zeroed out. All
	 * other channels remain the same
	 * 
	 * @param picture the image to be altered
	 */
	public static void zeroBlue(DigitalPicture picture)
	{

		//obtain a representation of the picture as a 2D array of Pixel objects
		Pixel[][] pixel2D = picture.getPixels2D();
		
		//Traverse the 2D array
		for (int row = 0; row < pixel2D.length; row++)
		{
			for (int col = 0; col < pixel2D[row].length; col++)
			{
				//invoke the setBlue method on each Pixel object
				pixel2D[row][col].setBlue(0);
			}
		}
	
	}
	
	/**
	 * Black and White
	 * Purpose: Set entire picture to black and white by taking the average 
	 * of all color values and setting each RGB color value to said average
	 * @param picture Declares what picture is being edited
	 */
	
	public static void blackAndWhite(DigitalPicture picture) {
	
		//obtain a representation of the picture as a 2D array of Pixel objects
		Pixel[][] pixel2D = picture.getPixels2D();
		
		//Traverse the 2D array
		for (int row = 0; row < pixel2D.length; row++)
		{
			for (int col = 0; col < pixel2D[row].length; col++)
			{
				//Average out the values of r,g,b values
				int val = (int) pixel2D[row][col].getAverage();
				
				//Set all current pixel color to found average
				pixel2D[row][col].setRed(val);
				pixel2D[row][col].setGreen(val);
				pixel2D[row][col].setBlue(val);
				
			}
		}
	}
	
	/**
	 * Negative
	 * Purpose: Sets each RBG value to the opposite of its current value by subtracting 
	 * the current value from 255, the max RBG value.
	 * @param picture Declares what picture is being edited
	 */
	public static void negative(DigitalPicture picture) {
		
		//obtain a representation of the picture as a 2D array of Pixel objects
		Pixel[][] pixel2D = picture.getPixels2D();
		
		//Traverse the 2D array
		for (int row = 0; row < pixel2D.length; row++)
		{
			for (int col = 0; col < pixel2D[row].length; col++)
			{
				//Set inverse values for all colors
				pixel2D[row][col].setRed(255 - pixel2D[row][col].getRed());
				pixel2D[row][col].setGreen(255 - pixel2D[row][col].getGreen());
				pixel2D[row][col].setBlue(255 - pixel2D[row][col].getBlue());
				
			}
		}
	}
	
	/**
	 * makeSunset
	 * Purpose: Increases all red values, and decreases all blue and green values 
	 * by multiplying the red value by a parameter > 1, and the blue and 
	 * green values by a parameter < 1.
	 * @param picture Declares what picture is being edited
	 * @param redIntensity Intensity multiplier multiplied by the current red value 
	 * to increase said red value.
	 * @param greenBlueReduction Reduction multiplier multiplied by the current blue and 
	 * green values to decrease said blue and green values.
	 */
	public static void makeSunset(DigitalPicture picture, double redIntensity, double greenBlueReduction) {
		
		//obtain a representation of the picture as a 2D array of Pixel objects
		Pixel[][] pixel2D = picture.getPixels2D();
		
		//Traverse the 2D array
		for (int row = 0; row < pixel2D.length; row++)
		{
			for (int col = 0; col < pixel2D[row].length; col++)
			{
				//Multiply current red value by redIntensity parameter
				pixel2D[row][col].setRed((int) (pixel2D[row][col].getRed() * redIntensity) );
				
				//Multiply current blue and green values by greenBlueReduction parameter
				pixel2D[row][col].setBlue((int) (pixel2D[row][col].getBlue() * greenBlueReduction) );
				pixel2D[row][col].setGreen((int) (pixel2D[row][col].getGreen() * greenBlueReduction) );
				
			}
		}
	}
	
	/**
	 * flipHorizontal
	 * Purpose: Saves the current color of the pixel in a temporary variable, then swaps the color values 
	 * of the current pixel with the color values of the pixel in the same row, but complete opposite indexed column
	 * (pixel2D[row].length-1-col)(Entire row length, minus 1, minus the current column). 
	 * Additionally, the for-loop is edited to only move halfway through the columns, in order to prevent repetitive swapping. 
	 * @param picture Declares what picture is being edited
	 */
	
	public static void flipHorizontal(DigitalPicture picture) {
		
		//obtain a representation of the picture as a 2D array of Pixel objects
		Pixel[][] pixel2D = picture.getPixels2D();
		
		//Traverse the 2D array
		for (int row = 0; row < pixel2D.length; row++)
		{
			for (int col = 0; col < pixel2D[row].length / 2; col++)
			{
				//Save current pixel colors and declare variable for indexing opposite pixel.
				Color colorSwap = pixel2D[row][col].getColor();
				int swappingColumn = pixel2D[row].length-1-col;
				
				//Set current pixel colors to opposite indexed pixel colors, and opposite indexed pixel colors to the temporary current pixel colors.
				pixel2D[row][col].setColor(pixel2D[row][swappingColumn].getColor());
				pixel2D[row][swappingColumn].setColor(colorSwap);
			}
		}
	}
	
	/**
	 * flipVertical
	 * Purpose: Saves the current color of the pixel in a temporary variable, then swaps the color values 
	 * of the current pixel with the color values of the pixel in the same column, but complete opposite indexed row.
	 * (pixel2D[col].length-1-row)(Entire column length, minus 1, minus the current row). 
	 * Additionally, the for-loop is edited to only move halfway through the rows, in order to prevent repetitive swapping. 
	 * @param picture Declares what picture is being edited
	 */
	
	public static void flipVertical(DigitalPicture picture) {
		
		//obtain a representation of the picture as a 2D array of Pixel objects
		Pixel[][] pixel2D = picture.getPixels2D();
		
		//Traverse the 2D array
		for (int row = 0; row < pixel2D.length / 2; row++)
		{
			for (int col = 0; col < pixel2D[row].length; col++)
			{
				//Save current pixel colors and declare variable for indexing opposite pixel.
				Color colorSwap = pixel2D[row][col].getColor();
				int swappingRow = pixel2D.length-1-row;
				
				//Set current pixel colors to opposite indexed pixel colors, and opposite indexed pixel to temp held current pixel colors.
				pixel2D[row][col].setColor(pixel2D[swappingRow][col].getColor());
				pixel2D[swappingRow][col].setColor(colorSwap);
			}
		}
	}

	/**
	 * blur
	 * Purpose: Separate by color, finds a total sum for each color value in a 2x2 area around the current pixel.
	 * These sums are individually averaged by dividing by 4 (4 total pixels in 2x2 area).
	 * The current pixel's color values are set to these averages.
	 * @param picture Declares what picture is being edited
	 */
	
	public static void blur(DigitalPicture picture) {
		
		//obtain a representation of the picture as a 2D array of Pixel objects
		Pixel[][] pixel2D = picture.getPixels2D();
		
		//Traverse the 2D array
		for (int row = 0; row < pixel2D.length-1; row++)
		{
			for (int col = 0; col < pixel2D[row].length-1; col++)
			{
				
				//Find average blue value of 2x2 area
				int redSum = pixel2D[row][col].getRed() + pixel2D[row+1][col].getRed() + pixel2D[row][col+1].getRed() + pixel2D[row+1][col+1].getRed();
				int redAverage = redSum / 4;
				
				//Find average blue value of 2x2 area
				int greenSum = pixel2D[row][col].getGreen() + pixel2D[row+1][col].getGreen() + pixel2D[row][col+1].getGreen() + pixel2D[row+1][col+1].getGreen();
				int greenAverage = greenSum / 4;
				
				//Find average blue value of 2x2 area
				int blueSum = pixel2D[row][col].getBlue() + pixel2D[row+1][col].getBlue() + pixel2D[row][col+1].getBlue() + pixel2D[row+1][col+1].getBlue();
				int blueAverage = blueSum / 4;
				
				//Set found averages as the color values for the current pixel.
				pixel2D[row][col].setRed(redAverage);
				pixel2D[row][col].setGreen(greenAverage);
				pixel2D[row][col].setBlue(blueAverage);
			}		
		}
	}
	
}
