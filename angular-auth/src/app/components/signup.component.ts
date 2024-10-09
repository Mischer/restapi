import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import {HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './signup.component.html'
})
export class SignupComponent {
  user = { username: '', password: '' };

  constructor(private authService: AuthService) { }

  signUp() {
    console.log('User this.user===', this.user);
    this.authService.signUp(this.user).subscribe(
      response => {
        console.log('User registered successfully!', response);
      },
      error => {
        console.error('Error during registration:', error);
      }
    );
  }
}
