import java.util.*;

/*
 * <문제 요약>
 * 주어진 하나의 수만 이용하여 원하는 숫자 만들기(괄호와 사칙연산 사용)
 * 
 * <풀이법 요약>
 * 숫자의 갯수에 따라 만들 수 있는 모든 경우의 수를 ArrayList배열로 저장하며 탐색
 * 
 * 솔직히 DP는 감이 안온다.
 * 어찌어찌 푸는데 이게 왜 DP인지 모르겠음(완탐이랑 별차이 없는거같음)
 */

class Solution {
    public int solution(int N, int number) {
        int answer = 0;
        ArrayList<Integer>[] numberList = new ArrayList[8];
        
        for(int i = 0; i < 8; i++)
        {
            numberList[i] = new ArrayList<>();
            String firstNum = "";
            for(int j = 0; j <= i; j++)
            {
                firstNum += Integer.toString(N);
            }
            
            if(number == Integer.parseInt(firstNum)) //N으로만 이루어진 수
                return i+1;
            else
            {
                numberList[i].add(Integer.parseInt(firstNum));
            }
        }
        
        for(int i = 1; i < 8; i++) // 탐색하는 숫자갯수
        {
            for(int j = 0, k = i-1; j <= k; j++, k--) // 만들수 있는 경우의 수 (예를 들어 5개짜리 탐색할때는 (1,4), (2,3) 이런식으로)
            {
                for(int num1 = 0; num1 < numberList[j].size(); num1++) // 첫번째수
                {
                    for(int num2 = 0; num2 < numberList[k].size(); num2++) // 두번째수
                    {
                        int sum = numberList[j].get(num1) + numberList[k].get(num2);
                        int subs = (Math.max(numberList[j].get(num1), numberList[k].get(num2))
                            - Math.min(numberList[j].get(num1), numberList[k].get(num2)));
                        int multi = numberList[j].get(num1) * numberList[k].get(num2);
                        int divi = 0;
                        if(numberList[j].get(num1) != 0 && numberList[k].get(num2) != 0)
                            divi = (Math.max(numberList[j].get(num1), numberList[k].get(num2))
                            / Math.min(numberList[j].get(num1), numberList[k].get(num2)));
                        
                        if(number == sum || number == subs || number == multi || number == divi)
                            return i+1;
                        
                        if(!numberList[i].contains(sum))
                            numberList[i].add(sum);
                        if(!numberList[i].contains(subs))
                            numberList[i].add(subs);
                        if(!numberList[i].contains(multi))
                            numberList[i].add(multi);
                        if(!numberList[i].contains(divi))
                            numberList[i].add(divi);
                    }
                }
            }
        }
        return -1;
    }
}