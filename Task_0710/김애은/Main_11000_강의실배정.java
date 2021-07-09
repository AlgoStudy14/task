package study0710;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
//총 O(nlogn)
/*
 * 강의 시간을 정렬 후 강의의 끝나는 시간을 다른 강의의 시작 시간과 비교해준다.
 * 만약 시작 시간이 크거나 같으면 같은 강의실에서 강의를 할 수 있다.
 * 만약 시작시간이 작으면 다른 강의실에서 강의를 해야되기 때문에 큐에 삽입한다.
 * 회의실 문제와 비슷하다 생각햇지만 회의실은 sort가 반대로 되어야 한다.
 * 필요없는 강의 시간을 버리는것이 아니라 큐에 넣기 때문ㅇ ㅔ최종적으로 큐의 길이를 출력한다.
 */

public class Main_11000_강의실배정 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N=sc.nextInt();
		int [][] times = new int[N][2];
		for (int i = 0; i < N; i++) {
			times[i][0]=sc.nextInt();
			times[i][1]=sc.nextInt();
		}
		Arrays.sort(times,(o1,o2)->{
			if(o1[0]==o2[0])return o1[1]-o2[1];
			else return o1[0]-o2[0];
		});
		PriorityQueue<Integer> que = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			int start=times[i][0];
			int end= times[i][1];
			if(!que.isEmpty() && que.peek()<=start) {
				que.poll();
			}
			que.add(end);
		}
		System.out.println(que.size());
	}

}
