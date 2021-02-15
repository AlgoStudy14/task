package Hash;

import java.util.*;
import java.util.Map.Entry;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 주어진 조건에 맞는 장르별 고유 번호 배열(2개씩 순서대로)(Ex : 클래식 2개, 팝 2개).
 * 문제 유형 : 구현 .
 * 요구 개념 : 해시맵, 정렬(Comparator).
 * <풀이법 요약> 
 * 1 - 1. 해시맵에 키를 장르로, 값을 재생 횟수로 저장한다.
 * 1 - 2. 더 재생이 많이된 장르를 파악한다(Comparator 이용). 
 * 2. 1의 내용을 바탕으로 장르 순서대로 다음과 같이 진행한다. 
 * 2 - 1. 먼저 선택된 장르의 고유 번호를 키로, 재생 횟수를 값으로 해시맵에 저장한다. 
 * 2 - 2. 1의 내용과 마찬가지로 Comparator를 이용해 정렬한다 
 * 2 - 3. 만일, 재생 횟수가 동일하다면 고유 번호가 낮은 노래 순으로 정렬한다. 
 * 2 - 4. 2개의 고유 번호를 뽑고, 다음 장르로 넘어간다.
 */

public class 베스트앨범 {
	public static void main(String[] args) {
		베스트앨범 doit = new 베스트앨범();
		String[] genres = new String[] { "classic", "pop", "classic", "classic", "pop", "rap" };
		int[] plays = new int[] { 500, 600, 500, 800, 2500, 5000 };
		doit.solution(genres, plays);
	}

	public int[] solution(String[] genres, int[] plays) {
		int[] answer = {};
		ArrayList<Integer> temp = new ArrayList<>();

		// 1 - 1. 키를 장르로, 값을 재생 횟수로하여 해시 맵에 저장한다.
		HashMap<String, Integer> tot_plays = new HashMap<String, Integer>();
		for (int i = 0; i < genres.length; i++) {
			// 만일 해당 장르가 존재하면, 재생 횟수 추
			if (tot_plays.get(genres[i]) != null) {
				tot_plays.put(genres[i], tot_plays.get(genres[i]) + plays[i]);
			} else {
				tot_plays.put(genres[i], plays[i]);
			}

		}

		// 1 - 2. HashMap을 정렬하여 플레이 횟수가 더 높은 장르 순으로 정렬한 값을 기억해둔다.
		List<Entry<String, Integer>> tot_plays_rank = new ArrayList<Entry<String, Integer>>(tot_plays.entrySet());
		Collections.sort(tot_plays_rank, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		// 2. 1의 내용을 바탕으로 장르 순서대로 다음과 같이 진행한다.
		for (Entry<String, Integer> e : tot_plays_rank) {
			HashMap<Integer, Integer> genre_plays = new HashMap<Integer, Integer>();
			// 2 - 1. 선택된 장르에 대하여 고유 번호를 키로, 재생 횟수를 값으로 해시맵에 저장한다.
			for (int i = 0; i < genres.length; i++) {
				if (genres[i].equals(e.getKey())) {
					// Key : 고유 번호, Value : 재생 횟수
					genre_plays.put(i, plays[i]);
				}
			}
			// 2 - 2. HashMap을 정렬하여, 더 재생 횟수가 높은 고유 번호 순으로 나열한다.
			List<Entry<Integer, Integer>> genre_plays_rank = new ArrayList<Entry<Integer, Integer>>(
					genre_plays.entrySet());
			Collections.sort(genre_plays_rank, new Comparator<Entry<Integer, Integer>>() {
				public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
					// 2 - 3. 이때 재생 횟수가 같다면 고유 번호 순으로 정렬.
					if (o2.getValue().compareTo(o1.getValue()) == 0) {
						return o1.getKey().compareTo(o2.getKey());
					} else {
						return o2.getValue().compareTo(o1.getValue());
					}
				}
			});

			// 2 - 4. 2개의 고유 번호를 뽑고, 다음 장르로 넘어간다(1개라면 한개만 선택).
			temp.add(genre_plays_rank.get(0).getKey());
			if (genre_plays_rank.size() >= 2) {
				temp.add(genre_plays_rank.get(1).getKey());
			}
		}

		// answer로 전환.
		answer = new int[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			answer[i] = temp.get(i);
		}

		return answer;
	}
}
