package com.fatihmayuk.issuemanagement.repository;

import com.fatihmayuk.issuemanagement.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue,Long> {



}
