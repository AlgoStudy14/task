import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 주어진 숫자 조합으로 만들 수 있는 소수 개수 구하기
 * 문제 유형 : 구현, 효율적인 방법을 찾는게 핵심인듯!
 * 요구 개념 : 완전탐색
 * <풀이법 요약> 
 * 1. HashSet을 사용해서 중복 방지
 * 2. 입력받은 물자열 num 배열에 넣어주기
 * 3. next permutation을 사용해서 배열 뽑아주기 
 * 4. 3에서 얻은 배열의 value를 하나하나 String에 추가하면서 소수 체크 (소수체크는 에라토스테네스의 체 인용)
 * 5. 소수이면 HashSet에 넣어줌 -> 중복이 많이 발생.. 좋은 코드는 아닌듯 ㅜ
 * 6. 마지막에 HashSet의 크기를 return
 */

public class PM_완전탐색_소수_찾기 {

	static int[] num;
	static int N;

	public static void main(String[] args) {
		PM_완전탐색_소수_찾기 pm = new PM_완전탐색_소수_찾기();
		String numbers = "17";
		System.out.println(pm.solution(numbers));
	}

	public int solution(String numbers) {
		Set<Integer> set = new HashSet<Integer>(); // 중복없이 값을 넣기 위해 HashSet사용
		N = numbers.length();
		num = new int[N];
		for (int i = 0; i < N; i++) {
			num[i] = numbers.charAt(i) - '0'; // 하나씩 잘라서 배열에 넣어주기
		}

		Arrays.sort(num);
		do { // next permutation 사용
			String prime = "";
			for (int i = 0; i < N; i++) { // next permutation로 얻은 배열을 사용
				prime += num[i]; // 배열 원소를 하나씩 늘려주면서 체크해주기 -> 비효율적이라고 생각하는데 더 좋은 방법이 떠오르지 않음..ㅜㅜ
				if (chk(Integer.parseInt(prime))) { // 소수이면 넣어주기
					set.add(Integer.parseInt(prime));
				}
			}
		} while (np(N - 1));

		return set.size(); // size를 return;
	}

	// next permutation
	private static boolean np(int size) {
		int i = size;
		while (i > 0 && num[i - 1] >= num[i]) {
			i--;
		}

		if (i == 0)
			return false;

		int j = size;
		while (num[i - 1] >= num[j]) {
			j--;
		}

		int tmp = num[i - 1];
		num[i - 1] = num[j];
		num[j] = tmp;

		int k = size;
		while (i < k) {
			tmp = num[i];
			num[i] = num[k];
			num[k] = tmp;
			i++;
			k--;
		}

		return true;
	}

	// 에라토스테네스의 체 
	private static boolean chk(int n) {
		if (n < 2)
			return false;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

}
