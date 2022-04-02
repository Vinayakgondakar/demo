package com.infy.infyinterns.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MentorDTO {
    @NotNull(message="{mentor.mentorId.absent}")
    @Min(value=1000, message="{mentor.mentorId.invalid}")
    @Max(value=9999,message="{mentor.mentorId.Invalid}")
	private Integer mentorId;
	private String mentorName;
	private Integer numberOfProjectsMentored;

	public MentorDTO() {
		super();
	}

	public MentorDTO(Integer mentorId, String mentorName, Integer numberOfProjectsMentored) {
		super();
		this.mentorId = mentorId;
		this.mentorName = mentorName;
		this.numberOfProjectsMentored = numberOfProjectsMentored;
	}

	public Integer getMentorId() {
		return mentorId;
	}

	public void setMentorId(Integer mentorId) {
		this.mentorId = mentorId;
	}

	public String getMentorName() {
		return mentorName;
	}

	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}

	public Integer getNumberOfProjectsMentored() {
		return numberOfProjectsMentored;
	}

	public void setNumberOfProjectsMentored(Integer numberOfProjectsMentored) {
		this.numberOfProjectsMentored = numberOfProjectsMentored;
	}

}
