package misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Solution {
	
	private int val(int row, int col) {
		return row*26+col;
	}
	
	private int[] coord(String s) {
		int length = s.length();
		int column = s.charAt(length-1)-'A';
		int row = Integer.parseInt(s.substring(0, length-1));
		return new int[] {row, column};
	}
	    public String solution(int N, String S, String T) {
	    	ArrayList<Set<Integer>> shipList = new ArrayList<>();
	        String[] str = S.split(",");
	        for (int i = 0; i < str.length; i++) {
	        	String[] coord = str[i].split(" ");
	        	int[] uleft = coord(coord[0]);
	        	int[] lright = coord(coord[1]);
	        	
	        	Set<Integer> ship = new HashSet<Integer>();
	        	for (int r = uleft[0]; r <= lright[0]; r++)
	        		for (int c = uleft[1]; c <= lright[1]; c++) {
	        			ship.add(val(r, c));
	        		}
	        	
	        	shipList.add(ship);
	        }
	        
	        Set<Integer> hits = new HashSet<Integer>();
	        String[] pos = T.split(" ");
	        for (int i = 0; i < pos.length; i++) {
	        	int[] hitPos = coord(pos[i]);
	        	hits.add(val(hitPos[0], hitPos[1]));
	        }
	        
	        int sunken = 0;
	        int hit = 0;
	        for (int i = 0; i < shipList.size(); i++) {
	        	Set<Integer> ship = shipList.get(i);
	        	int count = 0;
	        	for (int p : ship)
	        		if (hits.contains(p)) count++;
	        	if (count == ship.size()) sunken++;
	        	else if (count > 0) hit++;
	        }
	        
	    	return sunken+","+hit;
	    }
}
