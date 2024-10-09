import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [CommonModule, FormsModule], // Добавляем HttpClientModule
  templateUrl: './signin.component.html'
})
export class SigninComponent {
  loginData = { username: '', password: '' };

  constructor(private authService: AuthService) { }

  signIn() {
    this.authService.signIn(this.loginData).subscribe(
      response => {
        console.log('Login successful!', response);
      },
      error => {
        console.error('Error during login:', error);
      }
    );
  }
}
