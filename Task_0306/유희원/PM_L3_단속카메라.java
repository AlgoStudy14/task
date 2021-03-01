/*
 * 프로그래머스 코딩테스트 연습 고득점 kit - 탐욕법(Greedy) - 단속카메라
 * routes = {{들어온지점, 나간지점},{}...}
 * 모든 차들이 단속카메라 한번은 지나가게끔 설치할건데 그 카메라 개수 최소
 */

//맞긴 맞았는데 예제에서 왜 -5지점에 카메라 설치했을때 첫번째(-20,15)가 해당이 안되는지 모르겠음;;?

import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
      
        Arrays.sort(routes,new Comparator<int[]>() {
      //이 부분 람다식+Integer compare로 표현하면 
		  //Arrays.sort(routes, (o1,o2) -> Integer.compare(o1[1],o2[1]));
		  //이렇게도 표현 가능
			@Override
			public int compare(int[] o1, int[] o2) {
        //나간지점 기준 정렬
				return o1[1]-o2[1];
			}
		});
    //만약에 숫자로 줄거면 -30001인가 그런식으로 주면 될거같은데 걍 맥스때림
		int camera = -Integer.MAX_VALUE;
		for (int i = 0; i <routes.length; i++) {
      //시작지점이 camera설치 지점보다 뒤면 하나 추가요~~~~ 그리고 비교할 camera 위치갱신
			if(camera<routes[i][0]) {
				camera = routes[i][1];
				answer++;
			}
		}
        return answer;
    }
}
