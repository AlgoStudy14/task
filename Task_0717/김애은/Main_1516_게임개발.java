package study0717;
/*
 * 위상정렬문제
 * 1 ≤ N ≤ 500
 * num 배열: 건물 짓는데 걸리는 시간
 * res 배열: 최종 시간 저장하는 배열
 * degree 배열: 내가 되려면 앞에 몇개가 되어야 하는지
 * 
 * degree가 0이면 내 차례가 되므로 큐에 집어넣는다.
 * 후에 붙어있는 것들을 다 돌며 degree가 0이 될때까지 돈다.
 * 큐에서 빼고 난 후에 자기 자신까지 가장 오래 걸린 시간을 배열에 저장한다.
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1516_게임개발 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] list = new ArrayList[N+1];
		int degree[] = new int[N+1]; //내가 하려면 앞에 몇개 해야되는지
		int num[] = new int[N+1];
		int res[] = new int[N+1];
		for (int i = 1; i <= N; i++) {
			list[i]=new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			st=new StringTokenizer(br.readLine());
			num[i]=Integer.parseInt(st.nextToken());
			int temp = Integer.parseInt(st.nextToken());
			while(temp!=-1) {
				degree[i]++;
				list[temp].add(i);
				temp=Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<Integer> que = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			if(degree[i]==0) {
				que.add(i);
				res[i]=num[i];
			}
		}
		while(!que.isEmpty()) {
			int cur = que.poll();
			for (int i = 0; i < list[cur].size(); i++) {
				int next = list[cur].get(i);
				degree[next]--;
				res[next]=Math.max(res[next], res[cur]+num[next]);
				if(degree[next]==0)
					que.add(next);
			} 
		}
		for (int i = 1; i <= N; i++) {
			System.out.println(res[i]);
		}
		
	}

}
