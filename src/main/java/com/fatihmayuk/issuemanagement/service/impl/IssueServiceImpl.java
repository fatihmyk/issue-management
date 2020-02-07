package com.fatihmayuk.issuemanagement.service.impl;
import com.fatihmayuk.issuemanagement.dto.IssueDetailDto;
import com.fatihmayuk.issuemanagement.dto.IssueDto;
import com.fatihmayuk.issuemanagement.dto.IssueHistoryDto;
import com.fatihmayuk.issuemanagement.dto.IssueUpdateDto;
import com.fatihmayuk.issuemanagement.entity.Issue;
import com.fatihmayuk.issuemanagement.entity.IssueStatus;
import com.fatihmayuk.issuemanagement.entity.Project;
import com.fatihmayuk.issuemanagement.entity.User;
import com.fatihmayuk.issuemanagement.repository.IssueRepository;
import com.fatihmayuk.issuemanagement.repository.ProjectRepository;
import com.fatihmayuk.issuemanagement.repository.UserRepository;
import com.fatihmayuk.issuemanagement.service.IssueHistoryService;
import com.fatihmayuk.issuemanagement.service.IssueService;
import com.fatihmayuk.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final IssueHistoryService issueHistoryService;

    public IssueServiceImpl(IssueRepository issueRepository, UserRepository userRepository, ProjectRepository projectRepository, ModelMapper modelMapper, IssueHistoryService issueHistoryService) {
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.issueHistoryService = issueHistoryService;
    }


    @Override
    public IssueDto save(IssueDto issueDto) {

        issueDto.setDate(new Date());
        issueDto.setIssueStatus(IssueStatus.OPEN);

        /*if (issueDto.getDate() == null) {
        throw new IllegalArgumentException("Issue Date cannot be null!");
        }*/
        Issue issueDb = modelMapper.map(issueDto,Issue.class);
        issueDb.setProject(projectRepository.getOne(issueDto.getProject().getId()));
        issueDb = issueRepository.save(issueDb);
        issueDto.setId(issueDb.getId());
        return issueDto;

      //  return modelMapper.map(issueDb,IssueDto.class);
    }

    @Transactional
    public IssueDetailDto update(Long id , IssueUpdateDto issueUpdateDto){

        Issue issueDb= issueRepository.getOne(id);
        User user = userRepository.getOne(issueUpdateDto.getAssignee_id());

        issueHistoryService.addHistory(id , issueDb);

        issueDb.setAssignee(user);
        issueDb.setDate(issueUpdateDto.getDate());
        issueDb.setDescription(issueUpdateDto.getDescription());
        issueDb.setDetails(issueUpdateDto.getDetails());
        issueDb.setIssueStatus(issueUpdateDto.getIssueStatus());
        issueDb.setProject(projectRepository.getOne(issueUpdateDto.getProject_id()));

        issueRepository.save(issueDb);

        return getByIdWithDetails(id);
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


    @Override
    public IssueDetailDto getByIdWithDetails(Long id) {
        Issue issue = issueRepository.getOne(id);
        IssueDetailDto issueDetailDto = modelMapper.map(issue, IssueDetailDto.class);
        List<IssueHistoryDto> issueHistoryDtos = issueHistoryService.getByIssueIdOrderById(issue.getId());
        issueDetailDto.setIssueHistories(issueHistoryDtos);
        return issueDetailDto;

    }
}
