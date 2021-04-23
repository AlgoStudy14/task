package 문자열;

public class Solution_카카오2021_광고삽입누적합 {
/*
 * prefix sum을 활용한 문제
 * 광고를 삽입했을 때 가장 누적으로 많이 보는 시작 시간 구하기
 * 1. 각 시간마다 보는 사람 count하기
 * 2. 처음 0초에 시작하는 adv로 보는 사람 수 구하기
 * 3. 1초~ adv+1, 2~adv+2 --- play-adv~play까지 모두 구해서 가장 큰값 체크하기

 */
	public static void main(String[] args) {
		String play_time="99:59:59";
		String adv_time="25:00:00";
		String[] logs= {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
		String result="01:30:59";
		System.out.println(solution(play_time,adv_time,logs));
	}
	
	  public static String solution(String play_time, String adv_time, String[] logs) {
		  if(play_time.equals(adv_time))return "00:00:00";
		  int le = logs.length;
		  int play=change(play_time);
		  int arr[]=new int[play+1];
		  int adv=change(adv_time);
		  long min=0;
		  int index=0;
		  
		  for(int i=0;i<le;i++) {//log개수만큼 돈다
			  String log[]=logs[i].split("-");
			  int start=change(log[0]);
			  int end = change(log[1]);
			  for(int j=start;j<end;j++) {
				  arr[j]+=1; //개수
			  }
		  }
		  int start=0;
		  for(int i=start;i<adv;i++) { //0초부터 광고 시작했을 경우
			  min+=arr[i];
		  }
		  long answer=min;
		  int end=adv;
		  while(end<=play) { //prefix sum
			 answer-=arr[start];
			 answer+=arr[end];
			 if(answer>min) {
				 min=answer;
				 index=start+1;
			 }
			 start++;
			 end++;
		  }
		 
		  System.out.println(index);
		  return print(index);
	}
		  
	private static String print(int ne_index) {
		int hour=ne_index/3600;
		int minute=ne_index%3600/60;
		int sec=ne_index%3600%60;
		return String.format("%02d:%02d:%02d", hour,minute,sec);
	}

	private static int change(String time) {
		String sec[] = time.split(":");
		return Integer.parseInt(sec[0])*3600 + Integer.parseInt(sec[1])*60+Integer.parseInt(sec[2]);
	}
}
