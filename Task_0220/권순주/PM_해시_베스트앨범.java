import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 주어진 조건에 맞게 고유번호 나열 
 * 문제 유형 : 구현, 로직은 쉬운데 구현 문법을 공부하는게 중요한듯!
 * 요구 개념 : 해시
 * <풀이법 요약> 
 * 1. 장르에 따른 재생된 총 횟수를 저장하는 HashMap (1)
 *    장르에 따른 재생된 노래 리스트를 저장하는 HashMap (2)
 *    을 각각 따로 만들어서 저장해준다.
 * 2. 지정된 길이만큼 반복문을 돌려준다.
 * 3. 만약 해당 장르가 HashMap에 포함되어 있지 않다면 각각의 HashMap에 추가해준다.
 * 4. 조건 1.속한 노래가 많이 재생된 장르를 먼저 수록합니다.에 맞게 (1)을 value값에 맞게 내림차순으로 소팅해준다.
 * 5. 반복문을 돌리면서 우선순위가 높은 장르를 하나씩 뺴온다.
 * 6. (2)에서 해당 장르를 key로 가지는 playList를 소팅해준다. 조건2,3에 맞게 소팅해준다. (class에 정의)
 * 7. 최대 2개씩 tmpList에 넣어준다.
 * 8. tmpList->answer 배열로 변경해준다.
 */

public class PM_해시_베스트앨범 {

	public int[] solution(String[] genres, int[] plays) {
		HashMap<String, Integer> genreMap = new HashMap<>(); // <장르,장르가 재생된 총 횟수> 저장
		HashMap<String, LinkedList<Music>> playMap = new HashMap<String, LinkedList<Music>>(); // <장르,재생된 노래> 저장
		int size = genres.length;

		for (int i = 0; i < size; i++) {
			if (!playMap.containsKey(genres[i])) { // 장르를 key값으로 하는 hashMap이 존재하지 않으면
				genreMap.put(genres[i], 0); // genreMap추가
				playMap.put(genres[i], new LinkedList<Music>()); // playMap 추가
			}
			genreMap.replace(genres[i], genreMap.get(genres[i]) + plays[i]); // 장르에 해당하는 총 재생 횟수 증가
			playMap.get(genres[i]).add(new Music(i, plays[i])); // 장르에 해당하는 Music만들어서 추가 
		}

		// value값으로 내림차순 정렬 - 코드 가져옴
		// 조건 1.속한 노래가 많이 재생된 장르를 먼저 수록합니다.
		List<String> genreList = new ArrayList<>(genreMap.keySet());
		Collections.sort(genreList, (o1, o2) -> (genreMap.get(o2).compareTo(genreMap.get(o1))));

		List<Integer> tmp = new ArrayList<>(); // 정답 저장을 위한 List
		int cnt = 0; // 배열 크기 체크를 위한 변수
		for (String genre : genreList) {
			LinkedList<Music> playList = playMap.get(genre); // 조건1에 해당하는 장르의 playList를 가져옴
			Collections.sort(playList); // playList를 조건2,3에 맞게 sort (아래 class에 정의)
			if (!playList.isEmpty()) { // 비어있지 않으면 한개 출력
				tmp.add(playList.poll().index);
				cnt++;
			}
			if (!playList.isEmpty()) { // 비어있지 않으면 한개 출력
				tmp.add(playList.poll().index);
				cnt++;
			}
		}

		int[] answer = new int[cnt]; // List -> Array
		int i = 0;
		for (int index : tmp) {
			answer[i++] = index;
		}

		return answer;
	}

	private static class Music implements Comparable<Music> { // Music 객체 생성
		int index; // 고유번호
		int play; // 재생 횟수

		public Music(int index, int play) {
			this.index = index;
			this.play = play;
		}

		@Override
		public int compareTo(Music o) {
			if (this.play == o.play)
				return this.index - o.index; // 조건 3.장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
			return o.play - this.play; // 조건 2.장르 내에서 많이 재생된 노래를 먼저 수록합니다.
		}
	}

	public static void main(String[] args) {
		PM_해시_베스트앨범 pm = new PM_해시_베스트앨범();
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };
		System.out.println(Arrays.toString(pm.solution(genres, plays)));
	}
}
