import java.util.Arrays;

/**
 * <문제 요약>
 * 구해야 하는 것: 고속도로를 이동하는 모든 차량이 한 번은 단속용 카메라를 만나도록 최소한의 카메라 설치 개수 
 * 제약 사항: 최소한의 카메라 
 * 문제 유형: 그리디 
 * 요구 개념: 그리디  
 * 
 * <풀이법 요약>
 * 1. 나가는 순서대로 오름차순 정렬 
 * 2. 단속카메라보다 진입 지점이 오른쪽에 있으면 나가는 지점에 카메라 설치 
 * 아무리 생각해도 모르겠어서 답 봤다. 그리디 어렵다ㅠㅠ 
 *
 */
public class PM_L3_단속카메라 {

	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{-20,15},{-14,-5},{-18,-13},{-5,-3}}));

	}
	
	public static int solution(int[][] routes) {
		
		Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
		
        int answer = 0;
		int camera=Integer.MIN_VALUE;
		for(int[] route : routes) {
			if(camera<route[0]) {
				camera=route[1];
				answer++;
			}
			
		}
        return answer;
    }

}
