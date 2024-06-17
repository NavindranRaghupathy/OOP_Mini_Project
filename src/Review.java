import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Review {
    private String reviewID;
    private float rating;
    private String review_Date;
    private String pid;
    private int totalCust;

    public Review(String pid , String reviewID , float rating , int totalCust , String date)
    {
        this.pid = pid;
        this.reviewID = reviewID;
        this.rating = rating;
        this.review_Date = date;
        this.totalCust = totalCust;
    }

    public Review(String pid , String reviewID , float rating)
    {
        this.pid = pid;
        this.reviewID = reviewID;
        this.rating = rating;
        LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        review_Date = currentDate.format(formatter);
        totalCust = 1;
    }

    public void setID(String reviewID){this.reviewID = reviewID;}
    public void setRate(float rating){this.rating += rating;}
    public void setDate(String review_Date){this.review_Date = review_Date;}
    public void setTotal(){totalCust+=1;}

    public String getID(){return reviewID;}
    public float getRate(){return rating;}
    public String getDate(){return review_Date;}
    public String getPID(){return pid;}
    public int getTotalCust(){return totalCust;}
}
