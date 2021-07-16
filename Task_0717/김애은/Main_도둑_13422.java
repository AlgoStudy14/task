package 백준;
/*
 * 단순 시뮬레이션 문제
 * 투포인터 사용할 수 있지만 안씀
 * 단순 시뮬
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_도둑_13422 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			int arr[] = new int[N];
			st=new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				arr[n]=Integer.parseInt(st.nextToken());
			}
			int cnt=0;
			int index=1;
			int temp=0;
			for(int i=0;i<M;i++) {
				temp+=arr[i];
			}
			if(N==M) {
				if(temp<K)System.out.println(1);
				else System.out.println(0);
			}
			else {
				while (index <= N) {
					if (temp < K)
						cnt++;
					int maindex = index + M - 1;
					if (maindex >= N)
						maindex = maindex - N;
					temp = temp - arr[index - 1] + arr[maindex];
					index++;
				
				}
			System.out.println(cnt);
			}
		}
	}

}
