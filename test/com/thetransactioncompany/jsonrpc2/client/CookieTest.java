package com.thetransactioncompany.jsonrpc2.client;


import java.io.*;

import java.net.*;

import java.util.*;

import com.thetransactioncompany.jsonrpc2.*;

import com.thetransactioncompany.jsonrpc2.util.*;

import junit.framework.TestCase;


/**
 * Tests the cookie management.
 *
 * @author Vladimir Dzhuvinov
 * @version $version$ (2013-01-17)
 */
public class CookieTest extends TestCase {
	
	
	public void testSingleCookie()
		throws Exception {
		
		CookieTestServer server = new CookieTestServer(18080);
		
		URL url = new URL("http://localhost:18080/jsonrpc2/");

		JSONRPC2Session client = new JSONRPC2Session(url);
		
		client.getOptions().acceptCookies(true);
		
		JSONRPC2Request req = new JSONRPC2Request("test.cookie", 0);
		JSONRPC2Response resp = client.send(req);
		System.out.println(resp);
		
		
		System.out.println("Listing received cookies:");

		Set<HttpCookie> cookies = client.getCookies();

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
