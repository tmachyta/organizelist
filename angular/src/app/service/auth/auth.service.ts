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

  // Збереження ідентифікатора користувача в localStorage
  saveUserId(userId: number) {
    localStorage.setItem('userId', userId.toString());
  }

  // Отримання ідентифікатора користувача з localStorage
  getUserId(): number | null {
    const userIdString = localStorage.getItem('userId');
    return userIdString ? parseInt(userIdString, 10) : null;
  }

  // Видалення ідентифікатора користувача з localStorage
  removeUserId() {
    localStorage.removeItem('userId');
  }
}
