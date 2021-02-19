class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        
        int x = 0; int y = 0;
        double yel = Math.sqrt(yellow);
		for (int i = 1; i <=yel; i++) { //노랭이세로길이
			if(yellow%i==0) {
				x=yellow/i;
				y=i;
			}
			if((x+i+2)*2==brown) break;
		}
		//System.out.println(x+2+" "+(y+2));
        answer = new int[]{x+2,y+2};
        
        return answer;
    }
}
