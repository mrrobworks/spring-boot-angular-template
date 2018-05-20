import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {PersonListComponent} from './person-list/person-list.component';
import {AppRoutingModule} from './app-routing.module';

import {PersonListService} from './services/person-list.service';
import {AppService} from './services/app.service';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    PersonListComponent,
    LoginComponent
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [PersonListService, AppService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
