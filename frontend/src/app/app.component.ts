import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/finally';
import {TemplateUser} from './models/template-user';

import {Router, ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  title = 'Spring Boot 2 + Angular 5 Template';
  auth: boolean = false;

  authenticated(auth: boolean) {
    this.auth = auth;
  }
}
