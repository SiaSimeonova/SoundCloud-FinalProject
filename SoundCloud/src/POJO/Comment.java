package POJO;

public class Comment {
private static int commentCounter=0;
		private int ID;
		private String commentText;
		private int postID;
		private String userID;
		
		public Comment(String commentText, int postID, String userID) {
			super();
			ID = ++commentCounter;
			this.commentText = commentText;
			this.postID = postID;
			this.userID = userID;
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
		
}
