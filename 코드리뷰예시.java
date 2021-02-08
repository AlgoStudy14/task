package StackQueue;

/***
 * <문제 요약>
 * 구해야 하는 것 : 모든 트럭이 다리를 건너는데 걸리는 최단 시간.
 * 제약 사항 : 다리의 최대 하중이 정해져 있다.
 * 문제 유형 : 구현(코드 부분이 더 중요 : 사소한 로직, 문법).
 * 요구 개념 : 순서대로 다리를 건너는 것, 다리를 빠져나오는 과정등에서 미루어 보아 큐가 적절하다고 생각했음.
 * <풀이법 요약>
 * 1. 대기 중인 트럭을 wait 큐에 넣는다(순서대로 나와야 하니까).
 * 2. 다리를 건너는 중인 트럭을 bridge 큐에 넣는다(역시 순서대로 나와야 하니까).
 * -> 이때, 크기가 2인 정수 배열을 넣고, 이동거리를 실시간으로 갱신.
 * 3. 시간부터 증가(초 단위 진행) -> 트럭 진입 허용 여부 확인 -> 다리위 트럭의 이동거리 갱신 -> 다 건넌 트럭이 있는지 체크.
 */
import java.util.LinkedList;
import java.util.Queue;

public class 다리를지나는트럭 {

	public static void main(String[] args) {
		다리를지나는트럭 doit = new 다리를지나는트럭();
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = { 7, 4, 5, 6 };

		doit.solution(bridge_length, weight, truck_weights);
	}

	public int solution(int bridge_length, int weight, int[] truck_weights) {
		// 대기 중인 트럭을 저장할 큐(큐와 이동거리를 동시에 배열로 저장).
		Queue<int[]> wait = new LinkedList<>();
		// 다리 위를 건너는 트럭을 저장할 큐
		Queue<int[]> bridge = new LinkedList<>();
		for (int i = 0; i < truck_weights.length; i++) {
			// 트럭의 무게, 이동거리(0으로 초기화).
			wait.offer(new int[] { truck_weights[i], 0 });
		}

		// 대기 트럭이 없을때까지 반복한다.
		int time = 0;
		int now_weight = 0;
		while (!wait.isEmpty()) {
			// 1초 경과.
			time++;
			// 다리 허용량을 초과하지 않으면, 트럭 진입을 허용한다.
			if (weight >= wait.peek()[0] + now_weight) {
				now_weight += wait.peek()[0];
				bridge.offer(wait.poll());
			}
			// 초 단위로 다리를 건너는 각 트럭의 이동 거리를 구한다.
			for (int i = 0; i < bridge.size(); i++) {
				// 배열을 넣는 경우, new를 해서 넣어주어야 한다. 그렇게 안하면 참조 복사가 일어남.
				bridge.offer(new int[] { bridge.peek()[0], bridge.peek()[1] + 1 });
				bridge.poll();
			}
			// 다리를 모두 건넌 트럭에 대하여, 현재 다리를 건너는 트럭의 총 무게를 갱신한다.
			while (!bridge.isEmpty() && bridge.peek()[1] == bridge_length) {
				now_weight -= bridge.peek()[0];
				bridge.poll();
			}

		}
		// 마지막 트럭까지 계산한다.
		time += bridge_length;

		return time;
	}
}
