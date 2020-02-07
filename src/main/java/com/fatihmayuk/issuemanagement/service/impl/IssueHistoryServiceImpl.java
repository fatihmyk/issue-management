package com.fatihmayuk.issuemanagement.service.impl;


import com.fatihmayuk.issuemanagement.dto.IssueHistoryDto;
import com.fatihmayuk.issuemanagement.dto.IssueUpdateDto;
import com.fatihmayuk.issuemanagement.entity.Issue;
import com.fatihmayuk.issuemanagement.entity.IssueHistory;
import com.fatihmayuk.issuemanagement.repository.IssueHistoryRepository;
import com.fatihmayuk.issuemanagement.service.IssueHistoryService;
import com.fatihmayuk.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class IssueHistoryServiceImpl implements IssueHistoryService {

    private final IssueHistoryRepository issueHistoryRepository;
    private final ModelMapper modelMapper;

    public IssueHistoryServiceImpl(IssueHistoryRepository issueHistoryRepository, ModelMapper modelMapper) {
        this.issueHistoryRepository = issueHistoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public IssueHistoryDto save(IssueHistoryDto issueHistoryDto) {

        IssueHistory issueHistory = modelMapper.map(issueHistoryDto,IssueHistory.class);
        issueHistory= issueHistoryRepository.save(issueHistory);
        issueHistoryDto.setId(issueHistory.getId());
        return issueHistoryDto;
    }

    @Override
    public IssueHistoryDto getById(Long id) {
        IssueHistory issueHistory = issueHistoryRepository.getOne(id);
        return modelMapper.map(issueHistory,IssueHistoryDto.class);
    }

    @Override
    public List<IssueHistoryDto> getByIssueIdOrderById(Long id) {
        return Arrays.asList(modelMapper.map(issueHistoryRepository.getByIssueIdOrderById(id),IssueHistoryDto[].class));
    }

    @Override
    public TPage<IssueHistoryDto> getAllPageable(Pageable pageable) {
        Page<IssueHistory> data = issueHistoryRepository.findAll(pageable);
        TPage<IssueHistoryDto> response = new TPage<IssueHistoryDto>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(),IssueHistoryDto[].class)));
        return response;
    }

    @Override
    public Boolean delete(IssueHistoryDto issueHistoryDto) {
        issueHistoryRepository.deleteById(issueHistoryDto.getId());
        return Boolean.TRUE;
    }

    @Override
    public void addHistory(Long id,  Issue issueDb) {
        IssueHistory issueHistory = new IssueHistory();
        issueHistory.setIssue(issueDb);
        issueHistory.setAssignee(issueDb.getAssignee());
        issueHistory.setDate(issueDb.getDate());
        issueHistory.setDescription(issueDb.getDescription());
        issueHistory.setDetails(issueDb.getDetails());
        issueHistory.setIssueStatus(issueDb.getIssueStatus());
        issueHistoryRepository.save(issueHistory);

    }
}
