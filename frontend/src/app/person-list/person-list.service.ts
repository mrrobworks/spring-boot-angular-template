import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Person } from '../domain/person';
import { catchError, map, tap } from 'rxjs/operators';

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
    return this.http.get<Person[]>(url, {headers});
  }
}
