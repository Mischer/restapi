import {Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export type RegisterDto = {
  username: string;
  password: string;
};

export type LoginDto = {
  username: string;
  password: string;
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  signUp(user: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/signup`, user);
  }

  signIn(loginData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/signin`, loginData);
  }

  register(registerDto: RegisterDto): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, registerDto);
  }

  login(loginDto: LoginDto): Observable<any> {
    return this.http.post(`${this.baseUrl}/signin`, loginDto);
  }
}
