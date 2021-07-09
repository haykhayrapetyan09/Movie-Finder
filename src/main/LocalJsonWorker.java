package main;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The Class which works with a local json file for favorites list.
 */
public class LocalJsonWorker {
	
	/** The local json file directory. */
	public static String fileLocation="data/favourites.json";
	
	/**
	 * Reads json from local file.
	 *
	 * @param page the method returns different results depending on page
	 * @return the result in json object format
	 */
	public static JSONObject readJson(int page) {
		try {
			String contents = new String((Files.readAllBytes(Paths.get(fileLocation))));
			JSONObject o = new JSONObject(contents);
			
			JSONObject newJs=new JSONObject();
			newJs.put("totalResults", o.get("totalResults").toString());	
			newJs.put("Response", o.get("Response").toString());
			
			JSONArray newArr=new JSONArray();
			newJs.put("Search", newArr);
			for(int i=page*10-10;i<page*10;i++) {
				try {
					newJs.getJSONArray("Search").put(o.getJSONArray("Search").get(i));
				} catch(Exception e) {
					break;
				}
			}
			return newJs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Checks if the given json object is in local json file.
	 *
	 * @param givenJsonObject this parameter is checked 
	 * @return true, if successful
	 */
	public static boolean checkInJson(JSONObject givenJsonObject) {
		try {
			String contents = new String((Files.readAllBytes(Paths.get(fileLocation))));
			JSONObject o = new JSONObject(contents);
			
			for(int i=0;i<o.getJSONArray("Search").length();i++) {
				if(o.getJSONArray("Search").getJSONObject(i).get("imdbID").toString().equals(givenJsonObject.get("imdbID").toString())) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/**
	 * Checks if the given movie is in the local file and returns the json object if finds.
	 *
	 * @param givenTitle the given movie name
	 * @param page method returns different results depending on the page
	 * @return the JSON object result
	 */
	public static JSONObject searchInJson(String givenTitle, int page) {
		try {
			String contents = new String((Files.readAllBytes(Paths.get(fileLocation))));
			JSONObject o = new JSONObject(contents);
			
			JSONObject newJs=new JSONObject();
			newJs.put("Response", o.get("Response").toString());
			
			JSONArray newArr=new JSONArray();
			newJs.put("Search", newArr);
			
			JSONArray allArr=new JSONArray();
			
			Integer totalResults=0;
			for(int i=0;i<o.getJSONArray("Search").length();i++) {
				if(o.getJSONArray("Search").getJSONObject(i).get("Title").toString().toLowerCase().contains(givenTitle.toLowerCase())) {
					allArr.put(o.getJSONArray("Search").getJSONObject(i));
					totalResults++;
				}
			}
			newJs.put("totalResults", totalResults.toString());
			
			for(int i=page*10-10;i<page*10;i++) {
				try {
					newJs.getJSONArray("Search").put(allArr.get(i));
				} catch(Exception e) {
					break;
				}
			}
			return newJs;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * Adds the given json object to the local json file.
	 *
	 * @param givenJsonObject the json object to add in the local file
	 */
	public static void writeJson(JSONObject givenJsonObject) {
		try {
			String contents = new String((Files.readAllBytes(Paths.get(fileLocation))));
			JSONObject o = new JSONObject(contents);
			o.getJSONArray("Search").put(givenJsonObject);
			Integer res=Integer.parseInt(o.get("totalResults").toString());
			res++;
			o.put("totalResults", res.toString());
			
			System.out.println(o.toString());
			File JSONOutputFile = new File(fileLocation);
	        PrintWriter writer = new PrintWriter(JSONOutputFile, "UTF-8");
	        writer.print("");
	        writer.println(o);
	        writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Removes the given json object from the local json file.
	 *
	 * @param givenJsonObject the object to remove from local file
	 */
	public static void removeJson(JSONObject givenJsonObject) {
		try {
			String contents = new String((Files.readAllBytes(Paths.get(fileLocation))));
			JSONObject o = new JSONObject(contents);
			
			for(int i=0;i<o.getJSONArray("Search").length();i++) {
				if(o.getJSONArray("Search").getJSONObject(i).get("imdbID").toString().equals(givenJsonObject.get("imdbID").toString())) {
					System.out.println("deleting jsonobject in jsonworker"+i);
					o.getJSONArray("Search").remove(i);
					Integer res=Integer.parseInt(o.get("totalResults").toString());
					res--;
					o.put("totalResults", res.toString());
				}
			}
			
			
			System.out.println(o.toString());
			File JSONOutputFile = new File(fileLocation);
	        PrintWriter writer = new PrintWriter(JSONOutputFile, "UTF-8");
	        writer.print("");
	        writer.println(o);
	        writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
