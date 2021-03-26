package sw_a_prac;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 사용자 A,B가 각각 (1,1) , (10,10)에서 출발하고 
 * 시간에 따라 그 방향이 주어짐 
 * 충전범위 C는 맨해튼거리 (D = |X-x|+|Y-y|)가 C 이하면 접속 가능하다는 의미 -> 마름모 모양
 * 한 충전범위에 두명이상이 접속하면 그 사용자 수만큼 충전양 나눔
 * 
 * 충전 양 최댓값 출력
 */

public class SWEA_5644_무선충전 {

	static int T,M,A, ans;
	//이거 순서 정지 상하좌우로했다가 틀림^^
	//좌표가 x,y좌표라 dr,dc 헷갈려서 또 틀림^^ 방향주의!!
	static int[] dc = {0,-1,0,1,0};
	static int[] dr = {0,0,1,0,-1};
	static int[][] battery, move;
	static class Node{
		int x,y;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static List<Node> user;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <=T; t++) {
			//이동시간
			M = sc.nextInt();
			//BC개수
			A = sc.nextInt();
			ans = 0;
			//배터리 개수, 좌표(x,y), 충전범위, 처리량
			battery = new int[A][4];
			//0초부터 M초까지 필요함
			move = new int[2][M+1];
			user = new ArrayList<>();
			//user A는 1,1 / user B는 10,10에서 시작
			user.add(new Node(1,1));
			user.add(new Node(10,10));
			//A,B의 이동 궤적(?) 배열
			for (int i = 0; i <2; i++) {
				for (int j = 0; j < M; j++) {
					move[i][j] = sc.nextInt();
				}
			}
			//충전영역 정보 
			for (int i = 0; i <A; i++) {
				for (int j = 0; j < 4; j++) {
					battery[i][j] = sc.nextInt();
				}
			}
			//이동시간만큼
			for (int i = 0; i <=M; i++) {
				charge();
				for (int j = 0; j < 2; j++) {
					user.get(j).x += dr[move[j][i]];
					user.get(j).y += dc[move[j][i]];
				}
			}
			System.out.println("#"+t+" "+ans);
		}

	}
	private static void charge() {
		ArrayList<Integer> bcA = new ArrayList<>();
		ArrayList<Integer> bcB = new ArrayList<>();
		//배터리 개수만큼
		for (int i = 0; i <A; i++) {
			//맨해튼 거리로 범위내에있으면 추가
			if(Math.abs(user.get(0).x-battery[i][0])
					+Math.abs(user.get(0).y-battery[i][1])<=battery[i][2]) bcA.add(i);
			if(Math.abs(user.get(1).x-battery[i][0])
					+Math.abs(user.get(1).y-battery[i][1])<=battery[i][2]) bcB.add(i);
		}
		//충전된 횟수
		int sizeA = bcA.size();
		int sizeB = bcB.size();
		int ap = 0, bp = 0;
		int max = 0;
		//둘다 없으면 뭐..return -> ans 는 0
		if(sizeA==0&&sizeB==0) return;
		//각각 A또는 B만 해당될때 최대 충전량 저장
		else if(sizeA==0) {
			for (int b: bcB) {
				int sum = battery[b][3];
				bp = Math.max(sum, bp);
			}
		}
		else if(sizeB==0) {
			for (int a: bcA) {
				int sum = battery[a][3];
				ap = Math.max(sum, ap);
			}
		}
		//둘다 0이 아니면
		else {
			for (int a: bcA) {
				for (int b: bcB) {
					int sum = 0;
					//같은 충전단자면 나눠써야함
					if(a==b) {
						sum = battery[a][3];
						if(sum>max) {
							ap = sum/2;
							bp = sum/2;
							max = sum;
						}
					}else { //아니면 각각 저장
						sum = battery[a][3]+battery[b][3];
						if(sum>max) {
							ap = battery[a][3];
							bp = battery[b][3];
							max = sum;
						}
					}
				}
			}
		}
		ans += ap+bp;
	}

}
