import { Injectable } from '@angular/core';
import { TemplateUser } from '../models/template-user';
import { Observable } from 'rxjs/Observable';
import { retry, map, catchError } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { TemplateUserRaw } from '../models/template-user-raw';
import { TemplateUserFactory } from '../models/template-user-factory';

@Injectable()
export class LoginService {
  constructor(private http: HttpClient) {}

  getTemplateUser(): Observable<TemplateUser> {
    return this.http
      .get<TemplateUserRaw>('/backend/user/info')
      .pipe(
        retry(3),
        map(rawTemplateUser => TemplateUserFactory.fromObject(rawTemplateUser))
      );
    //,
    //catchError(this.errorHandler)
    //);
  }

  logout(): Observable<any> {
    return this.http.post('logout', {});
  }

  private errorHandler(error: Error | any): Observable<any> {
    return Observable.throw(error);
  }
}
