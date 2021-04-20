package algo210424;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: 가장 많은 득점을 하는 타순을 찾고, 그 때의 득점을 구해보자
 * 제약 사항: 한 이닝에 3아웃이 발생하면 이닝이 종료된다.
 * 무조건 4번 타자가 먼저 시작. 이닝이 끝나면 다음 이닝에서는 다음 타자가 이어서 시작.
 * 문제 유형: 순열, 시뮬레이션, 완탐
 * 요구 개념: 순열(nextpermutation?)
 * 
 * -> 8!이므로 시간복잡도 안에 해결 가능
 * <풀이법 요약>
 * 1. 순열 다 체크
 * 2. 시뮬레이션으로 체크해보기
 * 
 */
public class BOJ_Gold4_17281_야구 {

	static int[] order = {2,3,4,5,6,7,8,9};
	static int N, ining[][], pos[], maxScore = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		ining = new int[N+1][10];
		pos = new int[4];
		
		StringTokenizer st = null;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			
			for (int j = 1; j <= 9; j++) {
				ining[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		do {
			doIning();
			
		}while(nextPermutation(order.length-1));
		
		System.out.println(maxScore);
	}

	private static void doIning() {
		int outCnt = 0;
		int iningCnt = 1;
		int idx = 0;
		int score = 0;
		
		int[] realOrder = new int[9];
		realOrder[3] = 1;
		
		for (int i = 0; i <= 8 ; i++) {
			if(i==3) continue;
			else if(i>=0 && i<3) {
				realOrder[i] = order[i];
			} else if(i>=4 && i<=8){
				realOrder[i] = order[i-1];
			}
		}

		int curTaja = realOrder[0];
		
		while(iningCnt <= N) {
			
			pos[0] = 1;
			
			switch (ining[iningCnt][curTaja]) {
			case 0:	//아
				++outCnt;
				
				if(outCnt >= 3) {
					++iningCnt;
					outCnt = 0;
					
					//1,2,3,타자석 0으로 초기화(새로운 이닝 시작)
					Arrays.fill(pos, 0);
				}
				pos[0] = 0;
				break;
			case 1:	//안타
				if(pos[3] != 0) {
					++score;
					pos[3] = 0;
				}
				for (int i = 3; i >= 1; i--) {
					pos[i] = pos[i-1];
				}
				pos[0] = 0;
				break;
			case 2:	//2루타 
				for (int i = 2; i <= 3; i++) {
					if(pos[i] != 0) {
						++score;
						pos[i] = 0;
					}
				}
				pos[3] = pos[1];
				pos[2] = pos[0];
				pos[1] = 0;
				pos[0] = 0;
				break;
			case 3:	//3루타
				for (int i = 1; i <= 3; i++) {
					if(pos[i] != 0) {
						++score;
						pos[i] = 0;
					}
				}
				pos[3] = pos[0];
				pos[0] = 0;
				break;
			case 4:	//홈런
				for (int i = 0; i <= 3; i++) {
					if(pos[i] != 0) {
						++score;
						pos[i] = 0;
					}
				}
				break;
			default:
				break;
			}
			
			curTaja = realOrder[(++idx)%9];
			
		}
		
		maxScore = Math.max(score, maxScore);
	}

	private static boolean nextPermutation(int size) {
		
		int i=size;
		while(i>0 && order[i-1]>=order[i]) --i;
		
		if(i==0) return false;
		
		int j=size;
		while(order[i-1] >= order[j]) --j;
		
		int temp = order[i-1];
		order[i-1] = order[j];
		order[j] = temp;
		
		int k=size;
		while(i<k) {
			temp = order[i];
			order[i] = order[k];
			order[k] = temp;
			++i; --k;
		}
		
		return true;
	}

}
