import { Routes } from '@angular/router';
import { SignupComponent } from './components/signup.component';
import { SigninComponent } from './components/signin.component';

export const appRoutes: Routes = [
  { path: 'signup', component: SignupComponent },
  { path: 'signin', component: SigninComponent },
  { path: '', redirectTo: '/signin', pathMatch: 'full' }
];
