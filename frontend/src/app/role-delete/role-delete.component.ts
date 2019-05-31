import {
  Component,
  ElementRef,
  EventEmitter,
  Output,
  ViewChild
} from '@angular/core';
import { RoleService } from '../services/role.service';

@Component({
  selector: 'app-role-delete',
  templateUrl: './role-delete.component.html'
})
export class RoleDeleteComponent {
  @Output() deleteActionDoneEvent = new EventEmitter<any>();
  @ViewChild('disposeModal', { static: true }) disposeModal: ElementRef;
  templateRoleId: string;

  constructor(private roleService: RoleService) {}

  initComponent(templateRoleId: string) {
    this.templateRoleId = templateRoleId;
  }

  deleteRole() {
    this.disposeModal.nativeElement.click();
    this.roleService.deleteRole(this.templateRoleId).subscribe(
      value => {},
      error => {
        this.deleteActionDoneEvent.emit({
          statusMessage: `Failure. Role ${
            this.templateRoleId
          } not deleted. Reason: ${error.message}`,
          deleteSuccessful: false
        });
      },
      () => {
        this.deleteActionDoneEvent.emit({
          statusMessage: `Role ${this.templateRoleId} successfully deleted.`,
          deleteSuccessful: true
        });
      }
    );
  }
}
