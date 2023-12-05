import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() {
  }


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

  // Збереження email користувача в localStorage
  saveUserEmail(email: string) {
    localStorage.setItem('email', email);
  }

  // Отримання email користувача з localStorage
  getUserEmail(): string | null {
    return localStorage.getItem('email');
  }

  // Видалення email користувача з localStorage
  removeUserEmail() {
    localStorage.removeItem('email');
  }


  saveUserId(userId: number | null) {
    if (userId !== null && userId !== undefined) {
      localStorage.setItem('userId', userId.toString());
    }
  }

    // Отримання ID користувача з localStorage
  getLoggedInUserId(): number | null {
    const userId = localStorage.getItem('userId');
    return userId ? +userId : null;
  }
}
