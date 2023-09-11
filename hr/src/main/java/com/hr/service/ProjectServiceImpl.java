package com.hr.service;

import com.hr.domain.Project;
import com.hr.domain.ProjectMember;
import com.hr.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    ProjectMapper projectMapper;
    @Override
    public List<Project> findAllProjects() {
        return projectMapper.findAllProjects();
    }

    @Override
    public Project findProjectByProjName(String proj_name) {
        return projectMapper.findProjectByProjName(proj_name);
    }

    @Override
    public int addProject(Project project) {
       return projectMapper.addProject(project);
    }

    @Override
    public List<Map<String, Object>> findAllProjectMembers() {
        return projectMapper.findAllProjectMembers();
    }

    @Override
    public List<Map<String, Object>> findProjectLeader() {
        return projectMapper.findProjectLeader();
    }

    @Override
    public List<Map<String, Object>> findMembersEachProj() {
        return projectMapper.findMembersEachProj();
    }


    @Override
    public void addProjectMember(ProjectMember projectMember) {
        projectMapper.addProjectMember(projectMember);
    }

    @Override
    public void deleteProject(int proj_id) {
        projectMapper.deleteProject(proj_id);
    }

    @Override
    public Project findProjectByProjId(int proj_id) {
        return projectMapper.findProjectByProjId(proj_id);
    }

    @Override
    public void updateProject(Project project) {
        projectMapper.updateProject(project);
    }

    @Override
    public void deleteProjectMember(int proj_id) {
        projectMapper.deleteProjectMember(proj_id);
    }

    @Override
    public List<Map<String, Object>> findProjMemberByProjId(int proj_id) {
        return projectMapper.findProjMemberByProjId(proj_id);
    }
}
