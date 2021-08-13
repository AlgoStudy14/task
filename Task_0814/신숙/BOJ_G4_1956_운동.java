import java.util.Scanner;

public class BOJ_G4_1956_운동 {
	/*
	 * <문제>
	 * V개의 마을이 E개의 도로로 이루어짐.
	 * 운동을 하기 위한 경로를 찾을건데, 다시 시작점으로 와야하기때문에 사이클을 찾아야함. 운동이 귀찮아서 사이클 도로들의 합이 최소가 되도록.
	 * 
	 * <문제풀이>
	 * 일반적인 플로이드와샬 문제.
	 * 
	 * <오류>
	 * 답이 -2147483644 => IntegerMax 때문인듯.
	 */
	static int V, E, ans;
	static int MAX = 999999999;
	static int[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		map = new int[V + 1][V + 1];
		
		for(int i = 1; i <= V; i++) {
			for(int j = 1; j <= V; j++) {
				if(i != j)
					map[i][j] = MAX;
			}
		}
		
		for(int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			map[a][b] = c;
		}
		//플로이드 와샬 
		for(int k = 1; k <= V; k++) {
			for(int i = 1; i <= V; i++) {
				for(int j = 1; j <= V; j++) {
					if(i == j)
						continue;
					if(map[i][j] > map[i][k] + map[k][j])
						map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
		
		ans = MAX;
		for(int i = 1; i <= V; i++) {
			for(int j = 1; j <= V; j++) {
				if(i == j)
					continue;
				if(map[i][j] != MAX && map[j][i] != MAX)
					ans = Math.min(ans, map[i][j] + map[j][i]);
			}
		}
		System.out.println(ans == MAX ? -1 : ans);
	}
}
