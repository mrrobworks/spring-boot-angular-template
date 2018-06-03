import { Component, OnInit } from '@angular/core';
import { PersonListService } from '../services/person-list.service';
import { TemplateUser } from '../models/template-user';
import { Person } from '../models/person';
import { DataSource } from '@angular/cdk/table';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html'
})
export class PersonListComponent implements OnInit {
  currentUser: TemplateUser;
  persons: Person[];
  dataSource = new PersonListDataSource(this.personListService);
  displayedColumns = ['id', 'firstname', 'lastname'];

  constructor(private personListService: PersonListService) {}

  ngOnInit() {
    this.currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
    this.personListService.load();
    this.personListService
      .findAllPersons()
      .subscribe(persons => (this.persons = persons));
  }
}

export class PersonListDataSource extends DataSource<any> {
  constructor(private personListService: PersonListService) {
    super();
  }

  connect(): Observable<Person[]> {
    return this.personListService.findAllPersons();
  }

  disconnect() {}
}
