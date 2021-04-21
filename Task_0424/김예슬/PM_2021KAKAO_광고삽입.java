package algo0421;

/***
 * <문제 요약>
 * 구해야 하는 것 : 누적 재생시간이 가장 큰 시작 시각을 출력
 * 제약 사항 : 
 * 문제 유형 : 투포인터(슬라이딩 윈도우) 사용
 * <풀이법 요약>
 * 1. 동영상 재생시간, 광고 재생시간, 시청자들의 재생기록을 초단위로 변환
 * 2. 크기가 동영상 재생시간만큼인 배열을 만들고, 시청자 재생시간을 누적
 * 3. 광고 재생시간만큼 이동하며 누적 재생시간이 가장 큰 곳을 찾음
 *  3-1. 이때, 매번 처음부터 끝까지 계산을 해 저장하면 O(n^2)의 복잡도를 가짐
 *  3-2. 투포인터 사용 시, O(nlogn)로 줄일 수 있음
 * 4. 누적 재생시간이 가장 큰 곳의 시작 시각을 출력 포맷에 맞게 변환
 * 
 * c.f) 문제의 마지막에도 나와있듯이 (종료된 시각 - 시작된 시각)을 못보고 착각해 종료된 시각까지 포함해서 계산함..
 *      17번 테스트의 경우, 광고시간이 36000초이고 시청자들의 재생시간이 30만이면 오버플로우 발생..
 */

public class PM_2021KAKAO_광고삽입 {
    static int[] video;
    static int play_second, adv_second, max_time_start;
    static long max_time;
	
    public static String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        play_second = convertInt(play_time);
        adv_second = convertInt(adv_time);
        video = new int[play_second + 1];
        
        // 시청자 재생시간 기록
        for (int i = 0; i < logs.length; i++) {
            int start = convertInt(logs[i].substring(0, 8));
            int end = convertInt(logs[i].substring(9));
            for (int t = start; t < end; t++)
                ++video[t];
        }
        
        find();

        return convertString();
    }
    
    // 누적 재생시간 찾기
    static void find() {
    	// 제일 처음 재생시간 저장
        max_time_start = 0;
        for (int i = 0; i < adv_second; i++)
            max_time += video[i];
        
        // 슬라이딩 윈도우가 이동하면서 전의 값은 빼주고, 후의 값은 더해줌
        long tmp = max_time;
        for (int i = 1; i < play_second - adv_second + 1; i++) {
            tmp -= video[i - 1];
            tmp += video[i + adv_second - 1];
            
            if (max_time < tmp) {
                max_time = tmp;
                max_time_start = i;
            }
        }
    }
    
    // 문자열 -> 초로 변환
    static int convertInt(String time) {
        int result = 0;
        result += Integer.parseInt(time.substring(0, 2)) * 3600;
        result += Integer.parseInt(time.substring(3, 5)) * 60;
        result += Integer.parseInt(time.substring(6));
        return result;
    }
    
    // 시작 시각 출력 포맷으로 변환
    static String convertString() {
    	StringBuffer answer = new StringBuffer();
        int h = max_time_start / 3600;
        max_time_start %= 3600;
        int m = max_time_start / 60;
        max_time_start %= 60;
        int s = max_time_start;
        
        if (h < 10) answer.append("0");
        answer.append(h + ":");
        if (m < 10) answer.append("0");
        answer.append(m + ":");
        if (s < 10) answer.append("0");
        answer.append(s);
        return answer.toString();
    }
}