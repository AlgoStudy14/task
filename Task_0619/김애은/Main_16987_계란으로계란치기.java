package algo0619;
/*
 * dfs로 푼 문제
 * 계란을 다 돌면서 확인한다.
 * 두개 배열 만들어서 값 계산해서 돌기
 * 
 * <문제 요약>
 * 구해야 하는 것:달걀 깰 수 있는 최대개수 구하기
 * 제약 사항: 1<=N<8  Si(1 ≤ Si ≤ 300)와 무게 Wi(1 ≤ Wi ≤ 300)
 * 문제 유형: 백트래킹
 * 요구 개념: 백트래킹
 * 
 * <풀이법>
 * 이것도 백트레킹 문제이다.
 * 내가 백트레킹에서 하나도 없을때를 자꾸 무시하고 풀어서 틀린다...
 * 이 문제는 내구도와 무게를 각각 배열로 만들어 저장하고
 * 돌면서 내구도 배열 값을 빼주며 계산한다.
 * 
 */
import java.util.Scanner;

public class Main_16987_계란으로계란치기 {
	static int N,max=0;
	static int[] S,W;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		S=new int[N];
		W=new int[N];
		for(int i=0;i<N;i++) {
			int s = sc.nextInt();
			int w = sc.nextInt();
			S[i]=s;
			W[i]=w;
		}
		go(0);
		System.out.println(max);
	}
	private static void go(int cnt) {
		if(cnt==N) {
			int c=0;
			for(int i=0;i<N;i++) {
				if(S[i]<=0)c++;
			}
			if(c>max)max=c;
			return;
		}
		if(S[cnt]<=0)go(cnt+1);
		else {
			boolean c = false;
			for(int i=0;i<N;i++) {
				if(i==cnt || S[i]<=0)continue;
				c=true; // 이부분 봐야될거같다... 없이 했다가 틀렷음
				S[i]-=W[cnt];
				S[cnt]-=W[i];
				go(cnt+1);
				S[i]+=W[cnt];
				S[cnt]+=W[i];
			}
			if(!c)go(cnt+1);
		}
	}

}
