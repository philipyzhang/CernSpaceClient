/**
 * Class to fetch projects from Firebase Firestore
 *
 * @author Devam Sisodraker (devam@alumni.ubc.ca)
 */

package utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class ProjectsFetcher {

    private static HttpClient httpClient = HttpClient.newHttpClient();
    private static final ProjectsFetcher INSTANCE = new ProjectsFetcher();

    private ProjectsFetcher() {

    }

    /**
     * Retrieves the singleton instance of the ProjectsFetcher
     *
     * @return Singleton instance of ProjectsFetcher
     */
    public static ProjectsFetcher getInstance() {
        return INSTANCE;
    }

    /**
     * Gets all the projects from the cloud
     *
     * @return Array of projects
     * @throws IOException
     * @throws ParseException
     */
    // TODO: Change the signature of this function after you change the signature of formatToProject (line 68)
    public Object getProjects() throws IOException, ParseException {
        return formatToProject(fetchFromFirestore());
    }F

    /**
     * Fetch data in string format from firestore
     *
     * @return HTTP Connection
     * @throws IOException
     */
    private HttpURLConnection fetchFromFirestore() throws IOException {

        URL url = new URL("https://firestore.googleapis.com/v1/projects/cernspace/databases/(default)/documents/Projects");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP Error code : "
                    + conn.getResponseCode());
        }

        return conn;
    }


    // TODO: Change the signature to return Project[]
    private Object formatToProject(HttpURLConnection connection) throws IOException, ParseException {
        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(in);
        Object object = new JSONParser().parse(br);
        JSONObject jo = (JSONObject) object;

        JSONArray documents = (JSONArray) jo.get("documents");
        documents.forEach(documentOne -> {

            // TODO: This part isn't finished yet, please extract the data properly
            JSONObject documentObject = (JSONObject) documentOne;
            JSONObject fieldsObject = (JSONObject) documentObject.get("fields");

            String _id = ((String) ((JSONObject) fieldsObject.get("_id")).get("stringValue"));
            String name = ((String) ((JSONObject) fieldsObject.get("name")).get("stringValue"));
            Integer lastUpdated = ((Integer) ((JSONObject) fieldsObject.get("lastUpdated")).get("integerValue"));

            JSONObject hostObject = ((JSONObject) ((JSONObject) fieldsObject.get("host")).get("mapValue"));
            String hostIp = ((String) ((JSONObject) hostObject.get("ip")).get("stringValue"));
            Integer hostPort = ((Integer) ((JSONObject) hostObject.get("port")).get("integerValue"));
            String hostToken = ((String) ((JSONObject) hostObject.get("token")).get("stringValue"));

        });
        connection.disconnect();
        return new Object();
    }
}