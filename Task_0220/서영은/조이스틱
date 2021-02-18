package algoStudy;

import java.util.ArrayList;
/**
 * <문제 요약>
 * 구해야 하는 것: 조이스틱 조작 횟수의 최솟값 
 * 제약 사항: name은 알파벳 대문자로만 이루어져 있고, 길이는 1이상 20이하 
 * 문제 유형: 그리디 
 * 요구 개념: 그리디 
 * 
 * <풀이법 요약>
 * 1. A로 이루어진 String을 만들어 한글자씩 비교하여 arraylist에 바꿔야 할 문자열과 다른 인덱스 넣기 
 * 2. 현재 위치랑 바꿔야할 문자열이 있는 인덱스가 같으면 알파벳 변경 
 * 3. 가장 가까운 이동거리 찾기 -> 이동 
 *
 */
public class programmers_조이스틱 {

	public static void main(String[] args) {

		System.out.println(solution("JAZ"));

	}

	static ArrayList<Integer> index = new ArrayList<Integer>();
	static int nameSize, curIdx;

	public static int solution(String name) {
		nameSize = name.length();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < nameSize; i++) {
			sb.append("A");
		}

		String compareName = sb.toString();
		int answer = 0;

		curIdx = 0;


		while (true) {
			index.clear();

			for (int i = 0; i < nameSize; i++) {
				if (name.charAt(i) != compareName.charAt(i)) {
					index.add(i);

					if (curIdx == i) { // 알파벳 변경 
						answer += checkAlphabet(name.charAt(i), compareName.charAt(i));
						String tempName = compareName.substring(0,i)+name.charAt(i)+compareName.substring(i+1,nameSize);
						compareName = tempName;
						index.remove((Integer) curIdx);
					}
				}
			}

			if (!index.isEmpty()) {
				answer += checkMove(curIdx);
			} else {
				break;
			}

		}

	return answer;

	}

	// 이동 최소 횟수 체크 
	private static int checkMove(int cur) {
		int front,back;
		
		if(cur < index.get(0)) {
			front=Math.min(index.get(0)-cur, nameSize-index.get(0)+cur);
		} else {
			front=cur-index.get(0);
		}
		
		int lastIdx = index.get(index.size()-1);
		
		if(cur < lastIdx) {
			back=Math.min(lastIdx-cur, nameSize-lastIdx+cur);
		} else {
			back=cur-lastIdx;
		}
		
		if(front <= back) {
			curIdx = index.get(0);
			return front;
		} else {
			curIdx = lastIdx;
			return back;
		}		
	}

	// 알파벳 최소 횟수 체크 
	private static int checkAlphabet(char nameAl, char compareNameAl) {
		return Math.min(nameAl - compareNameAl,'Z' - nameAl + 1);
	}

}
