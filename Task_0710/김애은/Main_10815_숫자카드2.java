package study0710;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_10815_숫자카드2 {

	public static void main(String[] args) throws IOException {
		ArrayList<Integer> arr = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int temp =Integer.parseInt(st.nextToken());
			arr.add(temp);
		}
		
		st=new StringTokenizer(br.readLine());
		int M=Integer.parseInt(st.nextToken());
		int ans[] = new int[M];
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int temp = Integer.parseInt(st.nextToken());
			ans[i]=(arr.indexOf(temp)>-1)?1:0;
			System.out.print(ans[i]+" ");
		}
	}

}
