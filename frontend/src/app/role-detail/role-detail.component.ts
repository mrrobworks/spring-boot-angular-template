import { Component, Input, OnInit } from '@angular/core';
import { TemplateRole } from '../models/template-role';
import { RoleService } from '../services/role.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { map } from 'rxjs/operators';
import { DetailMode } from '../models/detail-mode';

@Component({
  selector: 'app-role-detail',
  templateUrl: './role-detail.component.html'
})
export class RoleDetailComponent implements OnInit {
  form: FormGroup;
  @Input() templateRole: TemplateRole;
  @Input() detailMode: DetailMode;
  DetailMode = DetailMode;

  constructor(private fb: FormBuilder, private roleService: RoleService) {}

  ngOnInit() {
    this.form = this.fb.group({
      description: [this.templateRole.description]
    });

    this.form.valueChanges
      .pipe(
        // TODO: Validation with filter(() => this.form.valid)
        map(form => new TemplateRole(this.templateRole.id, form.description))
      )
      .subscribe(templateRole => (this.templateRole = templateRole));
  }

  header(): string {
    if (this.detailMode === DetailMode.NEW) {
      return 'Create a new role';
    } else {
      return 'Edit role with ID: ' + this.templateRole.id;
    }
  }

  save() {
    if (this.detailMode === DetailMode.NEW) {
      this.roleService.add(this.templateRole).subscribe();
    } else {
      this.roleService.update(this.templateRole).subscribe();
    }
    this.roleService.getAllRoles().subscribe();
  }

  close() {
    this.roleService.getAllRoles().subscribe();
  }
}
