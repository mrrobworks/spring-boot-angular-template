import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Person } from '../models/person';

@Injectable()
export class PersonListService {
  constructor(private http: HttpClient) {}

  persons: Person[] = [];

  load(): void {
    this.findAllPersons().subscribe(
      persons => {
        this.persons = persons;
      },
      err => {
        console.error('error loading persons', err);
      }
    );
  }

  findAllPersons(): Observable<Person[]> {
    const url = '/backend/person/findallpersons';
    const headers = new HttpHeaders().set('Accept', 'application/json');
    return this.http.get<Person[]>(url, { headers });
  }
}
