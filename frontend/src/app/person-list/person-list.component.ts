import { Component, OnInit } from '@angular/core';
import { PersonListService} from '../person-list.service';
import { Observable } from 'rxjs/Observable';
import { Person } from '../domain/person';

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit {

  constructor(private personListService: PersonListService) { }

  ngOnInit() {
    this.personListService.load();
  }

  get persons() {
    return this.personListService.persons;
  }
}
