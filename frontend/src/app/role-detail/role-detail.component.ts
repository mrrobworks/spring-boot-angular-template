import { Component, OnInit } from '@angular/core';
import { TemplateRole } from '../models/template-role';
import { TemplateRoleFactory } from '../models/template-role-factory';
import { RoleService } from '../services/role.service';
import { ActivatedRoute } from '@angular/router';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-role-detail',
  templateUrl: './role-detail.component.html'
})
export class RoleDetailComponent implements OnInit {
  templateRole: TemplateRole = TemplateRoleFactory.empty();

  constructor(
    private roleService: RoleService,
    private route: ActivatedRoute,
    private dialogRef: MatDialogRef<RoleDetailComponent>
  ) {}

  ngOnInit() {
    const params = this.route.snapshot.params;
    this.roleService
      .getRole(params['id'])
      .subscribe(templateRole => (this.templateRole = templateRole));
  }
}
