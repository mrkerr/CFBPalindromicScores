package cfbScores;

import java.util.Comparator;

public class PalindromeCompare implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		return Integer.compare(Integer.parseInt(o1.replaceAll("[^0-9]", "")), Integer.parseInt(o2.replaceAll("[^0-9]", "")));

}
}