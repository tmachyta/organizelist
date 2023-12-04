import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import {AppComponent} from "./app.component";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {TaskgroupComponent} from "./components/taskgroup/taskgroup.component";
import {TaskgrouplistComponent} from "./components/taskgroup/taskgrouplist/taskgrouplist.component";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    TaskgroupComponent,
    TaskgrouplistComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot([])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
