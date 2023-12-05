import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {TaskgroupComponent} from "./components/taskgroup/taskgroup.component";
import {TaskgrouplistComponent} from "./components/taskgroup/taskgrouplist/taskgrouplist.component";
import {TasklistComponent} from "./components/task/tasklist/tasklist.component";
import {UserComponent} from "./components/user/user.component";


const routes: Routes = [
  { path: '', redirectTo: '/api/auth/login', pathMatch: 'full' },
  { path: 'api/auth/register', component: RegisterComponent },
  { path: 'api/auth/login', component: LoginComponent },
  { path: 'api/task-groups', component: TaskgroupComponent },
  { path: 'api/task-groups/user/email/:email', component: TaskgrouplistComponent },
  { path: 'api/task-groups/user/id/:userId', component: TaskgrouplistComponent },
  { path: 'api/tasks/user/email/:email', component: TasklistComponent },
  { path: 'api/user/me/:id', component: UserComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
