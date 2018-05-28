import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TemplateRoleRaw } from '../models/template-role-raw';
import { TemplateRole } from '../models/template-role';
import { map, retry } from 'rxjs/operators';
import { TemplateRoleFactory } from '../models/template-role-factory';

@Injectable()
export class RoleService {
  constructor(private http: HttpClient) {}

  getAllRoles(): Observable<Array<TemplateRole>> {
    const url = '/backend/role/list';
    // TODO: map...
    return this.http.get<TemplateRole[]>(url).pipe(
      retry(3),
      map(templateRolesRaw =>
        templateRolesRaw.map(oneTemplateRoleRaw =>
          TemplateRoleFactory.fromObject(oneTemplateRoleRaw)
        )
      )
      // TODO: catchError(...)
    );
  }
}
