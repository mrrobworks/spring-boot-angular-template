import { AfterViewInit, Component, EventEmitter, Output } from '@angular/core';
import { UserService } from '../services/user.service';
import { TemplateUser } from '../models/template-user';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { TemplateRole } from '../models/template-role';
import { RoleService } from '../services/role.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html'
})
export class UserDetailComponent implements AfterViewInit {
  @Output() detailActionDoneEvent = new EventEmitter<boolean>();
  selectedTemplateUser: TemplateUser;
  form: FormGroup;
  templateRoles: TemplateRole[] = undefined;
  statusMessage: string = undefined;
  saveSuccessful = undefined;

  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private roleService: RoleService
  ) {}

  ngAfterViewInit(): void {
    //document.getElementById('preloader').classList.add('hide');
    // jQuery('select').selectpicker();
    /*jQuery('select').selectpicker({
      style: 'btn form-control btn-sm',
    });*/

    this.roleService
      .getRoles()
      .subscribe(templateRoles => (this.templateRoles = templateRoles));
  }

  initComponent(selectedTemplateUser: TemplateUser) {
    //document.getElementById('preloader').classList.add('hide');
    //jQuery('select').selectpicker();
    this.saveSuccessful = undefined;
    this.selectedTemplateUser = selectedTemplateUser;
    this.initDetailForm();
  }

  public initDetailForm(): void {
    if (this.form !== undefined) {
      //this.form.reset();
    }

    this.form = this.fb.group({
      id: new FormControl(
        { value: this.selectedTemplateUser.id, disabled: true },
        Validators.required
      ),
      roles: [this.selectedTemplateUser.roles]
    });
  }

  save(model: TemplateUser) {
    this.userService.updateUser(model).subscribe(
      value => {},
      error => {
        this.saveSuccessful = false;
        this.statusMessage = `Failure. User ${
          this.selectedTemplateUser.id
        } has not been changed. Reason: ${error.message}`;
        console.log(this.statusMessage);
      },
      () => {
        this.saveSuccessful = true;
        this.statusMessage = `User ${
          this.selectedTemplateUser.id
        } successfully changed.`;
        this.detailActionDoneEvent.emit(this.saveSuccessful);
        console.log(this.statusMessage);
      }
    );
  }

  close() {
    this.selectedTemplateUser = undefined;
  }

  hasRole(role: TemplateRole): TemplateRole {
    const templateRole = this.templateRoles.includes(role) ? role : undefined;
    return role;
  }

  compareFn(role1: TemplateRole, role2: TemplateRole): boolean {
    if (role1 === undefined || role2 === undefined) {
      return false;
    }
    //return role1 && role2 ? role1.id === role2.id : role1 === role2;
    return role1.id === role2.id;
  }
}
