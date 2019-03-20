package swexpertacademy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class P_5648_Simulation_AD3 {
	static int[][] dxdy = {{0,1},{0,-1},{-1,0},{1,0}};
	static int N,result;
	static HashSet<Atom> atom_set;
	static HashSet<Atom> crash_set;
	static HashMap<String,Atom> crash_map;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		for (int test = 1; test < t+1; test++) {
			result = 0;
			N = scan.nextInt();
			atom_set = new HashSet<>();
			for (int i = 0; i < N; i++) {
				Atom a = new Atom(scan.nextInt()*2, scan.nextInt()*2, scan.nextInt(), scan.nextInt());
				atom_set.add(a);
			}
			while(N>=1) {
				Iterator<Atom> it = atom_set.iterator();
				crash_set = new HashSet<>();
				crash_map = new HashMap<>();
				while(it.hasNext()) {
					Atom a = it.next();
					move_atom(a,it);
					String ij_str = a.px+"|"+a.py;
					if(crash_map.containsKey(ij_str)) {
						crash_set.add(a);
						crash_set.add(crash_map.get(ij_str));
					}else {
						crash_map.put(ij_str, a);
					}
				}
				Iterator<Atom> it2 = crash_set.iterator();
				while(it2.hasNext()) {
					Atom a = it2.next();
					explode_atom(a);
				}
			}
			System.out.println("#"+test+" "+result);
		}
	}
	static void explode_atom(Atom a) {
		result+=a.power;
		remove_atom_byset(a,null);
	}
	
	static void remove_atom_byset(Atom a, Iterator<Atom> it) {
		if(it==null) {
			atom_set.remove(a);
		}else {
			it.remove();
		}
		N--;
	}
	
	static void move_atom(Atom a,Iterator<Atom> it) {
		a.px+=dxdy[a.dict][0];
		a.py+=dxdy[a.dict][1];
		if(a.px<-2000||a.py<-2000||a.px>2000||a.py>2000) {
			remove_atom_byset(a,it);
		}
	}
}

class Atom {
	int px;
	int py;
	int dict;
	int power;
	public Atom(int px, int py, int dict, int power) {
		this.px = px;
		this.py = py;
		this.dict = dict;
		this.power = power;
	}
}