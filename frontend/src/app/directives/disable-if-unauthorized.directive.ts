import { Directive, ElementRef, OnInit, Input } from '@angular/core';
import {AuthGroup} from '../models/authorization-types';
import {AuthorizationService} from '../services/authorization.service';

@Directive({
  selector: '[appDisableIfUnauthorized]'
})
export class DisableIfUnauthorizedDirective implements OnInit {
  @Input('appDisableIfUnauthorized') permission: AuthGroup; // Required permission passed in
  constructor(private el: ElementRef, private authorizationService: AuthorizationService) { }
  ngOnInit() {
    if (!this.authorizationService.hasPermission(this.permission)) {
      this.el.nativeElement.disabled = true;
    }
  }
}
