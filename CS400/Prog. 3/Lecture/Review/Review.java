// Use songs.csv from p1 to practice stream processing: filter, map, and reduce
// Use Regex to match/filter songs from a specific year, like 2017
// Use Regex to extract/map the title portion of a csv row 
// Have a webserver return an unordered list of song titles from requested year
package Review;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;
import java.io.*;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.*;

/*
 * Client:
	The client is the entity that sends a request to the server, typically a browser (e.g., Chrome or Firefox) or a program (e.g., curl or a custom application).
	Example: When you type http://localhost/?2017 into your browser and hit Enter, your browser is acting as the client.

* Server:
	The server is the program (your Java HttpServer) that listens for incoming requests from clients, processes them, and sends back a response.
	Example: Your Java program receives the client’s request for ?2017, processes it, and sends back the HTML page with the song list.

	The client initiates requests; the server processes them and responds.

 */

public class Review {
    public static void main(String[] args) throws Exception {
		// 80 = default port for HTTP: allows browsers to connect w/o explicitly specifying the port in the URL
		InetSocketAddress address = new InetSocketAddress(80); 
		// creates HTTP server instance that listens on the specified address and port
		// backlog: 0 = defines number of pending connections the server will allow before rejecting new ones 
		HttpServer server = HttpServer.create(address,0); 
		// sets up a context (endpoint) on the server to handle incoming HTTP requests
		// "/" is the root path. Any requests to the server will trigger the handler assigned to this context
		HttpContext context = server.createContext("/");

		// THIS IS THE AFORMENTIONED HANDLER!
		// exchange: represents the HTTP request-response communication between the client and the server
		context.setHandler( exchange -> {
			// http://hostname:portNumber/path?query =======> Hostname for VScode .java project is http://localhost/
			// retrieves query string from the URL
			// exchange.getRequestURL(): gets the full URI of the incoming HTTP request
			// .getQuery(): Extracts the query part (everything after ?) from the URI. For example, in http://localhost/?2017, this will return "2017".
			String year = exchange.getRequestURI().getQuery();
			String songList = "Oops, there was a problem.";
			try { songList = getSongList(year); }
			catch(Exception e) {}
			
			String html = "<html><body>" + songList + "</body></html>";
			// respond with 200 success code, and contents of html string
			exchange.sendResponseHeaders(200,0);
			OutputStream os = exchange.getResponseBody();
			os.write(html.getBytes());
			os.close();
		});
		
		server.start();	
    }

    public static String getSongList(String year) throws Exception {
		ArrayList<String> list = new ArrayList<>();
		Scanner fin = new Scanner(new File("songs.csv"));

		// load all lines of the file into list
		while(fin.hasNextLine()){
			list.add(fin.nextLine());
		}

		//String year = "2017";
		// filter songs by year through java stream
		// map = transformes each element in the stream into a new form
		// filter = keeps only elements of the given condition 
		// reduce = combines all elements in the stream into a single result
		String songList = list.stream() // Stream.of(array)
			.filter(line -> line.matches(".*("+year+")(,-?\\d+){10}$")) // keep elements with the given year
			.filter(line -> !line.contains("\"")) // filter out lines without single quotations (remove complexity of double quotes)
			
			// transorm this data from csv row into song title
			.map( line -> {
				Pattern regex = Pattern.compile("^([^,]+),"); // pick out just the title of each line
				// applies previously compiled regex pattern to the current line 
				// creates a "matcher" object that matchs the regex variable to the line 
				Matcher matcher = regex.matcher(line);
				// calls find() method of the matcher object, locating the first occurrence of the regex in the line string
				// returns false if there is no match, else true and moves matcher's cursor to the start of the match
				// default searches from the current position of the cursor after each match
				// Must be called to search matcher!!!
				matcher.find();
				return matcher.group(1); // extracts just the title (no comma included even though regex had it in)
			})

			// transform song titles in <li> html elements
			.map(title -> "<li>" + title + "</li>" )
			// combining ll of the <li>'s into a single <ul>
			// identity "<ul>" is the starting point for the accumulation into one result
			// accumilator function "(progress,next)->progress+next)"
			//		progress = the accumulated value so far. Begins as the identity "<ul>"
			//		next = the next value in the stream, which is a string containing an indicidual song title 
			//			wrapped in <li> tags 
			.reduce("<ul>",(progress,next)->progress+next) + "</ul>";
			//.forEach( song -> System.out.println(song) );

		//System.out.println(songList);
		return songList;
    }
}
