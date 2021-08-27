import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_G4_6443_에너그램 {
	/*
	 * <문제>
	 * 입력받은 알파벳들로 만들 수 있는 모든 단어를 출력하라.
	 * 중복이 될 수 있는데, 중복되는 단어는 제거하라.
	 * 
	 * <문제풀이>
	 * 단어 길이 <= 20, 에너그램 수 <= 100,000
	 * 순열문제.
	 * 중복제거를 어떻게 할 것인가...?		=>	우선순위큐...? 정렬?를 통해서 어떻게 하지
	 * 
	 * <후기>
	 * 다시 풀어봐야할듯 구글링 참조.
	 */
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			StringBuilder sb = new StringBuilder();
			char[] arr = new char[input.length()];
			
			for(int j = 0; j < arr.length; j++)
				arr[j] = input.charAt(j);
			
			Arrays.sort(arr);
			for(int j = 0; j < arr.length; j++)
				sb.append(arr[j]);
			sb.append('\n');
			
			while(np(arr)) {
				for(int j = 0; j < arr.length; j++)
					sb.append(arr[j]);
				sb.append("\n");
			}
			System.out.println(sb.toString());
		}
	}
	
	static boolean np(char[] arr) {
		int i = arr.length - 1;
		//앞의 문자보다 뒤에 문자가 사전순으로 뒤에 오는 경우에만 탐색	=> 중복을 제거하는 매커니즘?
		while(i > 0 && arr[i] <= arr[i - 1])
			i--;
		if(i <= 0)
			return false;
		
		int j = arr.length - 1;
		//선택한 문자보다 사전상 뒤에 오는 문자를 배열 끝에서부터 탐색.
		while(arr[i - 1] >= arr[j])
			j--;
		
		//두 문자 교환
		char temp = arr[j];
		arr[j] = arr[i - 1];
		arr[i - 1] = temp;
		j = arr.length - 1;
		
		//뒤에 문자들 순서 뒤집어 줌.
		while(i < j) {
			temp = arr[j];
			arr[j] = arr[i];
			arr[i] = temp;
			i++; j--;
		}
		
		return true;
	}
}
