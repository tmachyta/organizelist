import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {TaskDto} from "../../model/task/task-dto";
import {CreateTaskRequest} from "../../model/task/create-task-request";
import {TaskGroupDto} from "../../model/taskgroup/task-group-dto";

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private baseUrl = 'http://localhost:8080/api/tasks';

  constructor(private http: HttpClient) { }

  private getHttpOptions() {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return {headers};
  }

  getAllTasks(): Observable<TaskDto[]> {
    return this.http.get<TaskDto[]>(this.baseUrl, this.getHttpOptions());
  }

  getTaskById(id: number): Observable<TaskDto> {
    return this.http.get<TaskDto>(`${this.baseUrl}/${id}`, this.getHttpOptions());
  }

  deleteTaskById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`, this.getHttpOptions());
  }

  createTask(request: CreateTaskRequest): Observable<any> {
    return this.http.post(this.baseUrl, request, this.getHttpOptions());
  }

  updateTask(id: number, request: CreateTaskRequest): Observable<TaskDto> {
    return this.http.put<TaskDto>(`${this.baseUrl}/${id}`, request, this.getHttpOptions());
  }

  getAllTaskByCurrentUser(userId: number): Observable<TaskDto[]> {
    return this.http.get<TaskDto[]>(`${this.baseUrl}/user/id/${userId}`, this.getHttpOptions());
  }

  getAllTaskByCurrentUserEmail(email: string): Observable<TaskDto[]> {
    return this.http.get<TaskDto[]>(`${this.baseUrl}/user/email/${email}`, this.getHttpOptions());
  }

  getTaskByIdAndUserId(id: number, userId: number): Observable<TaskDto> {
    return this.http.get<TaskDto>(`${this.baseUrl}/user/${id}?userId=${userId}`, this.getHttpOptions());
  }

  updateTaskByCurrentUser(id: number, request: CreateTaskRequest, userId: number): Observable<TaskDto> {
    return this.http.put<TaskDto>(`${this.baseUrl}/user/${id}?userId=${userId}`, request, this.getHttpOptions());
  }

  deleteTaskByIdByCurrentUser(id: number, userId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/user/${id}?userId=${userId}`, this.getHttpOptions());
  }
}
