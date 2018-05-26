import { Component, EventEmitter, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import 'rxjs/add/operator/finally';
import { Output } from '@angular/core';
import { LoginService } from '../services/login.service';
import { TemplateUserFactory } from '../models/template-user-factory';
import { AuthorizationService } from '../services/authorization.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  auth = false;
  @Output() authenticated = new EventEmitter<boolean>();
  templateUser = TemplateUserFactory.empty();

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private loginService: LoginService,
    private authService: AuthorizationService
  ) {}

  ngOnInit() {
    this.authenticate();
  }

  authenticate() {
    this.loginService
      .getTemplateUser()
      .finally(() => {
        this.authenticated.emit(this.auth);
      })
      .subscribe(
        templateUser => {
          this.templateUser = templateUser;
          this.auth = templateUser['id'] ? true : false;
          if (this.auth) {
            sessionStorage.setItem(
              'currentUser',
              JSON.stringify(this.templateUser)
            );
            this.authService.initializePermissions();
          }
        },
        () => {
          this.auth = false;
        }
      );
  }

  logout() {
    this.loginService
      .logout()
      .finally(() => {
        this.auth = false;
        this.authenticated.emit(this.auth);
      })
      .subscribe();
  }
}
