import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild
} from '@angular/core';
import { RoleService } from '../services/role.service';

@Component({
  selector: 'app-role-delete',
  templateUrl: './role-delete.component.html'
})
export class RoleDeleteComponent implements OnInit {
  @Input() templateRoleId: string;
  @ViewChild('disposeModal') disposeModal: ElementRef;
  @Output() reloadListEvent = new EventEmitter<any>();

  constructor(private roleService: RoleService) {}

  ngOnInit() {}

  deleteRole() {
    this.roleService.delete(this.templateRoleId).subscribe(
      value => {},
      error => {
        console.log(error.message);
        this.disposeModal.nativeElement.click();
        this.reloadListEvent.emit({
          errorMessage: error.message,
          deleteSuccess: false
        });
      },
      () => {
        this.disposeModal.nativeElement.click();
        this.reloadListEvent.emit({
          errorMessage: '',
          deleteSuccess: true
        });
      }
    );
  }
}
