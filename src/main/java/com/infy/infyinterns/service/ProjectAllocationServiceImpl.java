package com.infy.infyinterns.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.entity.Mentor;
import com.infy.infyinterns.entity.Project;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.repository.MentorRepository;
import com.infy.infyinterns.repository.ProjectRepository;
@Service(value="projectService")
@Transactional
public class ProjectAllocationServiceImpl implements ProjectAllocationService {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private MentorRepository mentorRepository;
	
	@Override
	public Integer allocateProject(ProjectDTO project) throws InfyInternException {
     Optional<Mentor> opt=mentorRepository.findById(project.getMentorDTO().getMentorId());
     Mentor mentor=opt.orElseThrow(()->new InfyInternException( "Service.MENTOR_NOT_FOUND"));
     if(mentor.getNumberOfProjectsMentored()>=3) {
    	 throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");
     }
    	 Project projet =new Project();
    	 projet.setIdeaOwner(project.getIdeaOwner());
    	 projet.setMentor(mentor);
    	 projet.setProjectId(project.getProjectId());
    	 projet.setProjectName(project.getProjectName());
    	 projet.setReleaseDate(project.getReleaseDate());
    	 mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()+1);
    	 Project newProject=projectRepository.save(projet);
      
		return projet.getProjectId();
	}

	
	@Override
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InfyInternException {
		List<Mentor> opt=mentorRepository.FindByNuberOfPorjectsMentored(numberOfProjectsMentored);
		if(opt.isEmpty()) {
			throw new InfyInternException("Service.MENTOR_NOT_FOUND" );
		}
		List<MentorDTO> mentorDto=new ArrayList<>();
		for(Mentor mentor : opt) {
			MentorDTO mentorD=new MentorDTO();
			mentorD.setMentorId(mentor.getMentorId());
			mentorD.setMentorName(mentor.getMentorName());
			mentorD.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored());
			mentorDto.add(mentorD);
		}
			return mentorDto;
		}


	@Override
	public void updateProjectMentor(Integer projectId, Integer mentorId) throws InfyInternException {
	
	}

	@Override
	public void deleteProject(Integer projectId) throws InfyInternException {
		
	}
}