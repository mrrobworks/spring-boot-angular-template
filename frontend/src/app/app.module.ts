import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { PersonListComponent } from './person-list/person-list.component';
import { AppRoutingModule } from './app-routing.module';

import { PersonListService } from './person-list/person-list.service';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AppService } from './app.service';

@NgModule({
  declarations: [
    AppComponent,
    PersonListComponent,
    HomeComponent,
    LoginComponent
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [PersonListService, AppService],
  bootstrap: [AppComponent]
})
export class AppModule {}
