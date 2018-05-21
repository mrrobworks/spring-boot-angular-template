import {Directive, ElementRef, OnInit, Input} from '@angular/core';
import {AuthGroup} from '../models/authorization-types';
import {AuthorizationService} from '../services/authorization.service';

@Directive({
  selector: '[appHideIfUnauthorized]'
})
export class HideIfUnauthorizedDirective implements OnInit {
  @Input('appHideIfUnauthorized') permission: AuthGroup; // Required permission passed in
  constructor(private el: ElementRef, private authorizationService: AuthorizationService) {
  }

  ngOnInit() {
    if (!this.authorizationService.hasPermission(this.permission)) {
      this.el.nativeElement.style.display = 'none';
    }
  }
}
