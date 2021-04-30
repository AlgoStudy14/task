import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * <문제 요약>
 * 번갈아가며 석순과 종유석의 높이가 주어질 때 일정한 특정높이로 날아가며 뚫는 벽의 최소 값 & 갯수 구하기
 * 
 * <풀이법 요약>
 * 1. 석순과 종유석을 구분해서 저장
 * 2. 석순배열과 종유석 배열 sorting
 * 3. 이분탐색을 이용하여 뚫는 벽의 수 구하기(종유석배열한번 석순배열한번)
 * 4. 뚫는 벽의 수를 합해 저장한 뒤 최소값구하기
 * 
 * <느낀점>
 * 이분탐색 어려움.
 * 이분탐색이랑 부분합이랑 어떤 기준으로 접근을 해야할 지가 아직 감이 잘 오지않음
 */

public class Main_BOJ_3020_개똥벌레 {
	static int N, H;
	static int[] block;

	public static void main(String[] args) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String[] input = br.readLine().split(" ");
	        N = Integer.parseInt(input[0]);
	        H = Integer.parseInt(input[1]);
	        int[] under = new int[N/2];    //석순
	        int[] upper = new int[N/2];    //종유석
	        int[] cntArr = new int[H]; //뚫은 갯수 저장배열
	        int min = Integer.MAX_VALUE; // 최소값저장
	        int minCnt = 0;

	        for(int i=0; i<N; i++) { //석순과 종유석 나눠서 저장
	            int l = Integer.parseInt(br.readLine());
	            if(i%2==0)
	                under[i/2] = l;
	            else
	                upper[i/2] = l;
	        }

	        Arrays.sort(under);
	        Arrays.sort(upper);

	        for(int i=1; i<=H; i++) { //뚫리는 벽 갯수 찾기

	            int j = binarySearch(0, N/2-1, i, under);
	            int k = binarySearch(0, N/2-1, H-i+1, upper);
	            cntArr[i-1] = j+k;
	            if(min>cntArr[i-1])
	                min = cntArr[i-1];
	        }

	        for(int i=0; i<H; i++) {
	            if(min==cntArr[i])
	                minCnt++;
	        }

	        System.out.println(min+" "+minCnt);
	    }

	    static int binarySearch(int above, int below, int h, int[] arr) { //under기준 코드, 2분탐색으로 벽높이를 기준으로 for문을 돌리면서 뚫리는 벽 갯수를 구한다.
	        int left = above;
	        int right = below;

	        int min = Integer.MAX_VALUE;

	        while (left <= right) {
	            int mid = (left + right) / 2;

	            if (h <= arr[mid]) {
	                min = Math.min(min, mid);
	                right = mid - 1;
	            }
	            else
	                left = mid + 1;
	        }

	        if (min == Integer.MAX_VALUE)
	            return 0;

	        return N / 2 - min;
	    }
}
