import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TaskGroupDto} from "../../model/taskgroup/task-group-dto";
import {CreateTaskGroupRequest} from "../../model/taskgroup/create-task-group-request";

@Injectable({
  providedIn: 'root'
})
export class TaskGroupService {
  private baseUrl = 'http://localhost:8080/api/task-groups';

  constructor(private http: HttpClient) { }

  private getHttpOptions() {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return {headers};
  }

  getAllTaskGroups(): Observable<TaskGroupDto[]> {
    return this.http.get<TaskGroupDto[]>(this.baseUrl, this.getHttpOptions());
  }

  deleteTaskGroupById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`, this.getHttpOptions());
  }

  createTaskGroup(request: CreateTaskGroupRequest): Observable<any> {
    return this.http.post(this.baseUrl, request, this.getHttpOptions());
  }


  getAllTaskGroupsByCurrentUser(userId: number): Observable<TaskGroupDto[]> {
    return this.http.get<TaskGroupDto[]>(`${this.baseUrl}/user/${userId}`, this.getHttpOptions());
  }
  getAllTaskGroupsByCurrentUserE(email: string): Observable<TaskGroupDto[]> {
    return this.http.get<TaskGroupDto[]>(`${this.baseUrl}/user/${email}`, this.getHttpOptions());
  }

  deleteTaskGroupByIdByCurrentUser(id: number, userId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/user/${id}/${userId}`, this.getHttpOptions());
  }
}
