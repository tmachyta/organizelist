import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserRegistrationRequest} from "../../model/user/user-registration-request";
import {Observable} from "rxjs";
import {UserLoginRequest} from "../../model/user/user-login-request";
import {UserLoginResponse} from "../../model/user/user-login-response";
import {UserResponseDto} from "../../model/user/user-response-dto";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrlRegister = 'http://localhost:8080/api/auth/register';
  private baseUrl = 'http://localhost:8080/api/users/me';
  private baseUrlLogin = 'http://localhost:8080/api/auth/login';

  constructor(private http: HttpClient) { }

  private getHttpOptions() {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return { headers };
  }

  registerUser(request: UserRegistrationRequest): Observable<any> {
    return this.http.post(`${this.baseUrlRegister}`, request);
  }

  loginUser(request: UserLoginRequest): Observable<UserLoginResponse> {
    return this.http.post<UserLoginResponse>(this.baseUrlLogin, request);
  }

  getUserById(id: number): Observable<UserResponseDto> {
    return this.http.get<UserResponseDto>(`${this.baseUrl}/${id}`, this.getHttpOptions());
  }
}
