import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  Output,
  ViewChild
} from '@angular/core';
import { RoleService } from '../services/role.service';

@Component({
  selector: 'app-role-delete',
  templateUrl: './role-delete.component.html'
})
export class RoleDeleteComponent {
  @Input() templateRoleId: string;
  @Output() deleteActionDoneEvent = new EventEmitter<any>();
  @ViewChild('disposeModal') disposeModal: ElementRef;

  constructor(private roleService: RoleService) {}

  deleteRole() {
    this.disposeModal.nativeElement.click();
    this.roleService.deleteRole(this.templateRoleId).subscribe(
      value => {},
      error => {
        this.deleteActionDoneEvent.emit({
          errorMessage: error.message,
          deleteSuccess: false
        });
      },
      () => {
        this.deleteActionDoneEvent.emit({
          errorMessage: '',
          deleteSuccess: true
        });
      }
    );
  }
}
