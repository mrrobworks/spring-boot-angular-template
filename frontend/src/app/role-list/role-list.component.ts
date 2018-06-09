import { Component, OnInit } from '@angular/core';
import { TemplateRole } from '../models/template-role';
import { RoleService } from '../services/role.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { RoleDetailComponent } from '../role-detail/role-detail.component';
import { DataSource } from '@angular/cdk/table';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html'
})
export class RoleListComponent implements OnInit {
  templateRoles: TemplateRole[];
  dataSource = new RoleListDataSource(this.roleService);
  displayedColumns = ['id', 'description', 'actions'];

  constructor(private roleService: RoleService, private dialog: MatDialog) {}

  ngOnInit() {
    this.initAllRoles();
  }

  openDialog(templateRole: TemplateRole) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '350px';
    dialogConfig.height = '350px';
    dialogConfig.data = templateRole;

    const dialogRef = this.dialog.open(RoleDetailComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(() => this.initAllRoles());
  }

  private initAllRoles() {
    return this.roleService
      .getAllRoles()
      .subscribe(templateRoles => (this.templateRoles = templateRoles));
  }
}

export class RoleListDataSource extends DataSource<any> {
  constructor(private roleService: RoleService) {
    super();
  }

  connect(): Observable<TemplateRole[]> {
    return this.roleService.getAllRoles();
  }

  disconnect() {}
}
