package com.devturtle.user;

public class UserRankingProgress {
    public static int[] getRankProgress(int userid) {
        UserDAO udao = new UserDAO();
        
        int rating = udao.selectUser(userid).getTotalScore();
        
        int remainingScore = 0;
        int remainingPercent = 0;
        int target = 0;

        if (rating >= 2000) {
            remainingScore = 0;
            remainingPercent = 100;
            return new int[] {remainingScore, remainingPercent};
        } else if (rating >= 1000) {
            target = 2000;
        } else if (rating >= 600) {
            target = 1000;
        } else if (rating >= 300) {
            target = 600;
        } else if (rating >= 100) {
            target = 300;
        } else if (rating >= 50) {
            target = 100;
            remainingScore = 100 - rating;
        } else {
            target = 50;
            
        }
   
        remainingScore = target - rating;
        remainingPercent = (int) (100-((target - rating) / (float) target) * 100);

        return new int[] {remainingScore, remainingPercent};
    }
}
