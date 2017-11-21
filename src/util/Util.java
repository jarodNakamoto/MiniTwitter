package util;

import java.util.ArrayList;

public class Util{
	public static <E> String[] convertToArray(ArrayList<E> arr) {
		String[] f = new String[arr.size()];
		for(int i = 0; i < arr.size(); i++) {
			f[i] = arr.get(i).toString();
		}
		return f;
	}
	
	public static boolean isValidID(String id) {
		int spaceIndex = id.indexOf(" ");
		return spaceIndex == -1;
	}
}
