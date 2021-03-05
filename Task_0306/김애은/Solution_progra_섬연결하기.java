package algo0305;

import java.util.Arrays;

public class Solution_progra_섬연결하기 {
	//비용이 낮은 순으로 정렬
	//제일 비용이 낮은 다리는 무조건 사용하고 건설한다.
	//다리별 방문 처리 해줌
	//while을 돌려서 지을 수 있는 다리 조건은 i번째 다리 지은적 없고 두 섬중 하나는 연결
	//하나 지으면 for break
	//다리 개수가 다 찰때까지 for문은 계속 반복한다.
	public static void main(String[] args) {
		int n=4;
		int costs[][]= {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
		boolean island[] = new boolean[n];//연결한 다리
		boolean bridge[] = new boolean[n];//해당 사용한 다리
		int tot=0;
		int answer=0;
		Arrays.sort(costs,(x1,x2)->{//낮은순대로 정렬
			return x1[2]-x2[2];
		});
		island[costs[0][0]]=true;//처음다리 추가
		island[costs[0][1]]=true;//두번째 다리 추가
		bridge[0]=true;//0번째 다리 지어짐
		answer+=costs[0][2];//cost
		tot+=1;//토탈 다리 개수
		
		while(tot<n-1) {
			for(int i=1;i<n;i++) {
				int start=costs[i][0];
				int end = costs[i][1];
				int cost = costs[i][2];
				if(!bridge[i] && (island[start]||island[end]) && !(island[start]&&island[end])) {
					island[start]=true;
					island[end]=true;
					bridge[i]=true;
					answer+=cost;
					tot++;
					break;
				}
			}
		}
		System.out.println(answer);
	}

}
