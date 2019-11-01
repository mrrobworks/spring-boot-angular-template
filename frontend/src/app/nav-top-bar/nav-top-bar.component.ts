import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {LoginService} from '../services/login.service';
import {TemplateUser} from '../models/template-user';
import {finalize} from 'rxjs/operators';

@Component({
  selector: 'app-nav-top-bar',
  templateUrl: './nav-top-bar.component.html',
  styles: [
      `
          .dropdown-menu a {
              color: #ff0000 !important;
          }
    `
  ]
})
export class NavTopBarComponent implements OnInit {
  @Output()
  private logoutEvent = new EventEmitter<boolean>();

  currentUser: TemplateUser;

  constructor(private loginService: LoginService) {
  }

  ngOnInit(): void {
    console.log('ngOnInit()');
    this.enableOrDisableComponents();
  }

  enableOrDisableComponents() {
    this.currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
  }

  logout() {
    this.loginService
      .logout()
      .pipe(
        finalize(() => {
          if (sessionStorage.getItem('currentUser') !== null) {
            sessionStorage.removeItem('currentUser');
          }
          this.logoutEvent.emit();
          console.log('Benutzer wurde erfolgreich abgemeldet.');
        })
      )
      .subscribe();
  }
}
