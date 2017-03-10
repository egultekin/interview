package dp;

public class RockStar {

	public int  getNumSongs(int ff, int fs, int sf, int ss) {
		int sumF = ff+fs;
		int sumS = ss+sf;
		boolean f = false;
		
		if (sumF > 0) f = true;
		else f = sumF >= sumS;
		
		while(true) {
			if (f && fs > 0) {
				if (sf > 0 || ff == 0) {
					fs--;
					f = !f;
				} else ff--;
			} else if (!f && sf > 0) {
				if (fs > 0 || ss == 0) {
					sf--;
					f = !f;
				} else ss--;
			} else if (f && ff > 0) ff--;
			else ss--;
			
			if ((f && (ff + fs) == 0) || (!f && (ss+sf) == 0)) break;
		}
		
		return sumF+sumS-ff-fs-ss-sf;
	}
	
	public static void main(String[] args) {
		System.out.println(new RockStar().getNumSongs(192, 279, 971, 249));
	}

}
