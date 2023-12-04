import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {TaskgroupComponent} from "./components/taskgroup/taskgroup.component";
import {TaskgrouplistComponent} from "./components/taskgroup/taskgrouplist/taskgrouplist.component";


const routes: Routes = [
  { path: '', redirectTo: '/api/auth/login', pathMatch: 'full' },
  { path: 'api/auth/register', component: RegisterComponent },
  { path: 'api/auth/login', component: LoginComponent },
  { path: 'api/task-groups', component: TaskgroupComponent },
  { path: 'api/task-groups/user/:email', component: TaskgrouplistComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
