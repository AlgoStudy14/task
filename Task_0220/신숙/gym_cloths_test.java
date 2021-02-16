package Greedy;

public class gym_cloths_test {
	static int[] cloth;
	/* n 만큼 사람이 있고(1~n), lost는 체육복이 없는 사람 배열, reserve는 여분이 있는 사람 배열
	 * cloth 배열은 0으로 초기화되므로, 0일때 옷이 있는 경우, 1일때 없는 걸로 하자.
	 * 
	 * 배열을 써서, lost인 경우들을 빼놓고, 그후에 reserve.
	 * 
	 * 1,2,3,7,12 실패.... 왜?	 	=>  마지막 경우가 이미 여벌을 가져온 사람이 도난을 당했을 수도 있다... 
	 * 추가해줬더니 5, 7 실패했는디??
	 * 아니 이거 고치니까 1,2,3,9,12 가 에러뜨네...	=> 0 이아닐때 예외처리를 덜해줬네.
	 * 시간도 빨라서 만족.
	 */
	public static int solution(int n, int[] lost, int[] reserve) {
		int leng = lost.length;
		int re_leng = reserve.length;
        int answer = n - lost.length;
        cloth = new int[n+1];
        for(int i = 0; i < leng; i++) {
        	cloth[lost[i]] = 1;
        }
        //여벌이 있는 사람이 옷을 가져온 경우는 먼저 없애나봄?
        for(int i = 0; i < re_leng; i++) {
        	if(cloth[reserve[i]] == 1) {
        		cloth[reserve[i]] = 0;
        		reserve[i] = 0;
        		answer++;
        	}
        }
       //여벌이 있는데 없던 사람 제외하고 빌려주기.
        for(int i = 0; i < re_leng; i++) {
        	if(reserve[i] != 1 && reserve[i] != 0) {
        		if(cloth[reserve[i] - 1] == 1) {
        			cloth[reserve[i] - 1] = 0;
        			answer++;
        			continue;
        		}
        	}
        	if(reserve[i] != n && reserve[i] != 0) {
        		if(cloth[reserve[i] + 1] == 1) {
        			cloth[reserve[i] + 1] = 0;
        			answer++;
        			continue;
        		}
        	}
        }
        return answer;        
    }
	public static void main(String[] args) {
		int[] lost = {1,2,4,6};
		int[] reserve = {1,2,4,6};
		int ans = solution(8, lost, reserve);
		System.out.println(ans);
	}
}