package algoStudy;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
/**
 * <문제 요약>
 * 구해야 하는 것: 입력 받은 String 배열의 모든 조합에서 소수의 개수
 * 제약 사항: 중복 제거 
 * 문제 유형: 완전 탐색 -> 순열 으로 모든 경우 체크 
 * 요구 개념: 조합 
 * 
 * <풀이법 요약>
 * 1. numbers 배열의 모든 순열  구하기
 * 2. 구한 순열의 집합을 이용하여 소수 체크하여 카운트해 정답 구하기
 *
 */
public class programmers_소수찾기 {

	// 중복 제거할 수 있는 Set 컬렉
	static Set<Integer> numbersArr = new HashSet<Integer>();
	static boolean[] isSelected; //부분집합 체크 
	static String numbers; // 입력받는 String
	// 입력받은 numbers 스트링의 길이 
	static int numbersLength;
	static int answer=0; // 소수를 카운트할 답
	static int R; // numbersLength 중에서 뽑을 R의 갯수 
	static char[] numbersChar;
	static char[] perm;

	public static void main(String[] args) {
		
		Scanner scann = new Scanner(System.in);
		
		numbers = scann.nextLine();
		numbersLength = numbers.length();
		isSelected = new boolean[numbersLength];
		numbersChar = numbers.toCharArray();
		
		for (int i = 1; i <= numbersLength; i++) {
			R=i;
			perm=new char[R];
			nPr(0);
		}
		
		Iterator<Integer> iterator = numbersArr.iterator();
		
		while(iterator.hasNext()) {
			checkSosu(iterator.next());
		}
		System.out.println(answer);

	}

	private static void nPr(int cnt) {
		if(cnt == R) {
			numbersArr.add(Integer.parseInt(new String(perm)));
			return;
		}
		
		for (int i = 0; i < numbersLength; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i]=true;
			perm[cnt]=numbersChar[i];
			nPr(cnt+1);
			isSelected[i]=false;
		}
	}

	private static void checkSosu(Integer next) {
		if(next == 1 || next == 0) return;
		if(next == 2 || next == 3) {
			answer++;
			return;
		}
		int check = (int) Math.sqrt(next);
		
		for (int i = 2; i <= check; i++) {
			if(next%i==0) {
				return;
			}
		}
		answer++;
	}
}
