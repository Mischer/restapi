import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import {WebSocketTestComponent} from "../web-socket-test/web-socket-test.component";
import {HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, WebSocketTestComponent],
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
