import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_G1_최종순위 {
	/*
	 * <문제>
	 * 1 ~ N개의 팀이 예선 참가. 참여 팀이 작년과 동일.
	 * 순위가 바뀐 팀만 발표하려고함.EX 13팀이 작년엔 6팀보다 높았는데 이번엔 6팀이 더 높으면 (6, 13)
	 * 이 정보만으로 올해 최종 순위를 만드려고 하는데, 가능할 경우 출력 / 순서가 불가능하면 Impossible / 순서를 못찾으면 ?
	 * 
	 * <문제풀이>
	 * 구선생... 
	 * 위상정렬을 조금 더 풀어보고 난 뒤에 다시풀어봐야할듯./
	 * 
	 * 보통의 위상정렬은 A가 B보다 앞에 있는데, 단순히 관계를 설정만 해주면됨. 
	 * 하지만 이번 문제는 순서가 미리 정해져있고 순위의 선후관계가 바뀌는 경우라고 함
	 * 순위 이동이 일어나면 indegree를 +1, -1 해줌.
	 */
	
	static int T, N, M;
	static int[] indegree;
	static int[] array;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			
			indegree = new int[N + 1];
			array = new int[N + 1];
			list = new ArrayList[N + 1];
			for(int i = 1; i <= N; i++) {
				array[i] = sc.nextInt();
				list[i] = new ArrayList<>();
			}
			
			for(int i = 1; i <= N; i++) {
				int from = array[i];
				for(int j = i + 1; j <= N; j++) {
					list[from].add(array[j]);
					indegree[array[j]]++;
				}
			}
			
			M = sc.nextInt();
			for(int i = 0; i < M; i++) {
				int front = sc.nextInt();
				int back = sc.nextInt();
				
				if(list[front].contains(back)) {
					list[front].remove((Integer)back);
					list[back].add(front);
					indegree[front]++;
					indegree[back]--;
				}else {
					list[back].remove((Integer)front);
					list[front].add(back);
					indegree[back]++;
					indegree[front]--;
				}
			}
			
			StringBuilder sb = new StringBuilder();
			Queue<Integer> q = new LinkedList<>();
			
			int cnt = 0;
			for(int i = 1; i <= N; i++) {
				if(indegree[i] == 0) {
					cnt++;
					q.add(i);
				}
			}
			//확실한 순위 모르는 경우
			if(cnt > 1) {
				System.out.println("?");
				continue;
			}
			
			int result = 0;
			
			boolean isFinished = false;
			for(int i = 1; i <= N; i++) {
				if(q.isEmpty()) {
					System.out.println("IMPOSSIBLE");
					isFinished = true;
					break;
				}
				int from = q.poll();
				result++;
				sb.append(from).append(" ");
				for(int to : list[from]) {
					indegree[to]--;
					if(indegree[to] == 0)
						q.add(to);
				}
			}
			if(isFinished)
				continue;
			System.out.println(sb.toString());
		}
	}
}
