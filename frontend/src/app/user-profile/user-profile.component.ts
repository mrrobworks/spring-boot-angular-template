import { Component, Input, OnInit } from '@angular/core';
import { TemplateUser } from '../models/template-user';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html'
})
export class UserProfileComponent implements OnInit {
  @Input() currentUser: TemplateUser;

  constructor() {}

  ngOnInit() {}
}
