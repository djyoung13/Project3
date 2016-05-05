package ws;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

import ws.Review;

@WebService
@SOAPBinding(style = Style.RPC)
public interface BroadwayReview {
	@WebMethod
	public ArrayList<Review> parseInfo() throws IOException, ParseException, FileNotFoundException;
	@WebMethod
	public void printReview(Review r);
	@WebMethod
	public void popCheck(Review[] rlist, Review r);
	@WebMethod
	public Review[] reviewsByDate(Review[] rlist);
	@WebMethod
	public Review[] reviewsByGenre(Review[] rlist, String genre);
	@WebMethod
	public Review[] reviewsByName(Review[] rlist, String name);
}
