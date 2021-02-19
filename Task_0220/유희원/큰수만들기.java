class Solution {
    static int idx,comp;
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        //answer자리수
        for(int i =0; i<number.length()-k;i++){
            comp = 0;
            for(int j = idx; j<=i+k; j++){
                if(comp<number.charAt(j)-'0'){
                    comp = number.charAt(j)-'0';
                //comp = Math.max(comp,number.charAt(j)-'0'); //하면 안됨
                //3번케이스 752841나옴
                    //선택한애 다음부터 ㅇㅇ
                    idx = j+1;
                }
            }
            answer.append(comp);
        }
        return answer.toString();
    }
}
