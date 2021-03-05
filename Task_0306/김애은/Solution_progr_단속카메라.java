package algo0305;
//고속도로 이동하는 모든 차량을 단속하는 카메라 개수 찾기
//접근법
//나가는 순서대로 sort 한다.
//가장 빨리 나가는애한테 카메라 설치하면 처음에 가장 많은 차를 단속할 수 있다.
//arraylist를 사용해 그 안에 지나가는 차량은 다 remove 한다.
//마지막이 남을때까지 하고 카메라 개수는 answer
import java.util.ArrayList;
import java.util.Collections;

public class Solution_progr_단속카메라 {
	public static void main(String[] args) {
		int[][] routes = {{-20,15},{-14,-5},{-18,-13},{-5,-3}};
		ArrayList<int[]> arr = new ArrayList<>();
		for(int [] route:routes) {
			arr.add(route);
		}
		int answer=0;
		Collections.sort(arr,(x,y)->x[1]-y[1]);
		while(!arr.isEmpty()) {
			int rem = arr.get(0)[1];
			answer++;
			for(int i=0;i<arr.size();i++) {
				if(rem>=arr.get(i)[0]) {
					arr.remove(i);
					i--;
				}
			}
		}
		System.out.println(answer);
	}
}
