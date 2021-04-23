package algo0424;

import java.util.ArrayList;
import java.util.Scanner;
/*
 * 수식에 괄호 추가해서 (안해도됨) 얻을 수 있는 최댓값
 * 계산은 우선순위 없이 왼쪽부터 순서대로,
 * 괄호 안에는 연산자 한개만 끼울수있음 **
 */
public class BOJ_16637_괄호추가하기 {

	static ArrayList<Character> operators;
	static ArrayList<Integer> numbers;
	static int N,ans,size;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		size = N/2;
		String s = sc.next();
		operators = new ArrayList<>();
		numbers = new ArrayList<>();
		//연산자와 숫자 리스트 따로 담음
		for (int i = 0; i < N; i++) {
			char c = s.charAt(i);
			if(c=='+'||c=='-'||c=='*') {
				operators.add(c);
			}else numbers.add(c-'0');
		}
		ans = Integer.MIN_VALUE;
		int start = numbers.get(0);
		DFS(start,0);
		System.out.println(ans);
	}
	private static void DFS(int result, int cnt) {
		//연산자 다쓰면
		if(cnt>=size) {
			ans = Math.max(ans, result);
			return;
		}
		//괄호 안넣음
		int nobracket = calc(operators.get(cnt),result,numbers.get(cnt+1));
		DFS(nobracket,cnt+1);
		//괄호 넣는 부분,  result 오른쪽 부분 괄호 넣어보기
		if(cnt+1<size) {
			//result 오른쪽 연산
			int bracket = calc(operators.get(cnt+1),numbers.get(cnt+1),numbers.get(cnt+2));
			//result와 연산결과 연산. 괄호 뒤의 인덱스 cnt+2 넘겨줘야함!
			DFS(calc(operators.get(cnt),result,bracket),cnt+2);
		}
		
	}
	//연산자, a,b 계산
	private static int calc(Character op, int a, Integer b) {
		switch(op) {
		case '+':
			return a+b;
		case '-':
			return a-b;
		case '*':
			return a*b;
		}
		return -1;
	}

}
