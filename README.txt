JSON-RPC 2.0 Client

Copyright (c) Vladimir Dzhuvinov, 2010 - 2013


README

Provides a client-side class for dispatching requests and notifications to a 
JSON-RPC 2.0 server. The JSON-RPC 2.0 messages are sent by HTTP(S) POST.

Optional client session features:

	* Customise the "Content-Type" header in HTTP POST requests.
	
	* Set an "Origin" header in HTTP POST requests to simulate Cross-Origin 
	  Resource Sharing (CORS) requests from a browser.
	  
	* Accept HTTP cookies (if client sessions are established by this mean
	  instead of through the JSON-RPC protocol itself).
	  
	* Customise the allowable "Content-Type" header values in HTTP POST 
	  responses.
	 
	* Set an HTTP proxy.
	
	* Enable HTTP response compression using GZIP and DEFLATE content 
	  encoding.
	  
	* Preserve parse order of JSON object members in JSON-RPC 2.0 response 
	  results (for human facing clients, e.g. the JSON-RPC 2.0 Shell).
	  
	* Ignore version 2.0 checks when parsing responses to allow client 
	  sessions to older JSON-RPC (1.0) servers.

	* Parse non-standard attributes appended in JSON-RPC 2.0 responses.
	
	* Trust all X.509 server certificates (for HTTPS connections), including
	  self-signed.


Requirements:

	* Java 1.5 or later
	
	* The JSON-RPC 2.0 Base package com.thetransactioncompany.jsonrpc2 to 
	  represent, serialise and parse JSON-RPC 2.0 messages. Available from
	  http://software.dzhuvinov.com/json-rpc-2.0-base.html


For complete JSON-RPC 2.0 Client documentation, examples and updates visit
	
	http://software.dzhuvinov.com/json-rpc-2.0-client.html


The JSON-RPC 2.0 specification and user group forum can be found at

	http://groups.google.com/group/json-rpc


[EOF]
