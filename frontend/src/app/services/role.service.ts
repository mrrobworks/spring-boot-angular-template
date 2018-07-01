import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { TemplateRoleRaw } from '../models/template-role-raw';
import { TemplateRole } from '../models/template-role';
import { catchError, map, retry } from 'rxjs/operators';
import { TemplateRoleFactory } from '../models/template-role-factory';
import { throwError } from 'rxjs/internal/observable/throwError';

@Injectable()
export class RoleService {
  constructor(private http: HttpClient) {}

  getRole(id: String): Observable<TemplateRole> {
    return this.http
      .get<TemplateRoleRaw>(`/backend/role/${id}`)
      .pipe(
        retry(3),
        map(templateRoleRaw => TemplateRoleFactory.fromObject(templateRoleRaw)),
        catchError(this.errorHandler)
      );
  }

  getAllRoles(): Observable<Array<TemplateRole>> {
    return this.http
      .get<TemplateRoleRaw[]>('/backend/role/list')
      .pipe(
        retry(3),
        map(templateRolesRaw =>
          templateRolesRaw.map(oneTemplateRoleRaw =>
            TemplateRoleFactory.fromObject(oneTemplateRoleRaw)
          )
        ),
        catchError(this.errorHandler)
      );
  }

  add(templateRole: TemplateRole): Observable<TemplateRole> {
    return this.http
      .post<TemplateRoleRaw>('/backend/role/add', templateRole)
      .pipe(catchError(this.errorHandler));
  }

  update(templateRole: TemplateRole): Observable<TemplateRole> {
    return this.http
      .put<TemplateRoleRaw>(`/backend/role/${templateRole.id}`, templateRole)
      .pipe(catchError(this.errorHandler));
  }

  private errorHandler(error: Error | any): Observable<any> {
    return throwError(error);
  }
}
