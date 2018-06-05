import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { TemplateRoleRaw } from '../models/template-role-raw';
import { TemplateRole } from '../models/template-role';
import { map, retry } from 'rxjs/operators';
import { TemplateRoleFactory } from '../models/template-role-factory';

@Injectable()
export class RoleService {
  constructor(private http: HttpClient) {}

  getRole(id: String): Observable<TemplateRole> {
    return this.http
      .get<TemplateRoleRaw>(`/backend/role/${id}`)
      .pipe(
        retry(3),
        map(templateRoleRaw => TemplateRoleFactory.fromObject(templateRoleRaw))
      );
    // TODO: catchError(...)
  }

  getAllRoles(): Observable<Array<TemplateRole>> {
    return this.http.get<TemplateRoleRaw[]>('/backend/role/list').pipe(
      retry(3),
      map(templateRolesRaw =>
        templateRolesRaw.map(oneTemplateRoleRaw =>
          TemplateRoleFactory.fromObject(oneTemplateRoleRaw)
        )
      )
      // TODO: catchError(...)
    );
  }

  update(templateRole: TemplateRole): Observable<TemplateRole> {
    return this.http.put<TemplateRoleRaw>(
      `/backend/role/${templateRole.id}`,
      templateRole
      // TODO: catchError(...)
    );
  }
}
