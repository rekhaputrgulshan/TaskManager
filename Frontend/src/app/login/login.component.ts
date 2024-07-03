import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user = {
    username: '',
    password: ''
  };

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.http.post('http://localhost:8080/api/v1/user/login', this.user)
      .subscribe(response => {
        console.log('User logged in successfully', response);
        // Navigate to a different page or handle successful login
        this.router.navigate(['/tasks']);
      }, error => {
        console.error('Error during login', error);
        // Handle login error
      });
  }
}
