package ws;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.io.*;
import java.nio.*;
import java.util.Date;

public class Review {
	public static String reviewSource;
	public String playTitle;
	public String reviewTitle;
	public String playGenre;
	public Date reviewDate;
	public String fullReview;
	public static int popularity;
	
	public Review()
	{
	}
	
	public void increasePopularity(Review r)
	{
		popularity++;
	}
}
