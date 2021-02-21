package SW_Expert;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution_D3_1234 {
	/*
	 * 비밀번호 배열을 입력받음.
	 * 비밀번호 중 한 자리가 다음 자리의 수와 같을 경우 소거.
	 * 이 소거를 하면 그 이전과 다음 것을 비교하여 계속 소거해나감.
	 * 남은 수가 비밀번호.
	 * 
	 * 1. ArrayList 를 이용하여 문자열을 넣고 left와 right가 같으면 remove후, left-1 과 right-1을 비교 만약 지워졌다면 또 left-1과 right-1을 비교 반복. 재귀로
	 * 2. 추가 : i 값이 이미 커진 후이기 때문에, 이제 다음 인덱스를 볼 때, 아직 안 본것들이 이미 넘어가 있어서 left와 right를 한번 더 재귀로 해줘야함.
	 */
	static int N, count;		// count = 배열의 크기를 줄이므로 그걸 판단할 것.
	static String ps;
	static char[] password;
	static ArrayList<Character> arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = 0;
		for(int t = 1; t <= 10; t++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#" + t + " ");
			arr = new ArrayList<>();
			N = sc.nextInt();
			count = N;
			ps = sc.next();
			password = new char[N];
			password = ps.toCharArray();
			for(int i = 0; i < N; i++) 
				arr.add(password[i]);
			
			for(int i = 0; i < N; i++) {
				if(i > count)
					break;
				cp(i, i+1);
			}
			size = arr.size();
			for(int i = 0; i < size; i++)
				sb.append(arr.get(i));
			System.out.println(sb.toString());
		}
	}
	
	static void cp(int left, int right) {
		if(left < 0 || right >= count)
			return;
		if(arr.get(left) == arr.get(right)) {
			arr.remove(left);
			arr.remove(left);
			count -= 2;
			if(left -1 >= 0 && right - 1 < count)
				cp(left-1, right-1);
			if(left >= 0 || right < count)
				cp(left, right);
		}
	}
}
