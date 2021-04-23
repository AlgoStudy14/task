package algo0424;

import java.util.Scanner;
import java.util.Stack;
/*
 * 야구 문제.. ???? 설명 듣고 다시 풀어보
 */
public class BOJ_17281_baseball {

	static int N,max;
	static boolean[] visit;
	static int[] order,score;
	static int[][] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //이닝 수
		visit = new boolean[9];
		order = new int[9]; //타순
		score = new int[9]; //점수
		arr = new int[N][9]; //각 선수가 각 이닝에서 얻는 결과 1번 이닝부터 N번 이닝까지 주어짐
		
		for (int i = 0; i <N; i++) {
			for (int j = 0; j < 9; j++) {
				arr[i][j] = sc.nextInt();
				score[j] += arr[i][j];
			}
		}
		int best = 0;
		int idx = 0;
		for (int i = 0; i < 9; i++) {
			if(score[i]>best) {
				idx = i;
				best = score[i];
			}
		}
		order[0] = idx;
		visit[idx] = true;
		for (int i = 0; i < 9; i++) {
			if(i==idx) continue;
			dfs(i,1);
		}
		System.out.println(max);
	}
	//9명의 순서를 순서대로 배치하는 모든 경우의 수 찾고 점수 계산
	private static void dfs(int x, int cnt) {
		
		if(cnt==8) {
			play();
			visit[x] = true;
			order[cnt] = 0;
			return;
		}
		order[cnt] = x;
		visit[x] = true;
		for (int i = 0; i < 9; i++) {
			if(!visit[i]) dfs(i,cnt+1);
		}
		order[cnt] = 0;
		visit[x] = false;
		
	}
	//점수계산
	private static void play() {
		int score = 0;
		int j = 6;
		for (int i = 0; i < N; i++) {
			int out = 0;
			int run = 0;
			
			Stack<Integer> st = new Stack<>();
			//쓰리아웃발생하기 전까지
			while(out<3) {
				if(arr[i][order[j]]==0) out++;
				else {
					//선수 수 카운트
					run++;
					//루에 남아있는 선수 저장
					st.push(arr[i][order[j]]);
				}
				j++;
				//다시 첫번째 타자
				if(j>8) j = 0;
			}
			int k = 0;
			int remain = 0;
			while(!st.isEmpty()) {
				k+=st.pop();
				if(k>3) break;
				remain++;
			}
			run-=remain;
			score+=run;
		}
		max = Math.max(score, max);
	}

}
