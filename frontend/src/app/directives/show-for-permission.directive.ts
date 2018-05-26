import { Directive, ElementRef, OnInit, Input } from '@angular/core';
import { AuthGroup } from '../models/authorization-types';
import { AuthorizationService } from '../services/authorization.service';

@Directive({
  selector: '[appShowForPermission]'
})
export class ShowForPermissionDirective implements OnInit {
  @Input() appShowForPermission: AuthGroup;

  constructor(
    private elementRef: ElementRef,
    private authorizationService: AuthorizationService
  ) {}

  ngOnInit() {
    if (!this.authorizationService.hasPermission(this.appShowForPermission)) {
      this.elementRef.nativeElement.style.display = 'none';
    }
  }
}
