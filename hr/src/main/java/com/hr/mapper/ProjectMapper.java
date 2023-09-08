package com.hr.mapper;

import com.hr.domain.Member;
import com.hr.domain.Project;
import com.hr.domain.ProjectMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectMapper {
    List<Project> findAllProjects(); //전체 프로젝트 리스트
    Project findProjectByProjName();    //프로젝트명으로 프로젝트 찾기
    void addProject(Project project);   // 프로젝트 추가

    List<Map<String, Object>> findAllProjectMembers();

    List<Map<String,Object>> findProjectLeader();

    List<Map<String, Object>> findMembersEachProj();
    void addProjectMember(ProjectMember projectMember); //프로젝트에 인원 추가

}
