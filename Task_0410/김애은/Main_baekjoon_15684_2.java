package algo0405;
/*
 * 사다리 문제
 * 처음에 가로, 개수, 세로 입력받는다.
 * 처음엔 아주 쉽게 생각했다. 짝수여야 된다로
 * 했더니 아예 다른 결과..
 * 다시 보니 완탐이다.
 * 1,2,3을 다 해봐야되는 문제
 * 적은걸 구하기 때문에 1,2,3했을때 더 적은게 나오면 그걸로 끝
 * 1일때 다 넣어보고 다 돌아보고 나서 결정 나는 문제
 * 기본 dfs를 응용
 */
import java.util.Scanner;

public class Main_baekjoon_15684_2 {
	static final int maxY=31;// 가장 큰 세로
	static final int maxX=11;//가로
	static int N,M,C;
	static boolean V[][];
	static boolean isTrue;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		C=sc.nextInt();
		M=sc.nextInt();
		V=new boolean[maxY][maxX];
		
		for(int i=0;i<C;i++) {
			V[sc.nextInt()][sc.nextInt()]=true;
		}
		for(int i=0;i<=3;i++) {
			dfs(1,0,i);
			if(isTrue) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(-1);
	}
	private static void dfs(int y, int cnt,int result) {
		if(cnt==result) {//만약 넣은 개수가 위에서 정한 숫자와 같으면
			boolean isPossible=true;
			for(int i=1;i<=N;i++) {
				int col=i;
				for(int j=1;j<=M;j++) {
					if(V[j][col]) col++;
					else if(col>1 && V[j][col-1])col--;
				}
				if(i!=col) {
					isPossible=false;
					break;
				}
			}
			if(isPossible)
				isTrue=true;
			return;
		}
		for(int i=y;i<=M;i++) {
			for(int j=1;j<N;j++) {
				if(!V[i][j-1] && !V[i][j] && !V[i][j+1]) {// 없을경우
					V[i][j]=true;//내려온다
					dfs(i,cnt+1,result);//다시 돌아본다
					V[i][j]=false;
				}
			}
		}
		return;
	}
}
