import { Component, Inject, OnInit } from '@angular/core';
import { TemplateRole } from '../models/template-role';
import { RoleService } from '../services/role.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { FormBuilder, FormGroup } from '@angular/forms';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-role-detail',
  templateUrl: './role-detail.component.html'
})
export class RoleDetailComponent implements OnInit {
  description: string;
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private roleService: RoleService,
    public dialogRef: MatDialogRef<RoleDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public templateRole: TemplateRole
  ) {}

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
    this.roleService.update(this.templateRole).subscribe();
  }

  close() {
    this.dialogRef.close();
  }
}
