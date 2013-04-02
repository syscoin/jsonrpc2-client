package com.thetransactioncompany.jsonrpc2.client;


import java.net.*;

import java.util.*;

import com.thetransactioncompany.jsonrpc2.*;

import com.thetransactioncompany.jsonrpc2.util.*;

import junit.framework.TestCase;


/**
 * Tests the cookie management.
 *
 * @author Vladimir Dzhuvinov
 */
public class CookieTest extends TestCase {
	
	
	public void testCookieSet() {

		HttpCookie c1 = new HttpCookie("sessionid", "1000");
		HttpCookie c2 = new HttpCookie("sessionid", "2000");

		System.out.println("Cookie 1 hash code: " + c1.hashCode());
		System.out.println("Cookie 2 hash code: " + c2.hashCode());

		assertEquals(c1.hashCode(), c2.hashCode());

		Set<HttpCookie> cookieSet = new HashSet<HttpCookie>();

		cookieSet.add(c1);
		assertEquals(1, cookieSet.size());

		cookieSet.add(c2);
		assertEquals(1, cookieSet.size());

		for (HttpCookie c: cookieSet) {

			assertEquals("1000", c.getValue());
		}
	}


	public void testSingleCookieOperation()
		throws Exception {
		
		CookieTestServer server = new CookieTestServer(18080);
		
		URL url = new URL("http://localhost:18080/jsonrpc2/");

		JSONRPC2Session client = new JSONRPC2Session(url);
		
		client.getOptions().acceptCookies(true);
		
		JSONRPC2Request req = new JSONRPC2Request("test.cookie", 0);
		JSONRPC2Response resp = client.send(req);
		System.out.println(resp);
		
		
		System.out.println("Listing received cookies:");

		List<HttpCookie> cookies = client.getCookies();

		assertEquals(1, cookies.size());

		for (HttpCookie cookie: cookies) {

			System.out.println("\t" + cookie);

			assertEquals("sessionid", cookie.getName());
			assertEquals("123", cookie.getValue());
		}
		
		// resend request
		resp = client.send(req);
		
		server.stop();
	}
}
