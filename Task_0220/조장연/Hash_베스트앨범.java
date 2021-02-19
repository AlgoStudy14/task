import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * <문제 요약>
 * 속한 노래가 많이 재생된 장르, 장르 내에서 많이 재생된 노래, 동일 재생 수 중에서 고유번호가 낮은 노래의 기준으로 앨범에 들어갈 노래 뽑기
 * 주의점 : 해당 장르에 속한 곡이 1개라면 1개의 곡만 선택
 * 
 * <풀이법 요약>
 * HashMap, set, ArrayList을 어떻게 효율적으로 사용하는지 잘 모르겠어서 문제의 조건에 맞춰서
 * 장르, 장르별 총 재생횟수, 장르 내 각각의 재생횟수, 노래의 고유번호를 전부 다 HashMap으로 만들어서 문제 조건에 따라 비교하는 방법으로 풀었음.
 
 * 굉장히 복잡하고 비효율적으로 푼 것 같음ㅠㅠ
 */

public class Hash_베스트앨범 {
	public static void main(String[] args) {
		Hash_베스트앨범 doit = new Hash_베스트앨범();
		String[] genres = new String[] { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = new int[] { 500, 600, 150, 800, 2500 };
		System.out.println(Arrays.toString(doit.solution(genres, plays)));
	}

	public int[] solution(String[] genres, int[] plays) {

		HashMap<String, ArrayList<Integer>> gNumber = new HashMap<>(); // 장르와 거기에 속한 각각의 곡(장르이름, 곡마다 재생횟수가 저장된 배열)
		HashMap<String, Integer> pNumber = new HashMap<>(); // 장르별 플레이 횟수(장르이름, 횟수)
		HashMap<Integer, Integer> npMap = new HashMap<>(); // 고유번호랑 노래 매칭(고유번호, 재생횟수)
		ArrayList<String> nameList = new ArrayList<>(); // 장르 이름 중복되지 않게 저장

		for (int i = 0; i < plays.length; i++) {
			npMap.put(i, plays[i]);
		}

		for (int i = 0; i < genres.length; i++) { // gNumber, pNumber Map 초기화
			if (gNumber.containsKey(genres[i])) {
				String name = genres[i];

				ArrayList<Integer> arrList = new ArrayList<>();
				arrList = gNumber.get(name);
				arrList.add((Integer) i);
				gNumber.put(name, arrList);

				int num = pNumber.get(name);
				num += plays[i];
				pNumber.put(name, num);
			} else {
				String name = genres[i];
				nameList.add(name);

				ArrayList<Integer> arrList = new ArrayList<>();
				arrList.add((Integer) i);
				gNumber.put(name, arrList);

				int num = plays[i];
				pNumber.put(name, num);
			}
		}

		int[] arr = new int[nameList.size()]; // 풀고나서 생각해보니 set으로 만들었으면 편했을것 같음

		for (int i = 0; i < nameList.size(); i++) {
			arr[i] = pNumber.get(nameList.get(i));
		}
		Arrays.sort(arr); // 재생횟수가 많은 거 뽑으려고 정렬

		ArrayList<Integer> answerList = new ArrayList<>();

		for (int i = nameList.size() - 1; i >= 0; i--) {
			String name = getKey(pNumber, arr[i]);

			ArrayList<Integer> arrList2 = new ArrayList<>();
			arrList2 = gNumber.get(name);
			int[] arr2 = new int[arrList2.size()];

			for (int j = 0; j < arr2.length; j++) {
				arr2[j] = plays[arrList2.get(j)];
			}
			Arrays.sort(arr2);
			int str = getKey(npMap, arr2[arr2.length - 1]);
			answerList.add(getKey(npMap, arr2[arr2.length - 1]));
			npMap.remove(str);

			if (arr2.length > 1)
				answerList.add(getKey(npMap, arr2[arr2.length - 2]));

		}

		int[] answer = new int[answerList.size()];
		for (int i = 0; i < answerList.size(); i++) {
			answer[i] = answerList.get(i);
		}
		return answer;
	}

	public static <K, V> K getKey(Map<K, V> map, V value) {
		// 찾을 hashmap 과 주어진 단서 value
		for (K key : map.keySet()) {
			if (value.equals(map.get(key))) {
				return key;
			}
		}
		return null;
	}

}
