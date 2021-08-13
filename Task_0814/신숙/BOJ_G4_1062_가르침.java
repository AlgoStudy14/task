import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G4_1062_가르침 {
	/*
	 * <문제>
	 * 문제 설명이 좀.... K개의 글자를 가르치고 난 후에 K개 글자로만 이루어진 단어만 읽을 수 있음.
	 * 남극의 모든 단어는 anta로 시작, tica로 끝남. 단어는 N개.
	 * 읽을 수 있는 최댓값
	 * 
	 * <문제풀이>
	 * a n t i c 5개를 배워야함.
	 * 이를 제외하고 한가지 알파벳을 배웠을때 문자열을 읽을수 있는 최대 경우의 수.
	 * 알파벳 크기의 배열을 두고, 해당 배열을 배웠는지 확인.
	 */
	static int N, K, ans;
	static boolean[] alphabet;
	static String[] s;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		alphabet = new boolean[26];
		s = new String[N];
		
		if(K < 5) {
			System.out.println(0);
			return;
		}else if(K >= 26) {
			System.out.println(N);
			return;
		}else {
			//앞 뒤 4짜른 걸 배열에 넣음.
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				s[i] = str.substring(4, str.length() - 4);
			}
			K -= 5;
			alphabet['a' - 'a'] = true;
			alphabet['n' - 'a'] = true;
			alphabet['t' - 'a'] = true;
			alphabet['i' - 'a'] = true;
			alphabet['c' - 'a'] = true;
			dfs(0, 0);
			System.out.println(ans);
		}
	}
	static void dfs(int start, int cnt) {
		if(cnt == K) {
			//다 골랐으면 최대값 비교
			int temp = 0;
			for(int i = 0; i < N; i++) {
				boolean flag = true;
				for(int j = 0; j < s[i].length(); j++) {
					if(!alphabet[s[i].charAt(j) - 97]) {
						flag = false;
						break;
					}
				}
				if(flag)
					temp++;
			}
			ans = Math.max(ans, temp);
			return;
		}
		
		for(int i = start; i < 26; i++) {
			if(!alphabet[i]) {
				alphabet[i] = true;
				dfs(i, cnt + 1);
				alphabet[i] = false;
			}
		}
	}
}
