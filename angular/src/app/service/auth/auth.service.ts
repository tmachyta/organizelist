import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }


  // Збереження токену в localStorage
  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  // Отримання токену з localStorage
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  // Видалення токену з localStorage
  removeToken() {
    localStorage.removeItem('token');
  }
}
