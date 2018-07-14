import { Component, OnInit } from '@angular/core';
import { TemplateRole } from '../models/template-role';
import { RoleService } from '../services/role.service';
import { TemplateRoleFactory } from '../models/template-role-factory';
import { DetailMode } from '../models/detail-mode';
import 'rxjs/add/operator/distinctUntilChanged';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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
  detailHeader: string;
  detailForm: FormGroup;

  constructor(private roleService: RoleService, private fb: FormBuilder) {}

  ngOnInit() {
    this.roleService
      .getAllRoles()
      .subscribe(templateRoles => (this.templateRoles = templateRoles));
  }

  init(detailMode: DetailMode, templateRole: TemplateRole) {
    this.detailMode = detailMode;
    this.selectedTemplateRole = templateRole;
    this.initDetailHeader();
    this.initDetailForm();
  }

  private initDetailHeader(): void {
    this.detailHeader =
      this.detailMode === DetailMode.NEW
        ? 'Create a new role'
        : 'Edit role with ID: ' + this.selectedTemplateRole.id;
  }

  private initDetailForm(): void {
    if (this.detailForm !== undefined) {
      this.detailForm.reset();
    }

    this.detailForm = this.fb.group({
      id: [
        {
          value: this.selectedTemplateRole.id,
          disabled: this.detailMode === DetailMode.EDIT
        },
        Validators.required
      ],
      description: [
        this.selectedTemplateRole.description,
        Validators.compose([
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(250)
        ])
      ]
    });

    // Workaround since no other known method to enable or disable the ID-Textfield dynamically
    this.detailForm
      .get('id')
      .valueChanges.distinctUntilChanged()
      .subscribe(() => {
        if (this.detailMode === DetailMode.EDIT) {
          this.detailForm.get('id').disable();
        } else {
          this.detailForm.get('id').enable();
        }
      });
  }
}
