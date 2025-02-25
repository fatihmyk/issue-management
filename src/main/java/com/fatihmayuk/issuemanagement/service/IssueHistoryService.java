package com.fatihmayuk.issuemanagement.service;

import com.fatihmayuk.issuemanagement.dto.IssueHistoryDto;
import com.fatihmayuk.issuemanagement.dto.IssueUpdateDto;
import com.fatihmayuk.issuemanagement.entity.Issue;
import com.fatihmayuk.issuemanagement.entity.IssueHistory;
import com.fatihmayuk.issuemanagement.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IssueHistoryService {

    IssueHistoryDto save(IssueHistoryDto issueHistoryDto);

    IssueHistoryDto getById(Long id);

    List<IssueHistoryDto> getByIssueIdOrderById(Long id);

    TPage<IssueHistoryDto> getAllPageable(Pageable pageable);

    Boolean delete(IssueHistoryDto issueHistoryDto);

   void addHistory(Long id, Issue issueDb);
}
