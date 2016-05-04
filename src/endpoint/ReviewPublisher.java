package endpoint;

import javax.xml.ws.Endpoint;
import ws.BroadwayReviewImpl;
import ws.NYTimesReviewImpl;
import ws.PlaybillReviewImpl;

public class ReviewPublisher {
	public static void main(String[] args)
	{
		Endpoint.publish("http://localhost:8888/ws/broadway", new BroadwayReviewImpl());
		Endpoint.publish("http://localhost:8887/ws/nytimes", new NYTimesReviewImpl());
		Endpoint.publish("http://localhost:8889/ws/playbill", new PlaybillReviewImpl());
	}
}
