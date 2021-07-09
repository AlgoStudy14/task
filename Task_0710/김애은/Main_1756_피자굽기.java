package study0710;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1756_피자굽기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int D=Integer.parseInt(st.nextToken());
		int N=Integer.parseInt(st.nextToken());
		int[] arr=new int[D+1];
		st=new StringTokenizer(br.readLine());
		for (int d = 1; d <= D; d++) {
			arr[d]=Integer.parseInt(st.nextToken());
		}
		st=new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			int num=Integer.parseInt(st.nextToken());
			check(num);
		}
		int index=1;
		
	}

}
