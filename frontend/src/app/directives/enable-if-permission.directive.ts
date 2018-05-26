import { Directive, ElementRef, OnInit, Input } from '@angular/core';
import { AuthGroup } from '../models/authorization-types';
import { AuthorizationService } from '../services/authorization.service';

@Directive({
  selector: '[appEnableIfPermission]'
})
export class EnableIfPermissionDirective implements OnInit {
  @Input() appEnableIfPermission: AuthGroup;

  constructor(
    private elementRef: ElementRef,
    private authorizationService: AuthorizationService
  ) {}

  ngOnInit() {
    if (!this.authorizationService.hasPermission(this.appEnableIfPermission)) {
      this.elementRef.nativeElement.disabled = true;
    }
  }
}
