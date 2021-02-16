package sort;

import java.util.Arrays;
import java.util.Collections;

public class Hindex {
	//citations.length = n, h번 이상 인용된 논문이 h이상, 나머지가 h이하일때 최대값이 H-index
	// h번 이상 인용된 논문이 h이상이면 무조건 나머지가 h이하인데 왜 뒷말이 붙었지?
	//크기 비교를 위해 Arrays.sort해서 정렬함.
	//저 h라는 값이 여러가지가 나올 수가 없지않나? => 없을 것 같은데 조합 아무리 생각해도. => 값들이 중복되면 가능하네. 근데 최대가 될려면 중간쯔음에 있어야함. => 남은 개수랑 비교.
	//근데 이거 정렬해두면 그냥 가운데 부근이 답 아닌가? 같은 수 중복일 경우 대비해서 근처만 좀 더 보면 될거같은데. ex {1,2,3,3,3,4,5} => 중간도 3 앞에도 3.
											// ㄴ 중복이 되는 수도 포함이겠지?
	//잡은 h가 남은개수랑 비교해서 h가 최대 수가 되기 위해선 {3,0,1,6,5} 같은 케이스에선 값과 남은 개수가 같을때, {1,2,3,4,5,6} => 3, {1,2,4,5,7,8} => 4
	//남은개수보다 인덱스가 크거나 같아야하네.
	//런타임 에러...? => import.....
	public static int solution(int[] citations) {
		int answer = 0;
        Arrays.sort(citations);
        //h 값이 h값이상 갯수가 h이상이여야함.   H값은 citations[index], h 값 이상인 갯수는? => length 에서 현재위치 빼야함.
        for(int i = 0; i < citations.length; i++) {			//int 배열 한번 돌면서
        	if(citations[i] >= citations.length - i)		//남은 개수, 즉 남은 인용된 수보다 크거나 같을때.
        		//return citations[i];						//citation[i] 반환하면 예제랑, 테케 16번만 맞음. 값을 반환하면 답이 틀리나본데? 남은 개수반환??
        		return citations.length-i;					//흠 왜지... 둘이 같을때가 아닐때가 있나? 아래와 같은 예제일때 답이 하나 차이로 다르게 나오네.
        													//값을 반환하니 6개가 5개이상인데 반환되었음.
        }
		return answer;
    }
	
	public static void main(String[] args) {
		//int[] citations = {1,1,1,1,2,3,4,5,6,6,6,6,6};
		int[] citations = {1,2,4,7,5,6, 9, 10, 11};
		int ans = solution(citations);
		System.out.println(ans);
	}
}