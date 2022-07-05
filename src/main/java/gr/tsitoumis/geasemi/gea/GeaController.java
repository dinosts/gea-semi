package gr.tsitoumis.geasemi.gea;

import gr.tsitoumis.geasemi.gea.entities.GeaRunRequestBody;
import gr.tsitoumis.geasemi.gea.entities.GeaRunResponseBody;
import gr.tsitoumis.geasemi.gea.services.GeaClassesService;
import gr.tsitoumis.geasemi.utils.Commands;
import gr.tsitoumis.geasemi.utils.GeaSemiException;
import gr.tsitoumis.geasemi.utils.GitTools;

//

import gr.tsitoumis.geasemi.gea.entities.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/gea")
public class GeaController {
    @RequestMapping(value = "run", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<GeaRunResponseBody> run(@RequestBody GeaRunRequestBody request) throws Exception {
        String url = request.getUrl();
        String name = GitTools.getProjectName(request.getUrl());
        String lang = request.getLanguage();
        ResponseEntity<GeaRunResponseBody> response;

        try {
            Commands.gitClone(url);
            Commands.geaRun(name, lang);
            response = ResponseEntity.status(HttpStatus.OK).body(new GeaRunResponseBody("Gea analysis COMPLETED"));
        } catch (GeaSemiException e) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeaRunResponseBody("Gea analysis FAILED: " + e.getMessage()));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GeaRunResponseBody("Gea analysis FAILED: Internal Error"));
        }

        Commands.deleteProject(name);

        return response;
    }

    //

    private static final String SPLIT_BY = "\\.";

    @Autowired
    private GeaClassesService geaClassesService;

    @GetMapping(value = "moveClassRefactorings")
    public ResponseEntity<MoveClassRefactoringsResponseEntity> search(@RequestParam(value = "projectID", required = true) String projectID, @RequestParam(value = "isNew", required = true) int isNew) {
        System.out.println("> search projectID:" + projectID + ", isNew: " + isNew);

        MoveClassRefactoringsResponseEntity moveClassRefactoringsResponseEntity;
        List<MoveClassRefactoringsNameChildren> children = new ArrayList<>();

        List<ClassPackageProjectName> res = geaClassesService.getMoveClassRefactoringsByProjectNameAndIsNew(projectID, (isNew == 1 ? true : false));

        // Check if there are refactorings in database
        if (res != null && !res.isEmpty()) {
            String packageName = res.get(0).getPackageName();
            String rootPackage = packageName.split(SPLIT_BY)[0];

            Map<String, Set<String>> myMap = new HashMap<>();
            myMap.put(packageName, new LinkedHashSet<>(Arrays.asList(res.get(0).getClassName())));
            for (int i = 1; i < res.size(); i++) {
                if (Objects.equals(res.get(i).getPackageName(), packageName)) {
                    if (!myMap.containsKey(packageName))
                        myMap.put(packageName, new LinkedHashSet<>(Arrays.asList(res.get(i).getClassName())));
                    else {
                        Set<String> classNamesSet = myMap.get(packageName);
                        classNamesSet.add(res.get(i).getClassName());
                        myMap.put(packageName, classNamesSet);
                    }

                } else {
                    packageName = res.get(i).getPackageName();
                    myMap.put(packageName, new LinkedHashSet<>(Arrays.asList(res.get(i).getClassName())));
                }
            }

            for (Map.Entry<String, Set<String>> entry : myMap.entrySet())
                children.add(new MoveClassRefactoringsNameChildren(entry.getKey().replaceAll(rootPackage + SPLIT_BY, ""), entry.getValue().stream().map(MoveClassRefactoringsChild::new).collect(Collectors.toList())));

            moveClassRefactoringsResponseEntity = new MoveClassRefactoringsResponseEntity(new MoveClassRefactorings(rootPackage, children));

            System.out.println("< search projectID: " + projectID + ", isNew: " + isNew);

            return new ResponseEntity<MoveClassRefactoringsResponseEntity>(moveClassRefactoringsResponseEntity, HttpStatus.OK);
        } else {
            moveClassRefactoringsResponseEntity = new MoveClassRefactoringsResponseEntity(new MoveClassRefactorings());
            return new ResponseEntity<MoveClassRefactoringsResponseEntity>(moveClassRefactoringsResponseEntity, HttpStatus.OK);
        }
    }

    @Autowired
    private GeaClassesService metricsService;

    @GetMapping(value = "/moveClassRefactoringMetrics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<moveClassRefactoringsMetricsResponseEntity> search(@RequestParam(value = "projectID", required = true) String projectID) {
        System.out.println("> search projectID: " + projectID);

        moveClassRefactoringsMetrics mcrmProject = metricsService.getMoveClassRefactoringsMetrics(projectID);
        moveClassRefactoringsMetricsResponseEntity metrics = new moveClassRefactoringsMetricsResponseEntity(Objects.isNull(mcrmProject) ? new moveClassRefactoringsMetrics() : mcrmProject);

        System.out.println("< search projectID: " + projectID);
        return new ResponseEntity<moveClassRefactoringsMetricsResponseEntity>(metrics, HttpStatus.OK);

    }
}
