import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonListComponent } from './person-list/person-list.component';
import { RoleListComponent } from './role-list/role-list.component';

const routes: Routes = [
  { path: 'personlist', component: PersonListComponent },
  { path: 'rolelist', component: RoleListComponent }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
