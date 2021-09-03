import java.util.Scanner;

public class BOJ_G4_1043_거짓말 {
	/*
	 * <문제>
	 * 파티에 갈때마다 지민이는 가장 좋아하는 이야기를 함.하지만 과장해서 말함.
	 * 거짓말쟁이는 되기 싫은데, 몇몇이 진실을 알고있음. 이런 사람들이 왔을 땐 ㅣㄴ실을 이야기하고자함.
	 * 어떤사람이 파티에서 진실을 듣고, 또 다른 파티에서 과장된걸 듣는다면 거짓말쟁이가됨.
	 * N이 주어지고, 진실을 아는 사람이 주어질 때, 모든 파티에 참가해야 하는데 과장된 이야기를 할 수 있는 최대값은?
	 * 
	 * <문제풀이>
	 * 구글,...
	 * 
	 * 진실을 아는 사람이 한 사람이라도 파티에 있다면 해당 파티는 모두 진실을 알게 됨.
	 * 따라서 진실을 아는 사람과 파티에 참가한 적 없는 사람만 존재해야 거짓말을 할 수 있는 파티.
	 * 
	 */
	static int N, M;
	static boolean[] know;
	static boolean[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		know = new boolean[N + 1];
		//진실을 아는 사람의 수와 번호.
		int len = sc.nextInt();
		for(int i = 0; i < len; i++)
			know[sc.nextInt()] = true;
		
		int[][] party = new int[M][];
		map = new boolean[N + 1][N + 1];
		
		for(int i = 0; i < M; i++) {
			int input = sc.nextInt();
			party[i] = new int[input];
			party[i][0] = sc.nextInt();
			
			for(int j = 1; j < input; j++) {
				party[i][j] = sc.nextInt();
				map[party[i][j - 1]][party[i][j]] = map[party[i][j]][party[i][j - 1]] = true;
			}
		}
		
		for(int i = 1; i <= N; i++)
			if(know[i])
				dfs(i);
		
		int cnt = 0;
		for(int i = 0; i < M; i++)
			if(!know[party[i][0]])
				cnt++;
		System.out.println(cnt);
	}
	
	static void dfs(int n) {
		for(int i = 1; i <= N; i++)
			if(map[n][i] && !know[i]) {
				know[i] = true;
				dfs(i);
			}
	}
}
