package algo0501;

import java.util.Scanner;
/*
 * G5
 * 
 * 석순, 종유석이 번갈아가면서 주어짐, 시작은 항상 석순
 * 동굴 길이 N미터(장애물 개수), 높이 H미터
 * N개의 장애물 크기 주어짐. N은 항상 짝수
 * 개똥벌레가 이 동굴에 있는 장애물들 파괴하면서 지나갈건데, 
 * 지나갈 높이 중 파괴할 장애물의 개수 최솟값과 그 최솟값일때의 높이 갯수 출력
 */
public class BOJ_3020_개똥벌레 {

	static int N,H;
	static int[] up,down,up_sum,down_sum;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		H = sc.nextInt();
		up = new int[H+1];		//석순
		down = new int[H+1];	//종유석
		//부분합
		up_sum = new int[H+1];	//석순 부분합
		down_sum = new int[H+1];//종유석 부분합
		for (int i = 0; i <N/2; i++) {
			up[sc.nextInt()]++;
			down[sc.nextInt()]++;
		}
		for (int i = 1; i <=H; i++) {
			//up_sum[h]는 높이가 h이하인 장애물의 개수
			up_sum[i] = up_sum[i-1]+up[i];
			down_sum[i] = down_sum[i-1]+down[i];
		}
		//파괴해야하는 장애물 최솟값
		int min = N;
		//그러한 구간의 수
		int ans = 0;
		
		for (int i = 1; i <=H; i++) {
			int cnt = 0;
			//부딪히는 개수 = 전체-안부딪히는 개수
			cnt+=up_sum[H]-up_sum[H-i];
			cnt+=down_sum[H]-down_sum[i-1];
			if(cnt<min) {	//최솟값보다 작으면
				min = cnt;	//갱신
				ans = 1; 	//카운트 초기화
			}else if(cnt==min) ans++; //같으면 갯수++
		}
		System.out.println(min+" "+ans);
	}

}
