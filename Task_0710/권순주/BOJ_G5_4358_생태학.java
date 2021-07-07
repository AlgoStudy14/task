import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
 * <문제 요약>
 * 문제 정의 : 각 나무 종이 전체에서 몇 %를 차지하는지 구하는 프로그램
 * 문제 유형 : 구현
 * 제약 사항 : double을 얻고 싶으면 double로 나눌 것
 * 
 * <풀이법 요약>
 * 1. 입력받으면서 hashMap에 값을 넣어준다.
 * 1-1. 저장할 트리 종류가 존재할 때 -> 그 종류에 해당하는 트리의 개수 + 1
 * 1-2. 저장할 트리 종류가 존재하지 않을 때 -> 그 종류에 해당하는 트리의 개수 = 1
 * 2. key로 오름차순한다.
 * 3. 형식에 맞게 출력한다.
 * 
 * <피드백>
 * HashMap과 TreeMap을 정리하면서 풀면 좋은 문제!
 * 이참에 반올림해서 출력하는 방법도 복습했다!
 * 
 */

public class BOJ_G5_4358_생태학 {

	static Map<String, Integer> treeList = new HashMap<>();
	static int totalCnt;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String tree = br.readLine();
			// 입력값이 없으면 끝!
			if (tree == null || tree.length() == 0) {
				break;
			}
			if (treeList.containsKey(tree)) { // 이미 나무의 종류가 존재할 때
				// 저장된 종의 개수 불러오기
				int treeCnt = treeList.get(tree);
				// map에 개수 + 1해서 다시 넣어주기
				treeList.put(tree, treeCnt + 1);
			} else { // 나무의 종류가 존재하지 않을 때
				// map에 개수를 1로해서 넣어주기
				treeList.put(tree, 1);
			}
			// 전체 나무 개수 증가
			totalCnt++;
		}

		// key로 오름차순 정렬
		List<String> keys = new ArrayList<>(treeList.keySet());
		Collections.sort(keys);
		for (String key : keys) {
			double percent = (double) treeList.get(key) / (double) totalCnt * 100;
			answer.append(key);
			answer.append(" ");
			// 반올림
			answer.append(String.format("%.4f", percent));
			answer.append("\n");
		}

		System.out.println(answer.toString());
	}

}
