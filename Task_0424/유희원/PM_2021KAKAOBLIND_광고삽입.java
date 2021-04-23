package algo0424;

import java.util.Arrays;

/*
 * play_time: "죠르디" 동영상 재생시간 길이
 * adv_time: 공익광고 재생시간
 * logs: 시청자들이 해당 동영상을 재생했던 구간 정보
 * 시청자들의 누적 재생시간이 가장 많은 곳 시작시각 return
 */
public class PM_2021KAKAOBLIND_광고삽입 {

	public static void main(String[] args) {
		String play_time = "02:03:55";
		String adv_time = "00:14:15";
		String[] logs = {
				"01:20:15-01:45:14", 
				"00:40:31-01:00:00", 
				"00:25:50-00:48:29", 
				"01:30:59-01:53:29", 
				"01:37:44-02:02:30"
		};
		System.out.println(solution(play_time, adv_time, logs));
	}
	
    public static String solution(String play_time, String adv_time, String[] logs) {
        int playtime = durtime(play_time);
        int advtime = durtime(adv_time);
        //int로하면 17번 틀림 ㅠ
        long[] playCnt = new long[playtime+1];
        for (int i = 0; i < logs.length; i++) {
			String[] split = logs[i].split("-");
			playCnt[durtime(split[0])]++; //시작시간
			playCnt[durtime(split[1])]--; //종료시간
		}
        for (int i = 1; i <=playtime; i++) playCnt[i] += playCnt[i-1]; //현재 동영상을 시청하고 있는 사람의 수
        for (int i = 1; i <=playtime; i++) playCnt[i] += playCnt[i-1]; //현재시간까지 동영상을 시청한 총 재생시간
        long maxTime = playCnt[advtime];
        long maxStart = 0;
        for (int i = 0; i+advtime <=playtime; i++) {
        	//광고 종료시간 - 광고시작시간
			long tmp = playCnt[i+advtime] - playCnt[i];
			//최댓값 나올때마다 갱신
			if(tmp > maxTime) {
				maxTime = tmp;
				maxStart = i+1;
			}
		}
        
        return String.format("%02d:%02d:%02d", maxStart/(60*60),(maxStart/60)%60,maxStart%60);
    }

	private static int durtime(String time) {
		String[] arr = time.split(":");
		int[] arr2 = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			arr2[i] = Integer.parseInt(arr[i]);
		}
		//int[] arr3 = Arrays.stream(time.split(":")).mapToInt(Integer::parseInt).toArray();
		return arr2[0]*3600+arr2[1]*60+arr2[2];
	}

}
