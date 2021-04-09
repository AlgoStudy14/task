package algo0405;

import java.util.Scanner;

public class Main_10026적록색약 {
	static int N;
	static char[][] arr;
	static boolean[][]check;
	static boolean[][]red_check;
	static int red,non_red;
	static int dx[]= {0,0,-1,1};
	static int dy[]= {1,-1,0,0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		arr=new char[N][N];
		check=new boolean[N][N];
		red_check=new boolean[N][N];
		for(int i=0;i<N;i++) {
			String s = sc.next();
			for(int j=0;j<N;j++) {
				arr[i][j]=s.charAt(j);
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!check[i][j]) {
					nonjek(i,j);
					non_red++;
				}
				if(!red_check[i][j]) {
					jek(i,j);
					red++;
				}
			}
		}
		System.out.println(non_red + " "+ red);
		
	}
	private static void jek(int i, int j) {
		if(red_check[i][j])return;
		char res=arr[i][j];
		red_check[i][j]=true;
		for(int d=0;d<4;d++) {
			int my=i+dy[d];
			int mx=j+dx[d];
			if(!che(my,mx)) {
				if(res=='R'||res=='G') {
					if(arr[my][mx]=='R'||arr[my][mx]=='G')
						jek(my,mx);
				}else {
					if(arr[my][mx]==res)
						jek(my,mx);
				}
			}
		}
	}
	private static void nonjek(int i, int j) {
		if(check[i][j]) return;
		char res=arr[i][j];
		check[i][j]=true;
		for(int d=0;d<4;d++) {
			int my=i+dy[d];
			int mx=j+dx[d];
			if(!che(my,mx)) {
				if(arr[my][mx]==res) {
					nonjek(my,mx);
				}
			}
		}
	}
	private static boolean che(int my, int mx) {
		if(my<0 || mx<0 ||my>=N|| mx>=N)
			return true;
		return false;
	}

}
