package gr.tsitoumis.geasemi.semi.tool.source.db;

import java.util.ArrayList;
import gr.tsitoumis.geasemi.semi.SemiService;
import gr.tsitoumis.geasemi.semi.entities.Opportunities;

public class DbController {
    ArrayList<Opportunities> opportunitiesList = new ArrayList();

    SemiService service = new SemiService();
    int version;

    public DbController(SemiService service){
        this.service = service;
    }

    public boolean dbActions(String projectName, int C_ProjectVersion) {
        if (projectName == null || projectName.isEmpty() || C_ProjectVersion < 0) {
            return false;
        }

        this.version = C_ProjectVersion;

        boolean ret = deletePreviousInsertsOfProject(projectName, C_ProjectVersion);

        return ret && doInserts(C_ProjectVersion);
    }

    private boolean deletePreviousInsertsOfProject(String projectName, int projectVersion) {
        try {
            long rows = service.deleteByProjectNameAndProjectVersion(projectName, projectVersion);
            System.out.println("Deleted " + rows + " rows");
            return true;

        } catch (Exception e) {
            System.out.println("Could not DELETE old database info for this project");
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean doInserts(int C_ProjectVersion) {
        for (Opportunities opportunities : opportunitiesList){
            opportunities.setProjectVersion(C_ProjectVersion);
        }

        try {
            service.saveOpportunitiesArray(opportunitiesList);
        } catch (Exception e) {
            System.out.println("Could not execute INSERT to Database");
            System.out.println(e.getMessage());
        }

        return true;
    }

    public boolean insertMethodToDatabase(String projectName, String className, String methodName, int line_start,
                                          int line_end, double cohesion_benefit, double methodOriginalCohesion, double LoC, String classPath) {

        if (projectName == null || projectName.isEmpty() || className == null || className.isEmpty()
                || methodName == null || methodName.isEmpty() || classPath == null || classPath.isEmpty()
                || cohesion_benefit < 0 || line_start < 0 || line_end < 0 || line_start >= line_end || LoC <= 0) {
            return false;
        }
        Opportunities opportunities = new Opportunities();

        opportunities.setProjectName(projectName);
        opportunities.setClassName(className);
        opportunities.setMethodName(methodName);
        opportunities.setLineStart(line_start);
        opportunities.setLineEnd(line_end);
        opportunities.setCohesionBenefiit(cohesion_benefit);
        opportunities.setMethodOriginalCohesion(methodOriginalCohesion);
        opportunities.setLinesOfCode(LoC);
        opportunities.setClassPath(classPath);

        opportunitiesList.add(opportunities);
        return true;
    }

}