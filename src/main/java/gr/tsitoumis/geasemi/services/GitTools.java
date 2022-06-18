package gr.tsitoumis.geasemi.services;

public class GitTools {
    public static String getProjectName(String url) {
        String splittedUrl[] = url.split("/");
        String projectName = splittedUrl[splittedUrl.length - 1];
        return projectName;
    }
}
