package Greedy;

import java.util.Stack;

/*** 
 * <문제 요약>
 * 구해야 하는 것 : k개의 수를 제거 했을때 얻을 수 있는 가장 큰 수.
 * 문제 유형 : 그리디.
 * 요구 개념 : 문자열 변환, 스택.
 * <풀이법 요약>
 * 1. 그리디 : 최고 자릿수를 가장 크게 만들자 : 들어오는 수보다 작은 수는 제거한다.
 * 2. 스택에 숫자를 차례로 넣으며 아래 조건을 수행한다.
 * 2 - 1. 만일, 넣는 숫자가 현재 스택의 Top보다 작거나 같다면, 그냥 넣는다.
 * 2 - 2. 만일, 넣는 숫자가 현재 스택의 Top보다 크다면, 크거나 같은 수가 나올때 까지 스택을 비우고 수를 넣는다(스택을 비울때마다 제거 + 1)
 * 2 - 3. 스택을 다 채웠는데 k개를 제거하지 못했다면, 스택을 k개 만큼 pop한다(pop마다 제거 + 1)
 * 3. 결과를 도출한다.
 * <난해 했던 점 1>
 * ArrayList를 이용하여 인덱스를 기반으로 해당 로직을 구현했으나
 * 구현이 매끄럽지 못하고, 결과적으로 remove연산에서 시간이 많이 걸리기 때문에 시간 초과 발생.
 * 따라서 이 문제는 Stack을 쓰는 것이 맞다.
 * <난해 했던 점 2>
 * 들어오는 숫자가 작다고 바로 제거하면 안된다.
 * 반드시, 들어오는 숫자가 큰 경우 스택을 비우는 방식으로 숫자를 제거해야 한다.
 */

public class 큰수만들기 {
	public static void main(String[] args) {
		큰수만들기 doit = new 큰수만들기();
		String number = "1924";
		int k = 2;
		doit.solution(number, k);
	}

	public String solution(String number, int k) {
		String answer = "";
		// 스택에 숫자를 차례로 넣으며 조건 수행.
		Stack<Integer> s = new Stack<Integer>();
		int cnt = 0;
		for (int i = 0; i < number.length(); i++) {
			int push_num = number.charAt(i) - '0';
			// k개를 제거했으면 스택을 마저 채우고 반복 종료.
			if(cnt == k) {
				s.push(push_num);
				continue;
			}
			// 스택이 비어 있으면 숫자를 넣고 반복문 진행.
			if (s.isEmpty()) {
				s.push(push_num);
				continue;
			}
			// 2 - 1. 만일, Top이 넣는 수보다 크다면 넣지 않고 제거 개수 + 1.
			if (s.peek() >= push_num) {
				s.push(push_num);
				continue;
			}
			// 2 - 2. 만일, Top이 넣는 수보다 작다면 Top이 넣는 수보다 크거나 같은 수가 나올때가지 제거 개수를 + 1하며 스택을 비운다.

			else {
				while(true) {
					if(s.isEmpty() || cnt == k) {
						break;
					}
					if(s.peek() >= push_num) {
						break;
					}
					s.pop();
					cnt++;
					}
				}
				// 제거가 완료 되었으면 숫자를 넣는다.
				s.push(push_num);
			}
		
		// 2 - 3. 만일 제거 개수가 k개가 안된다면, 스택을 k - cnt만큼 pop한다.
		for(int i = 0; i < k - cnt; i++) {
			s.pop();
		}
		
		// 결과 도출.
		for(Integer num : s) {
			answer += num;
		}
		return answer;
	}
}
