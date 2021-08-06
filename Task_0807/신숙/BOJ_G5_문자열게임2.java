import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_G5_문자열게임2 {
	/*
	 * <문제>
	 * 1. 소문자 문자열 W
	 * 2. 양의정수 K
	 * 3. 어떤 문자를 정확히 k개 포함하는 가장 짧은 연속 문자열의길이를 구함
	 * 4. 어떤 문자를 K개 포함하고 문자열의 첫번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이를 구한다. 
	 * 
	 * <문제풀이>
	 * 
	 */
	static int T, K, min, max;
	static String W;
	static ArrayList<Integer>[] arr = new ArrayList[26];		//list[index] = alphabet - 'a' 의 인덱스 list
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 26; i++)
			arr[i] = new ArrayList<>();
		
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			boolean flag = true;
			for(int i = 0; i < 26; i++)
				arr[i].clear();
			
			W = br.readLine();
			K = Integer.parseInt(br.readLine());
			//값 저장
			for(int i = 0; i < W.length(); i++) {
				int a = W.charAt(i) - 'a';
				arr[a].add(i);
			}
			//배열의 길이가 K보다 길다면 함수를 실행.
			for(int i = 0; i < 26; i++) {
				if(arr[i].size() >= K) {
					flag = false;
					solve(i);
				}
			}
			
			if(flag) {
				System.out.println(-1);
				continue;
			}else {
				System.out.println(min + " " + max);
			}
		}
	}
	
	static void solve(int idx) {
		ArrayList<Integer> list = arr[idx];
		int start = 0;
		int end = K - 1;
		while(end < list.size()) {
			//end - start + 1 이 문자열의 길이.
			min = Math.min(min, list.get(end) - list.get(start) + 1);
			max = Math.max(max, list.get(end) - list.get(start) + 1);
			start++;
			end++;
		}
	}
}
