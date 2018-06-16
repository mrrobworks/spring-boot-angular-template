import {Component, Input, OnInit} from '@angular/core';
import {TemplateRole} from '../models/template-role';
import {RoleService} from '../services/role.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-role-detail',
  templateUrl: './role-detail.component.html'
})
export class RoleDetailComponent implements OnInit {
  description: string;
  form: FormGroup;
  @Input() templateRole: TemplateRole;

  constructor(private fb: FormBuilder, private roleService: RoleService) {
  }

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

  update() {
    console.info('----------------------------------------');
    console.info(this.templateRole.description);
    this.roleService.update(this.templateRole).subscribe();
  }

  close() {
    console.info('----------------------------------------close');
    this.roleService.getAllRoles().subscribe();
  }
}
