import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { PersonListComponent } from './person-list/person-list.component';
import { AppRoutingModule } from './app-routing.module';

import { PersonListService } from './services/person-list.service';
import { LoginComponent } from './login/login.component';
import { LoginService } from './services/login.service';
import { EnableIfPermissionDirective } from './directives/enable-if-permission.directive';
import { ShowIfPermissionDirective } from './directives/show-if-permission.directive';
import { AuthorizationService } from './services/authorization.service';
import { RoleListComponent } from './role-list/role-list.component';
import { RoleService } from './services/role.service';
import { RoleDetailComponent } from './role-detail/role-detail.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavTopBarComponent } from './nav-top-bar/nav-top-bar.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { RoleDeleteComponent } from './role-delete/role-delete.component';
import { AutofocusDirective } from './directives/autofocus.directive';

@NgModule({
  declarations: [
    AppComponent,
    PersonListComponent,
    LoginComponent,
    EnableIfPermissionDirective,
    ShowIfPermissionDirective,
    RoleListComponent,
    RoleDetailComponent,
    NavTopBarComponent,
    UserProfileComponent,
    RoleDeleteComponent,
    AutofocusDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule
  ],
  providers: [
    PersonListService,
    LoginService,
    AuthorizationService,
    RoleService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
