
public class KAKAO21_광고삽입 {
	/*
	 * 광고삽입
	 * 
	 * 0~100시간을 초로 바꾸고 이걸 배열로 하여 사용중 확인
	 * 
	 */
	static int convert(String time) {
		String[] nums = time.split(":");
		return Integer.parseInt(nums[0]) * 60 * 60 + Integer.parseInt(nums[1]) * 60 + Integer.parseInt(nums[2]);
	}
	
	public static String solution(String play_time, String adv_time, String[] logs) {
		int playSec = convert(play_time);
		int advSec = convert(adv_time);
		int[] totalSec = new int[100 * 3600];
		for(String log : logs) {
			int start = convert(log.substring(0, 8));
			int end = convert(log.substring(9, 17));
			
			for(int i = start; i < end; i++)
				totalSec[i] += 1;
		}
		//누적 시간
		long sum = 0;
		for(int i = 0; i < advSec; i++) {
			sum += totalSec[i];
		}
		
		long max = sum;
		int maxIdx = 0;
		for(int i = advSec; i < playSec; i++) {
			sum = sum + totalSec[i] - totalSec[i - advSec];
			if(sum > max) {
				max = sum;
				maxIdx = i - advSec + 1;
			}
		}
		return String.format("%02:%02d:%02d", (maxIdx/3600), ((maxIdx/60) % 60), maxIdx % 60);
	}
}
