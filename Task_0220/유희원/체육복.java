class Solution {
    static int ans, losts, cnt;
    public int solution(int n, int[] lost, int[] reserve) {
		
		for (int i = 0; i <lost.length; i++) {
			for (int j = 0; j < reserve.length; j++) {
      //잃어버린애가 도난당하면
				if(lost[i]==reserve[j]) {
					losts++;
          //얘가 못빌려주게 -1로 만들어놓음
					lost[i]=-1;
					reserve[j] =-1;
					break;
				}
			}
		}
		
		for (int i = 0; i <lost.length; i++) {
			for (int j = 0; j < reserve.length; j++) {
      //잃어버린애 앞뒤에 빌려줄애 있으면
				if(lost[i]==reserve[j]-1||lost[i]==reserve[j]+1) {
       // 그 빌려준애는 다시 못빌려주게 -1 해놓고 cnt++
					cnt++;
					reserve[j]=-1;
					break;
				}
			}
		}
    //전체학생수 - 도난당한 학생 수 + 도난당한 학생 수 중 여벌옷을 가지고 있던 학생수 + 다른학생으로부터 체육복을 받은 학생 수
		ans = n-lost.length+losts+cnt;
		return ans;
    }
}
