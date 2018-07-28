import { Component, OnInit } from '@angular/core';
import { TemplateRole } from '../models/template-role';
import { RoleService } from '../services/role.service';
import { TemplateRoleFactory } from '../models/template-role-factory';
import { DetailMode } from '../models/detail-mode';

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html'
})
export class RoleListComponent implements OnInit {
  selectedTemplateRole: TemplateRole;
  templateRoles: TemplateRole[];
  TemplateRoleFactory = TemplateRoleFactory;
  DetailMode = DetailMode;
  detailMode: DetailMode;
  selectedTemplateRoleId: String;
  deleteSuccess: boolean;
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
    this.deleteSuccess = undefined;
  }

  openRoleDetailDialog(detailMode: DetailMode, templateRole: TemplateRole) {
    this.resetAlertMessages();
    this.detailMode = detailMode;
    this.selectedTemplateRole = templateRole;
    this.roleService.initRoleDetailComponent(
      this.detailMode,
      this.selectedTemplateRole
    );
  }

  openRoleDeleteDialog(templateRoleId: String) {
    this.resetAlertMessages();
    this.selectedTemplateRoleId = templateRoleId;
  }

  detailActionDone(event: boolean) {
    if (event) {
      this.loadRoleList();
    }
  }

  deleteActionDone(event: any) {
    this.deleteSuccess = event.deleteSuccess;
    if (this.deleteSuccess) {
      this.loadRoleList();
    }
    this.statusMessage = event.errorMessage;
  }
}
