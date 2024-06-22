import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Review {
    private String reviewID;
    private float rating;
    private String review_Date;
    private String pid;
    private int totalCust;

    // Constructor to initialize Review object with all details
    public Review(String pid , String reviewID , float rating , int totalCust , String date)
    {
        this.pid = pid;
        this.reviewID = reviewID;
        this.rating = rating;
        this.review_Date = date;
        this.totalCust = totalCust;
    }

    // Constructor to initialize Review object with pid, reviewID, and rating
    // Automatically sets the review date to the current date and totalCust to 1
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

    // Setter method for reviewID
    public void setID(String reviewID){this.reviewID = reviewID;}
	
    // Setter method to update the rating
    public void setRate(float rating){this.rating += rating;}
	
    // Setter method for review_Date
    public void setDate(String review_Date){this.review_Date = review_Date;}
	
    // Method to increment the totalCust by 1
    public void setTotal(){totalCust+=1;}

    // Getter method for reviewID
    public String getID(){return reviewID;}

    // Getter method for rating
    public float getRate(){return rating;}

    // Getter method for review_Date
    public String getDate(){return review_Date;}

    // Getter method for pid
    public String getPID(){return pid;}

    // Getter method for totalCust
    public int getTotalCust(){return totalCust;}
}
