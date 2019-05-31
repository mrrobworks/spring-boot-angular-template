import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { TemplateRoleRaw } from '../models/template-role-raw';
import { TemplateRole } from '../models/template-role';
import { catchError, map, retry } from 'rxjs/operators';
import { TemplateRoleFactory } from '../models/template-role-factory';
import { throwError } from 'rxjs/internal/observable/throwError';

@Injectable()
export class RoleService {
  private urlPrefix = '/backend/role';

  constructor(private http: HttpClient) {}

  private static errorHandler(error: Error | any): Observable<any> {
    return throwError(error);
  }

  getRole(id: String): Observable<TemplateRole> {
    return this.http.get<TemplateRoleRaw>(`${this.urlPrefix}/${id}`).pipe(
      retry(3),
      map(templateRoleRaw => TemplateRoleFactory.fromObject(templateRoleRaw)),
      catchError(RoleService.errorHandler)
    );
  }

  getRoles(): Observable<Array<TemplateRole>> {
    return this.http.get<TemplateRoleRaw[]>(`${this.urlPrefix}/list`).pipe(
      retry(3),
      map(templateRolesRaw =>
        templateRolesRaw.map(oneTemplateRoleRaw =>
          TemplateRoleFactory.fromObject(oneTemplateRoleRaw)
        )
      ),
      catchError(RoleService.errorHandler)
    );
  }

  addRole(templateRole: TemplateRole): Observable<TemplateRole> {
    return this.http
      .post<TemplateRoleRaw>(`${this.urlPrefix}/add`, templateRole)
      .pipe(catchError(RoleService.errorHandler));
  }

  updateRole(templateRole: TemplateRole): Observable<TemplateRole> {
    return this.http
      .put<TemplateRoleRaw>(
        `${this.urlPrefix}/${templateRole.id}`,
        templateRole
      )
      .pipe(catchError(RoleService.errorHandler));
  }

  deleteRole(id: string) {
    return this.http
      .delete<TemplateRoleRaw>(`${this.urlPrefix}/${id}`)
      .pipe(catchError(RoleService.errorHandler));
  }
}
