package cfbScores;

import java.awt.List;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CFBPalindromeScores {

	public static int count = 0;
	public static int count2 = 0;
	public static ArrayList<String> gameList = new ArrayList<>();

	public static void main(String[] args) {
		generatePalindromesOne();
		System.out.println(" ");
		System.out.println("Total number of games per team");
		countTeams(gameList);

		System.out.println("Total of: " + count + " games");
		System.out.println("Total of: " + count2 + " teams");
	}

	public static void countTeams(ArrayList<String> games) {
		ArrayList<String> toReturn = new ArrayList<>();
		for (int i = 0; i < games.size(); i++) {
			String teamName = "";
			boolean check = false;
			boolean checkTwo = false;
			for (int j = 0; j < games.get(i).length(); j++) {
				if (!Character.isDigit(games.get(i).charAt(j))) { // If the index has reached the first score
					teamName = teamName + games.get(i).charAt(j);
				}
				if (Character.isDigit(games.get(i).charAt(j)) && !check) {
					toReturn.add(teamName);
					check = true;
					if (Character.isDigit(games.get(i).charAt(j + 1))) {
						j = j + 1;
					}
					teamName = "";
				} else if (Character.isDigit(games.get(i).charAt(j)) && check && !checkTwo) {
					toReturn.add(teamName);
					checkTwo = true;
				}

			}
		}

		ArrayList<String> tempTwo = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<>();
		for (int z = 0; z < toReturn.size(); z++) {
			if (!temp.contains(toReturn.get(z))) {
				int counter = 0;
				String tempName = toReturn.get(z);
				temp.add(tempName);
				for (int y = z; y < toReturn.size(); y++) {
					if (toReturn.get(y).equals(tempName)) {
						counter++;
					}
				}
				tempTwo.add(tempName + counter);

			}
		}
		String[] forSorting = new String[tempTwo.size()];
		for (int q = 0; q < forSorting.length; q++) {
			forSorting[q] = tempTwo.get(q);
		}
		String toReturnString = sorter(forSorting);
		String[] splitArray = toReturnString.split(",");
		for (int r = 0; r < splitArray.length; r++) {
			String name = "";
			String spaces = "";
			int number = 0;
			for(int u=0; u<splitArray[r].length(); u++){
				if(Character.isDigit(splitArray[r].charAt(u))){
					name = splitArray[r].substring(0, u);
					number = Integer.parseInt(splitArray[r].replaceAll("[^0-9]", ""));
					int spaceToAdd = 30-splitArray[r].length();
					for(int e=0; e<spaceToAdd; e++){
						spaces = spaces + " ";
					}
					
					
				}
			}
			System.out.println(name+spaces+number);
			count2++;
		}

	}

	public static String sorter(String[] toSort) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < toSort.length; i++) {
			list.add(toSort[i]);
		}
		PalindromeCompare comparator = new PalindromeCompare();
		Collections.sort(list, comparator);
		String newList = Arrays.toString(list.toArray());
		return newList;

	}

	public static void generatePalindromesOne() {
		// The name of the file to open.
		String fileName = "src/cfbScores.txt";

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String Score = "";
			String toPrint = "";
			while ((line = bufferedReader.readLine()) != null) {
				toPrint = line;
				line = line.replaceAll("\\s+", "").substring(9);
				for (int i = 0; i < line.length(); i++) {
					if (Character.isDigit(line.charAt(i))) {
						Score = Score + line.charAt(i);

					}

				}
				if (Score.equals(reverse(Score))) {
					System.out.println(toPrint);
					gameList.add(line);
					count++;
				} else {
					generatePalindromesTwo(line, toPrint);
				}
				Score = "";
				toPrint = "";

			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

	}

	public static void generatePalindromesTwo(String line, String toPrint) {
		String Score = "";
		int tracker = 0;
		boolean boolTrack = false;
		for (int i = 0; i < line.length(); i++) {
			if (Character.isDigit(line.charAt(i)) && !boolTrack) {
				tracker = i;
				i = i + 3;
				boolTrack = true;

			}

			else if (Character.isDigit(line.charAt(i)) && boolTrack) {
				Score = Score + line.charAt(i);

			} else {

			}

		}

		for (int i = tracker; i < tracker + 3; i++) {
			if (Character.isDigit(line.charAt(i))) {
				Score = Score + line.charAt(i);

			}
		}
		tracker = 0;
		boolTrack = false;

		if (Score.equals(reverse(Score))) {
			System.out.println(toPrint + " read backwards");
			gameList.add(line);
			count++;
		}
		Score = "";
		toPrint = "";

	}

	public static String reverse(String input) {
		char[] in = input.toCharArray();
		int begin = 0;
		int end = in.length - 1;
		char temp;
		while (end > begin) {
			temp = in[begin];
			in[begin] = in[end];
			in[end] = temp;
			end--;
			begin++;
		}
		return new String(in);
	}

}
