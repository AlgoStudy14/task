package SWEA;

import java.util.ArrayList;
import java.util.Scanner;

public class SWEA_모의_2117 {
	/*
	 * 계산 
	 * 운영비용 = K * K + (K - 1 * K - 1)
	 * 
	 * <풀이>
	 * 집을 입력받을 때, 리스트에 넣어서 마름모 시작점과 크기에 따라서 거리계산. 
	 * 
	 * 
	 * 1. 답이 말도 안되게 크게나오네.	=> houseN을 증가시킬때가 문제네.
	 * 2. 이번엔 왜 몇 개는 맞고 약간씩 다르고 하는겨...	+ 수정해도 다른게 맞고 다른게 틀리네 
	 * 아마 마름모 내에 있는지 구하는걸 못하는듯
	 * 
	 */
	static int T, N, M, ans;
	static int[][] map;
	static ArrayList<House> arr;
	
	static class House{
		int r, c;
		House(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			arr = new ArrayList<>();
			ans = Integer.MIN_VALUE;
			int temp;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					temp = sc.nextInt();
					map[i][j] = temp;
					if(temp == 1)
						arr.add(new House(i, j));
				}
			}
			//마름모 K 는 원같이 최대 반지름 N까지/ 이게 dfs인가 bfs인가?
			dia();
			System.out.println("#" + t + " " + ans);
		}
	}
	
	static void dia() {
		//일단 완탐은 해야함. 근데 한번 노드를 갈때 마름모 크기를 전부 확인할 것인지. or 마름모 크기마다 완탐을 할 것인지.
		//	=> 근데 이러면 리스트 확인도 해야하니까 4중 포문이 되어버리는디?
		int costN;
		int houseN;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				houseN = 0;
				//i, j는 좌표. k는 마름모 반지름?이라해야하나		=> 이게 왜 k=0부터일까?
				for(int k = 0; k <= N; k++) {
					costN = k * k + ((k - 1) * (k - 1));
					for(int s= 0; s < arr.size(); s++) {
						//if( (Math.abs(arr.get(s).r) <= i + k) && (Math.abs(arr.get(s).c) <= j + k)) {		=> 정사각형이 아니라서 이렇게 하면 더 많이나오네.
						if(Math.abs(arr.get(s).r - i) + Math.abs(arr.get(s).c - j) == k)
							houseN++;
					}
					//K마다 cost값 비교해야함. 손해는 보지 않는선이라고 함	=> 이익이 제일 클때의 경우인지 알았는데, 가장많이 서비스하는경우
					//이익 : M * 집 개수  - costN 
					if(houseN > 0) {
						if(houseN * M - costN >= 0)
							ans = Math.max(ans, houseN);
					}
				}
			}
		}
	}
}
