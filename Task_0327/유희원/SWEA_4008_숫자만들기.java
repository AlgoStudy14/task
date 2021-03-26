package sw_a_prac;

import java.util.Scanner;

//연산자 우선순위 고려하지 않고 왼쪽에서 오른쪽으로 차례대로 계산
//최대 최소 구해서 두값의 차이 출력

/*
 * 수식 계산시, 연산자의 우선순위 고려하지 않고 왼쪽부터 차례대로 계산!!
 * 가지고있는 각 연산자의 개수와 수식에 사용되는 숫자가 주어지고 
 * 이 조합으로 만들 수 있는 결과중 최댓값과 최솟값의 차이 출력
 */
public class SWEA_4008_숫자만들기 {

	static int T, N, max, min;
	static int[] operator, numbers;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <=T; t++) {
			//수식에 들어가는 숫자 갯수
			N = sc.nextInt();
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			//연산자 갯수 배열
			operator = new int[4];
			//숫자 배열
			numbers = new int[N];
			for (int i = 0; i < 4; i++) {
				operator[i] = sc.nextInt();
			}
			for (int i = 0; i < N; i++) {
				numbers[i] = sc.nextInt();
			}
			dfs(numbers[0],1);
			System.out.println("#"+t+" "+(max-min));
		}

	}
	private static void dfs(int res, int idx) {
		//숫자 다고르면(기저조건)
		if(idx == N) {
			min = Math.min(min, res);
			max = Math.max(res, max);
			return;
		}
		for (int i = 0; i < 4; i++) {
			//연산자 남아있으면
			if(operator[i]!=0) {
				//하나 꺼내서 써봄
				operator[i]--;
				//i 돌려가면서 dfs돌려보기
				if(i==0) dfs(res+numbers[idx],idx+1);
				else if(i==1) dfs(res-numbers[idx],idx+1);
				else if(i==2) dfs(res*numbers[idx],idx+1);
				else if(i==3) dfs(res/numbers[idx],idx+1);
				operator[i]++;
			}
		}
		
	}

}
