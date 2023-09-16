package dnaassignment;

import java.util.ArrayList;
import java.util.Scanner;

public class Task7DNA {

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		System.out.println("Welcome to the DNA-to-Protein generator!");
		System.out.println("You will enter in a DNA strand and all possible proteins will be outputted.\n");
		System.out.print("Please enter the DNA strand: ");
		String dnaStrand = in.next();
		System.out.println("\nAll possible proteins:\n ");
		Object [] possibleProteins = findAllProteins(dnaStrand);
		
		if (possibleProteins.length == 0) { //if there are no possible proteins
			System.out.print("This DNA strand does not encode any proteins. ");
		} 
		else {
			for (int i = 0; i < possibleProteins.length; i++) { //for loop which neatly outputs the possible proteins in lines
				System.out.print(possibleProteins[i].toString() + " \n");
			}
		}
	}
	
	/** This method takes one half of a DNA double helix and
	 * finds the complementary strand
	 * @param s - DNA strand (string)
	 * @return compliment strand (string)
	 */
	public static String findComplement(String s) {
		String compliment = "";
		
		for (int i = 0; i < s.length(); i++) { //loop which loops through the string of DNA
			char base = s.charAt(i);
			
			if (base == 'A' ) //if the base is an A
				base = 'T'; //change it to the complimentary T
			else if (base == 'T') //if the base is a T
				base = 'A'; //change it to the complimentary A
			else if (base == 'G') //if the base is a G
				base = 'C'; //change it to the complimentary C
			else //else the the base is a C
				base = 'G'; //change it to the complimentary G
			
			compliment += String.valueOf(base); //add the new base to the compliment string
		}
		
		return compliment;
	}
	
	/** This method takes one half of a DNA double helix,
	 * finds the complement and returns the RNA equivalent
	 * @param s - DNA strand (String)
	 * @return RNA strand (String)
	 */
	
	public static String findRNAStrand (String s) {
		String RNAStrand = "";
		
		for (int i = 0; i < s.length(); i++) { //loop which loops through the string of DNA
			char base = s.charAt(i);
			
			if (base == 'A' ) //if the base is an A
				base = 'U'; //change it to the complimentary U 
			else if (base == 'T') //if the base is a T
				base = 'A'; //change it to the complimentary A
			else if (base == 'G') //if the base is G
				base = 'C'; //change it to the complimentary C
			else //else the base is a C
				base = 'G'; //change it to the complimentary G
			
			RNAStrand += String.valueOf(base);
		}
		
		return RNAStrand;
	}
	
	/** This converts a valid codon into the appropriate amino acid
	 * @param s - the RNA Strand (string)
	 * @return the amino acid (string)
	 */
	public static String findAminoAcid(String s) {
		String[] AA_CODE ={"UUU","UUC","UUA","UUG","UCU","UCC","UCA","UCG","UAU","UAC","UAA","UAG","UGU","UGC","UGA","UGG","CUU","CUC","CUA","CUG","CCU","CCC","CCA","CCG","CAU","CAC","CAA","CAG","CGU","CGC","CGA","CGG","AUU","AUC","AUA","AUG","ACU","ACC","ACA","ACG","AAU","AAC","AAA","AAG","AGU","AGC","AGA","AGG","GUU","GUC","GUA","GUG","GCU","GCC","GCA","GCG","GAU","GAC","GAA","GAG","GGU","GGC","GGA","GGG",};
		String[] AA_CODE_VALUE = {"F","F","L","L","S","S","S","S","Y","Y","STOP","STOP","C","C","STOP","W","L","L","L","L","P","P","P","P","H","H","Q","Q","R","R","R","R","I","I","I","M","T","T","T","T","N","N","K","K","S","S","R","R","V","V","V","V","A","A","A","A","D","D","E","E","G","G","G","G",};
		String aminoAcid = "";
		
		for (int i = 0; i < s.length(); i = i + 3) { //this will loop through each codon, each set of 3
			String codon = s.substring(i, Math.min(s.length(), i + 3)); //isolate the codon from the string
			
			if (codon.length() < 3) //checks if the codon length is less than three, so there is no error
				return aminoAcid;
			
			for (int c = 0; c < AA_CODE.length; c++ ) { //this loops through the array filled with the amino acid codes, checking each index of the array
				
				if (AA_CODE[c].equals(codon)) //if the amino acid code at the counter is equal to the codon we are focusing on 
					aminoAcid += String.valueOf(AA_CODE_VALUE[c]); //find the value of that code and add it to a string with all the amino acids
			}
		}
		return aminoAcid;
	}
	
	/** This method takes an RNA strand and finds possible protein that can be
	 * constructed from the entire strand reading from the first nucleotide
	 * @param s - RNA Strand (string)
	 * @return protein (string)
	 */
	public static String findProtein(String s) {
		String aminoAcid = findAminoAcid(s);
		
		int index = aminoAcid.indexOf("STOP");
		
		if (index == - 1) //if stop is not found in the amino acids
			return "INVALID - NO STOP CODON";
		else
			return aminoAcid.substring(0, index);//return everything before the stop codon
	}
	
	/**
	 *  This method should return an array of integers which stores the position of the first 
	 *  nucleotide for all the start codon sequences.
	 * @param RNAStrand (string)
	 * @return indexes of start codons (array of integers)
	 */
	public static Object[] getStartCodonPositions(String RNAStrand) {
        ArrayList<Integer> startCodonPositions = new ArrayList<>();
		
		for (int i = 0; i < RNAStrand.length(); i++ ) { //for loop which loops through each letter in the RNA strand to find AUG
			String codon = RNAStrand.substring(i, Math.min(RNAStrand.length(), i + 3)); //isolate the codon from the main string
			if (codon.equals("AUG")) { //if the codon being observed is the start codon
				startCodonPositions.add(i);//add the index of it to the array called startCodonPositions
				//startCodonPositions[index] = i;
				//index++;
			}		
		}
		return startCodonPositions.toArray();
	}
	
	/**
	 * This method takes one half of a DNA double helix and find all
	 * possible proteins that can be constructed using any of the
	 * three valid reading frames and containing a start codon.
	 * It should return an array of Strings which holds all valid
	 * proteins.
	 * @param s - DNA strand (string)
	 * @return all possible proteins (array of strings)
	 */
	public static Object[] findAllProteins(String s) {
		String RNAStrand = findRNAStrand((s));
		ArrayList<String> possibleProteins = new ArrayList<String>();
		Object[] startCodonPositions = getStartCodonPositions(RNAStrand);
		
		for (int i = 0; i < startCodonPositions.length ; i++) { //for loop which loops through each start codon position to find possible proteins
			int index = (int) startCodonPositions[i];
			
			if (index > RNAStrand.length()) //if the index is out of bounds of the RNA strand
				break;
			
			String newRNAStrand = RNAStrand.substring(index); //make substring from start codon to the end
			
			for (int c = 0; c < newRNAStrand.length(); c = c + 3 ) { //for loop which loops through each letter in the RNA strand to find STOP
				String codon = newRNAStrand.substring(c, Math.min(newRNAStrand.length(), c + 3)); //isolate the codon from the main string
		
				if (codon.length() < 3) //if the codon is not complete, less than 3 letters
					break;
				
				if (codon.equals("UAG") || codon.equals("UAA") || codon.equals("UGA")) { //if the RNA Strand has a stop, showing that this is a possible protein strand
					possibleProteins.add(findProtein(newRNAStrand)); //add the proteins to the possible proteins array
					break;
				}
			}
		}
		
		return possibleProteins.toArray();
	}
}
