import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * dp로 해결법을 몰라서... dfs로 풀었는데 시간초과 날 것 같다..
 * 
 * 풀이법
 * 1. 각 추를 입력받는다. => hashmap으로 추무게-남은 추 개수 저장
 * 2. 구슬 입력받는다.
 * 3. 재귀 타고 들어가서 dfs 돌리기
 * @author yes
 *
 */
public class Algo2_서울_14반_서영은 {

	static int chuCnt, gooseulCnt;
	static Map<Integer, Integer> eachChuCnt = new HashMap<Integer, Integer>(); // 각각 추가 남아있는 개수
	static int[][] gooseul;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		chuCnt = Integer.parseInt(in.readLine());
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		//각 추 별로 남은 개수 체크
		for (int i = 0; i < chuCnt; i++) {
			int chuWeight = Integer.parseInt(st.nextToken());
			int cnt = eachChuCnt.getOrDefault(chuWeight, 0);
			eachChuCnt.put(chuWeight, ++cnt);
		}
		
		gooseulCnt = Integer.parseInt(in.readLine());
		
		gooseul = new int[gooseulCnt][2];
		
		// 구슬 입력
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < gooseulCnt; i++) {
			gooseul[i][0] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		
		for (int i = 0; i < gooseulCnt; i++) {
			if(gooseul[i][1] == 1) System.out.print("Y ");
			else System.out.print("N ");
		}
	}

	private static void dfs(int allCnt, int sum) {
		// 담은 추의 무게가 구슬의 무게와 같다면 무게를 확인할 수 있다고 체크해주기
		for (int i = 0; i < gooseul.length; i++) {
			if(gooseul[i][0] == sum && gooseul[i][1] == 0) {
				gooseul[i][1] = 1;
				break;
			}
		}
		
		if(allCnt >= chuCnt) return;
		
		Set<Integer> set = eachChuCnt.keySet();
		
		for (Iterator<Integer> iterator = set.iterator(); iterator.hasNext();) {
			Integer chuW = (Integer) iterator.next();
			
			// 해당 추의 무개의 사용할 수 있는 추의 개수가 남아있다면
			if(eachChuCnt.get(chuW) > 0) {
				int cnt = eachChuCnt.getOrDefault(chuW, 0);
				eachChuCnt.put(chuW, --cnt);
				dfs(allCnt+1, sum + chuW);
				eachChuCnt.put(chuW, ++cnt);
				
				// 여태껏 채운 추의 무게가 현재 넣을 추의 무게보다 크면
				if(sum > chuW) {
					eachChuCnt.put(chuW, --cnt);
					dfs(allCnt+1, sum - chuW);
					eachChuCnt.put(chuW, ++cnt);
				}
			}
		}
	}
}
