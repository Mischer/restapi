import { Routes } from '@angular/router';
import { SignupComponent } from './components/signup.component';
import { SigninComponent } from './components/signin.component';
import {WebSocketTestComponent} from "./web-socket-test/web-socket-test.component";
import {ChessboardComponent} from "./chessboard/chessboard.component";
import {RegisterComponent} from "./register/register.component";
import {LoginComponent} from "./login/login.component";

export const appRoutes: Routes = [
  { path: 'signup', component: SignupComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'signin', component: SigninComponent },
  { path: 'login', component: LoginComponent },
  { path: 'ws', component: WebSocketTestComponent },
  { path: 'play', component: ChessboardComponent },
  { path: '', redirectTo: '/register', pathMatch: 'full' }
];
