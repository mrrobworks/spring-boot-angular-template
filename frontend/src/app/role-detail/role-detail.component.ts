import { Component, Inject, OnInit } from '@angular/core';
import { TemplateRole } from '../models/template-role';
import { TemplateRoleFactory } from '../models/template-role-factory';
import { RoleService } from '../services/role.service';
import { ActivatedRoute } from '@angular/router';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-role-detail',
  templateUrl: './role-detail.component.html'
})
export class RoleDetailComponent implements OnInit {
  templateRole: TemplateRole = TemplateRoleFactory.empty();
  description: string;
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private roleService: RoleService,
    private route: ActivatedRoute,
    private dialogRef: MatDialogRef<RoleDetailComponent>,
    @Inject(MAT_DIALOG_DATA) templateRole: TemplateRole
  ) {
    this.templateRole = templateRole;
    this.form = this.fb.group({
      description: [this.templateRole.description],
      authority: [this.templateRole.authority]
    });
  }

  ngOnInit() {
    /*const params = this.route.snapshot.params;
    this.roleService
      .getRole(params['id'])
      .subscribe(templateRole => (this.templateRole = templateRole));*/
  }

  update() {
    this.roleService.update(this.templateRole).subscribe();
  }

  close() {
    this.dialogRef.close();
  }
}
