import { Component, OnInit, ViewChild } from '@angular/core';
import { TemplateRole } from '../models/template-role';
import { RoleService } from '../services/role.service';
import { TemplateRoleFactory } from '../models/template-role-factory';
import { DetailMode } from '../models/detail-mode';
import { RoleDetailComponent } from '../role-detail/role-detail.component';
import { RoleDeleteComponent } from '../role-delete/role-delete.component';

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html'
})
export class RoleListComponent implements OnInit {
  @ViewChild(RoleDetailComponent) roleDetailComponent;
  @ViewChild(RoleDeleteComponent) roleDeleteComponent;
  DetailMode = DetailMode;
  TemplateRoleFactory = TemplateRoleFactory;
  templateRoles: TemplateRole[];
  deleteSuccessful: boolean;
  statusMessage: string;

  constructor(private roleService: RoleService) {}

  ngOnInit() {
    this.loadRoleList();
  }

  private loadRoleList() {
    this.roleService
      .getRoles()
      .subscribe(templateRoles => (this.templateRoles = templateRoles));
  }

  private resetAlertMessages() {
    this.deleteSuccessful = undefined;
  }

  openRoleDetailDialog(detailMode: DetailMode, templateRole: TemplateRole) {
    this.resetAlertMessages();
    this.roleDetailComponent.initComponent(detailMode, templateRole);
  }

  openRoleDeleteDialog(templateRoleId: string) {
    this.resetAlertMessages();
    this.roleDeleteComponent.initComponent(templateRoleId);
  }

  detailActionDone(event: boolean) {
    if (event) {
      this.loadRoleList();
    }
  }

  deleteActionDone(event: any) {
    this.deleteSuccessful = event.deleteSuccessful;
    if (this.deleteSuccessful) {
      this.loadRoleList();
    }
    this.statusMessage = event.statusMessage;
  }
}
