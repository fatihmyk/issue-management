package com.fatihmayuk.issuemanagement.service;

import com.fatihmayuk.issuemanagement.dto.IssueDto;
import com.fatihmayuk.issuemanagement.entity.Issue;
import com.fatihmayuk.issuemanagement.util.TPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IssueService {

    IssueDto save(IssueDto issueDto);

    IssueDto getById(Long id);

    TPage<IssueDto> getAllPageable(Pageable pageable);

    Boolean delete(IssueDto issueDto);


}
