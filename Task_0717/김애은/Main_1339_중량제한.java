package 백준;
/*
 * 그냥 이분탐색을 다시 공부해야되나
 * 이문제는 이분탐색 +bfs
 * max에는 최댓값을 넣고
 * 이분탐색을 사용해서 최대 중량 기준을 mid로 두고 bfs를 실행한다.
 * bfs에서 방문하지 않았던 나라이고, mid보다 제한이 큰 경우 계속 탐색한다.
 * 이후 end까지 도닥하면 true를 만환하고 도달 못하면 false를 반환한다.
 * true 반환시 더 큰값으로 mid를 옮기고 아니면 마이너스
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1339_중량제한 {
	static int N,M,start,end;
	static ArrayList<int[]> list[];
	static boolean check[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		list=new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			list[i]=new ArrayList<>();
		}
		int min = 99999;
		int max = 0;
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			list[a].add(new int[] {b,c});
			list[b].add(new int[] {a,c});
			min=Math.min(c, min);
			max=Math.max(c, max);
		}
		st=new StringTokenizer(br.readLine());
		start=Integer.parseInt(st.nextToken());
		end= Integer.parseInt(st.nextToken());
		
		while(min<=max) {
			check=new boolean[N+1];
			int mid = (max+min)/2;
			if(bfs(mid))
				min=mid+1;
			else
				max=mid-1;
		}
		System.out.println(max);
	}
	private static boolean bfs(int mid) {
		Queue<Integer> que = new LinkedList<Integer>();
		check[start]=true;
		que.add(start);
		while(!que.isEmpty()) {
			int poll = que.poll();
			if(poll==end)
				return true;
			for (int i = 0; i < list[poll].size(); i++) {
				if(!check[list[poll].get(i)[0]] && mid<=list[poll].get(i)[1]) {
					check[list[poll].get(i)[0]]=true;
					que.add(list[poll].get(i)[0]);
				}
			}
		}
		return false;
	}

}
