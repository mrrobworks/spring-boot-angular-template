import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/finally';
import {TemplateUser} from './models/template-user';

import {Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  title = 'Spring Boot 2 + Angular 5 Template';
  auth = false;
  currentUser: TemplateUser = undefined;

  ngOnInit(): void {
    this.currentUser = (this.auth == true)
      ? JSON.parse(sessionStorage.getItem('currentUser'))
      : undefined;
  }

  authenticated(auth: boolean) {
    this.auth = auth;
  }
}
