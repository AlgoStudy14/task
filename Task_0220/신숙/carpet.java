package sort;

import java.util.Arrays;

public class carpet {
	/*
	 * 카펫 중앙엔 노란색, 테두리 1줄은 갈색.  => 테두리로 2차원 배열에 0과 N,M은 갈색
	 * 해당 수가 brown, yellow 변수로 주어짐.
	 * 
	 * 1번 방법. brown + yellow = 넓이 (리턴값 두개 곱하는 것.)
	 * 2번 방법. yellow 개수를 제곱근 만큼 반복문으로 나누면서 갈색 개수를 더해서 그게 개수가 맞는지 확인하는 방식.
	 * 			=> 1번이 더 빠를듯? => 굳이 제곱근만큼 할 필요는 없는듯. break써서 for문나오게.
	 */
	public static int[] solution(int brown, int yellow) {
        int quot = 0;
        int[] answer = new int[2];
        int area = brown + yellow;
        //최소는 yellow 1, brown 8. => 3x3이니까 3부터 시작.
        for(int i = 3; i < area; i++) {
        	//안 나누어 떨어지면 패스
        	if(area % i != 0)
        		continue;
        	//나누어 떨어지면 곱이 되는지 확인		=>   계산식은 어떻게?    quot x i = area
        	quot = area / i;
        	/*
        	 *  i는 세로, quot는 가로.
        	 * 몫을 저장해서 앞부터 검사하는거니까, 배열에 넣을땐 answer[0] = quot, answer[1] = i;
        	 * 테두리 빼고 곱하면 yellow, 즉 내부 넓이가 나와야함.
        	 */
        	if( (i - 2) * (quot - 2) == yellow) {
        		answer[0] = quot;
            	answer[1] = i;
            	break;
        	}
        }
        return answer;
    }
	
	public static void main(String[] args) {
		int[] ans = new int[2];
		ans = solution(24, 24);
		System.out.println(Arrays.toString(ans));
	}
}
