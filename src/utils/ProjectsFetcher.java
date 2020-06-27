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
import java.util.ArrayList;
import java.util.List;

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
    public List<Project> getProjects() throws IOException, ParseException {
        return formatToProject(fetchFromFirestore());
    };

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

    /**
     * Transform JSON stream from a web source to projects, provided it fits the schema
     *
     * @param connection Takes in an HTTP connection
     * @return List of Projects retrieved
     * @throws IOException
     * @throws ParseException
     */
    private List<Project> formatToProject(HttpURLConnection connection) throws IOException, ParseException {
        InputStreamReader in = new InputStreamReader(connection.getInputStream());
        BufferedReader br = new BufferedReader(in);
        Object object = new JSONParser().parse(br);
        JSONObject jo = (JSONObject) object;
        List<Project> projects = new ArrayList<Project>();

        JSONArray documents = (JSONArray) jo.get("documents");
        documents.forEach(documentOne -> {
            Project project = new Project();

            JSONObject documentObject = (JSONObject) documentOne;
            JSONObject fieldsObject = (JSONObject) documentObject.get("fields");

            String _id = ((String) ((JSONObject) fieldsObject.get("_id")).get("stringValue"));
            project._id = _id;

            String name = ((String) ((JSONObject) fieldsObject.get("name")).get("stringValue"));
            project.name = name;

            String lastUpdated = ((String) ((JSONObject) fieldsObject.get("lastUpdated")).get("integerValue"));
            project.lastUpdated = Integer.parseInt(lastUpdated);

            JSONObject hostObject = (JSONObject) ((JSONObject) ((JSONObject) fieldsObject.get("host")).get("mapValue")).get("fields");
            String hostIp = ((String) ((JSONObject) hostObject.get("ip")).get("stringValue"));
            project.hostIp = hostIp;

            String hostPort = ((String) ((JSONObject) hostObject.get("port")).get("integerValue"));
            project.hostPort = Integer.parseInt(hostPort);

            String hostToken = ((String) ((JSONObject) hostObject.get("token")).get("stringValue"));
            project.hostToken = hostToken;

            projects.add(project);
        });

        connection.disconnect();
        return projects;
    }
}