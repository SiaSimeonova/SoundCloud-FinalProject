package POJO;

public class Comment {
private static int commentCounter=0;
		private int ID;
		private String commentText;
		private int postID;
		private String userID;
		private String date;
		
		public Comment(String commentText, int postID, String userID, String date) {
			ID = ++commentCounter;
			this.commentText = commentText;
			this.postID = postID;
			this.userID = userID;
			this.date = date;
		}

		public int getID() {
			return ID;
		}

		public String getCommentText() {
			return commentText;
		}

		public int getPostID() {
			return postID;
		}

		public String getUserID() {
			return userID;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}
		
}
