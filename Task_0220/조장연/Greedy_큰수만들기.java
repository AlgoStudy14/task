
/*
 * <문제 요약>
 * 어떤 숫자와 제거할 숫자객수가 주어졌을때 제거 후 가장 큰 수 구하기
 * 
 * <풀이법 요약>
 * 빼야되는 숫자의 크기만큼 잘라서 앞에서 부터 생각
 * 이때 해당 범위에서 가장 큰 수를 찾음
 * 주어진 수를 앞에서부터 찾은 수와 비교해서 다르면 버림, 같으면 answer에 추가
 * 만약 주어진 수의 끝까지 가기전에 다 찾아서 버리면 나머지는 answer에 그냥 붙임
 * 
 * 시간은 별로 안중요한 문제인 것 같아서 그냥 +로 다 붙였는데 교수님이 보시면 등짝때릴듯. 
 */

public class Greedy_큰수만들기 {
	public static void main(String[] args) {
		Greedy_큰수만들기 doit = new Greedy_큰수만들기();
		String number = "1924";
		int k = 2;
		System.out.println(doit.solution(number, k));
	}

	public String solution(String number, int k) {
		String answer = "";
		int pointer = 0; // 범위의 처음을 나타내는 포인터

		while (k > 0 && pointer < number.length() - k) {
			char alphabet = func1(number, pointer, pointer + k);
			int num = 0;
			while (alphabet != number.charAt(pointer + num)) { // 찾은 수랑 비교해서 같으면 answer추가 다르면 버림!
				k--;
				num++;
			}
			answer += number.charAt(pointer + num);
			pointer += num + 1;
		}

		if (k == 0) { // 제거할 숫자를 다 채우면 나머지 가져다 붙임
			for (int i = pointer; i < number.length(); i++) {
				answer += number.charAt(i);
			}
		}

		return answer;
	}

	public char func1(String number, int start, int end) { // 범위내에서 가장 큰 수 찾기
		char answer = '0';
		for (int i = start; i <= end; i++) {
			if ((int) number.charAt(i) > (int) answer)
				answer = number.charAt(i);
		}

		return answer;
	}
}
