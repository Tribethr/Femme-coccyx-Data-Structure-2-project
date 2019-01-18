package imageManagement;

/*
 * https://westcentralus.api.cognitive.microsoft.com/vision/v1.0
 * https://westcentralus.api.cognitive.microsoft.com/vision/v2.0
 * Clave 1: 3bad672ba84246ed9e7f97ba18dd7d81
 * Clave 2: dc1a14b994b94ef1b9cffae318107d73
 */

import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;




public class AzureCommunicator {
	private static String DROPBOX_URL = "https://www.dropbox.com/s";
	
	//public static void main(String[] args)
	public ArrayList<Tag> getTags(String Url) 
	{
        HttpClient httpclient = HttpClients.createDefault();
        ArrayList<Tag> tags = new ArrayList<Tag>();
        try
        {
        	//String Url = "https://www.dropbox.com/s/ab45rzgaca8wgdi/path4522.png";
        	if (Url.startsWith(DROPBOX_URL)) {
            	Url = DROPBOX_URL + "/raw" + Url.substring(DROPBOX_URL.length());
            }
        	
        	URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/vision/v2.0/analyze");
            
            builder.setParameter("visualFeatures", "Tags");
            builder.setParameter("language", "en");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "dc1a14b994b94ef1b9cffae318107d73");


            // Request body
            StringEntity reqEntity = new StringEntity("{\"url\":\"" + Url + "\"}");
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
            	JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(
                		((JSONArray)(
                				(JSONObject) parser.parse(EntityUtils.toString(entity))
                		).get("tags")).toJSONString());
                if(array != null) {
	                for (int i = 0; i < array.size(); i++) {
	                	JSONObject jsontag = (JSONObject) array.get(i);
	                	String strTag = (String)jsontag.get("name");
	                	double confidence = (Double) jsontag.get("confidence");
						tags.add(new Tag(strTag, confidence));
					}
                } else {
                	System.out.println("No se pudo conseguir los tags de la imagen.");
                }
                //System.out.println(tags);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
		return tags;
    }
}