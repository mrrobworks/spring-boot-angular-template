import {Injectable} from '@angular/core';
import {TemplateUser} from "../models/template-user";
import {Observable} from "rxjs/Observable";
import {retry, catchError} from 'rxjs/operators';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) {
  }

  getTemplateUser(): Observable<TemplateUser> {
    return this.http
    .get<TemplateUser>('/backend/user/info')
    .pipe(retry(3),
      catchError(this.errorHandler));
  }

  logout(): Observable<any> {
    return this.http.post('logout', {});
  }

  private errorHandler(error: Error | any): Observable<any> {
    return Observable.throw(error);
  }
}
