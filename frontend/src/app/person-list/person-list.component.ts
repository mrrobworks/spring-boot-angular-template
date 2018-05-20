import {Component, OnInit} from '@angular/core';
import {PersonListService} from '../services/person-list.service';
import {Observable} from 'rxjs/Observable';
import {Person} from '../models/person';

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html'
})
export class PersonListComponent implements OnInit {

  constructor(private personListService: PersonListService) {
  }

  ngOnInit() {
    this.personListService.load();
  }

  get persons() {
    return this.personListService.persons;
  }
}
