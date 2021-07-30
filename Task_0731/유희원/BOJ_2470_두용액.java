package algo0729;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class BOJ_2470_두용액 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		String[] strarr = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(strarr[i]);
		}
		Arrays.sort(arr);
		int start = 0;
		int end = arr.length-1;
		long result = Integer.MAX_VALUE;
		int answer1 = 0;
		int answer2 = 0;
		
		//start와 end포인터가 만날때까지 반복
		while(start!=end) {
			//현재 start와 end 포인터가 가리키는 두 용액의 특성 값 합을 구하고 결과값 업데이트
			if(result>Math.abs(arr[end]+arr[start])) {
				result = Math.abs(arr[end]+arr[start]);
				answer1 = arr[start];
				answer2 = arr[end];
			}
			//start 포인터를 1증가시켰을때와 end포인터를 1증가시켰을 때의 값을 비교하여 0에 더 가까운 쪽으로 포인터 값 증가
			if(Math.abs(arr[start]+arr[end-1])<Math.abs(arr[start+1]+arr[end])) end--;
			else start++;
		}
		System.out.println(answer1+" "+answer2);

	}

}
