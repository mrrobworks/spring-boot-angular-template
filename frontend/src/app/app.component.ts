import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/finally';
import {TemplateUser} from './domain/template-user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'Spring Boot 2 + Angular 5 Template';
  authenticated = false;
  greeting = {};
  templateUser: TemplateUser;

  constructor(private http: HttpClient) {
    this.authenticate();
  }

  authenticate() {
    this.http.get<TemplateUser>('/backend/user/info').subscribe(response => {
      this.templateUser = response;
      // if (response['name']) {
      if (response['id']) {
        this.authenticated = true;
        this.http.get('resource').subscribe(data => this.greeting = data);
      } else {
        this.authenticated = false;
      }
    }, () => {
      this.authenticated = false;
    });
  }

  logout() {
    this.http.post('logout', {}).finally(() => {
      this.authenticated = false;
    }).subscribe();
  }
}
