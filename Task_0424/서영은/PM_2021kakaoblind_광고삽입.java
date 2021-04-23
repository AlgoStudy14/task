class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        
        int playTime = convertStrToTime(play_time), advTime = convertStrToTime(adv_time);
        
        long allTime[] = new long[playTime+1];
        //시작시간 ++, 종료시간 --
        for(String log : logs) {
            String[] str = log.split("-");
            allTime[convertStrToTime(str[0])]++;
            allTime[convertStrToTime(str[1])]--;
        }
        
        for (int i=1; i<=playTime; i++) allTime[i] += allTime[i-1];
        for (int i=1; i<=playTime; i++) allTime[i] += allTime[i-1];
        
        long maxTime = Long.MIN_VALUE;
        int maxStartTime = 0;
        for(int i=0; i+advTime <= playTime; i++) {
            long tmp = allTime[i+advTime] - allTime[i];
            
            if(tmp > maxTime) {
                maxTime = tmp;
                maxStartTime = i+1;
            }
        }
        
        
        return convertTimeToStr(maxStartTime);
    }
    
    // sec로 바꾸기
    public int convertStrToTime(String timeStr) {
        String[] str = timeStr.split(":");
        
        int hour = Integer.parseInt(str[0]);
        int min = Integer.parseInt(str[1]);
        int sec = Integer.parseInt(str[2]);
        
        int time = 0;
        time = hour*60*60 + min*60 + sec;
        
        return time;
    }
    
    // 문자로 바꾸기
    public String convertTimeToStr(int time) {
        StringBuilder sb = new StringBuilder();
        int hour = time / (60*60);
        int min = (time / 60) % 60;
        int sec = time % 60;
        
        if(hour<10) {
            sb.append("0");
            sb.append(hour);
        } else {
            sb.append(hour);
        }
        
        sb.append(":");
        
        if(min<10) {
            sb.append("0");
            sb.append(min);
        } else {
            sb.append(min);
        }
        sb.append(":");
        
        if(sec<10) {
            sb.append("0");
            sb.append(sec);
        } else {
            sb.append(sec);
        }
        
        return sb.toString();
    }
}
