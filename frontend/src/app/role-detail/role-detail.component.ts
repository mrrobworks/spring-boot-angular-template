import { Component, Input } from '@angular/core';
import { TemplateRole } from '../models/template-role';
import { RoleService } from '../services/role.service';
import { FormGroup } from '@angular/forms';
import { DetailMode } from '../models/detail-mode';
import { Router } from '@angular/router';

@Component({
  selector: 'app-role-detail',
  templateUrl: './role-detail.component.html'
})
export class RoleDetailComponent {
  @Input() detailMode: DetailMode;
  @Input() form: FormGroup;
  @Input() header: string;
  errorMsg: string = undefined;
  success = undefined;

  constructor(private roleService: RoleService, private router: Router) {}

  save(model: TemplateRole) {
    if (this.detailMode === DetailMode.NEW) {
      this.roleService.add(model).subscribe();
    } else {
      this.roleService.update(model).subscribe(
        value => {},
        error => {
          this.errorMsg = error.toString();
          this.success = false;
        },
        () => {
          this.success = true;
        }
      );
    }
  }

  close() {
    this.roleService.getAllRoles().subscribe();
  }
}
