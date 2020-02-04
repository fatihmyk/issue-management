package com.fatihmayuk.issuemanagement.service.impl;
import com.fatihmayuk.issuemanagement.dto.IssueDetailDto;
import com.fatihmayuk.issuemanagement.dto.IssueDto;
import com.fatihmayuk.issuemanagement.dto.IssueHistoryDto;
import com.fatihmayuk.issuemanagement.entity.Issue;
import com.fatihmayuk.issuemanagement.repository.IssueRepository;
import com.fatihmayuk.issuemanagement.service.IssueHistoryService;
import com.fatihmayuk.issuemanagement.service.IssueService;
import com.fatihmayuk.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;
    private final IssueHistoryService issueHistoryService;

    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper, IssueHistoryService issueHistoryService) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
        this.issueHistoryService = issueHistoryService;
    }


    @Override
    public IssueDto save(IssueDto issueDto) {

        if (issueDto.getDate() == null) {
        throw new IllegalArgumentException("Issue Date cannot be null!");
        }

        Issue issueDb = modelMapper.map(issueDto,Issue.class);
        issueDb = issueRepository.save(issueDb);
        return modelMapper.map(issueDb,IssueDto.class);
    }

    @Override
    public IssueDto getById(Long id) {
        Issue issue = issueRepository.getOne(id);
        return modelMapper.map(issue,IssueDto.class);
    }

    @Override
    public TPage<IssueDto> getAllPageable(Pageable pageable) {
        Page<Issue> data= issueRepository.findAll(pageable);
        TPage<IssueDto> tPage = new TPage<IssueDto>();
        IssueDto[] issueDtos = modelMapper.map(data.getContent(),IssueDto[].class);
        tPage.setStat(data, Arrays.asList(issueDtos));
        return tPage;
    }

    public List<IssueDto> getAll() {
        List<Issue> data= issueRepository.findAll();
        return Arrays.asList( modelMapper.map(data, IssueDto[].class));
    }

    @Override
    public Boolean delete(Long id) {
        issueRepository.deleteById(id);
        return true;
    }

    @Override
    public IssueDto update(Long id, IssueDto issueDto) {
        return null;
    }

    public IssueDetailDto getByIdWithDetails(Long id) {
        Issue issue = issueRepository.getOne(id);
        IssueDetailDto issueDetailDto = modelMapper.map(issue, IssueDetailDto.class);
        List<IssueHistoryDto> issueHistoryDtos = issueHistoryService.getByIssueId(issue.getId());
        issueDetailDto.setIssueHistories(issueHistoryDtos);
        return issueDetailDto;

    }
}
