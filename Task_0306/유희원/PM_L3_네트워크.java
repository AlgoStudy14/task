/*
 * 프로그래머스 코딩테스트 연습 고득점 kit - BFSDFS - 네트워크
 * computers = {{},{},{}}; 걍 이 2차원 배열이 컴퓨터 연결되어있는거 나타냄
 * 첫번째 예시의 경우 
 * 1 1 0
 * 1 1 0
 * 0 0 1 이런식이고 index기준 컴 0,1이 연결되어있는거 ㅇㅇ
 * 연결되어있는 애들끼리는 같은 네트워크에 있다고 표현, 총 네트워크 갯수 구하는 문제
 */

class Solution {
    boolean visit[];
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visit = new boolean[n];
		//방문하지 않은 경우에만 dfs호출! 방문안했네? -> 다른 네트워크니까 answer++
		for (int i = 0; i < n; i++) {
			if(!visit[i]) {
				answer++;
				DFS(i,computers);
			}
		}
        return answer;
    }
    private void DFS(int i,int[][] computers) {
		//처음에 바이러스 문제랑 헷갈려서 0에서 시작했다가 똘끼
		visit[i] = true;
		for (int j = 0; j < computers.length; j++) {
			if(computers[i][j]==1&&!visit[j]) {
        //computers 안넘겨줘서 똘끼;;;;;
				DFS(j,computers);
			}
		}
	}
    
}
