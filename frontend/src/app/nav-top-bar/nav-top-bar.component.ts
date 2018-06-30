import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-nav-top-bar',
  templateUrl: './nav-top-bar.component.html',
  styles: [
    `.dropdown-menu a {
    color: #ff0000 !important;
  }`
  ]
})
export class NavTopBarComponent implements OnInit {
  @Output() authenticated = new EventEmitter<boolean>();
  @Input() auth: boolean;
  currentUser = JSON.parse(sessionStorage.getItem('currentUser'));

  constructor(private loginService: LoginService) {}

  ngOnInit() {}

  logout() {
    this.loginService
      .logout()
      .finally(() => {
        this.auth = false;
        this.authenticated.emit(this.auth);
        sessionStorage.setItem('currentUser', undefined);
      })
      .subscribe();
  }
}
