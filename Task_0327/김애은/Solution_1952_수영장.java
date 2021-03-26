package algo0326;

import java.util.Scanner;
/*
 * 가장 적은 수영장 비용
 * 하나씩 if문 사용한거
 * dp 점화식
 * 
 */
public class Solution_1952_수영장 {

	static int min=Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T=sc.nextInt();
		for (int t = 1; t <= T; t++) {
			int []arr=new int[13];
			int []month=new int[13];
			int []tot = new int[13];
			int day=sc.nextInt();
			int omonth=sc.nextInt();
			int tmonth=sc.nextInt();
			int year=sc.nextInt();
			for(int i=1;i<=12;i++) {
				arr[i]=sc.nextInt();
			}
			for(int i=1;i<=12;i++) {
				month[i]=Math.min(arr[i]*day, omonth);
			}
			for(int i=1;i<=12;i++) {
				tot[i]=tot[i-1]+month[i];//누적
				if(i>2) {//3개월부터
					if(tot[i]>tot[i-3]+tmonth)
						tot[i]=tot[i-3]+tmonth;
				}
			}
			if(tot[12]>year)
				tot[12]=year;
			System.out.println("#"+t+" "+tot[12]);
		}
	}

}
