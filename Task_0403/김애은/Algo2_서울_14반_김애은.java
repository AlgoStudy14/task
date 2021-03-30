package algo;

import java.util.Scanner;
//subset+dfs
public class Algo2_서울_14반_김애은 {
	static int N,M;//각 개수
	static int[] chu;//추무게
	static int[] gu;//구슬무게
	static boolean check[][];//확인용
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt(); //추 개수
		chu = new int[N]; //추 배열
		for(int i=0;i<N;i++) {
			chu[i]=sc.nextInt();//추 넣음
		}
		M= sc.nextInt();//구슬 개수
		gu = new int[M];
		for(int i=0;i<M;i++) {//구슬 넣음
			gu[i]=sc.nextInt();
		}
		check=new boolean[N+1][99999]; //추 개수에 따른 무게들 최대 구술 40000+ 추 500 *30..?
		dfs(0,0);//dfs를 사용해서 지금까지 만들 수 있는 추 무게 다 구함
		for(int i=0;i<M;i++) {//구슬 만큼 print
			boolean result=false;//false라면 n
			for(int j=0;j<N+1;j++) {//구슬 개수만큼 다 돈다
				if(check[j][gu[i]]) //구슬 무게가 true인게 있다면?
					result=true;//true
			}
			if(result) System.out.print("Y"+" ");//y
			else System.out.print("N"+" ");//n
		}
	}
	private static void dfs(int cnt, int w) {//추 개수만큼다 더했다 뺏다 한다
		if(check[cnt][w]) return;//이미 체크한거라면?
		check[cnt][w]=true;//체크 안되어 있으면 그대로
		//System.out.println(w);
		if(cnt==N)return;//개수만큼 돌았으면 끝
		
		dfs(cnt+1,w+chu[cnt]);//더하기
		dfs(cnt+1,w);//그대로
		//if(w-chu[cnt]<0)return; 
		dfs(cnt+1,Math.abs(w-chu[cnt]));//다른편 저울에 넣기
	}
}
