import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { UserService } from '../services/user.service';
import { TemplateUser } from '../models/template-user';
import { UserDetailComponent } from '../user-detail/user-detail.component';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit {
  // @ViewChild(UserDetailComponent) userDetailComponent;

  private userDetailComponent: UserDetailComponent;

  @ViewChild(UserDetailComponent, { static: true })
  set content(userDetailComponent: UserDetailComponent) {
    this.userDetailComponent = userDetailComponent;
  }

  templateUsers: TemplateUser[];

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.getAllUsers();
  }

  getAllUsers() {
    return this.userService
      .getUsers()
      .subscribe(templateUsers => (this.templateUsers = templateUsers));
  }

  openUserDetail(templateUser: TemplateUser) {
    this.userDetailComponent.initComponent(templateUser);
  }

  detailActionDone(event: boolean) {
    if (event) {
      this.getAllUsers();
    }
  }
}
