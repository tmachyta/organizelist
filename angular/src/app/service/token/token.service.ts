import { Injectable } from '@angular/core';
import jwt_decode, { JwtPayload } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  getDecodedToken(token: string): JwtPayload | null {
    try {
      const decodedToken: JwtPayload = jwt_decode(token);
      return decodedToken;
    } catch (error) {
      console.error('Error decoding token:', error);
      return null;
    }
  }
}
}
