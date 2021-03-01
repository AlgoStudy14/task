/*
 * 프로그래머스 코딩테스트 연습 고득점 kit - BFSDFS - 타켓넘버
 * numbers 배열에 있는 음이 아닌 정수들 덧셈 뺄셈 조합해서
 * target 만드는 경우의 수
 */

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = DFS(numbers,target,0,0);
        return answer;
    }
    //배열(numbers), 목표 합(target), 인덱스(==cnt), 합
    private static int DFS(int[] numbers, int target, int index, int sum) {
		//모든 자리 다 돌았으면
		if(index == numbers.length) {
			//target이랑 같냐? 그럼 1, 아님 0
			return sum == target? 1:0;
		}
		else {
			//덧셈하는거 뺄셈하는 거 각각 재귀 돌려
			return DFS(numbers,target,index+1,sum+numbers[index])+DFS(numbers,target,index+1,sum-numbers[index]);
		}
	}
}
