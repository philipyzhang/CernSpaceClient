package models;

import java.util.ArrayList;

public class ProjectsManager {
    private ArrayList<Project> projects;

    private static final ProjectsManager INSTANCE = new ProjectsManager();

    /**
     * Retrieves the singleton instance of the ProjectsManager
     *
     * @return Singleton instance of the ProjectsManager
     */
    public static ProjectsManager getInstance() {
        return INSTANCE;
    }

    public ProjectsManager() {
        projects = new ArrayList<>();
        addProject(new Project("id", "name", "ip",69, "token"));
        addProject(new Project("2id", "2name", "2ip",269, "2token"));
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }
    
    public void addProject(Project project) {
        projects.add(project);
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }
}
