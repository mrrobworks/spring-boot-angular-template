import {Injectable} from '@angular/core';
import {TemplateUser} from '../models/template-user';
import {AuthGroup} from '../models/authorization-types';

//import { AuthorizationDataService } from './authorization-data.service';

@Injectable()
export class AuthorizationService {
  //appShowIfPermission: Array<string>; // Store the actions for which this user has permission
  permissions: string[] = [];
  currentUser: TemplateUser;

  //constructor(private authorizationDataService: AuthorizationDataService) { }

  constructor() {

  }

  hasPermission(authGroup: AuthGroup) {
    if (this.permissions && this.permissions.find(permission => {
      return permission === authGroup;
    })) {
      return true;
    }
    return false;
  }

  initializePermissions() {
    this.currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
    for (const role of this.currentUser.roles) {
      console.info("Authority: ####" + role.authority);
      this.permissions.push(role.authority);
    }
  }

  /*
  // This method is called once and a list of appShowIfPermission is stored in the appShowIfPermission property
  initializePermissions() {
    return new Promise((resolve, reject) => {
      // Call API to retrieve the list of actions this user is permitted to perform.
      // In this case, the method returns a Promise, but it could have been implemented as an Observable
      this.authorizationDataService.getPermissions()
      .then(appShowIfPermission => {
        this.appShowIfPermission = appShowIfPermission;
        resolve();
      })
      .catch((e) => {
        reject(e);
      });
    });
  }*/
}
