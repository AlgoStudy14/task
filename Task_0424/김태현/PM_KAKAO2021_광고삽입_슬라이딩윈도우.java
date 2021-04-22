package s0424;

/*
 * <문제 요약>
 * 구해야 하는 것 : 공익 광고가 최대한 많이 노출되는 시작 지점.
 * 문제 유형 : 슬라이딩 윈도우(투 포인터).
 * <풀이법>
 * 1. String 형태의 시간을 모두 초단위로 변경한다.
 * 2. 로그의 모든 시간을 카운팅 배열에 기록한다. 
 * 3. 슬라이딩 윈도우를 이용하여 광고 시간 만큼 모든 로그의 합을 구하며 값을 갱신한다.
 */
public class PM_KAKAO2021_광고삽입_슬라이딩윈도우 {
	public static void main(String[] args) {
		String playtime = "02:03:55";
		String advtime = "00:14:15";
		String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
		
		solution(playtime, advtime, logs);
				
	}
	
	public static String solution(String play_time, String adv_time, String[] logs) {
		// 플레이 시간과 광고 시간이 같은 경우는 시작점 리턴
		if (play_time.equals(adv_time)) {
			return "00:00:00";
		}
		// 총 플레이 시간 변환
		int play = timeToSec(play_time);
		// 광고 시간 변환
		int adv = timeToSec(adv_time);

		// log 시간 변환
		int[] cnt = new int[360000];
		for (int i = 0; i < logs.length; i++) {
			String[] log = logs[i].split("-");
			int start = timeToSec(log[0]);
			int end = timeToSec(log[1]);
			for (int s = start; s < end; s++) {
				cnt[s]++;
			}
		}

		// 0초부터 시작하여 광고의 시간 만큼 겹치는 영상의 길이를 계산하고 갱신한다(슬라이딩 윈도우).
		int s = 0, e = 0;
		// 광고의 끝 부분이 플레이 시간을 벗어나는 순간 종료
		long run = 0L;
		long max = Integer.MIN_VALUE;
		int start = 0;
		// play 타임의 마지막 시간도 포함해야 한다(주의).
		while (e != play + 1) {
			// 총 구간의 길이가 광고 시간보다 작다면 런타임을 더해주며 구간 확장.
			if (e - s < adv) {
				run += cnt[e++];
			}
			// 총 구간의 길이가 광고 시간과 같아진다면
			// 런 타임 갱신 -> 앞 구간 축소 -> 뒷 구간 확대
			else if (e - s == adv) {
				if (run > max) {
					max = run;
					start = s;
				}
				run -= cnt[s++];
				run += cnt[e++];
			}
		}

		// 결과 변환
		int h = start / 3600;
		int m = (start % 3600) / 60;
		int sec = (start % 3600) % 60;
		String answer = "";
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
		if (sec < 10) {
			answer += "0" + sec;
		} else {
			answer += sec;
		}

		return answer;
	}

	private static int timeToSec(String time) {
		return Integer.parseInt(time.substring(0, 2)) * 3600 + Integer.parseInt(time.substring(3, 5)) * 60
				+ Integer.parseInt(time.substring(6, 8));
	}
}
