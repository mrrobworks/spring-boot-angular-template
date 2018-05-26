import { Directive, ElementRef, OnInit, Input } from '@angular/core';
import { AuthGroup } from '../models/authorization-types';
import { AuthorizationService } from '../services/authorization.service';

@Directive({
  selector: '[appShowIfPermission]'
})
export class ShowIfPermissionDirective implements OnInit {
  @Input() appShowIfPermission: AuthGroup;

  constructor(
    private elementRef: ElementRef,
    private authorizationService: AuthorizationService
  ) {}

  ngOnInit() {
    if (!this.authorizationService.hasPermission(this.appShowIfPermission)) {
      this.elementRef.nativeElement.style.display = 'none';
    }
  }
}
