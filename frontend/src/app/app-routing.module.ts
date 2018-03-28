import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { PersonListComponent } from './person-list/person-list.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path: 'personlist', component: PersonListComponent},
  {path: 'home', component: HomeComponent}
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
