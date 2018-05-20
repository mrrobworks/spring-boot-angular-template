import {Component, EventEmitter, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {TemplateUser} from "../models/template-user";
import 'rxjs/add/operator/finally';
import {Output} from "@angular/core";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  auth = false;
  @Output() authenticated = new EventEmitter<boolean>();
  greeting = {};
  templateUser: TemplateUser;

  constructor(private router: Router, private route: ActivatedRoute, private http: HttpClient) {
  }

  ngOnInit() {
    this.authenticate();
  }

  authenticate() {
    this.http.get<TemplateUser>('/backend/user/info').finally(() => {
      this.authenticated.emit(this.auth);
    })
    .subscribe(response => {
      this.templateUser = response;
      // if (response['name']) {
      if (response['id']) {
        this.auth = true;
        this.http.get('resource').subscribe(data => this.greeting = data);
      } else {
        this.auth = false;
      }
    }, () => {
      this.auth = false;
    });
  }

  /*login() {
    this.router.navigate(['login']);
  }*/

  logout() {
    this.http.post('logout', {}).finally(() => {
      this.auth = false;
      this.authenticated.emit(this.auth);
    }).subscribe();
  }
}
