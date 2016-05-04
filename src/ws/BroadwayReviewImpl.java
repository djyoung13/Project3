package ws;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.io.*;
import java.nio.*;
import java.util.Scanner;
import ws.Review;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebService(endpointInterface = "ws.BroadwayReview")
public class BroadwayReviewImpl implements BroadwayReview{
	//Does the brunt of the work parsing the text files into strings.
	//parseInfo() is different for each source, but this can be changed.
	public Review[] parseInfo() throws FileNotFoundException, ParseException{
		File text = new File("Broadway.com.txt");
		Scanner s = new Scanner(text);
		double numReviews = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date dateTemp;
		while (s.hasNextLine())
		{
			numReviews += 0.5;
		}
		if (numReviews % 2 == 1)
		{
			numReviews = numReviews + 0.5;
		}
		s.reset();
		Review[] allReviews = new Review[(int) numReviews];
		String delims = "[|]";
		String[] temp;
		int reviewCounter = 0;
		while (s.hasNextLine())
		{
			temp = s.nextLine().split(delims);
			if (temp.length > 2)
			{
				allReviews[reviewCounter].reviewSource = "Broadway.com";
				allReviews[reviewCounter].playTitle = temp[0];
				allReviews[reviewCounter].reviewTitle = temp[1];
				dateTemp = formatter.parse(temp[2]);
				allReviews[reviewCounter].reviewDate = dateTemp;
				allReviews[reviewCounter].playGenre = temp[3];
				allReviews[reviewCounter].fullReview = temp[4];
			}
			else
			{
				continue;
			}
		}
		s.close();
		return allReviews;
	}
	
	//Simply prints all the elements of the review listed in the
	//review class. Primarily for testing.
	@Override
	public void printReview(Review r)
	{
		System.out.println(r.reviewSource);
		System.out.println(r.playTitle);
		System.out.println(r.reviewTitle);
		System.out.println(r.reviewDate);
		System.out.println(r.playGenre);
		System.out.println(r.fullReview);
	}
	
	//Checks the review array entered for instances of the
	//review r, and increases the popularity of all reviews
	//about r accordingly.
	//Use after parsing all reviews and only once per
	//playTitle.
	public void popCheck(Review[] rlist, Review r)
	{
		int popCount = 0;
		for (int i = 0; i < rlist.length; i++)
		{
			if(rlist[i].playTitle.equals(r))
			{
				popCount++;
			}
		}
		for (int j = 0; j < rlist.length; j++)
		{
			if(rlist[j].playTitle.equals(r))
			{
				rlist[j].popularity += popCount;
			}
		}
	}
	
	//Takes a review array and returns the same array but
	//listed in order of date. This is done in order from
	//oldest to most recent.
	//This was done using Bubble Sort which, while not the
	//fastest, gets the reviews sorted without the need for a pivot.
	public Review[] reviewsByDate(Review[] rlist)
	{
		int n = rlist.length;
		int k;
		for (int m = n; m >= 0; m--)
		{
			for (int i = 0; i < n - 1; i++)
			{
				k = i + 1;
				if (rlist[i].reviewDate.after(rlist[k].reviewDate))
				{
					Review holder;
					holder = rlist[i];
					rlist[i] = rlist[k];
					rlist[k] = holder;
				}
			}
		}
		return rlist;
	}
	
	//Reads through an array of Reviews and returns an array of ONLY
	//plays in the given genre
	public Review[] reviewsByGenre(Review[] rlist, String genre)
	{
		Review[] results = new Review[rlist.length];
		int tracker = 0;
		for (int i = 0; i < rlist.length; i++)
		{
			if(rlist[i].playGenre.equalsIgnoreCase(genre))
			{
				results[tracker] = rlist[i];
				tracker++;
			}
		}
		return results;
	}
	
	//Returns an array of all reviews about a play by its name.
	public Review[] reviewsByName(Review[] rlist, String name)
	{
		Review[] results = new Review[rlist.length];
		int tracker = 0;
		for (int i = 0; i < rlist.length; i++)
		{
			if(rlist[i].playTitle.equalsIgnoreCase(name))
			{
				
				results[tracker] = rlist[i];
				tracker++;
			}
		}
		return results;
	}
	
	//Main method for testing above methods.
	public void main() throws IOException, ParseException
	{
		Review[] myReviews = parseInfo();
		for (int i = 0; i < myReviews.length; i++)
			printReview(myReviews[i]);
	}

}
