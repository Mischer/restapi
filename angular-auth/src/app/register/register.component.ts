import { Component } from '@angular/core';
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {AuthService} from "../auth.service";
import {MatFormField, MatFormFieldModule, MatLabel} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, MatFormField, MatLabel, MatInputModule, MatFormFieldModule, MatButtonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerDto = { username: '', password: '' };

  constructor(private authService: AuthService) { }

  register() {
    console.log('User this.user===', this.registerDto);
    this.authService.register(this.registerDto).subscribe(
      response => {
        console.log('User registered successfully!', response);
      },
      error => {
        console.error('Error during registration:', error);
      }
    );
  }
}
