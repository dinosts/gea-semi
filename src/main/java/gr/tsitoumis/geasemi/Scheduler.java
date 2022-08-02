package gr.tsitoumis.geasemi;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.*;

@Component
public class Scheduler {
    private static Logger logger = LogManager.getLogger(GeaSemiApplication.class);

    @Scheduled(cron = "* 0 3 * * ?")
    public void cleanGitProjects() {
        try {
            final String path = Paths.get("git-repositories").toAbsolutePath().toString();
            FileUtils.cleanDirectory(new File(path));
            logger.info("CLEAN_GIT_PROJECTS_CRONJOB: Cleaned git repositories");
        } catch (Exception err) {
            logger.error("CLEAN_GIT_PROJECTS_CRONJOB: Could not clean git repositories");
        }
    }
}