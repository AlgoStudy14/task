import java.io.BufferedReader;
import java.io.InputStreamReader;

/***
 * <문제 요약> 
 * 구해야 하는 것 : 조건에 맞게 뒤집은 단어 구하기
 * 문제 핵심 요약 : 조건마다 체크해주면서 StringBuijlder에 append해준다
 * <풀이법 요약> 
 * 1. StringBuilder 2개 필요 : 최종 정답을 저장할 answer, 뒤집을 단어를 저장할 tmp 
 * 2. 입력받은 문자열의 길이만큼 반복
 * 3. 만약 index에 해당하는 문자가 '<'이라면 바뀌지 않기 때문에 while문을 이용해 '>'을 입력받을 때 까지 answer에 append해준다
 * 	+ '<'을 입력받더라도 중간에 tmp가 존재할 수 있기 때문에 존재한다면 뒤집에서 answer에 append한 뒤 tmp를 clear 해준다
 * 4. 만약 index에 해당하는 문자가 ' '이라면 저장된 tmp를 뒤집어서 answer에 append한 뒤 tmp를 clear 해준다.
 * 5. 조건에 포함되지 않는다면 뒤집어야 하는 문자이기 때문에 tmp에 append
 * 6. 마지막으로 tmp에 저장되어 있는 문자를 뒤집어서 answer에 append한다
 */

public class Main_Silver_17413 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int len = line.length();

		StringBuilder answer = new StringBuilder(); // 최종 정답을 저장한 StringBuilder
		StringBuilder tmp = new StringBuilder(); // 뒤집을 단어를 저장할 StringBuiler

		for (int i = 0; i < len; i++) {
			if (line.charAt(i) == '<') { // 만약 '<'을 입력받으면
				if (tmp.length() != 0) { // tmp에 저장된 단어가 있다면
					answer.append(tmp.reverse()); // 뒤집어서 answer에 append하고
					tmp.setLength(0); // clear 해준다
				}
				while (line.charAt(i) != '>') { // '>'을 입력받을 때 까지
					answer.append(line.charAt(i++)); // <>안에 들어있는 단어는 바뀌지 않기 때문에 바로 answer에 append
				}
				answer.append('>'); // '>'append
			} else if (line.charAt(i) == ' ') { // 만약 ' '을 입력받으면
				answer.append(tmp.reverse()).append(' '); // 뒤집어서 answer에 append한 뒤 ' '도 append 해준다
				tmp.setLength(0); // clear 해준다
			} else { // 다 해당되지 않는다면 -> 뒤집을 단어
				tmp.append(line.charAt(i)); // tmp에 append
			}
		}
		answer.append(tmp.reverse()); // 마지막으로 tmp에 저장된 단어를 reverse해서 answer에 append

		System.out.println(answer);
	}

}
