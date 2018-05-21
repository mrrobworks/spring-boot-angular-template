import { Component, OnInit } from '@angular/core';
import { PersonListService } from '../services/person-list.service';
import { Observable } from 'rxjs/Observable';
import { Person } from '../models/person';
import {TemplateUser} from "../models/template-user";

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html'
})
export class PersonListComponent implements OnInit {
  currentUser : TemplateUser;

  constructor(private personListService: PersonListService) {}


  ngOnInit() {
    this.currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
    this.personListService.load();
  }

  get persons() {
    return this.personListService.persons;
  }
}
