package com.infy.infyinterns.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.service.ProjectAllocationService;
@RestController
@RequestMapping(value="/infyinterns")
@Validated
public class ProjectAllocationAPI
{
@Autowired
private ProjectAllocationService projectService;
@Autowired
private Environment envionment;

    // add new project along with mentor details
@PostMapping(value="/project")
    public ResponseEntity<String> allocateProject(@RequestBody @Valid ProjectDTO project) throws InfyInternException
    {Integer projId = projectService.allocateProject(project);
    return new ResponseEntity<>(envionment.getProperty("API.ALLOCATION_SUCCESS")+projId,HttpStatus.CREATED);
    }

    // get mentors based on idea owner
@GetMapping(value="/project/{numberOfProjectMentored}")
    public ResponseEntity<List<MentorDTO>> getMentors(@PathVariable Integer numberOfProjectsMentored) throws InfyInternException
    {
	return new ResponseEntity<>(projectService.getMentors(numberOfProjectsMentored),HttpStatus.OK);

    }

    // update the mentor of a project
   @PutMapping(value="“project/{ projectId }/{ mentorId }")
    public ResponseEntity<String> updateProjectMentor(@PathVariable Integer projectId, @PathVariable @Min(value=1000,message="{mentor.mentorId.invalid}")
    @Max(value=9999,message="{mentor.mentorId.invalid}")  Integer mentorId) throws InfyInternException
    {
	   projectService.updateProjectMentor(projectId, mentorId);
	  

	return new ResponseEntity<>(envionment.getProperty("API.PROJECT_UPDATE_SUCCESS"),HttpStatus.OK);
    }

    // delete a project
   @DeleteMapping(value="project/{ projectId }")
    public ResponseEntity<String> deleteProject(@PathVariable Integer projectId) throws InfyInternException
    {
	   projectService.deleteProject(projectId);
	   
	return new ResponseEntity<>(envionment.getProperty("API.PROJECT_DELETE _SUCCESS"),HttpStatus.OK);
    }

}
