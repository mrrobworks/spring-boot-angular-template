import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { TemplateUser } from '../models/template-user';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit {
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
}
