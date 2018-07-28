import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { TemplateRoleRaw } from '../models/template-role-raw';
import { TemplateRole } from '../models/template-role';
import { catchError, map, retry } from 'rxjs/operators';
import { TemplateRoleFactory } from '../models/template-role-factory';
import { throwError } from 'rxjs/internal/observable/throwError';
import { Subject } from 'rxjs/Subject';
import { DetailMode } from '../models/detail-mode';

@Injectable()
export class RoleService {
  private urlPrefix = '/backend/role';

  public initRoleDetailComponentSubject = new Subject<any>();

  constructor(private http: HttpClient) {}

  private errorHandler(error: Error | any): Observable<any> {
    const errMsg = error.message
      ? error.message
      : error.status
        ? `${error.status} - ${error.statusText}`
        : 'Server error';
    return throwError(error);
  }

  initRoleDetailComponent(
    detailMode: DetailMode,
    selectedTemplateRole: TemplateRole
  ) {
    this.initRoleDetailComponentSubject.next({
      detailMode: detailMode,
      selectedTemplateRole: selectedTemplateRole
    });
  }

  getRole(id: String): Observable<TemplateRole> {
    return this.http
      .get<TemplateRoleRaw>(`${this.urlPrefix}/${id}`)
      .pipe(
        retry(3),
        map(templateRoleRaw => TemplateRoleFactory.fromObject(templateRoleRaw)),
        catchError(this.errorHandler)
      );
  }

  getRoles(): Observable<Array<TemplateRole>> {
    return this.http
      .get<TemplateRoleRaw[]>(`${this.urlPrefix}/list`)
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

  addRole(templateRole: TemplateRole): Observable<TemplateRole> {
    return this.http
      .post<TemplateRoleRaw>(`${this.urlPrefix}/add`, templateRole)
      .pipe(catchError(this.errorHandler));
  }

  updateRole(templateRole: TemplateRole): Observable<TemplateRole> {
    return this.http
      .put<TemplateRoleRaw>(
        `${this.urlPrefix}/${templateRole.id}`,
        templateRole
      )
      .pipe(catchError(this.errorHandler));
  }

  deleteRole(id: string) {
    return this.http
      .delete<TemplateRoleRaw>(`${this.urlPrefix}/${id}`)
      .pipe(catchError(this.errorHandler));
  }
}
