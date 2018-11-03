import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { TemplateRole } from '../models/template-role';
import { RoleService } from '../services/role.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DetailMode } from '../models/detail-mode';
import { TemplateRoleFactory } from '../models/template-role-factory';

@Component({
  selector: 'app-role-detail',
  templateUrl: './role-detail.component.html'
})
export class RoleDetailComponent implements OnInit {
  @Output()
  detailActionDoneEvent = new EventEmitter<boolean>();
  form: FormGroup;
  header: string;
  statusMessage: string = undefined;
  saveSuccessful = undefined;
  detailMode: DetailMode;
  DetailMode = DetailMode;
  selectedTemplateRole: TemplateRole;

  constructor(private roleService: RoleService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.initComponent(DetailMode.UNDEFINED, TemplateRoleFactory.empty());
  }

  initComponent(detailMode: DetailMode, selectedTemplateRole: TemplateRole) {
    this.saveSuccessful = undefined;
    this.detailMode = detailMode;
    this.selectedTemplateRole = selectedTemplateRole;
    this.initDetailHeader();
    this.initDetailForm();
  }

  public initDetailHeader(): void {
    this.header =
      this.detailMode === DetailMode.NEW
        ? 'Create a new role'
        : 'Edit role with ID: ' + this.selectedTemplateRole.id;
  }

  public initDetailForm(): void {
    if (this.form !== undefined) {
      this.form.reset();
    }

    this.form = this.fb.group({
      id: [this.selectedTemplateRole.id, Validators.required],
      description: [
        this.selectedTemplateRole.description,
        Validators.compose([
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(250)
        ])
      ]
    });
  }

  save(model: TemplateRole) {
    if (this.detailMode === DetailMode.NEW) {
      this.roleService.addRole(model).subscribe(
        value => {},
        error => {
          this.saveSuccessful = false;
          this.statusMessage = `Failure. New Role not created. Reason: ${
            error.message
          }`;
        },
        () => {
          this.saveSuccessful = true;
          this.statusMessage = `New Role ${
            this.selectedTemplateRole.id
          } successfully created.`;
          this.detailActionDoneEvent.emit(this.saveSuccessful);
        }
      );
    } else if (this.detailMode === DetailMode.EDIT) {
      this.roleService.updateRole(model).subscribe(
        value => {},
        error => {
          this.saveSuccessful = false;
          this.statusMessage = `Failure. Role ${
            this.selectedTemplateRole.id
          } has not been changed. Reason: ${error.message}`;
        },
        () => {
          this.saveSuccessful = true;
          this.statusMessage = `Role ${
            this.selectedTemplateRole.id
          } successfully changed.`;
          this.detailActionDoneEvent.emit(this.saveSuccessful);
        }
      );
    }
  }

  close() {
    this.selectedTemplateRole = undefined;
    this.detailMode = undefined;
    this.detailActionDoneEvent.emit(this.saveSuccessful);
  }
}
