
/*
 * <문제 요약>
 * 구해야 하는 것 : 공익광고가 들어갈 시작 시간
 * 문제 유형 : 구간 합
 * 
 * <풀이법 요약>
 * 1. log에 저장된 시간의 시청자 수를 누적한다
 * 2. (구간 합 - 이전 시작 시간 + 현재 종료 시간)을 이용해서 구간합을 구한다
 * 3. 구간 합이 최댓값보다 크다면
 * 	(1). 최댓값 변경
 * 	(2). Index 변경
 * 4. Index에 해당하는 시간을 출력한다
 * 
 * 어제 풀다가 포기하고 오늘 아침 맑은 정신으로 구글링했다 
 * 유툽보고 풀었음 배워가는게 많은 문제
 */

public class Solution_2021KakaoBlind_채용_광고_삽입 {

	static int[] timeTable;

	public String solution(String play_time, String adv_time, String[] logs) {
		// 동영상 재생 시간
		int playTime = stringToTime(play_time);
		// 광고 재생 시간
		int advTime = stringToTime(adv_time);
		timeTable = new int[playTime + 1];

		for (String log : logs) {
			String[] tmp = log.split("-");
			int start = stringToTime(tmp[0]); // 시작 시간
			int end = stringToTime(tmp[1]); // 종료 시간

			// 시청 시간내 시청자 누적
			for (int i = start; i < end; i++) {
				timeTable[i] += 1;
			}
		}

		// sumViewer : 광고 시청자 수의 구간 합
		long sumViewer = 0;
		// 광고 시청자 수 초기값 설정
		for (int i = 0; i < advTime; i++) {
			sumViewer += timeTable[i];
		}

		// timeIndex : 광고 시청자 수가 최대일 때의 광고 시작시간
		int timeIndex = 0;
		// maxViewer : 광고 시청자 수의 최댓값
		long maxViewer = sumViewer;
		for (int i = advTime; i < playTime; i++) {
			// 구간 합 - 이전 시작 시간 + 현재 종료 시간
			sumViewer += (timeTable[i] - timeTable[i - advTime]);
			// 구간 합이 최댓값보다 크다면
			// 1. 최댓값 변경
			// 2. Index변경
			if (sumViewer > maxViewer) {
				maxViewer = sumViewer;
				timeIndex = i - advTime + 1;
			}
		}

		return timeToString(timeIndex);
	}

	// String -> Time
	private static int stringToTime(String log) {
		String[] time = log.split(":");
		return Integer.parseInt(time[0]) * 60 * 60 + Integer.parseInt(time[1]) * 60 + Integer.parseInt(time[2]);
	}

	// Time -> String
	private static String timeToString(int time) {
		return String.format("%02d:%02d:%02d", time / 3600, time / 60 % 60, time % 60);
	}

	public static void main(String[] args) {
		Solution_2021KakaoBlind_채용_광고_삽입 pm = new Solution_2021KakaoBlind_채용_광고_삽입();
		String play_time = "02:03:55";
		String adv_time = "00:14:15";
		String logs[] = { "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29",
				"01:37:44-02:02:30" };
		System.out.println(pm.solution(play_time, adv_time, logs));
	}

}
