package Greedy;

import java.util.Arrays;
import java.util.Comparator;

public class PM_L3_단속카메라 {
	/*
	 * 고속도로 이용 차량이 최소한 한번은 단속 카메라를 만나도록 설치.
	 * 차 경로가 routes 배열로 주어짐.
	 * 배열은 2차원 배열로, 내부 배열에서도 2개의 값이 주어짐.
	 * routes[i][0]은 차량 고속도로 진입 지점, routes[i][1]은 고속도로 탈출 지점.
	 * 진입 지점도 카메라를 만난 것으로 간주.
	 * 
	 * 흠... 저번에 정올 냉장고 온도랑 비슷한 문젠가?
	 * 개념 => 해당 범위에 설치하여 적용될 때 개수는 1개로 판단. 범위를 벗어나면 1개씩 늘어난다.
	 * 1. 최저값 기준으로 정렬 필요 -> 최저값이 같다면 그뒤는 최고값 기준 정렬.
	 * 2. 반복문으로 하나씩 확인하면서 다음에 오는 것의 시작점이 다른 것의 종료지점보다 더 크다면, answer++, max값 변경.
	 * 3. 더 적을때는 최고값 기준으로 정렬을 한 것을 기준으로 MAX값을 바꾸어주어야함. 
	 */
	
	public static int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, new Comparator<int[]>(){
            @Override
            public int compare(int[] s1, int[] s2) {
                int diff = s1[0] - s2[0];
                return diff != 0 ? diff : s1[1] - s2[1];
            }
        });
        int maxR = Integer.MIN_VALUE;
        int N = routes.length;
        for(int i = 0; i < N; i++) {
            if(routes[i][0] > maxR) {
                maxR = routes[i][1];
                answer++;
            }else {
                if(routes[i][1] < maxR)
                    maxR = routes[i][1];
            }
        }
        return answer;
    }
	
	public static void main(String[] args) {
		int a = solution(new int[][] { {-20, 15}, {-14, -5}, {-18, -13}, {-5,-3}});
		System.out.println(a);
	}
}
