package com.devturtle.user;

public class UserRankText {
	public static String getRank(int userid) {
		UserDAO udao = new UserDAO();
		
		int rating = udao.selectUser(userid).getTotalScore();
		
		
		if(rating >= 2000) {
			return "Pirate King Turtle";
		} else if(rating >= 1000) {
			return "Pirate Turtle";
		} else if(rating >= 600) {
			return "Middle School Turtle";
		} else if(rating >= 300) {
			return "Element School Turtle";
		} else if(rating >= 100) {
			return "Kinder Turtle";
		} else if(rating >= 50) {
			return "Baby Turtle";
		} else {
			return "Turtle EGG"; 
		}
    }
}
