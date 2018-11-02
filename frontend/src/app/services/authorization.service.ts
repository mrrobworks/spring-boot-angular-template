import { Injectable } from '@angular/core';
import { AuthGroup } from '../models/authorization-types';
import { TemplateUser } from '../models/template-user';

@Injectable()
export class AuthorizationService {
  constructor() {}

  hasPermission(authGroup: AuthGroup) {
    const currentUser: TemplateUser = JSON.parse(
      sessionStorage.getItem('currentUser')
    );

    if (
      currentUser.roles &&
      currentUser.roles.find(role => {
        return role.id === authGroup;
      })
    ) {
      return true;
    }
    return false;
  }

  isLoggedIn(): boolean {
    return sessionStorage.getItem('currentUser') !== null;
  }
}
