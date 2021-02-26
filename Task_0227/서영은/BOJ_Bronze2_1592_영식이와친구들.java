import java.util.Scanner;
/**
 * <문제 요약>
 * 구해야 하는 것: 공을 총 몇번 던질 수 있는지
 * 제약 사항: 한 사람이라도 공을 받은 횟수가 M이 되면 종료 
 * 문제 유형: 구현
 * 요구 개념: 일차원 배열  + 인덱스 이용 
 * 
 * <풀이법 요약>
 * 1. 그냥 단순하게 구현. 홀수일 때, 짝수일 때 나눠서 문제 순대로 구현!
 * 
 */
public class BOJ_Bronze2_1592_영식이와친구들 {

	static int N,M,L;
	static int[] friends;
	public static void main(String[] args) {

		Scanner scann=new Scanner(System.in);
		
		N=scann.nextInt();
		M=scann.nextInt();
		L=scann.nextInt();
		
		friends=new int[N+1];
		
		int cnt=0; // 던지는 횟수
		int curIdx=1;
		
		while(true) {
			friends[curIdx]++;
			if(friends[curIdx] == M) break;
			
			if(friends[curIdx]%L == 1) { // 홀수일 때, 시계방향 
				if(curIdx+L>N) curIdx = curIdx+L-N;
				else curIdx = curIdx+L;
			} else { // 짝수일 때, 반시계 방향 
				if(curIdx-L<1) {
					curIdx = N+curIdx-L;
				} else {
					curIdx = curIdx-L;
				}
			}
			cnt++;
		}
		
		System.out.println(cnt);
	}
}
