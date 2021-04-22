package s0424;

/*
 * <문제 요약>
 * 구해야 하는 것 : 공익 광고가 최대한 많이 노출되는 시작 지점.
 * 문제 유형 : 구간합.
 * <풀이법>
 * 1. String 형태의 시간을 모두 초단위로 변경한다.
 * 2. 로그의 모든 시간을 카운팅 배열에 기록한다. 
 * 3. 구간합을 이용하여 광고 시간 만큼 모든 로그의 합을 구하며 값을 갱신한다.
 */
public class PM_KAKAO2021_광고삽입_구간합 {
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

		// 누적 합 구하기.
		long[] prefix = new long[360001];
		for(int i = 1; i < 360001; i++) {
			prefix[i] = prefix[i - 1] + cnt[i - 1];
		}
		// 광고 구간을 옮겨가며 최대합 갱신.
		int s = 0, e = adv;
		long run = 0;
		int start = 0;
		while(e < play + 1) {
			if(run < prefix[e] - prefix[s]) {
				run = prefix[e] - prefix[s];
				start = s;
			}
			e++;
			s++;
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
