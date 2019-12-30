package com.fatihmayuk.issuemanagement.repository;

import com.fatihmayuk.issuemanagement.entity.IssueHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueHistoryRepository extends JpaRepository<IssueHistory,Long> {
}
