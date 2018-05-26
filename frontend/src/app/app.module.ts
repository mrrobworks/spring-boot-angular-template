import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { PersonListComponent } from './person-list/person-list.component';
import { AppRoutingModule } from './app-routing.module';

import { PersonListService } from './services/person-list.service';
import { LoginComponent } from './login/login.component';
import { LoginService } from './services/login.service';
import { EnableForPermissionDirective } from './directives/enable-for-permission.directive';
import { ShowForPermissionDirective } from './directives/show-for-permission.directive';
import { AuthorizationService } from './services/authorization.service';

@NgModule({
  declarations: [
    AppComponent,
    PersonListComponent,
    LoginComponent,
    EnableForPermissionDirective,
    ShowForPermissionDirective
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [PersonListService, LoginService, AuthorizationService],
  bootstrap: [AppComponent]
})
export class AppModule {}
