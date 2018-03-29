import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 


public class Test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		int n = 3;
		//printX(n);
		//enigma(n);
		//display(2009);
		int count = 0;
		//pow(1.234,9);
		//System.out.println(mysterySum(4));
		
		Test http = new Test();
		String itemId = "52200004265";
		String url = "https://trackapi.nutritionix.com/v2/search/item?upc=" + itemId;
		try {
			URL callingUrl = new URL(url);
			HttpURLConnection conn =  (HttpURLConnection) callingUrl.openConnection();
			// Set Connection Method
			conn.setRequestMethod("GET");
			
			// Add Request Headers, App ID and App Key
			conn.setRequestProperty("x-app-id", "97231a09");
			conn.setRequestProperty("x-app-key", "bb3005678f642ad0795b5b84dfbc49bf");
			
			int responseCode = conn.getResponseCode();
			System.out.println("Response Code:" + responseCode);
			
			BufferedReader inputBuffer = new BufferedReader (new InputStreamReader(conn.getInputStream()));
			StringBuffer response = new StringBuffer();
			
			
			String inputLine;
			while ((inputLine = inputBuffer.readLine()) != null) {
				response.append(inputLine);
			}
			inputBuffer.close();
			System.out.println(response.toString());
			
			jsonParser(response.toString());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void printX(int n)
	{
	  if (n <= 0)
	    System.out.print(0);
	  else
	  {
	    printX(n - 1);
	    System.out.print(n);
	    printX(n - 2);
	  }
	}
	public static void enigma(int n) 
	{
	  for (int i = 0; i < n; i++)
	    enigma(i);
	  System.out.print(n);
	}
	public static void display(int x)
	{
	  System.out.println("x:" + x);
		if (x >= 10)
	  {
	    display(x/10);
	    System.out.print(x % 10);
	  }
	}
	
	public static double pow(double x, int n)
	{
	  
		double y;
	  if (n == 1)
	    y = x;
	  else
		  //System.out.println("Multply");
	    y = pow(x, n/2) * pow(x, n - n/2);  // Line 7
	  //{ y = pow(x, n/2); y *= y; if (n % 2 != 0) y *= x; }

		  System.out.println(y);
	  return y;
	}

	public static int mysterySum(int n)
	{
	  if (n == 1)
	    return 1;
	  else
		  
	    return mysterySum(n - 1) + 2*n - 1;
	}
	
	public static void jsonParser(String jString) throws ParseException {
		JSONParser jParser = new JSONParser();
		JSONObject jObject = (JSONObject) jParser.parse(jString);
		
		JSONArray food = (JSONArray) jObject.get("foods");
		System.out.println(food);
		System.out.println(food.size());
		JSONObject foodAttribs = (JSONObject) food.get(0);
		System.out.println("Brand:" + foodAttribs.get("nix_brand_name"));
		System.out.println("Item:" + foodAttribs.get("nix_item_name"));
		System.out.println("INGR:" + foodAttribs.get("nf_ingredient_statement"));
		JSONObject fPhoto = (JSONObject) foodAttribs.get("photo");
		System.out.println("Photo URL:" + fPhoto.get("thumb"));
		
		// Adding a comment
	}


}
