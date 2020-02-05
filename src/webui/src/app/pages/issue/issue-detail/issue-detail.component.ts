import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {IssueService} from "../../../services/shared/issue.service";
import {ProjectService} from "../../../services/shared/project.service";
import {UserService} from "../../../services/shared/user.service";

@Component({
  selector: 'app-issue-detail',
  templateUrl: './issue-detail.component.html',
  styleUrls: ['./issue-detail.component.scss']
})
export class IssueDetailComponent implements OnInit {

  issueDetail={};   //ARRAY DEGIL OBJECT. JSONDA OBJECT OLARAK DURUYOR.

  // History Table Options
  datatable_rows=[];
  columns=[];

  // Route Parameter Options
  id: number;
  private sub: any;

  //DropDown Values
  issueStatusOptions=[];
  projectOptions=[];
  assigneeOptions=[];

  constructor(private route:ActivatedRoute,
              private issueService:IssueService,
              private projectService:ProjectService,
              private userService:UserService) { }

  ngOnInit() {

    this.sub = this.route.params.subscribe(params => {
      this.id= +params['id'];
      this.loadIssueDetails();
    });

    this.columns = [{prop:'id', name:'No'},
      { prop:'description', name: 'Description', sortable:false },
      { prop:'date', name: 'Issue Date' , sortable:false },
      { prop:'issueStatus', name: 'Issue Status' , sortable:false },
      { prop:'assignee.nameSurname', name: 'Assignee' , sortable:false },
      { prop:'issue.project.projectName', name: 'Project Name' }
    ];


    //  1- Project DropDown dolacak
    this.loadProjects();
    //  2- Assignee DD dolacak
    this.loadAssignees();
    //  3- Issue Status DD dolacak.
    this.loadIssueStatuses();

  }

  private loadIssueStatuses() {
    this.issueService.getAllIssueStatuses().subscribe(response => {
      this.issueStatusOptions = response;
    });
  }

  private loadProjects() {

    this.projectService.getAllNP().subscribe(response => {
      this.projectOptions = response;
    });

  }

  private loadAssignees() {
    this.userService.getAll().subscribe(response => {
      this.assigneeOptions = response;
    });

  }

  private loadIssueDetails() {
    this.issueService.getByIdWithDetails(this.id).subscribe( response => {
      this.issueDetail= response;
      this.datatable_rows = response['issueHistories'];
    });

  }
}
