package com.fatihmayuk.issuemanagement.service.impl;

import com.fatihmayuk.issuemanagement.dto.IssueDto;
import com.fatihmayuk.issuemanagement.entity.Issue;
import com.fatihmayuk.issuemanagement.repository.IssueRepository;
import com.fatihmayuk.issuemanagement.service.IssueService;
import com.fatihmayuk.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;

    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
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
        return null;
    }

    @Override
    public TPage<IssueDto> getAllPageable(Pageable pageable) {
        Page<Issue> data= issueRepository.findAll(pageable);
        TPage tPage = new TPage<IssueDto>();
        IssueDto[] issueDtos = modelMapper.map(data.getContent(),IssueDto[].class);
        tPage.setStat(data, Arrays.asList(issueDtos));
        return tPage;

    }

    @Override
    public Boolean delete(IssueDto issueDto) {
        return null;
    }
}
