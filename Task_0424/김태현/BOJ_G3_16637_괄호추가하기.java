package s0424;

import java.util.Arrays;

/*
 * <문제 요약>
 * 구해야 하는 것 : 공익 광고가 최대한 많이 노출되는 시작 지점.
 * 문제 유형 : 시뮬레이션.
 * <풀이법>
 * 1. 로그를 (시 -> 분 -> 초)순으로 정렬한다.
 * 2. 로그를 {시작 시, 시작 분, 시작 초, 종료 시, 종료 분, 종료 초}순 int형 배열로 변환한다.
 * 3. 1번 로그부터 N번 로그를 시작점으로하여 광고 길이와 겹치는 부분을 계산하고 갱신한다.
 * -> 단, 광고 시간이 플레이시간을 넘어서는 경우에서 반복을 종료한다.
 */
public class PM_KAKAO2021_광고삽입 {
	public String solution(String play_time, String adv_time, String[] logs) {
		// 플레이 시간과 광고 시간이 같은 경우는 시작점 리턴
		if (play_time.equals(adv_time)) {
			return "00:00:00";
		}
		// 플레이 시간 변환
		int play = Integer.parseInt(play_time.substring(0, 2)) * 3600 + Integer.parseInt(play_time.substring(3, 5)) * 60
				+ Integer.parseInt(play_time.substring(6, 8));

		// 광고 시간 변환
		int adv = Integer.parseInt(adv_time.substring(0, 2)) * 3600 + Integer.parseInt(adv_time.substring(3, 5)) * 60
				+ Integer.parseInt(adv_time.substring(6, 8));

		// 로그 시간들 정렬
		Arrays.sort(logs);
		System.out.println(Arrays.toString(logs));
		// log 시간 변환
		int[][] log = new int[logs.length][2];
		for (int i = 0; i < logs.length; i++) {
			log[i] = new int[] {
					Integer.parseInt(logs[i].substring(0, 2)) * 3600 + Integer.parseInt(logs[i].substring(3, 5)) * 60
							+ Integer.parseInt(logs[i].substring(6, 8)),
					Integer.parseInt(logs[i].substring(9, 11)) * 3600 + Integer.parseInt(logs[i].substring(12, 14)) * 60
							+ Integer.parseInt(logs[i].substring(15, 17)) };
		}

		// 로그 1번부터 시작점으로 하여 겹치는 시간을 계산하며 값을 갱신한다.
		String answer = "";
		long max = 0;
		int start = 0;
		for (int i = 0; i < log.length; i++) {
			// 광고 종료 시간
			int end = log[i][0] + adv;
			// 총 광고 상영시간
			long run = 0;

			// 만일, 광고 종료 시간이 플레이 타임보다 크다면 반복 종료
			if (end > play) {
				break;
			}

			// 만일 로그의 시작점이 광고 종료 시간의 종료점보다 작거나 같다면
			for (int j = 0; j < log.length; j++) {
				if (log[j][0] < end) {
					// 만일 종료지점도 작으면 로그 시간을 넣기
					if (log[j][1] < end) {
						run += (log[j][1] - log[j][0]);
					}
					// 그게 아니면 종료점 - 로그 시작점
					else {
						run += (end - log[j][0]);
					}
				}
				// 그게 아니면 반복 종료
				else {
					break;
				}
			}

			// 만일, 광고 시간이 더 긴 경우에만 시작점 갱신
			if (max < run) {
				max = run;
				start = log[i][0];
			}
		}
		// 결과 변환
		int h = start / 3600;
		int m = (start % 3600) / 60;
		int s = (start % 3600) % 60;
		if (h < 10) {
			answer += "0" + h + ":";
		} else {
			answer += h + ":";
		}
		if (m < 10) {
			answer += "0" + m + ":";
		} else {
			answer += m + ":";
		}
		if (s < 10) {
			answer += "0" + s;
		} else {
			answer += s;
		}
		System.out.println(answer);

		return answer;
	}
}
