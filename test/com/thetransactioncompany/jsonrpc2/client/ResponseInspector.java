package com.thetransactioncompany.jsonrpc2.client;


/**
 * Test raw response inspector.
 *
 * @author Vladimir Dzhuvinov
 * @version $version$ (2013-01=17)
 */
public class ResponseInspector implements RawResponseInspector {


	private RawResponse rawResponse;


	public void inspect(final RawResponse rawResponse) {

		this.rawResponse = rawResponse;
	}


	public RawResponse getLastRawResponse() {

		return rawResponse;
	}
}