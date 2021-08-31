package 하반기;
/*
 * 간단한 dfs문제
 * 갔다온곳 check해주는 배열과 정답 넣는 ans 배열만 있으면됨
 * 값이 있는지만 확인해보면 될거 같고
 * 따로 어려운건 없었음.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_16432_떡장수와호랑이 {
	static List<Integer> arr[];
	static int N;
	static boolean[][] check;
	static int[] ans;
	static boolean ch;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		arr = new ArrayList[N+1];
		check=new boolean[N+1][10];
		ans = new int[N+1];
		for (int i = 0; i < N; i++) {
			arr[i]=new ArrayList<>();
			st=new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			for (int j = 0; j < c; j++) {
				arr[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		dfs(0,0);
		if(!ch)System.out.println(-1);
	}
	private static void dfs(int start,int before) {
		if(start==N) {
			for(int i=0;i<N;i++) {
				System.out.println(ans[i]);
			}
			ch=true;
			System.exit(0);
		}
		for (int i = 1; i < 10; i++) {
			if(i!=before && !check[start+1][i]&&arr[start].contains(i)) {
				check[start+1][i]=true;
				ans[start]=i;
				dfs(start+1,i);
			}
		}
	}
}
