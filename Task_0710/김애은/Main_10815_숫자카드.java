package study0710;
/*
 * 처음 풀이는 그냥 함수 사용하기 (500,000 * 20,000,000) 
 * 이번에는 이분탐색을 함
 * 이분탐색을 통해 시간을 nlogn으로 줄일 수 있다.
 * 이분탐색 알고리즘
 * 값을 가지고 비교한다.
 * 초기값으로 elft = 0, right=n
 * mid는 그 중간
 * arr[mid]와 num 값을 비교한다. 같을때까지
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10815_숫자카드 {
	static int arr[];
	static int N,M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int temp = Integer.parseInt(st.nextToken());
			if(binarysearch(temp))System.out.print("1 ");
			else System.out.print("0 ");
		}
	}
	private static boolean binarysearch(int temp) {
		int left=0;
		int right=N-1;
		while(left<=right) {
			int midin = (left+right)/2;
			int mid = arr[midin];
			
			if(temp<mid)right = midin-1;
			else if(temp>mid)left = midin+1;
			else return true;
		}
		return false;
	}

}
