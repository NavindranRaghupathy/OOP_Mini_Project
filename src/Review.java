public class Review {
    private String reviewID;
    private String review_text;
    private String review_Date;

    public Review(String reviewID , String review_text , String review_Date)
    {
        this.reviewID = reviewID;
        this.review_text = review_text;
        this.review_Date = review_Date;
    }

    public void setID(String reviewID){this.reviewID = reviewID;}
    public void setText(String review_text){this.review_text = review_text;}
    public void setDate(String review_Date){this.review_Date = review_Date;}

    public String getID(){return reviewID;}
    public String getText(){return review_text;}
    public String getDate(){return review_Date;}
}
