import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class GameService {
  private apiUrl = 'http://localhost:8080/api/games';

  constructor(private http: HttpClient) {}

  createGame(gameData: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, gameData);
  }
  makeMove(gameId: number, moveData: any): Observable<any> {
    const url = `${this.apiUrl}/${gameId}/moves`;
    return this.http.post<any>(url, moveData);
  }
}
