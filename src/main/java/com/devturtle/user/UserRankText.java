package com.devturtle.user;

public class UserRankText {
	public static String getRank(int userid) {
		UserDAO udao = new UserDAO();
		
		int rating = udao.selectUser(userid).getTotalScore();
		
        if (rating < 20) {
            return "Turtle EGG";
        } else if (rating > 50) {
            return "Baby Turtle";
        } else if (rating > 100) {
            return "Kinder Turtle";
        } else if (rating > 200) {
            return "Element School Turtle";
        } else if (rating > 350) {
            return "Middle School Turtle";
        } else if (rating > 600) {
            return "Pirate Turtle";
        } else {
            return "Pirate King Turtle";
        }
    }
}
