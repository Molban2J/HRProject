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
    public Project findProjectByProjName(String projName) {
        return projectMapper.findProjectByProjName();
    }

    @Override
    public void addProject(Project project) {
        projectMapper.addProject(project);
    }

    @Override
    public List<ProjectMember> findAllProjectMembers() {
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
}
