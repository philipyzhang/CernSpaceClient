package models;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Project {
    private SimpleStringProperty _id ;
    private SimpleStringProperty name;
    private SimpleStringProperty shortDescription;
    private SimpleStringProperty longDescription;
    private SimpleStringProperty ip;
    private SimpleIntegerProperty port;
    private SimpleStringProperty token;
    private SimpleIntegerProperty lastUpdated;

    public Project(String _id, String name, String shortDescription, String longDescription, String ip, Integer port, String token, Integer lastUpdated) {
        this._id = new SimpleStringProperty(_id);
        this.name = new SimpleStringProperty(name);
        this.shortDescription = new SimpleStringProperty(shortDescription);
        this.longDescription = new SimpleStringProperty(longDescription);
        this.ip = new SimpleStringProperty(ip);
        this.port = new SimpleIntegerProperty(port);
        this.token = new SimpleStringProperty(token);
        this.lastUpdated = new SimpleIntegerProperty(lastUpdated);
    }

    public String getId() {
        return _id.get();
    }

    public void setId(String _id) {
        this._id.set(_id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getIp() {
        return ip.get();
    }

    public void setIp(String ip) {
        this.ip.set(ip);
    }

    public Integer getPort() {
        return port.get();
    }

    public void setPort(Integer port) {
        this.port.set(port);
    }

    public String getShortDescription() {
        return shortDescription.get();
    }

    public void setShortDescription(String token) {
        this.shortDescription.set(token);
    }

    public String getLongDescription() {
        return longDescription.get();
    }

    public void setLongDescription(String token) {
        this.longDescription.set(token);
    }

    public String getToken() {
        return token.get();
    }

    public void setToken(String token) {
        this.token.set(token);
    }

    public Integer getLastUpdated() {
        return lastUpdated.get();
    }

    public void setLastUpdated(Integer lastUpdated) {
        this.lastUpdated.set(lastUpdated);
    }
}
