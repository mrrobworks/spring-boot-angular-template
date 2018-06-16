import { Component, OnInit } from '@angular/core';
import { PersonListService } from '../services/person-list.service';
import { TemplateUser } from '../models/template-user';
import { Person } from '../models/person';

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html'
})
export class PersonListComponent implements OnInit {
  currentUser: TemplateUser;
  persons: Person[];

  constructor(private personListService: PersonListService) {}

  ngOnInit() {
    this.currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
    this.personListService.load();
    this.personListService
      .findAllPersons()
      .subscribe(persons => (this.persons = persons));
  }
}
