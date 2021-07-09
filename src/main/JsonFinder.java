package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The Class for getting information in json format from API.
 */
public class JsonFinder {
	
	/** The query URL for lists. */
	public static String firstUrlStringWithoutQuery = "http://www.omdbapi.com/?apikey=90d07043&s=";
	
	/** The query URL for one movie. */
	public static String secondUrlStringWithoutQuery = "http://www.omdbapi.com/?apikey=90d07043&i=";
	
	/** The variable for filtering results by page. */
	public static String page;
	
	/** The full final query URL. */
	public static String fullUrl;
	
	/** The URL query link. */
	public static URL url;
	
	
	/**
	 * Reads json from the API.
	 *
	 * @param givenQuery the query for reading json
	 * @param a the method returns different results depending on the parameter
	 * @param givenPage filters the search by page
	 * @param year filters the search by year
	 * @return the JSON object is the result
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static JSONObject readJson(String givenQuery, int a, int givenPage, String year) throws IOException {	
		if(a==1) {
			page=givenPage+"";
			fullUrl = firstUrlStringWithoutQuery+givenQuery+"&type=movie&page="+givenPage;	
		}
		else if(a==2) {
			fullUrl = secondUrlStringWithoutQuery+givenQuery+"&type=movie";
		}
		
		try {
			fullUrl+="&y="+year;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			url = new URL(fullUrl);
			URLConnection conn = null;
			
			conn = url.openConnection();
			conn.setConnectTimeout(25000);
			conn.setReadTimeout(25000);
			
			conn.connect();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			StringBuilder sb = new StringBuilder();
			int cp;
			while((cp = rd.read()) != -1) {
				sb.append((char)cp);
			}
			
			JSONObject json = new JSONObject(sb.toString());
					
			
			return json;				
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("NO Internet Connection");
			throw new IOException();
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
}
