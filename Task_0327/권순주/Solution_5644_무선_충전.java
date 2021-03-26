import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * <문제 요약>
 * 구해야 하는 것 : 사용자가 충전한 배터리 양의 합의 최댓값
 * 문제 핵심 요약 : 각 위치에 2개의 배터리만 저장해주면 된다. (A,B 두명이니까!)
 * 
 * <풀이법 요약>
 * A, B 두명이기 때문에 각 위치별로 최댓값 기준으로 2개의 배터리만 저장해주면 된다.
 * 배터리를 저장할 때 list 내림차순 정렬해서 뽑으면 최댓값부터 차곡차곡 저장됨
 * 처음에는 int[][][]로 performance만 저장했 -> 테케 4번에서 같은 값을 가질 수 있기 때문에 틀림 -> index 추가해줌
 * [총합 구할 때]
 * 1. A,B의 최댓값이 같고 index도 같다면 => 두개가 같은 배터리 범위 안에 존재한다면
 * 	=> A[0]+B[1] vs A[1]+B[0] 해서 max값 저장
 * 2. A,B의 최댓값이 다를 때 => 두개가 다른 배터리 범위 안에 존재한다면
 * 	=> A[0]+B[0] 저장 => 이미 내림차순 정렬해서 넣어줬기 때문에 A[0],B[0]은 각각 최댓값을 가지고 있기 때문에 A[0]+B[0]가 최댓값이다!
 * 
 */

public class Solution_5644_무선_충전 {

	static class Point { // 충전 범위 안에 있는 배터리를 저장하기 위한 class
		int index;
		int p;

		public Point(int index, int p) {
			this.index = index;
			this.p = p;
		}
	}

	static class Battery implements Comparable<Battery> { // 입력받은 배터리를 저장하기 위한 class
		int x;
		int y;
		int c;
		int p;

		public Battery(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}

		@Override
		public int compareTo(Battery o) { // 비용순으로 정렬
			return o.p - this.p;
		}

	}

	static int M, N;
	static Point[][][] map;
	static int[] dx = { 0, -1, 0, 1, 0 };
	static int[] dy = { 0, 0, 1, 0, -1 };
	static int[] A, B;
	static ArrayList<Battery> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			A = new int[M + 1];
			B = new int[M + 1];
			map = new Point[10][10][2]; // A,B 두명이니까 2개면 된다
			list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				list.add(new Battery(x, y, c, p)); // 배터리 정보 저장
			}
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					for (int k = 0; k < 2; k++) {
						map[i][j][k] = new Point(0, 0); // map 초기화
					}
				}
			}
			makeMap();
			System.out.printf("#%d %d\n", t, move(0, 0, 9, 9));
		}
	}

	// 이부분은 과거 별찍기를 참고
	private static void makeMap() {
		Collections.sort(list); // 배터리 비용순으로 정렬
		int index = 1; // 배터리 index 저장 변수
		for (Battery BC : list) {
			int n = 2 * BC.c + 1;
			int x = BC.x - n / 2;
			for (int i = 0; i < n; i++) {
				int y = BC.y;
				for (int k = 0; k < -Math.abs(i - n / 2) + n / 2; k++) {
					y--;
				}
				for (int j = 0; j < -2 * Math.abs(i - n / 2) + n; j++) {
					if (0 <= x && x < 10 && 0 <= y && y < 10) {
						if (map[x][y][0].p < BC.p) { // 만약 0번째 배터리 성능보다 좋다면
							map[x][y][1] = map[x][y][0]; // 뒤로 밀고
							map[x][y][0] = new Point(index, BC.p); // 0번째에 배터리 저장
						} else if (map[x][y][1].p < BC.p) { // 만약 1번째 배터리 성능보다 좋다면
							map[x][y][1] = new Point(index, BC.p); // 1번째에 배터리 저장
						}
					}
					y++;
				}
				x++;
			}
			index++; // 배터리 index 증가
		}
	}

	private static int move(int Ax, int Ay, int Bx, int By) {
		int answer = 0;
		Point[] listA;
		Point[] listB;
		for (int i = 0; i <= M; i++) {
			// A,B 이동
			Ax += dx[A[i]];
			Ay += dy[A[i]];
			Bx += dx[B[i]];
			By += dy[B[i]];
			listA = map[Ax][Ay]; // A 위치의 배터리 정보 가져옴
			listB = map[Bx][By]; // B 위치의 배터리 정보 가져옴
			// listA[0].p : A 위치의 배터리 중 최댓값
			// listA[0].index : A 위치의 배터리 중 최댓값을 가지는 배터리 index
			// listA[1].p : A 위치의 배터리 중 두 번째 큰 값
			// listA[1].index : A 위치의 배터리 중 두 번째 큰 값을 가지는 배터리 index
			if (listA[0].p == listB[0].p && listA[0].index == listB[0].index) { // A,B의 최댓값이 같은 배터리일 때
				answer += Math.max(listA[0].p + listB[1].p, listA[1].p + listB[0].p);
			} else { // A,B의 최댓값이 다른 배터리일 때
				answer += (listA[0].p + listB[0].p);
			}
		}
		return answer;
	}

}
