package com.hr.service;

import com.hr.domain.Project;
import com.hr.domain.ProjectMember;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    public List<Project> findAllProjects();

    public Project findProjectByProjName(String projName);

    public void addProject(Project project);

    public List<ProjectMember> findAllProjectMembers();

    public List<Map<String, Object>> findProjectLeader();

    public List<Map<String, Object>> findMembersEachProj();

    public void addProjectMember(ProjectMember projectMember);
}
