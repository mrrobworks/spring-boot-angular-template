import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { throwError } from 'rxjs/internal/observable/throwError';
import { HttpClient } from '@angular/common/http';
import { catchError, map, retry } from 'rxjs/operators';
import { TemplateUserRaw } from '../models/template-user-raw';
import { TemplateUser } from '../models/template-user';
import { TemplateUserFactory } from '../models/template-user-factory';

@Injectable({
  providedIn: 'root'
})
@Injectable()
export class UserService {
  private urlPrefix = '/backend/user';

  constructor(private http: HttpClient) {}

  private static errorHandler(error: Error | any): Observable<any> {
    return throwError(error);
  }

  updateUser(templateUser: TemplateUser): Observable<TemplateUser> {
    return this.http
      .put<TemplateUserRaw>(
        `${this.urlPrefix}/${templateUser.id}`,
        templateUser
      )
      .pipe(catchError(UserService.errorHandler));
  }

  getUsers(): Observable<Array<TemplateUser>> {
    return this.http.get<TemplateUserRaw[]>(`${this.urlPrefix}/list`).pipe(
      retry(3),
      map(rawTemplateUsers =>
        rawTemplateUsers.map(oneRawTemplateUser =>
          TemplateUserFactory.fromObject(oneRawTemplateUser)
        )
      ),
      catchError(UserService.errorHandler)
    );
  }
}
