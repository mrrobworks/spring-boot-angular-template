import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { TemplateRole } from '../models/template-role';
import { RoleService } from '../services/role.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DetailMode } from '../models/detail-mode';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-role-detail',
  templateUrl: './role-detail.component.html'
})
export class RoleDetailComponent implements OnInit {
  form: FormGroup;
  header: string;
  errorMsg: string = undefined;
  roleSavedSuccessful = undefined;
  detailMode: DetailMode;
  DetailMode = DetailMode;
  selectedTemplateRole: TemplateRole;
  @Output() detailActionDoneEvent = new EventEmitter<boolean>();

  constructor(private roleService: RoleService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.roleService.initRoleDetailComponentSubject
      .pipe(
        tap(value => {
          this.roleSavedSuccessful = undefined;
          this.detailMode = value.detailMode;
          this.selectedTemplateRole = value.selectedTemplateRole;
          this.initDetailHeader();
          this.initDetailForm();
        })
      )
      .subscribe();
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
          this.errorMsg = error.toString();
          this.roleSavedSuccessful = false;
        },
        () => {
          this.roleSavedSuccessful = true;
          this.detailActionDoneEvent.emit(this.roleSavedSuccessful);
        }
      );
    } else if (this.detailMode === DetailMode.EDIT) {
      this.roleService.updateRole(model).subscribe(
        value => {},
        error => {
          this.errorMsg = error.toString();
          this.roleSavedSuccessful = false;
        },
        () => {
          this.roleSavedSuccessful = true;
          this.detailActionDoneEvent.emit(this.roleSavedSuccessful);
        }
      );
    }
  }

  close() {
    this.detailActionDoneEvent.emit(this.roleSavedSuccessful);
  }
}
