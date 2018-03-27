import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { HttpClient } from '@angular/common/http';
import { Person } from '../domain/person';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  title = 'Demo';
  persons: Person[] = [];

  constructor(private app: AppService, private http: HttpClient) {
    http
      .get<Person[]>('http://localhost:8081/springbootangular/findallpersons')
      .subscribe(data => (this.persons = data));
  }

  authenticated() {
    return this.app.authenticated;
  }
}
