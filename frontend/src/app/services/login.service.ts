import { Injectable } from '@angular/core';
import { TemplateUser } from '../models/template-user';
import { Observable, throwError } from 'rxjs';
import { catchError, map, retry } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { TemplateUserRaw } from '../models/template-user-raw';
import { TemplateUserFactory } from '../models/template-user-factory';

@Injectable()
export class LoginService {
  private urlPrefix = '/backend/user';

  constructor(private http: HttpClient) {}

  private static errorHandler(error: Error | any): Observable<any> {
    return throwError(error);
  }

  authenticate(): Observable<TemplateUser> {
    return this.http.get<TemplateUserRaw>(`${this.urlPrefix}/info`).pipe(
      retry(3),
      map(rawTemplateUser => TemplateUserFactory.fromObject(rawTemplateUser)),
      catchError(LoginService.errorHandler)
    );
  }

  logout(): Observable<any> {
    return this.http.post('logout', {});
  }
}
