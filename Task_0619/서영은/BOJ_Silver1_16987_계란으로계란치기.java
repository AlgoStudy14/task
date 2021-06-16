package algo210619;
/**
 * <문제 요약>
 * 구해야 하는 것: 깰 수 있는 계란의 최대 개수
 * 제약 사항: 1<=N<=8
 * 문제 유형: 백트래킹
 * 요구 개념: 백트래킹 
 * 
 * <풀이법 요약>
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Silver1_16987_계란으로계란치기 {

	static int N, weight[], power[], maxCnt;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
	
		power = new int[N];
		weight = new int[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine()," ");
			
			power[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
		}
	
		fight(0);
		
		System.out.println(maxCnt);
	}
	
	// curIdx: 현재 든 계란, cnt: 여태까지 깨진 계란의 수 
	private static void fight(int curIdx) {
		
		//기저 조건
		if(curIdx == N) {
			int cnt=0;
			for (int i = 0; i < N; i++) 
				if(power[i] <= 0) ++cnt;
			
			if(cnt > maxCnt) maxCnt = cnt;
			return;
		}
		
		boolean noFight = true;
		if(power[curIdx] > 0) {//현재 든 계란이 깨지지 않았을 때
			for (int fightIdx = 0; fightIdx < N; fightIdx++) {
				if(curIdx == fightIdx) continue;
				if(power[fightIdx]>0) {
					noFight = false;
					power[curIdx] -= weight[fightIdx];
					power[fightIdx] -= weight[curIdx];
					
					fight(curIdx+1);
					
					power[curIdx] += weight[fightIdx];
					power[fightIdx] += weight[curIdx];
				}
			}
		} else { // 현재 든 계란이 깨졌을 때
			fight(curIdx+1);
		}
		
		if(noFight) { // 깨진 계란이 없을 때
			fight(curIdx+1);
		}
	}
}
