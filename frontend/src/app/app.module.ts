import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { PersonListComponent } from './person-list/person-list.component';
import { AppRoutingModule } from './/app-routing.module';

import { PersonListService } from './person-list.service';

@NgModule({
  declarations: [AppComponent, PersonListComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [PersonListService],
  bootstrap: [AppComponent]
})
export class AppModule {}
