package algo210227;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <문제 요약>
 * 구해야 하는 것: 각각의 색종이의 보이는 면적 출력 
 * 제약 사항: 색종이가 격자 밖으로 나가는 경우는 없다. 
 * 문제 유형: 구현?? 
 * 요구 개념: 구현...??? 
 * 
 * <풀이법 요약>
 * 1. 보기 편하게 원래 생각하던 좌표대로 오른쪽으로 돌려서 생각한다.
 * 2. 입력 받는 1 4 3 2 는 r, c, r의 방향 길이, c의 방향 길이라고 생각한다.
 * 3. 입력받은 색종이를 차례대로 1,2,....,N으로 map에 표시한다.
 * 4. 차례대로 포문을 돌려 면적을 구한다.  
 *
 */
public class BOJ_Bronze1_10163_색종이 {

	static int[][] map = new int[101][101];
	static int N;
	static ArrayList<Integer> wide = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		N=scann.nextInt();
		
		// 색종이 차례대로 면적 체크 
		for (int i = 0; i < N; i++) {
			int r=scann.nextInt();
			int c=scann.nextInt();
			int rwidth=scann.nextInt();
			int cwidth=scann.nextInt();
			
			for (int j = r; j < r+rwidth; j++) {
				for (int k = c; k < c+cwidth; k++) {
					map[j][k] = i+1;
				}
			}
		}
		
		// 색종이 차례대로 면적 구하
		for (int idx = 1; idx <= N; idx++) {
			int sum=0;
			for (int r = 0; r < 101; r++) {
				for (int c = 0; c < 101; c++) {
					if(map[r][c]==idx) sum++;
				}
			}
			wide.add(sum);
		}
		
		for(Integer w : wide) {
			System.out.println(w);
		}
	}
}
