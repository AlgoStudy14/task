import java.util.Stack;

/*
 * <문제 요약>
 * 컴퓨터간의 연결을 나타내는 map이 주어졌을 때 연결되는 집합인 네트워크의 수 구하기
 * 
 * <풀이법 요약>
 * 컴퓨터의 갯수만큼 int배열 생성
 * 0번 컴퓨터 부터 시작(스택에 0 넣음)
 * 만약에 해당 컴퓨터의 배열의 숫자가 0이고(아직 네트워크에 속하지 않음) 스택에서 나온 컴퓨터와의 map값이 1이면(해당 컴퓨터와 연결) 스택에 추가
 */

public class BFS_네트워크 {

	public static void main(String[] args) {
		BFS_네트워크 pm = new BFS_네트워크();
		int n = 3;
		int[][] computers = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
		System.out.println(pm.solution(n, computers));
	}

	public int solution(int n, int[][] computers) {
		int answer = 0;
		int count = 1;
		int[] arr = new int[n];
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < n; i++) {
			if (arr[i] == 0) {
				stack.push(i);
				arr[i] = count;

				while (stack.size() > 0) {
					int num = stack.pop();
					for (int j = 0; j < n; j++) {
						if (computers[num][j] == 1 && arr[j] == 0) {
							arr[j] = count;
							stack.push(j);
						}
					}
				}
				count++;
			}
		}
		return count - 1;
	}

}
