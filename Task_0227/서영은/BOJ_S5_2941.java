import java.util.Scanner;
/**
 * <문제 요약>
 * 구해야 하는 것: 입력받은 단어가 몇 개의 크로아티아 알파벳으로 이루어져 있는지 출력 
 * 제약 사항: 붙어 있는 알파벳은 붙어있는 알파벳이 우선, 목록에 없는 알파벳은 한 글자씩 센다. 
 * 문제 유형: 구현?? 
 * 요구 개념: 구현...??? 
 * 
 * <풀이법 요약>
 * 1. substring을 이용하여 단순히 반복문으로 방문하지 않고 먼저 목록에 있는 알파벳이 있으면 카운팅하고 방문 배열에 표시한다. 
 * 2. 단어에서 제외한 후, 나머지 알파벳은 한 글자씩 센다. 
 *
 */
public class BOJ_S5_2941 {

	static String[] alphabet = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
	static boolean[] visited;	//방문을 체크하는 배열 
	
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);

		String word = scann.next();
		
		visited = new boolean[word.length()];
		
		int cnt=0;
		
		for (int i = 0; i < word.length(); i++) {
			for (int j = 0; j < alphabet.length; j++) {
				int len = alphabet[j].length();
				
				if(i+len > word.length()) continue;
				//이미 카운팅 된 알파벳인지 확인 
				boolean pos = true;
				for (int k = i; k < i+len; k++) {
					if(visited[k]) {
						pos=false;
						break;
					}
				}
				
				if(pos && word.substring(i, i+len).equals(alphabet[j])) {
					// 카운팅 + 카운팅 한 문자에 표시 
					for (int k = i; k < i+len; k++) {
						visited[k]=true;
					}
					cnt++;
				}
			}
		}
		
		for (int i = 0; i < visited.length; i++) {
			if(!visited[i]) cnt++;
		}
		System.out.println(cnt);
	}

}
