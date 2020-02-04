package com.fatihmayuk.issuemanagement.repository;

import com.fatihmayuk.issuemanagement.entity.IssueHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueHistoryRepository extends JpaRepository<IssueHistory,Long> {

    List<IssueHistory> getByIssueId(Long id);
}
