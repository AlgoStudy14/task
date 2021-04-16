package study0417;

import java.util.Scanner;
/*
 * arr로 받은 캐릭터들을 상오하왼으로 d로 사용한다.
 * 메모이제이션 문제
 * che에 가능하면1 안되면 -1을 저장해서 1끼리 다 더한다.
 */
public class Main_17090_미로 {
	static int N,M;
	static int arr[][];
	static int che[][];
	static int dx[] = {0,1,0,-1}; //상오하왼
	static int dy[] = {-1,0,1,0};
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		arr=new int[N][M];
		che=new int[N][M];
		for(int i=0;i<N;i++) {
			String st = sc.next();
			for(int j=0;j<M;j++) {
				char temp = st.charAt(j);
				if(temp=='U')arr[i][j]=0;
				else if(temp=='R') arr[i][j]=1;
				else if(temp=='D')arr[i][j]=2;
				else arr[i][j]=3;
			}
		}
		int answer=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				int res=go(i,j);
				if(res>=0)
					answer+=1;
			}
		}
		System.out.println(answer);
	}
	
	private static int go(int i, int j) {
		int my = i+dy[arr[i][j]];
		int mx = j+dx[arr[i][j]];
		if(check(my,mx)) return che[i][j]=1;
		else if(che[my][mx]!=0) return che[my][mx];
		che[i][j]=-1;
		return che[i][j]=go(my,mx);
	}

	private static boolean check(int i,int j) {
		if(i<0 || j<0 || i>=N||j>=M) return true;
		return false;
	}

}
