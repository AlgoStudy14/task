/*
 * <문제 요약>
 * 시청자들이 동영상을 보는 구간이 주어졌을 때 최대한 많은 시청자가 공익광고를 보도록 공익광고 시간 설정해주기.
 * 
 * <풀이법 요약>
 * 전체 동영상을 초단위로 나누어서 배열을 만듬
 * 시청자를 기준으로 for문을 돌며 시청하는 구간을 +1해줌
 * 공익광고가 0초부터 시작한다는 가정하에 시청자가 공익광고를 보는 시간을 구함
 * 초단위로 앞은 1초빼고 뒤는 1초 더하고 식으로 시청자가 공익광고를 보는시간을 변경해가며 answer와 비교
 * 
 * <내가 생각했던 방식>
 * 공익광고의 시작지점은 시청자가 동영상시청시작시간 or 끝나는시간이라고 생각했음
 * 따라서 시청자별로 시청시작시간을 기준으로 배열을 sorting하고 공익광고가 시작할 수 있는 시간도 sorting해서 해당 공익광고 시간에 겹치는 시청자를 구해주는 방식을 했음
 * 
 * <문제점>
 * 시청자가 300000이라 효율적인 방법을 생각하다 저런 방법을 생각한 것이였음
 * 근데 오히려 최대 300000을 두번 sorting하는거라 훨씬 비효율적인 것이였음
 * 
 * <느낀점>
 * 완탐이 엄청 나쁜것만은 아님!!
 * 오히려 쉽게 풀수 있는것을 어렵게 생각한 느낌.
 */

public class 카카오_광고삽입 {

	class Solution {
	    public String solution(String play_time, String adv_time, String[] logs) {
	        String answer = "";
	                int playTime = strToSecond(play_time);
	        int advTime = strToSecond(adv_time);
	        int[] counts = new int[playTime + 1]; // playTime의 값까지 포함해야함으로 +1

	        for (String log : logs) {
	            String[] splits = log.split("-");
	            int startViewTime = strToSecond(splits[0]);
	            int endViewTime = strToSecond(splits[1]);

	            // viewer 의 시작부터 끝까지 +1 - 종료 시점은 본것아니므로 < 부등호 사용
	            for (int i = startViewTime; i < endViewTime; i++) {
	                counts[i]++;
	            }
	        }

	        // 0초에 광고를 넣는 다고가정 했을때 누적 값을 계산
	        int startTime = 0;
	        int endTime = advTime;
	        long sum = 0;
	        for (int i = startTime; i < endTime; i++) {
	            sum += counts[i];
	        }

	        // 누적값에서 앞에 값을 빼고 뒤에 값을 추가하면서 각초마다 광고를 넣었을때의 누적값을 구하고 비교
	        long max = sum;
	        int maxStartTime = 0;
	        while (endTime <= playTime) {
	            sum -= counts[startTime];
	            sum += counts[endTime];
	            if(sum > max) {
	                max = sum;
	                maxStartTime = startTime + 1;
	            }
	            startTime++;
	            endTime++;
	        }
	        return secondToStr(maxStartTime);
	    }

	    public int strToSecond(String str) {
	        String[] split = str.split(":");
	        return Integer.parseInt(split[0]) * 60 * 60 + Integer.parseInt(split[1]) * 60 + Integer.parseInt(split[2]);
	    }

	    public String secondToStr(int time) {
	        int hour = time / 3600;
	        time %= 3600;
	        int minute = time / 60;
	        int second = time % 60;

	        String strHour = hour > 9 ?  String.valueOf(hour) : "0" + hour;
	        String strMinute = minute > 9 ?  String.valueOf(minute) : "0" + minute;
	        String strSecond = second > 9 ?  String.valueOf(second) : "0" + second;

	        return String.join(":", strHour, strMinute, strSecond);
	    }
	}
	
}
