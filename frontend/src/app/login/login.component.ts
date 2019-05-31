import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { LoginService } from '../services/login.service';
import { SelectItem } from 'primeng/api';
import { finalize } from 'rxjs/operators';

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
    { label: 'Google', value: '/login/google', icon: 'fab fa-google' },
    { label: 'Github', value: '/login/github', icon: 'fab fa-github' }
  ];

  constructor(private loginService: LoginService) {}

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
          this.loginEvent.emit(true);
        },
        error => {
          console.log('Authentication fail.');
          this.loginEvent.emit(false);
        }
      );
  }
}
