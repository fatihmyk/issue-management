import {Component, OnInit, TemplateRef} from '@angular/core';
import {IssueService} from "../../services/shared/issue.service";
import {Page} from "../../common/page";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ProjectService} from "../../services/shared/project.service";

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.scss']
})
export class IssueComponent implements OnInit {
  /* DIALOGU MODALREF ILE INITIALIZED EDECEGIZ*/
  modalRef: BsModalRef;
  page = new Page();
  rows = [];
  issueForm: FormGroup;
  projectOptions = [];

  constructor(private issueService: IssueService,
              private formBuilder: FormBuilder,
              private modalService: BsModalService,
              private projectService: ProjectService) {
  }

  ngOnInit() {
    /*2 DEGISKENI BUILDERA FORM KONTROL OLARAK EKLEDIK*/
    this.issueForm = this.formBuilder.group({
      projectId: [null, [Validators.required]],
      description: [null, [Validators.required]]
    });

    this.loadProjects();

    this.setPage({offset: 0});
  }

  private loadProjects() {
    this.projectService.getAllNP().subscribe(response => {
      this.projectOptions = response;
    });
  }

  setPage(pageInfo) {
    this.page.page = pageInfo.offset;
    this.issueService.getAll(this.page).subscribe(pagedData => {
      this.page.size = pagedData.size;
      this.page.totalElements = pagedData.totalElements;
      this.page.page = pagedData.page;
      this.rows = pagedData.content;
    });
  }

  /*ISSUEFORM'UN KONTROLLERINI DISARIYA CIKARDIK*/
  get f() {
    return this.issueForm.controls
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  saveIssue() {

    if(!this.issueForm.valid)
      return;

    this.issueService.createIssue(this.issueForm.value).subscribe(
      resp => {
        this.setPage({offset: 0});
        this.closeAndResetModal();
      }
    );
  }

  closeAndResetModal() {
    this.issueForm.reset();
    this.modalRef.hide();   /*DIALOGU HIDE EDIYORUZ*/
  }


}
