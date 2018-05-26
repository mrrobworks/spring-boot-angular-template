import { Directive, ElementRef, OnInit, Input } from '@angular/core';
import { AuthGroup } from '../models/authorization-types';
import { AuthorizationService } from '../services/authorization.service';

@Directive({
  selector: '[appEnableForPermission]'
})
export class EnableForPermissionDirective implements OnInit {
  @Input() appEnableForPermission: AuthGroup;

  constructor(
    private elementRef: ElementRef,
    private authorizationService: AuthorizationService
  ) {}

  ngOnInit() {
    if (!this.authorizationService.hasPermission(this.appEnableForPermission)) {
      this.elementRef.nativeElement.disabled = true;
    }
  }
}
