package com.hr.service;

import com.hr.domain.Project;
import com.hr.domain.ProjectMember;

import java.util.List;
import java.util.Map;

 public interface ProjectService {
     List<Project> findAllProjects();

     Project findProjectByProjName(String proj_name);

     int addProject(Project project);

     List<Map<String, Object>> findAllProjectMembers();

     List<Map<String, Object>> findProjectLeader();

     List<Map<String, Object>> findMembersEachProj();

     void addProjectMember(ProjectMember projectMember);
    
    void deleteProject(int proj_id);

    List<Map<String, Object>> findProjMemberByProjId(int proj_id);

    Project findProjectByProjId(int proj_id);

    void updateProject(Project project);

    void deleteProjectMember(int proj_id);

 }
