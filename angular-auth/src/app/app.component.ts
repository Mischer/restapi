import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  template: `<router-outlet></router-outlet>`,
})
export class AppComponent {
  title = 'angular-auth';
}
