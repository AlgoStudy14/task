package algo_myselr;

import java.util.ArrayList;
import java.util.Scanner;

public class Main_baek2563 {
	static int arr[][]=new int[100][100];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		int tot=0;
		ArrayList<Point> aa = new ArrayList<>();
		for(int i=0;i<T;i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			aa.add(new Point(a,b));
		}
		for(Point ab:aa) {
			for(int j=ab.y;j<ab.y+10;j++) {
				for(int i=ab.x;i<ab.x+10;i++) {
					arr[j][i]=1;
				}			
			}
		}
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(arr[j][i]==1)
					tot++;
			}
		}
		System.out.println(tot);
	}
}
class Point {
	int x;
	int y;
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
}
