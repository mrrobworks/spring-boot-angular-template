import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { TemplateRole } from '../models/template-role';
import { RoleService } from '../services/role.service';
import { DetailMode } from '../models/detail-mode';
import { RoleDetailComponent } from '../role-detail/role-detail.component';
import { RoleDeleteComponent } from '../role-delete/role-delete.component';
import { AgGridBase } from '../util/ag-grid-base';
import { ColDef, GridApi } from 'ag-grid-community';
import { TemplateRoleFactory } from '../models/template-role-factory';

@Component({
  selector: 'app-role-list',
  templateUrl: './role-list.component.html'
})
export class RoleListComponent implements OnInit {
  @ViewChild(RoleDetailComponent, { static: true })
  roleDetailComponent;

  @ViewChild(RoleDeleteComponent, { static: true })
  roleDeleteComponent;

  @ViewChild('divAgGrid', { static: true })
  private divAgGrid: ElementRef;

  @ViewChild('btnEdit', { static: true })
  private btnEdit: ElementRef;

  DetailMode = DetailMode;
  templateRoles: TemplateRole[];
  deleteSuccessful: boolean;
  statusMessage: string;

  agGridRoleList;

  constructor(private roleService: RoleService) {}

  ngOnInit() {
    this.agGridRoleList = new (class extends AgGridBase {
      constructor(private roleListComponent: RoleListComponent) {
        super(roleListComponent.divAgGrid);
      }

      createColumnDefs(): ColDef[] {
        return [
          { headerName: 'ID', field: 'id', checkboxSelection: true },
          { headerName: 'Description', field: 'description' }
        ];
      }

      // TODO: api: GridApi set in constructor?
      onGridReady(api: GridApi): void {
        this.api = api;
        this.roleListComponent.loadRoleList();
      }

      onRowDoubleClicked(): void {
        this.roleListComponent.btnEdit.nativeElement.click();
      }
    })(this);
  }

  private loadRoleList() {
    this.roleService
      .getRoles()
      .subscribe(templateRoles => (this.templateRoles = templateRoles));
  }

  private resetAlertMessages() {
    this.deleteSuccessful = undefined;
  }

  openRoleDetailDialog(detailMode: DetailMode) {
    this.resetAlertMessages();
    if (detailMode === DetailMode.NEW) {
      this.roleDetailComponent.initComponent(
        detailMode,
        TemplateRoleFactory.empty()
      );
    } else if (detailMode === DetailMode.EDIT) {
      this.roleDetailComponent.initComponent(
        detailMode,
        this.agGridRoleList.api.getSelectedRows()[0] as TemplateRole
      );
    }
  }

  openRoleDeleteDialog() {
    this.resetAlertMessages();
    const selectedTemplateRole = this.agGridRoleList.api.getSelectedRows()[0] as TemplateRole;
    this.roleDeleteComponent.initComponent(selectedTemplateRole.id);
  }

  detailActionDone(event: boolean) {
    if (event) {
      this.loadRoleList();
    }
  }

  deleteActionDone(event: any) {
    this.deleteSuccessful = event.deleteSuccessful;
    if (this.deleteSuccessful) {
      this.loadRoleList();
    }
    this.statusMessage = event.statusMessage;
  }
}
