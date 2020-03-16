/**
 * @author: Mrs. Miller
 * MODIFIED BY: Sohail Shaik 
 * Assignment: Slurpry.java
 * Date: 3/1/2019
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SSSlurpy {
	public static void main(String[] args) {
		Scanner sc;
		try {
			sc = new Scanner(new File("data.txt"));
			int numWords = sc.nextInt();
			System.out.println("SLURPYS OUTPUT");
			for(int i = 0; i<numWords; i++){
				String word = sc.next();
				//Test each method as you write it by changing the method called below
				//				System.out.println("word : " + word + " " + isSlump(word));
				//				System.out.println("word : " + word + " " + isSlimp(word));
				//				System.out.println("word : " + word + " " + isSlurpy(word));
				//System.out.print((i+1) +": ");
				if(isSlurpy(word))
					System.out.println("YES");
				else
					System.out.println("NO");
			}
			System.out.println("END OF OUTPUT");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Determines whether or not w is a slump, a character string with the following properties:
	 * 1.	Its first character is either a 'D' or an 'E'.
	 * 2.	The first character is followed by a string of one or more 'F's.
	 * 3.	The string of one or more 'F's is followed by either a Slump or a 'G'. The Slump or 'G'
	 * 		that follows the F's ends the Slump. For example DFFEFFFG is a Slump since it has a 'D'
	 *		for its first character, followed by a string of two F's, and ended by the Slump 'EFFFG'.
	 *4.	Nothing else is a Slump.
	 * @param w is a non-null character string
	 * @return true if w follows above rules, false otherwise
	 */
	public static boolean isSlump(String w){
		if(0 == w.length()) {
			return false;
		}
		if(1 < w.length() && w.substring(0, 1).equals("D") || w.substring(0, 1).equals("E")) {
			return isSlump(w.substring(1));
		}else if (1 < w.length() && w.substring(0,1).equals("F")) {
			if(2 == w.length() && w.substring(1,2).equals("G")) {
				return true;
			}
			return isSlump(w.substring(1));
		}

		return false;

	}

	/**
	 * Determines whether or not w is slimp, a character string that has the following properties
	 * 1.	Its first character is an 'A'.
	 * 2.	If it is a two character Slimp then its second and last character is an 'H'.
	 * 3.	If it is not a two character Slimp then it is in one of these two forms:
	 * 		a) 'A' followed by 'B' followed by a Slimp followed by a 'C'.
	 * 		b) 'A' followed by a Slump (see above) followed by a 'C'.
	 * 4.	Nothing else is a Slimp.
	 * @param w
	 * @return
	 */
	public static boolean isSlimp(String w){
		if(0 == w.length()) {
			return false;
		}
		if(2 <= w.length() && w.substring(0,1).equals("A")) {
			if(w.length() == 2 && w.substring(1,2).equals("H")) {
				return true;
			}else if(2 < w.length() && w.substring(1,2).equals("B")) {
				if(isSlimp(w.substring(2,w.length()-1))) {
					if(w.substring(w.length()-1, w.length()).equals("C")) {
						return true;
					}
				}
			}else if(2 < w.length() && isSlump(w.substring(2,w.length()-1))) {
				if(w.substring(w.length()-1, w.length()).equals("C")) {
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * Determines whether or not w is a slimp followed by a slump
	 * @param w a non-null character string
	 * @return true if w is a slimp followed by a slump, false otherwise
	 */
	public static boolean isSlurpy(String w){
		return isSlurpy(w,1);
	}

	private static boolean isSlurpy(String w, int index) {
		if(isSlimp(w.substring(0,index))) {
			if(isSlump(w.substring(index))) {
				return true;
			}
		}else if(index+1 < w.length()) {
			return isSlurpy(w, index+1);
		}
		return false;
	}
}