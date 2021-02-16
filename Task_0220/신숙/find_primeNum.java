package sort;

import java.util.ArrayList;

public class find_primeNum {
	/*
	 * numbers라는 문자열 분해해서 합치는데, 이게 소수가 되는 경우
	 * 모든 자리수 순열을 check하면 될듯
	 * 배운 순열찾기를 쓰는데, 이걸 R값을 계속 바꿔주어야 할듯?
	 * 
	 * 소수 찾는 법 : 
	 * 1. 짝수가 아니여야함. 2. 홀수인데 그 수보다 작은 수로 나눴을 때 나머지가 0이면 안됨.
	 *  
	 * 1. 순열로 수 조합을 뽑아서 
	 * 2.그 수가 소수인지 체크하고 소수라면 answer++   => 이걸 하려면 위에 static으로줘야겠네.
	 * 
	 * 3번째 문제 : 011, 0 1 1 각각을 다른 것으로 처리해서 11이 4가지 경우, 101이 2가지경우가 나와버림.
	 * 중복제거는 어떻게? => checkPrime할때 배열에 있으면 안하고 없으면 하는 식
	 */
	static int NL, R, answer;
	static boolean[] isCheck;
	static int[] prime, num;
	static ArrayList<Integer> check = new ArrayList<>();
	public static int solution(String numbers) {
        NL = numbers.length();
        num = new int[NL];
        isCheck = new boolean[NL];
        //문자열로 주어진 수를 배열로 넣었음.
        for(int i = 0; i < NL; i++) 
        	num[i] = numbers.charAt(i) - '0';
        
        for(int i = 1; i <= NL; i++) {
        	R = i;
        	prime = new int[R];
        	perm(0);
        }
        
        return answer;
    }
	
	static void perm(int cnt) {
		//다 채워졌을 경우
		if(cnt == R) {
			String primeNum = "";
			for(int i = 0; i < R; i++)			//뽑은 배열을 합쳐서 문자열로 만들어야함.
				primeNum += prime[i];			//수를 만들었고 이제 이걸 소수 체크를 해주어야 함.
			checkPrime(primeNum);
			return;
		}
		//안 채워졌을 경우
		for(int i = 0; i < NL; i++) {
			//이미 쓴 수일때.
			if(isCheck[i])
				continue;
			//안 쓴 경우
			isCheck[i] = true;
			prime[cnt] = num[i];								//2번째 에러 Arrayindex. 0 => prime초기화 위치 문제.
			perm(cnt+1);
			isCheck[i] = false;
		}
	}
	
	static void checkPrime(String pr) {
		int isPrime = Integer.parseInt(pr);						//왜 여기서 에러가 나?? => nP0은 없어서 1부터.
		//중복 체크 먼저
		if(check.contains(isPrime))
			return;
		check.add(isPrime);
		
		if(isPrime == 1)
			return;
		if(isPrime == 2 || isPrime == 3) {		//2, 3은 소수 		??? 3도 소수가 아니라고 나오길래 이걸 추가했는데 갑자기 3,5,6,9만 되네?
			answer++;
			return;
		}
		//짝수 중 2를 제외하고 소수는 없으므로 짝수 체크 안함. 
		if(isPrime % 2 == 0)
			return;
		//3부터 홀수만 체크. 홀수 중에서 그보다 작은 수로 나누었을 때 나머지가 0인 경우가 있으면 소수. 
		for(int i = 3; i < isPrime; i += 2) {
			//나머지가 0이면 나누어떨어지므로 소수가 아님.
			if(isPrime % i == 0) 
				return;
			//여기에서 바로 추가 해버리면 for문 돌때마다 이걸 해버리는구나.
		}
		answer++;
	}
	
	public static void main(String[] args) {
		String numbers = "135";
		int ans = solution(numbers);
		System.out.println(ans);
	}
}
