package org.dselent.scheduling.server.httpReturnObject;

import java.util.List;

public class CourseInfo {
	private String courseName;
	private String courseNumber;
	private List<SectionInfo> sectionsOfCourse;
	
	public CourseInfo(String courseName, String courseNumber, List<SectionInfo> sectionsOfCourse) {
		super();
		this.courseName = courseName;
		this.courseNumber = courseNumber;
		this.sectionsOfCourse = sectionsOfCourse;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public List<SectionInfo> getSectionsOfCourse() {
		return sectionsOfCourse;
	}

	public void setSectionsOfCourse(List<SectionInfo> sectionsOfCourse) {
		this.sectionsOfCourse = sectionsOfCourse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + ((courseNumber == null) ? 0 : courseNumber.hashCode());
		result = prime * result + ((sectionsOfCourse == null) ? 0 : sectionsOfCourse.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseInfo other = (CourseInfo) obj;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (courseNumber == null) {
			if (other.courseNumber != null)
				return false;
		} else if (!courseNumber.equals(other.courseNumber))
			return false;
		if (sectionsOfCourse == null) {
			if (other.sectionsOfCourse != null)
				return false;
		} else if (!sectionsOfCourse.equals(other.sectionsOfCourse))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseInfo [courseName=" + courseName + ", courseNumber=" + courseNumber + ", sectionsOfCourse="
				+ sectionsOfCourse + "]";
	}

}
