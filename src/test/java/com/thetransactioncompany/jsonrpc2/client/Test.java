package com.thetransactioncompany.jsonrpc2.client;


import java.net.*;

import com.thetransactioncompany.jsonrpc2.*;

import junit.framework.TestCase;


/**
 * Tests the JSONRPC2Session class.
 *
 * @author Vladimir Dzhuvinov
 */
public class Test extends TestCase {

	public static final String URL_HTTP_GOOD = "http://demo.c2id.com/json2ldap/";
	public static final String URL_HTTP_BAD_HOST = "http://bad-host.com/ws/";
	public static final String URL_HTTP_BAD_PORT = "http://localhost:9999/";
	public static final String URL_HTTP_BAD_IP = "http://192.168.0.99";
	public static final String URL_HTTP_BAD_SERVICE = "http://google.com/";
	public static final String URL_HTTPS_SELF_SIGNED = "https://shakespeare.dzhuvinov.com:8443/json2ldap/";

	public static final String RPC_METHOD_GOOD = "ws.getTime";

	
	public Test(String name) {

		super(name);
	}


	public void testRequest() {

		// Assumes a Json2Ldap service

		JSONRPC2Request request = new JSONRPC2Request(RPC_METHOD_GOOD, 0);

		URL url = null;

		try {
			url = new URL(URL_HTTP_GOOD);

		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}

		JSONRPC2Session session = new JSONRPC2Session(url);


		JSONRPC2Response response = null;

		try {
			response = session.send(request);

		} catch (JSONRPC2SessionException e) {
			fail(e.getMessage());
		}

		System.out.println("Response: " + response);
	}


	// Disable temporarily
	public void _testRequestEnableCompression() {

		// Assumes a Json2Ldap service

		JSONRPC2Request request = new JSONRPC2Request(RPC_METHOD_GOOD, 0);

		URL url = null;

		try {
			url = new URL(URL_HTTP_GOOD);

		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}

		JSONRPC2Session session = new JSONRPC2Session(url);

		session.getOptions().enableCompression(true);
		assertTrue(session.getOptions().enableCompression());

		ResponseInspector responseInspector = new ResponseInspector();
		session.setRawResponseInspector(responseInspector);


		JSONRPC2Response response = null;

		try {
			response = session.send(request);

		} catch (JSONRPC2SessionException e) {
			fail(e.getMessage());
		}

		RawResponse rawResponse = responseInspector.getLastRawResponse();

		assertEquals("gzip", rawResponse.getContentEncoding());

		System.out.println("Response (decompressed): " + response);
	}


	public void testNotification() {

		// Assumes a Json2Ldap service

		JSONRPC2Notification notification = new JSONRPC2Notification(RPC_METHOD_GOOD);

		URL url = null;

		try {
			url = new URL(URL_HTTP_GOOD);

		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}

		JSONRPC2Session session = new JSONRPC2Session(url);


		try {
			session.send(notification);

		} catch (JSONRPC2SessionException e) {
			fail(e.getMessage());
		}
	}


	public void testNetworkException1() {

		// Assumes a Json2Ldap service

		JSONRPC2Request request = new JSONRPC2Request(RPC_METHOD_GOOD, 0);

		URL url = null;

		try {
			url = new URL(URL_HTTP_BAD_HOST);

		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}

		JSONRPC2Session session = new JSONRPC2Session(url);
		session.getOptions().setConnectTimeout(1000); // 1 second

		JSONRPC2Response response = null;

		try {
			response = session.send(request);
			fail("Failed to raise network exception");

		} catch (JSONRPC2SessionException e) {
			// ok
			assertEquals(JSONRPC2SessionException.NETWORK_EXCEPTION, e.getCauseType());
			System.out.println(e.getMessage() + " [" + e.getCause().getClass().getName() + "]");
		}
	}


	public void testNetworkException2() {

		// Assumes a Json2Ldap service

		JSONRPC2Request request = new JSONRPC2Request(RPC_METHOD_GOOD, 0);

		URL url = null;

		try {
			url = new URL(URL_HTTP_BAD_PORT);

		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}

		JSONRPC2Session session = new JSONRPC2Session(url);
		session.getOptions().setConnectTimeout(1000); // 1 second

		JSONRPC2Response response = null;

		try {
			response = session.send(request);
			fail("Failed to raise network exception");

		} catch (JSONRPC2SessionException e) {
			// ok
			assertEquals(JSONRPC2SessionException.NETWORK_EXCEPTION, e.getCauseType());
			System.out.println(e.getMessage() + " [" + e.getCause().getClass().getName() + "]");
		}
	}



	public void testNetworkException3() {

		// Assumes a Json2Ldap service

		JSONRPC2Request request = new JSONRPC2Request(RPC_METHOD_GOOD, 0);

		URL url = null;

		try {
			url = new URL(URL_HTTP_BAD_IP);

		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}

		JSONRPC2Session session = new JSONRPC2Session(url);
		session.getOptions().setConnectTimeout(1000); // 1 second

		JSONRPC2Response response = null;

		try {
			response = session.send(request);
			fail("Failed to raise network exception");

		} catch (JSONRPC2SessionException e) {
			// ok
			assertEquals(JSONRPC2SessionException.NETWORK_EXCEPTION, e.getCauseType());
			System.out.println(e.getMessage() + " [" + e.getCause().getClass().getName() + "]");
		}
	}


	public void testNetworkException4() {

		// Assumes a Json2Ldap service

		JSONRPC2Request request = new JSONRPC2Request(RPC_METHOD_GOOD, 0);

		URL url = null;

		try {
			// NO POST (HTTP 405)
			url = new URL(URL_HTTP_BAD_SERVICE);

		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}

		JSONRPC2Session session = new JSONRPC2Session(url);


		JSONRPC2Response response = null;

		try {
			response = session.send(request);
			fail("Failed to raise JSON-RPC 2.0 exception");

		} catch (JSONRPC2SessionException e) {
			// ok
			System.out.println(e.getMessage() + " [" + e.getCause().getClass().getName() + "]");

			assertEquals(JSONRPC2SessionException.NETWORK_EXCEPTION, e.getCauseType());
		}
	}


	// Disable temporarily
	public void _testRequestTrustAllCerts() {

		// Assumes a Json2Ldap service

		JSONRPC2Request request = new JSONRPC2Request(RPC_METHOD_GOOD, 0);

		URL url = null;

		try {
			url = new URL(URL_HTTPS_SELF_SIGNED);

		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}

		JSONRPC2Session session = new JSONRPC2Session(url);

		JSONRPC2SessionOptions opts = session.getOptions();
		opts.trustAllCerts(true);
		session.setOptions(opts);


		JSONRPC2Response response = null;

		try {
			response = session.send(request);

		} catch (JSONRPC2SessionException e) {

			String msg = e.getMessage();

			Throwable cause = e.getCause();
			if (cause != null)
				msg += ": " + cause.getMessage();

			fail(msg);
		}

		System.out.println("Response: " + response);
	}


	public void testRequestIDMatchingBoolean() {

		// Assumes a Json2Ldap service

		JSONRPC2Request request = new JSONRPC2Request(RPC_METHOD_GOOD, true);

		URL url = null;

		try {
			url = new URL(URL_HTTP_GOOD);

		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}

		JSONRPC2Session session = new JSONRPC2Session(url);


		JSONRPC2Response response = null;

		try {
			response = session.send(request);

		} catch (JSONRPC2SessionException e) {

			fail(e.getMessage());
		}

		System.out.println("Response: " + response);
	}


	public void testRequestIDMatchingNumber() {

		// Assumes a Json2Ldap service

		JSONRPC2Request request = new JSONRPC2Request(RPC_METHOD_GOOD, 3.14);

		URL url = null;

		try {
			url = new URL(URL_HTTP_GOOD);

		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}

		JSONRPC2Session session = new JSONRPC2Session(url);


		JSONRPC2Response response = null;

		try {
			response = session.send(request);

		} catch (JSONRPC2SessionException e) {

			fail(e.getMessage());
		}

		System.out.println("Response: " + response);
	}


	public void testRequestIDMatchingString() {

		// Assumes a Json2Ldap service

		JSONRPC2Request request = new JSONRPC2Request(RPC_METHOD_GOOD, "abc");

		URL url = null;

		try {
			url = new URL(URL_HTTP_GOOD);

		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}

		JSONRPC2Session session = new JSONRPC2Session(url);


		JSONRPC2Response response = null;

		try {
			response = session.send(request);

		} catch (JSONRPC2SessionException e) {

			fail(e.getMessage());
		}

		System.out.println("Response: " + response);
	}


	public void testRequestIDMatchingNull() {

		// Assumes a Json2Ldap service

		JSONRPC2Request request = new JSONRPC2Request(RPC_METHOD_GOOD, null);

		URL url = null;

		try {
			url = new URL(URL_HTTP_GOOD);

		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}

		JSONRPC2Session session = new JSONRPC2Session(url);


		JSONRPC2Response response = null;

		try {
			response = session.send(request);

		} catch (JSONRPC2SessionException e) {

			fail(e.getMessage());
		}

		System.out.println("Response: " + response);
	}

	public void testRequestContentType() {

		URL url = null;

		try {
			url = new URL(URL_HTTP_GOOD);

		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}

		JSONRPC2Session session = new JSONRPC2Session(url);

		session.getOptions().setRequestContentType("application/json+rpc");

		assertEquals("application/json+rpc", session.getOptions().getRequestContentType());
	}


	public void testResponseContentType() {

		URL url = null;

		try {
			url = new URL(URL_HTTP_GOOD);

		} catch (MalformedURLException e) {
			fail(e.getMessage());
		}

		JSONRPC2Session session = new JSONRPC2Session(url);

		session.getOptions().setAllowedResponseContentTypes(new String[]{"application/json+rpc"});

		JSONRPC2Request request = new JSONRPC2Request(RPC_METHOD_GOOD, null);

		try {
			session.send(request);

			fail("Failed to raise unexpected content-type exception");
		
		} catch (JSONRPC2SessionException e) {

			System.out.println(e.getMessage());
		}
	}
}
