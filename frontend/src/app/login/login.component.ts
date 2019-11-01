import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {LoginService} from '../services/login.service';
import {SelectItem} from 'primeng/api';
import {finalize} from 'rxjs/operators';
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  @Output()
  private loginEvent = new EventEmitter<boolean>();

  private currentUser;

  selectedAccount: string;

  public loginAccounts: Array<SelectItem> = [
    {label: 'Google', value: '/login/google', icon: 'fab fa-google'},
    {label: 'Github', value: '/login/github', icon: 'fab fa-github'}
  ];

  constructor(private loginService: LoginService, private router: Router) {
  }

  ngOnInit() {
    this.authenticate();
    this.selectedAccount = this.loginAccounts[0].value;
  }

  authenticate() {
    this.loginService
      .authenticate()
      .pipe(
        finalize(() => {
          console.log('Authentication done.');
        })
      )
      .subscribe(
        templateUser => {
          console.log('Authentication success.');
          this.currentUser = templateUser;
          sessionStorage.setItem(
            'currentUser',
            JSON.stringify(this.currentUser)
          );
          // this.loginEvent.emit(true);
          this.receiveLoginEvent(true);
        },
        error => {
          console.log('Authentication fail.');
          this.loginEvent.emit(false);
        }
      );
  }

  receiveLoginEvent(success: boolean) {
    if (success) {
      this.currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
      // this.router.navigate(['/personlist']);
    } else {
      this.currentUser = null;
    }
    // this.navTopBarComponent.enableOrDisableComponents();
    this.router.navigate(['/']);
  }
}
