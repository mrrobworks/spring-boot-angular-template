import { Component, OnInit } from '@angular/core';
import { TemplateRole } from '../models/template-role';
import { RoleService } from '../services/role.service';

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html'
})
export class RoleListComponent implements OnInit {
  templateRoles: TemplateRole[];

  constructor(private roleService: RoleService) {}

  ngOnInit() {
    this.roleService
      .getAllRoles()
      .subscribe(templateRoles => (this.templateRoles = templateRoles));
  }
}
