import { Routes } from '@angular/router';
import { SignupComponent } from './components/signup.component';
import { SigninComponent } from './components/signin.component';
import {WebSocketTestComponent} from "./web-socket-test/web-socket-test.component";

export const appRoutes: Routes = [
  { path: 'signup', component: SignupComponent },
  { path: 'signin', component: SigninComponent },
  { path: 'ws', component: WebSocketTestComponent },
  { path: '', redirectTo: '/signin', pathMatch: 'full' }
];
