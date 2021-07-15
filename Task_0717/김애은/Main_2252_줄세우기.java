package study0717;
/*
 * 위상정렬 문제
 * 그림 그려보자
 * 1->3 2->3이면 3으로 향하는 그래프가 2개
 * degree[3]=2이고 나머지는 0
 * 0인건? 자기가 젤 작은거다
 * 순서 상관 없다 했으니 0인게 먼저 나와야 되고 그 다음으로 연결되는것 중 degree0을 뽑아내면 된다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2252_줄세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int degree[] = new int[N+1];
		ArrayList<Integer>[] list = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			list[i]=new ArrayList<>();
		}
		for (int i = 1; i <=M; i++) {
			st=new StringTokenizer(br.readLine());
			int fron = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list[fron].add(end);
			degree[end]++;
		}
		
		Queue<Integer> que = new LinkedList<Integer>();
		for (int i = 1; i <=N ; i++) {
			if(degree[i]==0) {
				que.add(i);
			}
		}
		
		while(!que.isEmpty()) {
			int cur = que.poll();
			System.out.print(cur+" ");
			for (int i = 0; i < list[cur].size(); i++) {
				int next = list[cur].get(i);
				degree[next]--;
				if(degree[next]==0)
					que.add(next);
			}
		}
	}

}
