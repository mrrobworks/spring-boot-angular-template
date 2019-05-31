import { Component, OnInit, ViewChild } from '@angular/core';
import { TemplateUser } from './models/template-user';
import { NavTopBarComponent } from './nav-top-bar/nav-top-bar.component';
import { LoginComponent } from './login/login.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  @ViewChild(NavTopBarComponent, { static: true })
  private navTopBarComponent: NavTopBarComponent;

  @ViewChild(LoginComponent, { static: false })
  private loginComponent: LoginComponent;

  currentUser: TemplateUser;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
  }

  receiveLoginEvent(success: boolean) {
    if (success) {
      this.currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
      this.router.navigate(['/personlist']);
    } else {
      this.currentUser = null;
    }
    this.navTopBarComponent.enableOrDisableComponents();
  }

  receiveLogoutEvent() {
    this.currentUser = null;
    this.navTopBarComponent.enableOrDisableComponents();
  }
}
