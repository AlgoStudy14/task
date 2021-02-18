package Greedy;

public class makeBignum {
	/*
	 *  number 문자열에서 k개의 수를 제거 했을때 얻을 수 있는 가장 큰 수.
	 *  ex ) 1924, k=2 => 94
	 *  for문 두번돌리거나 조합 돌리는건 number가 1백만 자리라서 안 될듯.
	 *  
	 *  1. 가장 큰 수는 앞자리가 가장 커야함.
	 *  	=> length - k - 1번째 charAt까지 중에서 가장 큰수고르기. 
	 *  	=> 41772 중 가장 큰 수는 7. 가장큰수 앞은 버림. 몇 번째 항이 가장 큰 수인지 확인. ex)4177252841 에선 2번째항 => 2개버려짐.
	 *  2. 그럼 k = 4이므로 2개를 더 버려야함. k <= 2 대입
	 *  3. 이 행동을 k가 0일때까지 반복?
	 *  	+ 그후에 7252에서 가장 큰 수는 7  버려지는 거 없으니까 다시 252에서 가장 큰 수는 5, k=1
	 *  	+ 현재 문자열 775, 남은 자리수 3채워야하는데 2841 k = 1. 2,8 중에 더 큰건 8이므로 2 버리고 7758.
	 *  	+ k = 0이므로 k+1인 1자리수만 확인하니 그뒤로 계속 들어감.
	 *  근데 좀 헷갈리네.
	 *   
	 *  다시. 100백만자리 수에 k=1이면 비교할 수는 앞 2개만 비교하면됨.  => k+1까지 수중에서 가장 큰것을 찾는 것.
	 *  테스트 10 〉	통과 (8377.54ms, 61MB) =>	????
	 */
	public static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int maxNum = Integer.MIN_VALUE;
        int count = number.length() - k;
        int pointIndex = 0;		//몇번째 인덱스를 sb에 넣을건지 확인하기 위한 것.
        int startPoint = 0;
        //length - k 자리의 수를 넣어야 하므로 length - k반복
        for(int i = 0; i < count; i++) {
        	maxNum = Integer.MIN_VALUE;
        	for(int j = startPoint; j < startPoint + k + 1; j++) {
        		if(maxNum < number.charAt(j) - '0') {
        			maxNum = number.charAt(j) - '0';
        			pointIndex = j;
        		}
        	}
        	if(k > 0)
        		k -= pointIndex - startPoint;
        	sb.append(number.charAt(pointIndex) - '0');
        	startPoint = pointIndex + 1;
        }
        return sb.toString();
    }
	
	public static void main(String[] args) {
		String sq = solution("1924", 2);
		System.out.println(sq);
	}
}
